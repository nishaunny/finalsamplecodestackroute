import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApiDataProcessComponent } from './apiData/api-data-process/api-data-process.component';
import { CanActivateGuard } from './can-activate.guard';
import { FavoritesDialogComponent } from './favorites-dialog/favorites-dialog.component';
import { FavouritesListComponent } from './favourites-list/favourites-list.component';
import { LeaguesComponent } from './leagues/leagues.component';
import { LiveMatchesComponent } from './live-matches/live-matches.component';
import { LoginComponent } from './login/login.component';
import { NewsPageComponent } from './news-page/news-page.component';
import { UpcomingMatchesComponent } from './upcoming-matches/upcoming-matches.component';

const routes: Routes = [
  {
    path: 'news',
    component: NewsPageComponent,
    canActivate: [CanActivateGuard]
  },
  {
    path: 'upcoming',
    component: UpcomingMatchesComponent,
    canActivate: [CanActivateGuard]
  },
  {
    path: 'live',
    component: LiveMatchesComponent
  },
  {
    path: 'favouriteList',
    component: FavouritesListComponent,
    canActivate: [CanActivateGuard]
  },
  {
    path: 'leagues',
    component: LeaguesComponent,
    canActivate: [CanActivateGuard]
  },
  {
    path: 'addfavourites',
    component: FavoritesDialogComponent,
    canActivate: [CanActivateGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    redirectTo: 'live', pathMatch: 'full'
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
