/*
 * Copyright 2018 the original author or authors.
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
package com.michaelminella.internet_speed.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Michael Minella
 */
@Component
public class FastDotComTest {

	@Autowired
	private JdbcOperations jdbcTemplate;

	@Scheduled(fixedRate = 1800000)
	public void runTest() throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder("fast-speedtest",
				"YXNkZmFzZGxmbnNkYWZoYXNkZmhrYWxm");

		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();
		StringBuilder processOutput = new StringBuilder();

		try (BufferedReader processOutputReader = new BufferedReader(
				new InputStreamReader(process.getInputStream()));)
		{
			String readLine;

			while ((readLine = processOutputReader.readLine()) != null)
			{
				processOutput.append(readLine).append(System.lineSeparator());
			}

			process.waitFor();
		}

		String speed = processOutput.toString().trim().replace("Speed: ", "");
		String unit = speed.substring(speed.indexOf(" ") + 1);
		double speedDouble = Double.valueOf(speed.substring(0, speed.indexOf(" ")));

		this.jdbcTemplate.update("INSERT INTO FAST_SPEEDS (TIME_STAMP, SPEED, UNIT) VALUES (?, ?, ?)", new Date(), speedDouble, unit);
	}
}
