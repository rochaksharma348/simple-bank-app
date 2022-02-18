package com.rochak.bankingapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private int senderId;
    private int receiverId;
    private BigDecimal amount;
}
