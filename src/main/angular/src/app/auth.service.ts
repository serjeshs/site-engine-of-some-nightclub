import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {UserDto} from "./dto/userDto";
import {catchError} from "rxjs/operators";
import {NewsItemDto} from "./dto/newsItemDto";
import {ResponseEntity} from "./dto/ResponseEntity";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<ResponseEntity> {
    let url = '/api/loginprocessing?username=' + username + '&password=' + password;
    return this.http.post<ResponseEntity>(url, {}, httpOptions).pipe(
      // catchError(this.handleError('authService.login', new ResponseEntity()))
    );
  }

  currentUser(): Observable<UserDto> {
    return this.http.get<UserDto>('/api/user').pipe();
  }
}
