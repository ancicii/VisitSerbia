import {Routes} from '@angular/router';
import {DashboardComponent} from '../dashboard/dashboard.component';
import {LoginComponent} from '../auth/components/login/login.component';
import {RegisterComponent} from '../auth/components/register/register.component';
import {HomepageComponent} from '../dashboard/homepage/homepage.component';

export const routes: Routes = [
  {
    path: 'dashboard/:content',
    component: DashboardComponent
  },
  {
    path: 'dashboard',
    redirectTo: '/dashboard/home',
    pathMatch: 'full'
  },
  {
    path: '',
    redirectTo: 'dashboard/home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];
