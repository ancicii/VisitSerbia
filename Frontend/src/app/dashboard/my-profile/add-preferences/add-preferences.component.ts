import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RegisterUser} from '../../../shared/models/register-user.model';
import {MatSnackBar} from '@angular/material/snack-bar';
import {QuestionsService} from '../../../services/questions.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-preferences',
  templateUrl: './add-preferences.component.html',
  styleUrls: ['./add-preferences.component.css']
})
export class AddPreferencesComponent implements OnInit {

  selectedAnswers = [];
  questions = [];

  constructor(private snackBar: MatSnackBar, private questionsService: QuestionsService,
              private router: Router) { }

  ngOnInit(): void {
    this.selectedAnswers[2] = 8;
    const getQuestionsObserver = {
      next: x =>{
        this.questions = x;
        for(let i = 0; i<x.length; i++){
          this.selectedAnswers[i] = -1;
        }
      },
      error: (err: any) => {
        this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
          duration: 3000
        });
      }
    };
    this.questionsService.getQuestions().subscribe(getQuestionsObserver);
  }

  onSubmit() {
    const sendAnswersObserver = {
      next: x =>{
        this.snackBar.open("Preferences saved successfully!", 'Dismiss', {
          duration: 3000
        });
        this.router.navigate(['/dashboard/planTrip']);
      },
      error: (err: any) => {
        this.snackBar.open(JSON.parse(JSON.stringify(err))["error"], 'Dismiss', {
          duration: 3000
        });
      }
    };
    this.questionsService.sendAnswers(this.selectedAnswers).subscribe(sendAnswersObserver);

  }
}
