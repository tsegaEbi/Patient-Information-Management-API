import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  phoneNumber = "^(\+\d{1,3}[- ]?)?\d{10}$";
  formUser= new FormGroup({
    name:new FormControl('',[Validators.required,Validators.maxLength(60), Validators.minLength(1)]),
    surname:new FormControl('',[Validators.required,Validators.maxLength(60), Validators.minLength(1)]),
    email:new FormControl('',[Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
    phone:new FormControl('',[Validators.required,Validators.pattern("[0][0-9]{9}")]),
    dob:new FormControl('',[Validators.required]),
  });
  get name() { return this.formUser.get('name'); }
  get surname() { return this.formUser.get('surname'); }
  get dob() { return this.formUser.get('dob'); }
  get phone() { return this.formUser.get('phone'); }
  get email() { return this.formUser.get('email'); }

  error="";
  success="";

  submit(){
    this.error="";
    this.success="";

    if(this.formUser.valid){
      
      const newUser ={
        name :this.formUser.get("name").value,
        phone:this.formUser.get("phone").value,
        email:this.formUser.get("email").value,
        dob:this.formUser.get("dob").value,
       surname:this.formUser.get("surname").value,
      };

        this.userService.create(newUser).subscribe(
          (response)=>{
            this.success="User Created Successfully"
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
