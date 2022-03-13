import { Injectable } from "@angular/core";
import { of } from "rxjs"; 
import {   User } from "../models/models";
import { UserService } from "../services/user.service";
import { State,Action, Selector, StateContext } from "@ngxs/store";
import { GetUserById, GetUsers } from "./UserAction.action";
import { tap, catchError } from "rxjs/operators";

export interface UserStateModel{
    users: User[];
    user:User ;
    loading:boolean ;  // to those the state status if loading or not, usefull on componenets
}
@State<UserStateModel>({
    name: 'AsferroUsers',
    defaults: {
      users:undefined,
      user:undefined,
      loading:false,
      //trainer
    }
})
@Injectable()
export class AsferroUserState{
  constructor(private readonly userService: UserService){}

    @Selector()
    static getUsers(state:UserStateModel){
      return state.users;

    }
    @Selector()
    static getUser(state:UserStateModel){
      return state.user;

    }
    @Action(GetUsers)
    getUsers({patchState}: StateContext<UserStateModel>,{page}:GetUsers){

    patchState({
       loading: true
     });

     return this.userService.list().pipe(
       tap((result:  User[] ) => {
         patchState({
           users: result,
           loading: false
         });
       }),
       catchError((error) => {
         patchState({ loading: false });

         return of();
       })
     );
  }

  @Action(GetUserById)
    getUser({patchState}: StateContext<UserStateModel>,{id}:GetUserById){

    patchState({
       loading: true
     });

     return this.userService.getUserById(id).pipe(
       tap((result: User[]) => {
         patchState({
           user: result[0],
           loading: false
         });
       }),
       catchError((error) => {
         patchState({ loading: false });

         return of();
       })
     );
  }
}