/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * A trades application to demonstrate using Spanner.
 *
 * @author Daniel Zou
 */
@SpringBootApplication
public class TradesDriver {

	private static final Log LOGGER = LogFactory.getLog(TradesDriver.class);

	@Autowired
	private TradesRepositoryImpl tradesRepositoryImpl;

	@Autowired
	private TradesTemplate tradesTemplate;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		SpringApplication.run(TradesDriver.class, args);
	}

	@Bean
	@Profile("!test")
	ApplicationRunner applicationRunner() {
		return (args) -> {
				LOGGER.info("Running the Spanner Repository Example.");
				this.tradesRepositoryImpl.runExample();
		};
	}
}
