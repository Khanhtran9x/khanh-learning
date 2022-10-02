import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthenticationComponent} from './component/login/authentication.component';


const routes: Routes = [
  {
    path: 'sign-in',
    component: AuthenticationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }
