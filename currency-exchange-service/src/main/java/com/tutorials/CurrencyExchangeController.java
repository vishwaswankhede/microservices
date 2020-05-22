package com.tutorials;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment environment;

	@Autowired
	ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}",exchangeValue);
		
		return exchangeValue;
	}

	/*
	 * @GetMapping("/currency-exchange/currency") public List<ExchangeValue>
	 * retriveExchangeValuefromRepository( ) {
	 * 
	 * List<ExchangeValue> findAll = repository.findAll();
	 * 
	 * return findAll; }
	 */
}
