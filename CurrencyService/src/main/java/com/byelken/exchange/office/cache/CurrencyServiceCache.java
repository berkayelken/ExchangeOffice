package com.byelken.exchange.office.cache;

import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.beans.factory.annotation.Value;

import com.byelken.exchange.office.model.CurrencyRates;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyServiceCache.java
 */
class CurrencyServiceCache
{
	private ConcurrentSkipListMap<String, CurrencyRates> cache;

	@Value("${cache.enabled:true}")
	private boolean enabled;

	@Value("${cache.limit:10000}")
	private int limit;

	private static CurrencyServiceCache instance;

	static boolean isEnabled()
	{
		return getInstance().enabled;
	}

	static ConcurrentSkipListMap<String, CurrencyRates> getCache()
	{
		return getInstance().getIntanceCache();
	}

	private ConcurrentSkipListMap<String, CurrencyRates> getIntanceCache()
	{
		if (cache == null)
		{
			synchronized (this)
			{
				if (cache == null)
					cache = new ConcurrentSkipListMap<>();
			}
		}

		while (cache.size() >= limit)
			cache.pollFirstEntry();

		return cache;
	}

	private static CurrencyServiceCache getInstance()
	{
		if (instance == null)
		{
			synchronized (CurrencyServiceCache.class)
			{
				if (instance == null)
					instance = new CurrencyServiceCache();
			}
		}

		return instance;
	}

}
