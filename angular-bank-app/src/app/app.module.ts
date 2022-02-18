import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { Routes, RouterModule, Router } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { AppComponent } from './app.component';
import { CustomersListComponent } from './components/customers-list/customers-list.component';
import { CustomerDetailComponent } from './components/customer-detail/customer-detail.component';
import { ViewCustomersComponent } from './components/view-customers/view-customers.component';
import { TransferComponent } from './components/transfer-component/transfer-component.component';
import { TransferListComponent } from './components/transfer-list/transfer-list.component';
import { ReactiveFormsModule } from '@angular/forms';

const routes : Routes = [
  {path : 'transfer/:rid/:sid', component : TransferComponent},
  {path : 'transfer-list/:id', component :TransferListComponent},
  {path : 'user-details/:id', component : CustomerDetailComponent},
  {path : 'customers', component : CustomersListComponent},
  {path : 'home', component : ViewCustomersComponent},
  {path : '**', redirectTo : 'home', pathMatch : 'full'},
  {path : '', redirectTo : 'home', pathMatch : 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    CustomersListComponent,
    CustomerDetailComponent,
    ViewCustomersComponent,
    TransferComponent,
    TransferListComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
