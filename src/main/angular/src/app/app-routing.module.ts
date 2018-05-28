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
import {AdminComponent} from "./admin/admin.component";
import {AdminOrderListComponent} from "./admin-order-list/admin-order-list.component";

const routes: Routes = [
  {path: '', redirectTo: '/afisha', pathMatch: 'full'},
  {path: 'afisha', component: EventsComponent},
  {path: 'menu', component: MenuComponent},
  {path: 'media', component: MediaComponent},
  {path: 'news', component: NewsComponent},
  {path: 'club', component: ClubComponent},
  {path: 'events/:year/:prefix/:path', component: EventPageComponent},
  {path: 'event-reports/:id', component: EventReportComponent},
  {path: 'news/:id', component: NewsPageComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'admin-order-list', component: AdminOrderListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
