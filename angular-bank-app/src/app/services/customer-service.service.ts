import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../common/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = environment.bankAppUrl + "/customers";

  constructor(private httpClient : HttpClient) { }

  getCustomersList() : Observable<Customer[]> {
    return this.httpClient.get<GetResponseCustomers>(this.baseUrl).pipe(
      map(response => response._embedded.customers)
    );
  }

  getCustomer(theId : number) : Observable<Customer> {
    const searchUrl = `${this.baseUrl}/${theId}`;
    return this.httpClient.get<Customer>(searchUrl);
  }

  getCustomersExcept(theId : number) : Observable<Customer[]> {
    const searchUrl = `${this.baseUrl}/search/findByIdNot?id=${theId}`;
    return this.httpClient.get<GetResponseCustomers>(searchUrl).pipe(
      map(response => response._embedded.customers)
    );
  }
}

interface GetResponseCustomers {
  _embedded: {
    customers : Customer[];
  }
}

