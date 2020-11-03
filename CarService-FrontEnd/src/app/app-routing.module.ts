import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarListComponent } from './car-list/car-list.component';
import { CarListSingleComponent } from './car-list-single/car-list-single.component';
import { CarCreateComponent } from './car-create/car-create.component';
import { CarEditComponent } from './car-edit/car-edit.component';

const routes: Routes = [
  { path: 'car-list', component: CarListComponent },
  { path: 'car-list-single', component: CarListSingleComponent },
  { path: 'car-create', component: CarCreateComponent },
  { path: 'car-edit', component: CarEditComponent },
  { path: '', redirectTo: '/car-list', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})

export class AppRoutingModule { }
