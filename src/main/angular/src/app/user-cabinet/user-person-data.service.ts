import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {UserPersonDataDto} from "./dto/userPersonDataDto";

@Injectable()
export class UserPersonDataService {

  constructor(private http: HttpClient) {
  }

  retrieveUser(): Observable<UserPersonDataDto> {
    const url = '/api/private/user';
    return this.http.get<UserPersonDataDto>(url);
  }

  saveUserData(userData: UserPersonDataDto) {
    const url = '/api/private/user';
    return this.http.post(url, userData);
  }
}
