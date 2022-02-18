import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/common/customer';
import { CustomerService } from 'src/app/services/customer-service.service';

@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.css']
})
export class TransferListComponent implements OnInit {

  customers : Customer[];
  senderId : number;

  constructor(private customerService : CustomerService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.getCustomers();
  }
  getCustomers() {
    const theId : number = +this.route.snapshot.paramMap.get('id');
    this.senderId = theId;
    this.customerService.getCustomersExcept(theId).subscribe(
      data => {
        this.customers = data;
      }
    )
  }

}
