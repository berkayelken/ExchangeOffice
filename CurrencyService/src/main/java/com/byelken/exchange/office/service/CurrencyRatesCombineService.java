package com.byelken.exchange.office.service;

import static com.byelken.exchange.office.cache.CurrencyServiceCaches.addRates;
import static com.byelken.exchange.office.cache.CurrencyServiceCaches.getRates;
import static com.byelken.exchange.office.cache.CurrencyServiceCaches.removeRates;
import static com.byelken.exchange.office.util.CurrencyServiceUtil.createId;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byelken.exchange.office.NotFoundException;
import com.byelken.exchange.office.model.CurrencyRates;
import com.byelken.exchange.office.third.party.CurrencyRatesThirdParty;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 08-11-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRatesCombineService.java
 */
@Service
public class CurrencyRatesCombineService
{
	@Autowired
	private CurrencyRatesService ratesService;

	public CurrencyRates getCurrencyRates(String date) throws IOException
	{
		String key = createId(date);

		CurrencyRates cur = getRates(key);
		if (cur != null)
			return cur;

		try
		{
			cur = ratesService.read(date);
		}
		catch (NotFoundException e)
		{
			cur = null;
		}

		if (cur != null)
		{
			addRates(key, cur);
			return cur;
		}

		cur = CurrencyRatesThirdParty.getCurrencyRates(date);
		ratesService.create(cur);
		addRates(key, cur);

		return cur;
	}

	public CurrencyRates updateCurrencyRates(CurrencyRates cur, String date) throws IOException
	{
		try
		{
			cur = ratesService.update(date, cur);
		}
		catch (NotFoundException e)
		{
			cur = getCurrencyRates(date);
		}
		addRates(createId(date), cur);

		return cur;
	}

	public Map<String, String> removeCurrencyRates(String date) throws NotFoundException
	{
		removeRates(createId(date));
		return ratesService.delete(date);
	}
}
