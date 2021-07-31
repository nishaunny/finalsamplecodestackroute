import { ChangeDetectorRef, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs/internal/Observable';
import { FavoritesDialogComponent } from '../favorites-dialog/favorites-dialog.component';
import { ApiDataService } from '../shared/services/api-data.service';
import { RouteService } from '../shared/services/route.service';

@Component({
  selector: 'app-live-matches',
  templateUrl: './live-matches.component.html',
  styleUrls: ['./live-matches.component.css']
})
export class LiveMatchesComponent implements OnInit, OnDestroy {

  liveMatchesArray: any[];
  defaultCategory: string;

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  obs: Observable<any> | undefined;
  dataSource: MatTableDataSource<any>;

  constructor(private apiService: ApiDataService, private changeDetectorRef: ChangeDetectorRef,
    private routeService: RouteService, private dialog: MatDialog) {
    this.liveMatchesArray = [];
    this.defaultCategory = "football";
    this.dataSource = new MatTableDataSource<any>(this.liveMatchesArray);
  }

  ngOnInit(): void {
    // this.liveMatches(this.defaultCategory);
    this.apiService.getMatchesByDate("cricket","20210726").subscribe(res => {
      this.liveMatchesArray = res.Stages;
      this.changeDetectorRef.detectChanges();
      this.dataSource = new MatTableDataSource<any>(this.liveMatchesArray);
      this.dataSource.paginator = this.paginator!;
      this.obs = this.dataSource.connect();
    });

    this.changeDetectorRef.detectChanges();
    this.dataSource = new MatTableDataSource<any>(this.liveMatchesArray);
    this.dataSource.paginator = this.paginator!;
    this.obs = this.dataSource.connect();
  }

  liveMatches(cat: string) {
    this.apiService.getLiveMatches(cat).subscribe(res => {
      this.liveMatchesArray = res.Stages;
      this.changeDetectorRef.detectChanges();
      this.dataSource = new MatTableDataSource<any>(this.liveMatchesArray);
      this.dataSource.paginator = this.paginator!;
      this.obs = this.dataSource.connect();

      this.defaultCategory = cat;
      console.log("Live Matches : ");
      console.log(this.liveMatchesArray);
    }
    );
  }

  routeToSchedules() {
    this.routeService.routeToSchedules();
  }

  addFavourites(events: any) {
    if (sessionStorage.getItem('userName') != null) {
      this.dialog.open(FavoritesDialogComponent, {
        data: { teamsData: events },
        width: "60%"
      });
    }
    else{
      this.routeService.routeToLogin();
    }
  }

  ngOnDestroy() {
    if (this.dataSource) {
      this.dataSource.disconnect();
    }
  }
}
