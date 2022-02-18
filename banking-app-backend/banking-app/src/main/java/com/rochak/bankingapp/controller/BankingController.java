package com.rochak.bankingapp.controller;

import com.rochak.bankingapp.dto.TransferDto;
import com.rochak.bankingapp.dto.TransferResponse;
import com.rochak.bankingapp.entity.Customer;
import com.rochak.bankingapp.entity.Transfer;
import com.rochak.bankingapp.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankingController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferDto transferDto) {

        Customer sender = transferService.getSender(transferDto.getSenderId());
        Customer receiver = transferService.getReceiver(transferDto.getReceiverId());

        Transfer transfer = new Transfer(sender, receiver, transferDto.getAmount());

        return transferService.sendMoney(transfer);
    }
}
