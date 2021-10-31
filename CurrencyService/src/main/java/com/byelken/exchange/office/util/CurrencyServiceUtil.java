package com.byelken.exchange.office.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: CurrencyServiceUtil.java
 */
public interface CurrencyServiceUtil
{
	static String getDateAsStr(LocalDate date)
	{
		return date.format(DateTimeFormatter.BASIC_ISO_DATE);
	}

	static String createId(String date)
	{
		return DigestUtils.md5DigestAsHex(date.getBytes());
	}

	static String createId(LocalDate date)
	{
		return DigestUtils.md5DigestAsHex(getDateAsStr(date).getBytes());
	}

	static <T> T convertJsonToObject(String json, Class<T> type) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(json, type);
	}

}
