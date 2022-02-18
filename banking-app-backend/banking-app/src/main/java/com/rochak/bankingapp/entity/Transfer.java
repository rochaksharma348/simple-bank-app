package com.rochak.bankingapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "reciever_id")
    private Customer receiver;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transfer() {

    }

    public Transfer(Customer sender, Customer receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
}
