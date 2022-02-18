package com.rochak.bankingapp.dao;

import com.rochak.bankingapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findByIdNot(@RequestParam("id") int id);
}
