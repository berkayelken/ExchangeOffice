package com.byelken.exchange.office.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyExchanger.java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchanger
{
	private Double purchasePrice;
	private Double salePrice;

	public void setRates(Double rate)
	{
		purchasePrice = rate * 0.97;
		salePrice = rate * 1.03;
	}

	public static CurrencyExchanger createExchangeObject(Double rate)
	{
		CurrencyExchanger exch = new CurrencyExchanger();
		exch.setRates(rate);
		
		return exch;
	}
}
