import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpErrorResponse, HttpResponse, HttpStatusCode } from  '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {   User, UserCreate, UserUpdate } from '../models/models'; 
import { USER_URL } from '../shared/config';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   url=USER_URL;
   constructor(private httpClient:HttpClient) {
          
   }
   public getUserById(id:number):Observable<User[]>{

    return this.httpClient.get<User[]>(this.url+"/"+id); 

  }
   public getUserByEmail(email:string):Observable<User>{

    return this.httpClient.get<User>(this.url+"/email/"+email); 

  }
  public list( ):Observable< User[] >{

    return this.httpClient.get<User[]>(this.url+"/list");

  }
  public create(user:UserCreate):Observable<User>{

    return this.httpClient.post<User>(this.url+"/create",user);

  }
  public delete(id:number):Observable<any>{

    return this.httpClient.delete<any>(this.url+"/delete/"+id);

  }
  public update(user:UserUpdate):Observable<any>{

    return this.httpClient.put<any>(this.url+"/update",user);

  }
 
}
 
 

