import {Component, OnInit} from '@angular/core';
import {User} from "./model/user";
import {ApiService} from "../shared/api.service.service";

@Component({
  selector: 'app-test-consume-api',
  templateUrl: './test-consume-api.component.html',
  styleUrls: ['./test-consume-api.component.css']
})
export class TestConsumeApiComponent implements OnInit{
  users: User[] = [];
  constructor(private apiService: ApiService) {
  }
  ngOnInit(): void {
    this.getAllUsersRest();
  }

  getAllUsersRest() {
    this.apiService.getAllUsers().subscribe(
      res => {
        this.users = res;
      },
      err => {
        alert("An error has occurred while getting all users");
      }
    );
  }

}
