package com.nyz.queryEvaluator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReader {
	private BufferedReader bufferedReader;
	String line = "";
	private String[] headerColumn;

	public String[] fetchHeader(String tableName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\"+tableName+".csv"));
			line = bufferedReader.readLine();
			headerColumn = line.split(",");
		} catch (IOException e) {
			System.out.println("Fetching Header Failed");
		}
		return headerColumn;
	}
}
