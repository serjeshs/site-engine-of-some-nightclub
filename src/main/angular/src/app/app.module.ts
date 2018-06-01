import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {EventsComponent} from './events/events.component';
import {EventsService} from './events.service';
import {MenuComponent} from './menu/menu.component';
import {MenuService} from './menu.service';
import {FooterComponent} from './footer/footer.component';
import {ClubComponent} from './club/club.component';
import {MediaComponent} from './media/media.component';
import {NewsComponent} from './news/news.component';
import {NewsPageComponent} from './news-page/news-page.component';
import {EventPageComponent} from './event-page/event-page.component';
import {EventReportComponent} from './event-report/event-report.component';
import {NewsService} from "./news.service";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AdminComponent} from './admin/summary/admin.component';
import {AdminOrderListComponent} from './admin/admin-order-list/admin-order-list.component';
import {NgModule} from "@angular/core";
import {EventsListComponent} from './admin/events/events-list/events-list.component';
import {EventViewComponent} from './admin/events/event-view/event-view.component';
import {
  MatAutocompleteModule, MatBadgeModule, MatBottomSheetModule, MatButtonModule, MatButtonToggleModule,
  MatCardModule, MatCheckboxModule, MatChipsModule, MatDatepickerModule, MatDialogModule, MatDividerModule,
  MatExpansionModule, MatFormFieldModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule,
  MatMenuModule, MatNativeDateModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule,
  MatRadioModule, MatRippleModule, MatSelectModule, MatSidenavModule, MatSliderModule, MatSlideToggleModule,
  MatSnackBarModule, MatSortModule, MatStepperModule, MatTableModule, MatTabsModule, MatToolbarModule,
  MatTooltipModule, MatTreeModule,
} from '@angular/material';
import { QuillEditorModule } from 'ngx-quill-editor';
import { EventsAdminPageComponent } from './admin/events/events-admin-page/events-admin-page.component';

@NgModule({
  exports: [
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule
  ]
})
export class MaterialModule {
}

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule
    // QuillEditorModule,
  ],
  declarations: [
    AppComponent,
    EventsComponent,
    MenuComponent,
    FooterComponent,
    ClubComponent,
    MediaComponent,
    NewsComponent,
    NewsPageComponent,
    EventPageComponent,
    EventReportComponent,
    AdminComponent,
    AdminOrderListComponent,
    EventsListComponent,
    EventViewComponent,
    EventsAdminPageComponent
  ],
  providers: [EventsService, MenuService, NewsService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
