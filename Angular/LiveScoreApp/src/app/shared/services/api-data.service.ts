import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ApiDataService {

  constructor(private httpClient: HttpClient) { }

  getLiveMatches(category: string): Observable<any> {
    return this.httpClient.get<any>('https://livescore6.p.rapidapi.com/matches/v2/list-live?Category=' + category  ,
      { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
    );
  }

  getMatchesByDate(category:string,date: string): Observable<any> {
    return this.httpClient.get<any>('https://livescore6.p.rapidapi.com/matches/v2/list-by-date?Category=' + category+'&Date=' + date ,
      { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
    );
  }
 
  getMatchesByLeague(category:string,league: string): Observable<any>{
    return this.httpClient.get<any>(' https://livescore6.p.rapidapi.com/matches/v2/list-by-league?Category=' + category+'&Ccd=' + league ,
    { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
  );
  }

  getNews(): Observable<any>{
    return this.httpClient.get<any>('https://livescore6.p.rapidapi.com/news/v2/list',
    { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
  );
  }

  getNewsByCategory(category: string): Observable<any> {
    return this.httpClient.get<any>('https://livescore6.p.rapidapi.com/news/v2/list-by-sport?category=' + category  ,
      { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
    );
  }
}
