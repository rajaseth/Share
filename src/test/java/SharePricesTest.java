package test.java;

import main.java.SharePrices;

import org.junit.Test;

public class SharePricesTest {
	
	@Test
	public void run() {
		SharePrices sharePrices = new SharePrices();
		sharePrices.run("src/test.csv");
	}
	
}
