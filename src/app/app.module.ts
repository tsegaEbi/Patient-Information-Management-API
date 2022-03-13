import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxsModule } from '@ngxs/store';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/components/user/user.component';
import { AsferroUserState } from './user/RxStore/UserState.state';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserCreateComponent } from './user/components/user-create/user-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserDetailComponent } from './user/components/user-detail/user-detail.component';
import { UserUpdateComponent } from './user/components/user-update/user-update.component'; 

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserCreateComponent,
    UserDetailComponent,
    UserUpdateComponent 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxsModule.forRoot([
      AsferroUserState,
  ]),
    NgbModule,
    NgbModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
