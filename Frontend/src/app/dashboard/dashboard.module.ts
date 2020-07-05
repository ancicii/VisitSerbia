import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DashboardComponent} from './dashboard.component';
import {RouterModule} from '@angular/router';
import {ToolbarModule} from "../toolbar/toolbar.module";
import {FlexModule} from "@angular/flex-layout";
import {MatTabsModule} from "@angular/material/tabs";
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {HomepageComponent} from './homepage/homepage.component';
import {ButtonsModule, CardsModule, CarouselModule, InputsModule, WavesModule, ButtonRadioDirective} from 'angular-bootstrap-md';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { EditProfileComponent } from './my-profile/edit-profile/edit-profile.component';
import { AddPreferencesComponent } from './my-profile/add-preferences/add-preferences.component';
import {MatRadioModule} from '@angular/material/radio';
import { PlanTripComponent } from './plan-trip/plan-trip.component';
import {SatDatepickerModule, SatNativeDateModule} from 'saturn-datepicker';

@NgModule({
  declarations: [DashboardComponent, HomepageComponent, MyProfileComponent, EditProfileComponent, AddPreferencesComponent, PlanTripComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    MatGridListModule,
    MatTooltipModule,
    MatButtonToggleModule,
    MatSlideToggleModule,
    MatToolbarModule,
    MatInputModule,
    MatPaginatorModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    RouterModule,
    ToolbarModule,
    FlexModule,
    MatTabsModule,
    CarouselModule,
    WavesModule,
    CardsModule,
    InputsModule,
    ButtonsModule,
    ReactiveFormsModule,
    MatRadioModule,
    SatNativeDateModule,
    SatDatepickerModule

  ],

})
export class DashboardModule {
}
