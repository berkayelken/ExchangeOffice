package com.byelken.exchange.office.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 		: Celal Berkay Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRates.java
 */
@Document("CurrencyRates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRates
{
	@Id
	private String id;
	private LocalDate date;
	private Map<String, CurrencyExchanger> rates;
}
