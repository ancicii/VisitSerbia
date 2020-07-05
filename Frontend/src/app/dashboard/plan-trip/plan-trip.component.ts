import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SatDatepickerInputEvent} from 'saturn-datepicker';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';
import {TripService} from '../../services/trip.service';
import {RegisterUser} from '../../shared/models/register-user.model';
import {TripParamsModel} from '../../shared/models/trip-params.model';

@Component({
  selector: 'app-plan-trip',
  templateUrl: './plan-trip.component.html',
  styleUrls: ['./plan-trip.component.scss']
})
export class PlanTripComponent implements OnInit {

  dateRange = {'begin': Date, 'end': Date};
  todayDate:Date = new Date();

  constructor(private snackBar: MatSnackBar, private tripService: TripService,
              private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
  }



  checkDates = (control: AbstractControl): {[key: string]: boolean} =>{

    const beginDate = new Date(control.get('date').value.begin);
    const endDate = new Date(control.get('date').value.end);
    var oneDay = 24*60*60*1000;
    var diffDays = Math.abs((endDate.getTime() - beginDate.getTime())/(oneDay));

    return diffDays > 4 ? { moreThanFiveDays: true } : null ;
  }


  ngForm = this.fb.group({
    partOfSerbia: new FormControl('', [Validators.required]),
    noOfPassengers: new FormControl('', Validators.compose([
      Validators.max(10),
      Validators.min(1),
      Validators.required])),
    budget: new FormControl('', Validators.compose([
      Validators.min(0),
      Validators.required])),
    date: new FormControl({value: null})
  },{
    validator: this.checkDates

  });

  onSubmit() {
    const generatePlanObserver = {
      next: x =>{
        this.router.navigate(['/dashboard/trip']);
      },
      error: (err: any) => {
        this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
          duration: 3000
        });
        this.router.navigate(['/dashboard/myProfile']);
      }
    };

    const parameters: TripParamsModel = new TripParamsModel(
      this.ngForm.controls['partOfSerbia'].value,
      this.ngForm.controls['noOfPassengers'].value, this.ngForm.controls['budget'].value,
      this.ngForm.controls['date'].value.begin, this.ngForm.controls['date'].value.end
    );

    this.tripService.generateTrip(parameters).subscribe(generatePlanObserver);


  }
}
