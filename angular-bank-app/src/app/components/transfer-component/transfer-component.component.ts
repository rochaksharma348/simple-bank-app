import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Transfer } from 'src/app/common/transfer';
import { TransferrServiceService } from 'src/app/services/transferr-service.service';

@Component({
  selector: 'app-transfer-component',
  templateUrl: './transfer-component.component.html',
  styleUrls: ['./transfer-component.component.css']
})
export class TransferComponent implements OnInit {

  transferFormGroup : FormGroup;

  constructor(private formBuilder : FormBuilder, private router : Router, private route : ActivatedRoute, private transferService : TransferrServiceService) { }

  ngOnInit(): void {
    this.transferFormGroup = this.formBuilder.group({
      amount : new FormControl('', [Validators.required])
    });
  }

  get amount() { return this.transferFormGroup.get('amount'); }

  onSubmit() {
    if(this.transferFormGroup.invalid) {
      this.transferFormGroup.markAllAsTouched();
      return;
    }

    const senderId : number = +this.route.snapshot.paramMap.get('sid');
    const receiverId : number = +this.route.snapshot.paramMap.get('rid');
    const amount : number = this.transferFormGroup.get('amount').value;

    const transfer = new Transfer(senderId, receiverId, amount);

    console.log(`sid: ${senderId}, rid: ${receiverId}, amount: ${amount}`);

    this.transferService.transferMoney(transfer).subscribe({
      next : res => {
        alert(res.response);
        if(res.success) {
          this.router.navigate(['/customers'])
        }
      }, 
      error : err => {
        alert(`There has been an error: ${err.message}`)
      }
    });
  }
}
