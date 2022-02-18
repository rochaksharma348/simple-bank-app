import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Transfer } from '../common/transfer';
import { TransferResponse } from '../common/transfer-response';

@Injectable({
  providedIn: 'root'
})
export class TransferrServiceService {

  transferUrl = environment.bankAppUrl + "/transfer";

  constructor(private httpClient : HttpClient) { }

  public transferMoney(transfer : Transfer) : Observable<any> {
    return this.httpClient.post<Transfer>(this.transferUrl, transfer);
  }
}
