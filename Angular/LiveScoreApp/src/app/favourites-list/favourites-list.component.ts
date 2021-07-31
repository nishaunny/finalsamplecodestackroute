import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { Favourites } from '../shared/models/favourites';
import { FavouritesService } from '../shared/services/favourites.service';

@Component({
  selector: 'app-favourites-list',
  templateUrl: './favourites-list.component.html',
  styleUrls: ['./favourites-list.component.css']
})
export class FavouritesListComponent implements OnInit {

  favourites: Favourites[];
  dataSource!: MatTableDataSource<any>;
  confirmDelete: boolean;
  displayedColumns: string[] = ['teamName', 'actions'];
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(private favouriteService: FavouritesService, private snackBar: MatSnackBar,
    public dialog: MatDialog, private changeDetectorRef: ChangeDetectorRef) {
    this.favourites = [];
    this.confirmDelete = false;
  }

  ngOnInit(): void {
    this.getAllFavourites();
  }

  getAllFavourites() {
    const userName = sessionStorage.getItem('userName')!;
    this.favouriteService.getFavorites(userName).subscribe(
      result => {
        this.favourites = result;

        this.dataSource = new MatTableDataSource(this.favourites);
        this.changeDetectorRef.detectChanges();
        this.dataSource.paginator = this.paginator!;
      }
    );
  }

  deleteFavourite(fav: any, index: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      maxWidth: "400px",
      data: "Are you sure to delete this team?"
    });

    dialogRef.afterClosed().subscribe(dialogResult => {

      if (dialogResult) {
        this.favouriteService.deleteFavoriteTeam(fav.userName, fav.teamName).subscribe(
          res => {
            console.log("deleted");
            this.getAllFavourites();
          },
          err => {
            console.log(err.message);
            this.getAllFavourites();
          });
  
        this.snackBar.open(fav.teamName + 'Team deleted from favourites', '', { duration: 2000, verticalPosition: 'top' , panelClass: ['green-snackbar'] });
      }
    });
  }

}
