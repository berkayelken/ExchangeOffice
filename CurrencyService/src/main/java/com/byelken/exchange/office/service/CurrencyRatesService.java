package com.byelken.exchange.office.service;

import static com.byelken.exchange.office.util.CurrencyServiceUtil.createId;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byelken.exchange.office.NotFoundException;
import com.byelken.exchange.office.model.CurrencyRates;
import com.byelken.exchange.office.repository.CurrencyRatesRepository;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRatesService.java
 */
@Service
class CurrencyRatesService
{
	@Autowired
	private CurrencyRatesRepository currencyRepo;

	CurrencyRates create(CurrencyRates rate)
	{
		return currencyRepo.save(rate);
	}

	CurrencyRates read(String date) throws NotFoundException
	{
		String id = createId(date);
		Optional<CurrencyRates> rates = currencyRepo.findById(id);
		if (!rates.isPresent())
			throw new NotFoundException("Currency Rates cannot be found with date : " + date);

		return rates.get();
	}

	CurrencyRates update(String date, CurrencyRates rate) throws NotFoundException
	{
		String id = createId(date);
		Optional<CurrencyRates> rates = currencyRepo.findById(id);
		if (!rates.isPresent())
			throw new NotFoundException("Currency Rates cannot be found with date : " + date);

		return currencyRepo.save(rate);
	}

	Map<String, String> delete(String date) throws NotFoundException
	{
		String id = createId(date);
		if (!currencyRepo.findById(id).isPresent())
			throw new NotFoundException("Currency Rates cannot be found with date : " + date);

		currencyRepo.deleteById(id);

		Map<String, String> responseMap = new ConcurrentHashMap<>();
		
		responseMap.put("status", "success");
		responseMap.put("date", date);
		responseMap.put("id", id);

		return responseMap;
	}

}
