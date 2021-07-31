import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Favourites } from '../shared/models/favourites';
import { FavouritesService } from '../shared/services/favourites.service';
import { RouteService } from '../shared/services/route.service';

@Component({
  selector: 'app-favorites-dialog',
  templateUrl: './favorites-dialog.component.html',
  styleUrls: ['./favorites-dialog.component.css']
})
export class FavoritesDialogComponent implements OnInit {

  teams: any[];
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['teamName', 'actions'];
  favourite!: Favourites;
  isClicked: boolean;
  existFavourites: Favourites[];
  tempFavs: Favourites[];
  constructor(private matDialog: MatDialogRef<FavoritesDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private routeService: RouteService, private favouriteService: FavouritesService, private snackBar: MatSnackBar) {
    this.teams = [];
    this.existFavourites = [];
    this.tempFavs = [];
    this.isClicked = false;
  }

  ngOnInit(): void {
    this.getTeamNamesList();
    console.log(this.teams)
    this.dataSource = new MatTableDataSource(this.teams);
    this.getFavList();
  }

  getTeamNamesList() {
    // this.teams = this.data;
    this.data.teamsData.forEach((element: any) => {
      element.T1.forEach((team: any) => {
        this.teams.push({ 'teamName': team.Nm });
      });
      element.T2.forEach((team: any) => {
        this.teams.push({ 'teamName': team.Nm });
      });
    });
  }

  addFavourite(team: string) {
    const found = this.existFavourites.some(el => el.teamName.toString() === team);
    const tempFound = this.tempFavs.some(el => el.teamName.toString() === team);
    console.log(found)
    if (found == true || tempFound == true) {
      console.log("works")
      this.snackBar.open(team + ' Already added to favourites', '', { duration: 3000, verticalPosition: 'top', panelClass: ['red-snackbar'] });
    }
    else {
      const userName=sessionStorage.getItem('userName')!;
      this.favourite = {
        userName: userName,
        teamName: team
      }

      this.favouriteService.addFavourite(this.favourite).subscribe(res => {
        // console.log(res);
    
      },
        err => {
          console.log(err.message);
        });
        this.snackBar.open(team + 'Team added to favourites', '', { duration: 2000, verticalPosition: 'top', panelClass: ['green-snackbar'] });
        this.tempFavs.push(this.favourite);
    }
  }
  getFavList() {
    const userName=sessionStorage.getItem('userName')!;
    this.favouriteService.getFavorites(userName).subscribe(favs => this.existFavourites = favs);
  }
  close() {
    this.matDialog.close();
    this.routeService.routeBack();
  }

}
