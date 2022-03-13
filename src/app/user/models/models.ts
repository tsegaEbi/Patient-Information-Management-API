import { MAX_DEFAULT_PAGE_SIZE } from "../shared/config";

export class Models {
}
export interface UserCreate{
    name:string;
    surname:string;
    dob:Date;
    phone:string;
    email:string;
}
export interface UserUpdate{
  name:string;
  surname:string;
  dob:Date;
  phone:string;
  email:string;
  dateCreated:Date;
}
export interface User{
    id:number;     // not on the requirement through, I recommended to check for unique value
    name:string;
    surname:string;
    dob:Date;
    phone:string;
    email:string;
    dateCreated:Date;
    dateUpdated:Date;
}
 
  
