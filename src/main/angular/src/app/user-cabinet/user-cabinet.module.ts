import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonalInfoComponent } from './personal-info/personal-info.component';
import {UserPersonDataService} from "./user-person-data.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  imports: [
    FormsModule,
    CommonModule
  ],
  declarations: [PersonalInfoComponent],
  providers: [UserPersonDataService],
  exports: [PersonalInfoComponent]
})
export class UserCabinetModule { }
