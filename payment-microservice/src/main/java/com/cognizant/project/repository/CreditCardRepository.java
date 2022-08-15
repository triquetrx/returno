package com.cognizant.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

}
