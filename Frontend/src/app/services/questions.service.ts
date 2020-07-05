import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  private _headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true'
  });

  constructor(private _http: HttpClient) {
  }

  getQuestions(): Observable<any> {
    return this._http.get('questions', {headers: this._headers});
  }

  sendAnswers(selectedAnswers: any[]) {
    return this._http.post('questions/sendAnswers', selectedAnswers,{headers: this._headers});

  }
}
