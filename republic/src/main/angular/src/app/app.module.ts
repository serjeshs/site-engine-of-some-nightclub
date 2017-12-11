import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import { EventsComponent } from './events/events.component';
import { EventsService } from './events.service';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [
    AppComponent,
    EventsComponent,
    MenuComponent,
    FooterComponent,
  ],
  providers: [EventsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
