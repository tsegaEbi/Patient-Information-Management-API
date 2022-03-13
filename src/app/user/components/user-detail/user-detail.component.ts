import { HttpResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/models';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
   searchVal:any;
   user:User;
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
            this.user=response[0];
            else
            this.error="User not found";
        },
        (error)=>{
            this.error=error.message;
        }
        );
      }
  }
  delete(){
    this.error="";
    this.success=""
    this.userService.delete(this.id).subscribe(
      (response)=>{
        
      this.success=response; 
   } ,
   (error)=>{
     this.error=error.message;
   }
   );
  }

}
