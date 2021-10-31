package com.byelken.exchange.office.third.party;

import static com.byelken.exchange.office.util.CurrencyServiceUtil.createId;
import static com.byelken.exchange.office.model.CurrencyExchanger.createExchangeObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.stream.Collectors;

import com.byelken.exchange.office.model.CurrencyExchanger;
import com.byelken.exchange.office.model.CurrencyRates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRatesThirdPartyDTO.java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRatesThirdPartyDTO
{
	private String disclaimer;
	private String license;
	private Long timestamp;
	private String base;
	private Map<String, Double> rates;

	public CurrencyRates createCurrencyRatesObj()
	{

		LocalDate date = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
		String id = createId(date);
		Map<String, CurrencyExchanger> exchangeRates = rates.entrySet().stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> createExchangeObject(entry.getValue())));

		return new CurrencyRates(id, date, exchangeRates);
	}
}
