package com.byelken.exchange.office.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.byelken.exchange.office.model.CurrencyRates;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRatesRepository.java
 */
public interface CurrencyRatesRepository extends MongoRepository<CurrencyRates, String>
{

}
