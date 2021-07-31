import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Observable } from 'rxjs';
import { ApiDataService } from '../shared/services/api-data.service';

@Component({
  selector: 'app-news-page',
  templateUrl: './news-page.component.html',
  styleUrls: ['./news-page.component.css']
})
export class NewsPageComponent implements OnInit {
  sportsNews: any[];
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  obs: Observable<any> | undefined;
  dataSource: MatTableDataSource<any>;

  constructor(private apiService: ApiDataService, private changeDetectorRef: ChangeDetectorRef) {
    this.sportsNews = [];
    this.dataSource = new MatTableDataSource<any>(this.sportsNews);
  }

  ngOnInit(): void {

   
  }
  getSportNews(category: string) {
    this.apiService.getNewsByCategory(category).subscribe(news => {
      console.log(news.data)
      this.sportsNews = news.data;
      this.changeDetectorRef.detectChanges();
      this.dataSource = new MatTableDataSource<any>(this.sportsNews);
      this.dataSource.paginator = this.paginator!;
      this.obs = this.dataSource.connect();
    })
  }
}
