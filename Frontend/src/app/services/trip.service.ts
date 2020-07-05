import { Injectable } from '@angular/core';
import {TripParamsModel} from '../shared/models/trip-params.model';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private _headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private _http: HttpClient) {
  }

  generateTrip(parameters: TripParamsModel): Observable<any> {
    return this._http.post('trip', parameters, {headers: this._headers});

  }
}
