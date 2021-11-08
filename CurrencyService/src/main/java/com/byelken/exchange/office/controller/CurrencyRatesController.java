package com.byelken.exchange.office.controller;

import static com.byelken.exchange.office.util.CurrencyServiceUtil.getDateAsStr;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.byelken.exchange.office.NotFoundException;
import com.byelken.exchange.office.model.CurrencyRates;
import com.byelken.exchange.office.service.CurrencyRatesCombineService;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyServiceController.java
 */
@RestController("/api/currency-rates")
@CrossOrigin
public class CurrencyRatesController
{
	@Autowired
	private CurrencyRatesCombineService service;

	@GetMapping
	public CurrencyRates getCurrencyRates() throws IOException
	{
		return service.getCurrencyRates(getDateAsStr(LocalDate.now()));
	}

	@GetMapping("/{date}")
	public CurrencyRates getCurrencyRates(@PathVariable("date")
	String date) throws IOException
	{
		return service.getCurrencyRates(date);
	}

	@PutMapping
	public CurrencyRates updateCurrencyRates(@RequestBody
	CurrencyRates cur) throws IOException
	{
		return service.updateCurrencyRates(cur, getDateAsStr(LocalDate.now()));
	}

	@PutMapping("/{date}")
	public CurrencyRates updateCurrencyRates(@PathVariable("date")
	String date, @RequestBody
	CurrencyRates cur) throws IOException
	{
		return service.updateCurrencyRates(cur, date);
	}

	@DeleteMapping
	public Map<String, String> removeCurrencyRates() throws NotFoundException
	{
		return service.removeCurrencyRates(getDateAsStr(LocalDate.now()));
	}

	@DeleteMapping("/{date}")
	public Map<String, String> removeCurrencyRates(@PathVariable("date")
	String date) throws NotFoundException
	{
		return service.removeCurrencyRates(date);
	}

}
