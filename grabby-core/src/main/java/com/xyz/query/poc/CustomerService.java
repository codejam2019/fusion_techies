package com.xyz.query.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  @Autowired
  CustomerRepo customerRepo;

  @Cacheable("customer")
  public List<Customer> findAll(){
    return customerRepo.findAll();
  }

  @CacheEvict("customer")
  public void createNew(){
    customerRepo.save(Customer.builder().name("C1").address("XYZ").email("abc").age((int)Math.random()).build());
  }

  @Cacheable("customerId")
  public Customer getById(Long id){
    return customerRepo.findById(id).get();
  }
}
