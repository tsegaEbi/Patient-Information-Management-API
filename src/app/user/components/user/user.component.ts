import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import {  User } from '../../models/models';
import { GetUserById, GetUsers } from '../../RxStore/UserAction.action';
import { AsferroUserState } from '../../RxStore/UserState.state';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Select(AsferroUserState.getUsers) users$: Observable<any>;

  @Select(AsferroUserState.getUser)  user$: Observable<any>;

  // Initializing page
  pageSize=0;
  users:User[]=[];
  user:User;

  errorMessage="";
    
  constructor(private userService:UserService, private store: Store ) {
       
   }

  ngOnInit(): void {

      
       this.getUsers();
      
  }
  

  
  public getUser(id:number){
    this.errorMessage=null;
    this.store.dispatch(new GetUserById(id)).subscribe(); 
    this.user$.subscribe(
      (result)=>{
        this.user=result;
      },
      (error)=>{
        this.errorMessage="Unable to get the User"
      }
    )
  }
  public getUsers(){
    this.store.dispatch(new GetUsers(this.pageSize)).subscribe(); 
  
    this.users$.subscribe(
      (result)=>{
       
       if(result){
         this.users =result;
       }
       
   })
  }

}
