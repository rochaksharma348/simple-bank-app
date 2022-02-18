package com.rochak.bankingapp.service;

import com.rochak.bankingapp.dao.CustomerRepository;
import com.rochak.bankingapp.dao.TransferRepository;
import com.rochak.bankingapp.dto.TransferResponse;
import com.rochak.bankingapp.entity.Customer;
import com.rochak.bankingapp.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public TransferResponse sendMoney(Transfer transfer) {

        Customer sender = transfer.getSender();
        Customer receiver = transfer.getReceiver();
        BigDecimal amount = transfer.getAmount();

        TransferResponse transferResponse = new TransferResponse();

        System.out.println("Transfer amount: " + transfer.getAmount());

        System.out.println(sender.getFirstName() + "'s balance before transaction: " + sender.getBalance());
        System.out.println(receiver.getFirstName() + "'s balance before transaction: " + receiver.getBalance());
        boolean send = sender.sendMoney(amount);
        boolean receive = false;

        if (send) {
            transferResponse.setSuccess(true);
        } else {
            transferResponse.setSuccess(false);
            transferResponse.setResponse("You have insufficient funds to make this payment");
        }

        if (send) {
            receive = receiver.receiveMoney(amount);
            if (!receive) {
                sender.rollback(amount);
                transferResponse.setSuccess(false);
                transferResponse.setResponse("There was some problem with the servers. Please retry the payment. If any amount has been " +
                        "deducted from your bank account it will be refunded between 2-3 business days");
            } else {
                transferResponse.setResponse("Rs. " + amount + " has been transferred to " + receiver.getFirstName() + " " + receiver.getLastName());
            }
        }

        if (send && receive) {
            transferRepository.save(transfer);
        }

        System.out.println(sender.getFirstName() + "'s balance after transaction for transaction of Rs. " + amount + ": " + sender.getBalance());
        System.out.println(receiver.getFirstName() + "'s balance after transaction for transaction of Rs. " + amount + ": " + receiver.getBalance());

        return transferResponse;
    }

    @Override
    @Transactional
    public Customer getSender(int senderId) {
        return customerRepository.getById(senderId);
    }

    @Override
    @Transactional
    public Customer getReceiver(int receiverId) {
        return customerRepository.getById(receiverId);
    }
}
