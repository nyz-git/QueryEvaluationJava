package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.Map;

public class QueryEvaluationProcessor {
	
	public Map<Integer,ArrayList<String>> selectQueryProcessor(QueryEvaluationParameter queryEvaluationParameter, String[] headers, CsvFileReader csvFileReader) {
		String csvName = queryEvaluationParameter.getCsvName();
		if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {

			csvFileReader.fetchData(queryEvaluationParameter);
		}
		else {
			/*System.out.println("in type 2 select");
			System.out.println(queryEvaluationParameter.getColumnNames());
			System.out.println();*/
			csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),csvFileReader.fetchHeader(csvName));
		}
		return null;
}
}
