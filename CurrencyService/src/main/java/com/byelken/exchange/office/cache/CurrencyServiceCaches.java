package com.byelken.exchange.office.cache;

import static com.byelken.exchange.office.cache.CurrencyServiceCache.getCache;
import static com.byelken.exchange.office.cache.CurrencyServiceCache.isEnabled;

import com.byelken.exchange.office.model.CurrencyRates;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 08-11-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyServiceCaches.java
 */
public final class CurrencyServiceCaches
{
	public static void addRates(String key, CurrencyRates rates)
	{
		if (!isEnabled())
			return;

		getCache().put(key, rates);
	}

	public static void removeRates(String key)
	{
		if (!isEnabled())
			return;

		getCache().remove(key);
	}

	public static CurrencyRates getRates(String key)
	{
		return getCache().get(key);
	}
}
