import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Favourites } from '../models/favourites';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {
  favouriteList: Array<Favourites>;
  favsSubject: BehaviorSubject<Array<Favourites>>;
  token: any;
  constructor(private httpClient: HttpClient, private authService: AuthenticationService) {
    this.favouriteList = [];
    this.favsSubject = new BehaviorSubject<Array<Favourites>>([]);
  }

  addFavourite(favData: Favourites):Observable<Favourites>{
    // this.token = sessionStorage.getItem('myToken');
    const tok = this.authService.getBearerToken();
    return this.httpClient.post<Favourites>('http://localhost:8089/api/favourites/addFavourite', favData,
    { headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`) });
  }

  fetchFavourites(userName: string) {
    const tok = this.authService.getBearerToken();
    return this.httpClient.get('http://localhost:8089/api/favourites/getFavourites/'+userName,
    { headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`) }).subscribe(
      (res: any) => {
        this.favouriteList = res;
        this.favsSubject.next(this.favouriteList);
      },
      (err) =>
        this.favsSubject.error(err)
    );
  }

  getFavorites(userName: string): BehaviorSubject<Array<Favourites>> {
    this.fetchFavourites(userName);
    return this.favsSubject;
  }

  deleteFavoriteTeam(userName: string, teamName: string):Observable<any> {
    const tok = this.authService.getBearerToken();
    return this.httpClient.delete<any>('http://localhost:8089/api/favourites/deleteFavourite/' + userName + '/' + teamName,
    { headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`) });
  }
}
