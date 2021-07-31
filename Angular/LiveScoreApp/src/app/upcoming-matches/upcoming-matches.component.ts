import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { Favourites } from '../shared/models/favourites';
import { ApiDataService } from '../shared/services/api-data.service';
import { FavouritesService } from '../shared/services/favourites.service';

@Component({
  selector: 'app-upcoming-matches',
  templateUrl: './upcoming-matches.component.html',
  styleUrls: ['./upcoming-matches.component.css'],
  providers: [DatePipe, MatDatepickerModule]
})
export class UpcomingMatchesComponent implements OnInit {


  upcomingMatches: any[];
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  obs: Observable<any> | undefined;
  obs1: Observable<any> | undefined;
  category: string;
  dateToday!: any;
  matchDate: any;
  favourites: Favourites[];
  suggesteMatches: any[];
  categories: string[] = ['football', 'cricket', 'hockey'];
  dataSource: MatTableDataSource<any>;
  dataSourceSg: MatTableDataSource<any>;
  searchForm!: FormGroup;

  constructor(private apiService: ApiDataService, private changeDetectorRef: ChangeDetectorRef,
    private datePipe: DatePipe, private favouriteService: FavouritesService) {
    this.upcomingMatches = [];
    this.favourites = [];
    this.suggesteMatches = [];
    this.category = "football";
    this.dataSource = new MatTableDataSource<any>(this.upcomingMatches);
    this.dataSourceSg = new MatTableDataSource<any>(this.upcomingMatches);
  }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      date: new FormControl('', [Validators.required]),
      sportCategory: new FormControl('', [Validators.required])
    });

    this.getAllFavourites();
    this.dateToday = new Date();
    this.matchDate = this.datePipe.transform(this.dateToday, 'yyyyMMdd');
    this.searchForm.value.sportCategory= this.category;
    this.matchesByDate();
  }


  matchesByDate() {
    this.category = this.searchForm.value.sportCategory;
    this.matchDate = this.datePipe.transform(this.searchForm.value.date, 'yyyyMMdd');
    this.apiService.getMatchesByDate(this.category, this.matchDate).subscribe(res => {
      this.upcomingMatches = res.Stages;
      this.changeDetectorRef.detectChanges();
      this.dataSource = new MatTableDataSource<any>(this.upcomingMatches);
      this.dataSource.paginator = this.paginator!;
      this.obs = this.dataSource.connect();
      this.suggetions();
    }
    );
  }

  suggetions() {
    this.favourites.forEach((fav: Favourites) => {

      this.upcomingMatches.forEach((element: any) => {
        console.log(element)
        element.Events.forEach((event: any) => {
          event.T1.forEach((team1: any) => {
            if (team1.Nm.toLowerCase() == fav.teamName.toLowerCase()) {
              this.suggesteMatches.push(element);
            }
            else {
              event.T2.forEach((team2: any) => {
                if (team2.Nm.toLowerCase() == fav.teamName.toLowerCase()) {
                  this.suggesteMatches.push(element);
                }
              });
            }
          });
        });
      });
    })
      console.log(this.suggesteMatches)
      this.changeDetectorRef.detectChanges();
      this.dataSourceSg = new MatTableDataSource<any>(this.suggesteMatches);
      this.dataSourceSg.paginator = this.paginator!;
      this.obs1 = this.dataSourceSg.connect();
  }

  getAllFavourites() {
    const userName=sessionStorage.getItem('userName')!;
      this.favouriteService.getFavorites(userName).subscribe(
        result => {
          this.favourites = result;
        }
      );
    }
}
