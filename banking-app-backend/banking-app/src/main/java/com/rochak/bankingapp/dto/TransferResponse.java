package com.rochak.bankingapp.dto;

import lombok.Data;

@Data
public class TransferResponse {

    private boolean success;

    private String response;
}
