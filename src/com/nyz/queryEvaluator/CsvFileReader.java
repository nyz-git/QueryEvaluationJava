package com.nyz.queryEvaluator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvFileReader {
	private BufferedReader bufferedReader;
	String line = "";
	private String[] headerColumn;

	public String[] fetchHeader(String csvName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\"+csvName+".csv"));
			line = bufferedReader.readLine();
			headerColumn = line.split(",");
		} catch (IOException e) {
			System.out.println("Fetching Header Failed");
		}
		return headerColumn;
	}

	public void fetchData(QueryEvaluationParameter queryEvaluationParameter) {
		// TODO Auto-generated method stub
		Map<Integer, ArrayList<String>> rowSet = new LinkedHashMap<Integer, ArrayList<String>>();
		int counterForRowID = 0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] lineData = line.split(",");
				ArrayList<String> rowData = new ArrayList<>();
				for (String s : lineData) {
					rowData.add(s);
				}
				rowSet.put(counterForRowID, rowData);
				counterForRowID++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Map.Entry<Integer, ArrayList<String>> m : rowSet.entrySet()) {
			ArrayList<String> data = m.getValue();
			for (String s : data) {
				System.out.print(s + "\t\t");
			}
			System.out.println();

		}

	}
}
