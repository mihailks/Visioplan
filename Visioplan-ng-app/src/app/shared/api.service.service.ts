import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../test-consume-api/model/user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = "http://localhost:8080/api/test";

  constructor(private http: HttpClient) {

  }

  getAllUsers(): Observable<User[]> {
    let observable = this.http.get<User[]>(this.BASE_URL);
    return observable;
  }
}
