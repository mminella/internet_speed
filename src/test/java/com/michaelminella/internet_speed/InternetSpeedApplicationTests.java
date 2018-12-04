package com.michaelminella.internet_speed;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

public class InternetSpeedApplicationTests {

	@Test
	public void testCalls() throws Exception{
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
				processOutput.append(readLine + System.lineSeparator());
			}

			process.waitFor();
		}

		String speed = processOutput.toString().trim().replace("Speed: ", "");
		String unit = speed.substring(speed.indexOf(" ") + 1);
		double speedDouble = Double.valueOf(speed.substring(0, speed.indexOf(" ")));

		System.out.println(" speed: " + speedDouble + " unit: " + unit);
	}

}
