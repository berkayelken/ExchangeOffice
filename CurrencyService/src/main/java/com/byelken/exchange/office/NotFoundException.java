package com.byelken.exchange.office;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: NotFoundException.java
 */
public class NotFoundException extends Exception
{
	private static final long serialVersionUID = -2555687960152370513L;

	public NotFoundException(String message)
	{
		super(message);
	}
}
