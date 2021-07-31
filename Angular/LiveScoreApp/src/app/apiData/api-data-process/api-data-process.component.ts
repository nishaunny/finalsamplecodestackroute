import { Component, OnInit } from '@angular/core';
import { ApiDataService } from 'src/app/shared/services/api-data.service';

@Component({
  selector: 'app-api-data-process',
  templateUrl: './api-data-process.component.html',
  styleUrls: ['./api-data-process.component.css']
})
export class ApiDataProcessComponent implements OnInit {

  liveMatchesArray:any[];
  constructor(private apiService: ApiDataService) { 
    this.liveMatchesArray=[];
  }

  ngOnInit(): void {
    this.liveMatches();
    this.matchesByDate();
    this.matchesByLeague();
    this.news();
    this.newsNyCategory();
  }

  liveMatches() {
    this.apiService.getLiveMatches("football").subscribe(res => {
      this.liveMatchesArray=res.Stages; 
      console.log("Live Matches : ");
      console.log(this.liveMatchesArray); }
    );
  }
  matchesByDate() {
    this.apiService.getMatchesByDate("cricket", "20200714").subscribe(res =>{
      console.log("Live Matches by date: : ");
     console.log(res)}
    );
  }


  matchesByLeague() {
    this.apiService.getMatchesByLeague("cricket", "champions-league").subscribe(res => {
      console.log("Live Matches by league ");
      console.log(res)}
    );
  }

  news(){
    this.apiService.getNews().subscribe(res => {
      console.log("news ");
      console.log(res)}
    );
  }

  newsNyCategory(){
    this.apiService.getNewsByCategory("2021060710132755701").subscribe(res => {
      console.log("news by category ");
      console.log(res)}
    );
  }
}
