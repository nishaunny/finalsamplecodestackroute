import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { League } from '../league';
import { LeagueService } from '../shared/services/league.service';

@Component({
  selector: 'app-leagues',
  templateUrl: './leagues.component.html',
  styleUrls: ['./leagues.component.css']
})
export class LeaguesComponent implements OnInit {

  leagueobj: League;
  leaguesinfoarray: League[];
  leaguesarray: any[];
  columnsToDisplay: any[];
  categories: any[];
  leagues: any[];
  groups: any[];
  leagueForm!: FormGroup;
  selectData: any[] = [];
  categorySelected: string;
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['teamName', 'points', 'rank', 'actions'];
  isDisabled: boolean;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(private leagueService: LeagueService, private changeDetectorRef: ChangeDetectorRef, private snackBar: MatSnackBar) {
    this.columnsToDisplay = [];
    this.leaguesinfoarray = [];
    this.leaguesarray = [];
    this.categories = ['football', 'cricket', 'hockey'];
    this.leagues = [];
    this.groups = [];
    this.categorySelected = "";
    this.isDisabled = false;
    this.leagueobj = { leagueName: "", category: "", points: 0, rank: 0, teamName: "", userName: "", year: "" };
  }

  ngOnInit(): void {
    this.leagueForm = new FormGroup({
      category: new FormControl('', [Validators.required]),
      league: new FormControl('', [Validators.required]),
      group: new FormControl('', [Validators.required])
    });
  }
  onSelectCategory(cat: string) {
    this.leagueService.getLeagues(cat).subscribe(res => {
      this.selectData = res.Ccg;
      this.getLeagues();
      this.getGroups();
    }
    );
  }
  getLeagueData() {
    this.isDisabled = false;
    console.log(this.leagueForm.value)
    this.leagueService.getLeaguesInfo(this.leagueForm.value.category, this.leagueForm.value.league,
      this.leagueForm.value.group).subscribe(res => {

        this.columnsToDisplay = res.Stages;

        this.getFinalData();
      });
  }
  getFinalData() {
    console.log(this.columnsToDisplay)
    this.columnsToDisplay.forEach((element: any) => {
      element.LeagueTable.L.forEach((l: any) => {
        l.Tables.forEach((tab: any) => {

          tab.team.forEach((team: any) => {
            this.leagueobj.teamName = team.Tnm;
            this.leagueobj.points = team.pts;
            this.leagueobj.rank = team.rnk;
            this.leagueobj.leagueName = element.Cnm;
            this.leaguesarray.push(this.leagueobj);
            this.leagueobj = new League();
          });
        });
      });
    });
    console.log(this.leaguesarray)
    if (this.leaguesarray.length > 0) {

      this.changeDetectorRef.detectChanges();
      this.dataSource = new MatTableDataSource(this.leaguesarray);
      this.dataSource.paginator = this.paginator!;
      this.categorySelected = "";
    } else {
      this.categorySelected = "Oops!! there are no Leagues matches your search";
    }
  }

  getLeagues() {
    // Cnm
    this.selectData.forEach((el) => {
      if (!this.leagues.includes(el.Ccd)) {
        this.leagues.push(el.Ccd);
      }
    });
  }
  getGroups() {
    this.selectData.forEach((el: any) => {
      el.Stages.forEach((stg: any) => {
        if (!this.groups.includes(el.Scd)) {
          this.groups.push(stg.Scd);
        }
      });
    });
  }

  addLeague() {
    const userName=sessionStorage.getItem('userName')!;
    this.leagueService.saveLeague(userName, this.leaguesarray).subscribe(leagueRes => {
      console.log(leagueRes);

    },
      (err) => console.log(err));
    this.isDisabled = true;
    
    this.snackBar.open('Leagues saved successfully', '', { duration: 3000, verticalPosition: 'top', panelClass: ['green-snackbar'] })
  }
}
