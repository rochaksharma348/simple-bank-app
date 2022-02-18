package com.rochak.bankingapp.service;

import com.rochak.bankingapp.dto.TransferResponse;
import com.rochak.bankingapp.entity.Customer;
import com.rochak.bankingapp.entity.Transfer;

public interface TransferService {
    public TransferResponse sendMoney(Transfer transfer);

    public Customer getSender(int senderId);

    public Customer getReceiver(int senderId);
}