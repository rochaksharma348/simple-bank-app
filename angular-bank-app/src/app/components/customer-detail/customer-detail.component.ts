import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/common/customer';
import { CustomerService } from 'src/app/services/customer-service.service';

@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {

  customer : Customer;

  constructor(private customerService : CustomerService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getCustomerDescription();
    });
  }
  getCustomerDescription() {
    const theId : number = +this.route.snapshot.paramMap.get('id');

    this.customerService.getCustomer(theId).subscribe(
      data => {
        this.customer = data;
      }
    );
  }

}
