import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { League } from 'src/app/league';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

  constructor(private httpcli: HttpClient, private authService: AuthenticationService) { }

  getLeaguesInfo(cat: string, ccd: string, scd: string): Observable<any> {
    return this.httpcli.get<any>('https://livescore6.p.rapidapi.com/matches/v2/list-by-league?Category=' + cat + '&Ccd=' + ccd + '&Scd=' + scd,
      { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
    );
  }

  getLeagues(category: string): Observable<any> {
    return this.httpcli.get<any>('https://livescore6.p.rapidapi.com/leagues/v2/list?Category=' + category,
      { headers: new HttpHeaders().set('x-rapidapi-key', `e5083daae3msh626229faacb79e7p1bae53jsnfb33db015eca`) }
    );
  }

  saveLeague(userName: string, league: League[]): Observable<League[]> {
    const tok = this.authService.getBearerToken();
    console.log(tok)
    return this.httpcli.post<League[]>('http://localhost:9095/api/League/addLeagueinfo/' + userName, league,
      { headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`) });
  }
}
