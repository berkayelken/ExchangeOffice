package com.byelken.exchange.office.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author 		: Berkay.Yelken 
 * @createdOn 	: 31-10-2021
 * @project 	: CurrencyService
 * @file 		: MongoConfig.java
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.byelken.exchange.office.repository")
public class MongoConfig extends AbstractMongoClientConfiguration
{
	@Value("${spring.data.mongodb.host:'localhost'}")
	private String host;

	@Value("${spring.data.mongodb.port:27017}")
	private Integer port;

	@Value("${spring.data.mongodb.database:'localdb'}")
	private String dbName;

	@Override
	protected String getDatabaseName()
	{
		return dbName;
	}

	@Override
	public MongoClient mongoClient()
	{
		String connectionUri = "mongodb://" + host + ":" + port + "/" + dbName;
		ConnectionString connectionString = new ConnectionString(connectionUri);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();

		return MongoClients.create(mongoClientSettings);
	}

	@Override
	public Collection<String> getMappingBasePackages()
	{
		return Collections.singleton("com.byelken.exchange.office.model");
	}
}
