package com.byelken.exchange.office.third.party;

import static com.byelken.exchange.office.util.CurrencyServiceUtil.convertJsonToObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import com.byelken.exchange.office.model.CurrencyRates;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyRatesThirdParty.java
 */
public final class CurrencyRatesThirdParty
{
	private static CurrencyRatesThirdParty instance;

	@Value("${open.exchage.rates.appid:'b00531beb37e44a0b7993264040562d8'}")
	private String appId;

	public static CurrencyRates getCurrencyRates(String date) throws IOException
	{
		String appId = getInstance().appId;
		String uri = "https://openexchangerates.org/api/historical/" + date + ".json?app_id=" + appId;
		URL url = new URL(uri);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(HttpMethod.GET.name());

		String json = "";
		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));)
		{
			StringBuffer content = new StringBuffer();
			in.lines().filter(line -> !StringUtils.isBlank(line)).forEach(line -> content.append(line));
			json = content.toString();
		}
		finally
		{
			con.disconnect();
		}

		return convertJsonToObject(json, CurrencyRatesThirdPartyDTO.class).createCurrencyRatesObj();
	}

	private static CurrencyRatesThirdParty getInstance()
	{
		if (instance == null)
		{
			synchronized (CurrencyRatesThirdParty.class)
			{
				if (instance == null)
					instance = new CurrencyRatesThirdParty();
			}
		}

		return instance;
	}

}
