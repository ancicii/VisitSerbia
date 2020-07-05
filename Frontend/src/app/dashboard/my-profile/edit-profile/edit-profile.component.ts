import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../services/user.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {AbstractControl, FormBuilder, FormControl, Validators} from '@angular/forms';
import {RegisterUser} from '../../../shared/models/register-user.model';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  public data = '';

    constructor(private userService:UserService, private snackBar: MatSnackBar,
              private router: Router, private fb: FormBuilder) {
  }
  matchingPasswords = (control: AbstractControl): {[key: string]: boolean} =>{

    const newPassword = control.get('newPassword');
    const confirmPassword = control.get('newPassword2');
    if (!newPassword || !confirmPassword) {
      return null;
    }
    return newPassword.value === confirmPassword.value ? null : { mismatchedPasswords: true };
  }

  ngForm = this.fb.group({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    maritalStatus: new FormControl('', [Validators.required]),
    years: new FormControl('', Validators.compose([
      Validators.max(100),
      Validators.min(10),
      Validators.required])),
    numberOfKids: new FormControl('', Validators.compose([
      Validators.max(15),
      Validators.min(0),
      Validators.required])),
    email: new FormControl(''),
    password: new FormControl(''),
    newPassword: new FormControl('', Validators.compose([
      Validators.minLength(8),
      Validators.pattern("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!_*#$%^&+=])(?=\\S+$).{8,}")])),
    newPassword2: new FormControl('', Validators.compose([
      Validators.minLength(8),
      Validators.pattern("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!_*#$%^&+=])(?=\\S+$).{8,}")]))
  }, {
    validator: this.matchingPasswords

  });


  ngOnInit(): void {
    const getUserObserver = {
      next: x =>{
        this.ngForm.controls['email'].setValue(x.email);
        this.ngForm.controls['firstName'].setValue(x.firstName);
        this.ngForm.controls['lastName'].setValue(x.lastName);
        this.ngForm.controls['maritalStatus'].setValue(x.maritalStatus);
        this.ngForm.controls['years'].setValue(x.years);
        this.ngForm.controls['numberOfKids'].setValue(x.numberOfKids);
        this.ngForm.controls['password'].setValue("");
        this.ngForm.controls['newPassword'].setValue("");
        this.ngForm.controls['newPassword2'].setValue("");
      },
      error: (err: any) => {
        console.log(err);
        this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
          duration: 3000
        });
      }
    };

    this.userService.getUser().subscribe(getUserObserver);

  }

  onSubmit() {
    if (this.ngForm.controls['password'].value !== "" && this.ngForm.controls['newPassword'].value !== "" && this.ngForm.controls['newPassword2'].value !== "") {
      const passwordCheck = {
        next: x => {
          if (this.ngForm.controls['newPassword'].value !== "" && this.ngForm.controls['password'].value === "") {
            this.snackBar.open("You must enter old pass", 'Dismiss', {
              duration: 3000
            });
          } else {
            const updateObserver = {
              next: x => {
                this.snackBar.open(x, 'Dismiss', {
                  duration: 3000
                });
              },
              error: (err: any) => {
                this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
                  duration: 3000
                });
              }
            };
            let registerUser: RegisterUser;
            if (this.ngForm.controls['newPassword2'].value !== "") {
              registerUser = new RegisterUser(
                this.ngForm.controls['newPassword2'].value,
                this.ngForm.controls['firstName'].value, this.ngForm.controls['lastName'].value,
                this.ngForm.controls['email'].value, this.ngForm.controls['maritalStatus'].value,
                this.ngForm.controls['years'].value, this.ngForm.controls['numberOfKids'].value
              );
            } else {
              registerUser = new RegisterUser(
                null,
                this.ngForm.controls['firstName'].value, this.ngForm.controls['lastName'].value,
                this.ngForm.controls['email'].value, this.ngForm.controls['maritalStatus'].value,
                this.ngForm.controls['years'].value, this.ngForm.controls['numberOfKids'].value
              );
            }

            this.userService.editUser(registerUser).subscribe(updateObserver);

          }

        },
        error: (err: any) => {
          this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
            duration: 3000
          });
        }
      };
      this.userService.checkPassword(this.ngForm.controls['password'].value).subscribe(passwordCheck);
    } else {
      const updateObserver = {
        next: x => {
          this.snackBar.open(x, 'Dismiss', {
            duration: 3000
          });
        },
        error: (err: any) => {
          this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
            duration: 3000
          });
        }
      };
      let registerUser = new RegisterUser(
        null,
        this.ngForm.controls['firstName'].value, this.ngForm.controls['lastName'].value,
        this.ngForm.controls['email'].value, this.ngForm.controls['maritalStatus'].value,
        this.ngForm.controls['years'].value, this.ngForm.controls['numberOfKids'].value
      );
      this.userService.editUser(registerUser).subscribe(updateObserver);
    }

  }

}
