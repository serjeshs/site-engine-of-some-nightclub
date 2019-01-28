import {Component, OnInit} from '@angular/core';
import {UserPersonDataDto} from "../dto/userPersonDataDto";
import {UserPersonDataService} from "../user-person-data.service";

@Component({
  selector: 'app-personal-info',
  templateUrl: './personal-info.component.html',
  styleUrls: ['./personal-info.component.css']
})
export class PersonalInfoComponent implements OnInit {
  userData: UserPersonDataDto;

  constructor(private userPersonDataService :UserPersonDataService) {
  }

  ngOnInit() {
    this.userData = new UserPersonDataDto();
    this.userPersonDataService.retrieveUser()
      .pipe()
      .subscribe(responce => {
        this.userData = <UserPersonDataDto>responce;
      })
  }

  save() {
    this.userPersonDataService.saveUserData(this.userData)
      .pipe()
      .subscribe(responce => {
        this.userData = <UserPersonDataDto>responce;
        //alert save
      })
  }
}
