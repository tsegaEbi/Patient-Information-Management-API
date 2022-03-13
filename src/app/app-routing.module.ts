import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserCreateComponent } from './user/components/user-create/user-create.component';
import { UserDetailComponent } from './user/components/user-detail/user-detail.component';
import { UserComponent } from './user/components/user/user.component';

const routes: Routes = [
     {
      path: 'user/:id',
      component:UserDetailComponent
    },
    {
      path: 'users',
      component:UserComponent
    },
    {
      path: '',  //default router
      component:UserComponent
    },
    {
      path: 'create',  component:UserCreateComponent
    },
    {
      path: '**',  //default router
      component:UserComponent
    }
   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
