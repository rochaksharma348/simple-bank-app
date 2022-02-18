package com.rochak.bankingapp.dao;

import com.rochak.bankingapp.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
