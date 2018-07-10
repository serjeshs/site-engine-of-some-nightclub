import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";
import {UserDto} from "./dto/userDto";
import {catchError} from "rxjs/operators";
import {NewsItemDto} from "./dto/newsItemDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

class ResponseEntity {
  success: boolean;
}

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<ResponseEntity> {
    let data = {
      username: username,
      password: password
    };
    return this.http.post<ResponseEntity>('/api/loginprocessing', data, httpOptions).pipe(
      // catchError(this.handleError('authService.login', new ResponseEntity()))
    );
  }

  currentUser(): Observable<UserDto> {
    return this.http.get<UserDto>('/api/user/current').pipe();
  }
}
