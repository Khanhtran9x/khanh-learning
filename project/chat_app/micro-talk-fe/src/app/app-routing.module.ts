import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthenticationModule} from './authentication/authentication.module';
import {AuthenticationRoutingModule} from './authentication/authentication-routing.module';
import {ChatRoutingModule} from './chat/chat-routing.module';


const routes: Routes = [];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    AuthenticationRoutingModule,
    ChatRoutingModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
