import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from "./menu/menu.component";
import {EventsComponent} from "./events/events.component";

// import {HeroDetailComponent} from './hero-detail/hero-detail.component';

const routes: Routes = [
  {path: '', redirectTo: '/afisha', pathMatch: 'full'},
  {path: 'afisha', component: EventsComponent},
  {path: 'menu', component: MenuComponent},
  // {path: 'detail/:id', component: HeroDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
