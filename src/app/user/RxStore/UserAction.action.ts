 

export class GetUsers{
    static readonly type='[GetUsers] getUsers';
    constructor(public page:number){}
  }
  export class GetUserById {
    static readonly type = '[GetUserById] GetUserById';
    constructor(public id:number) {}
  }