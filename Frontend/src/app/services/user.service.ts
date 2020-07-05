import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from "rxjs";
import { RegisterUser } from "../shared/models/register-user.model";
import { LoginUser } from "../shared/models/login-user.model";

@Injectable({
  providedIn: "root"
})
export class UserService {

  private _headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private _http: HttpClient) {
  }

  getUser(): Observable<any> {
    return this._http.get('user', {headers: this._headers});
  }

  checkPassword(value: String) {
    return this._http.post('user/checkPassword', value, {headers: this._headers, responseType: 'text'});
  }

  editUser(registerUser: RegisterUser) {
    return this._http.put('user/editUser', registerUser, {headers: this._headers, responseType: 'text'});
  }
}
