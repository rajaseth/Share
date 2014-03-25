package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SharePrices {

	public static void main(String[] args) {
		SharePrices obj = new SharePrices();
		obj.run("src/test.csv");
	}

	public void run(String csvFile) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int count = 0;

		try {
			Map<Integer, CompanyPojo> map = new HashMap<Integer, CompanyPojo>();
			CompanyPojo pojo = null;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] str = line.split(cvsSplitBy);
				if (count == 0) {
					for (int i = 2; i < str.length; i++) {
						pojo = new CompanyPojo();
						pojo.setCompanyName(str[i].trim());
						map.put(i, pojo);
					}
				} else {
					for (int i = 2; i < str.length; i++) {
						pojo = new CompanyPojo();
						pojo = map.get(i);
						if (pojo.getPrice() < Long.valueOf(str[i].trim())) {
							pojo.setYear(Integer.parseInt(str[0].trim()));
							pojo.setMonth(str[1].trim());
							pojo.setPrice(Long.parseLong(str[i].trim()));
							map.put(i, pojo);
						}
					}
				}
				count++;
			}
			for (Map.Entry<Integer, CompanyPojo> entry : map.entrySet()) {
				System.out.println(entry.getValue().getCompanyName() + ", "
						+ entry.getValue().getYear() + ", "
						+ entry.getValue().getMonth() + ", "
						+ entry.getValue().getPrice());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}