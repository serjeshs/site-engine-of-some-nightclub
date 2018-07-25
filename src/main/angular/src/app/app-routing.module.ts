import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from "./menu/menu.component";
import {EventsComponent} from "./events/events.component";
import {ClubComponent} from "./club/club.component";
import {MediaComponent} from "./media/media.component";
import {NewsComponent} from "./news/news.component";
import {NewsPageComponent} from "./news-page/news-page.component";
import {EventPageComponent} from "./event-page/event-page.component";
import {EventReportComponent} from "./event-report/event-report.component";
import {AdminComponent} from "./admin/summary/admin.component";
import {AdminOrderListComponent} from "./admin/admin-order-list/admin-order-list.component";
import {EventsListComponent} from "./admin/events/events-list/events-list.component";
import {EventViewComponent} from "./components/event-view/event-view.component";
import {EventsAdminPageComponent} from "./admin/events/events-admin-page/events-admin-page.component";
import {PageLoginComponent} from "./page-login/page-login.component";
import {FeedbackComponent} from "./feedback/feedback.component";
import {MenuEditorComponent} from "./admin/menu-editor/menu-editor.component";
import {PhotoReportComponent} from "./admin/photo-report/photo-report.component";
import {DiscoComponent} from "./disco/disco.component";

const routes: Routes = [
  {path: '', redirectTo: '/afisha', pathMatch: 'full'},
  {path: 'afisha', component: EventsComponent},
  {path: 'menu', component: MenuComponent},
  {path: 'media', component: MediaComponent},
  {path: 'news', component: NewsComponent},
  {path: 'club', component: ClubComponent},
  {path: 'event/:id', component: EventPageComponent},
  {path: 'events/:sameData', component: DiscoComponent},
  {path: 'events/:sameData/:anotherData', component: DiscoComponent},
  {path: 'event-reports/:id', component: EventReportComponent},
  {path: 'news/:id', component: NewsPageComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'admin-order-list', component: AdminOrderListComponent},
  {path: 'admin-events-list', component: EventsListComponent},
  {path: 'admin-event-view', component: EventViewComponent},
  {path: 'admin-events', component: EventsAdminPageComponent},
  {path: 'login', component: PageLoginComponent},
  {path: 'feedback', component: FeedbackComponent},
  {path: 'admin-menu-editor', component: MenuEditorComponent},
  {path: 'admin-photo-report', component: PhotoReportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
