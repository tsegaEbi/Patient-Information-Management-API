import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../../models/models';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  searchVal:any;
  user:User;
  formEdit:FormGroup;
  id:any;
  
 constructor(private userService:UserService,  private router:Router,private activatedroute:ActivatedRoute) { }

 ngOnInit(): void {
   
   
   this.id = + this.activatedroute.snapshot.paramMap.get("id");
   this.search();
   
 }

error="";
success="";
 search(){
   this.error="";
   this.success=""
     if(this.id==='undefined' || this.id===undefined || this.id==null){
           this.error="Id is not defined";
     }
     else{
       this.userService.getUserById(this.id).subscribe((response)=>{
          if(response!==null)
            {
              this.user=response[0];
              this.initFormGroup();
            } 
           else
           this.error="User not found";
       },
       (error)=>{
           this.error=error.message;
       }
       );
     }
 }
 phoneNumber = "^(\+\d{1,3}[- ]?)?\d{10}$";

 initFormGroup(){
 this.formEdit= new FormGroup({
   name:new FormControl([Validators.required,Validators.maxLength(60), Validators.minLength(1)]),
   surname:new FormControl('',[Validators.required,Validators.maxLength(60), Validators.minLength(1)]),
   email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
   phone:new FormControl('',[Validators.required,Validators.pattern("[0][0-9]{9}")]),
   dob:new FormControl('',[Validators.required]),
 });
 this.formEdit.setValue(
   {
     name:this.user.name,
     surname:this.user.surname,
     email:this.user.email,
     phone:this.user.phone,
     dob:this.user.dob,
   }
 ) ;
}
 get name() { return this.formEdit.get('name'); }
 get surname() { return this.formEdit.get('surname'); }
 get dob() { return this.formEdit.get('dob'); }
 get phone() { return this.formEdit.get('phone'); }
 get email() { return this.formEdit.get('email'); }
 

 submit(){
   this.error="";
   this.success="";

   if(this.formEdit.valid){
     
     const newUser ={
       id:this.id,
       dateCreated:this.user.dateCreated,
       name :this.formEdit.get("name").value,
       phone:this.formEdit.get("phone").value,
       email:this.formEdit.get("email").value,
       dob:this.formEdit.get("dob").value,
      surname:this.formEdit.get("surname").value,
     };

       this.userService.update(newUser).subscribe(
         (response)=>{
           this.success="User Updated Successfully"
         },
         (error)=>{
           this.error=error.message;
         }
       )

   } else{
      
     this.error="Please enter valid inputs";
   }
   
     
 }


}
