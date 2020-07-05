import {Component, OnInit, ViewChild} from '@angular/core';
import {EditProfileComponent} from './edit-profile/edit-profile.component';
import {AddPreferencesComponent} from './add-preferences/add-preferences.component';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  @ViewChild(EditProfileComponent) private editProfileComponent: EditProfileComponent;
  @ViewChild(AddPreferencesComponent) private addPreferencesComponent: AddPreferencesComponent;

  constructor() { }

  ngOnInit(): void {
  }

  onTabChanged($event) {
    switch ($event.index) {
      case (0):
        this.editProfileComponent.ngOnInit();
        break;
      case (1):
        this.addPreferencesComponent.ngOnInit()
        break;
    }
  }
}
