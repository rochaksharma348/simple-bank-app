package com.rochak.bankingapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "img_url")
    private String imageUrl;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Transfer> sendTransactions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private List<Transfer> receivedTransactions;

    public boolean sendMoney(BigDecimal amount) {
        if(getBalance().compareTo(amount) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        return true;
    }

    public boolean receiveMoney(BigDecimal amount) {
        balance = balance.add(amount);
        return true;
    }

    public void rollback(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
