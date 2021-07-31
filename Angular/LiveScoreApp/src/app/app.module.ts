import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApiDataProcessComponent } from './apiData/api-data-process/api-data-process.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LiveMatchesComponent } from './live-matches/live-matches.component';
import { UpcomingMatchesComponent } from './upcoming-matches/upcoming-matches.component';
import { FavoritesDialogComponent } from './favorites-dialog/favorites-dialog.component';
import { FavouritesListComponent } from './favourites-list/favourites-list.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { LeaguesComponent } from './leagues/leagues.component';
import { LoginComponent } from './login/login.component';
import { NewsPageComponent } from './news-page/news-page.component';
import { TokeInterceptorService } from './token-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    ApiDataProcessComponent,
    HeaderComponent,
    FooterComponent,
    LiveMatchesComponent,
    UpcomingMatchesComponent,
    FavoritesDialogComponent,
    FavouritesListComponent,
    ConfirmDialogComponent,
    LeaguesComponent,
    LoginComponent,
    NewsPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    SharedModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
