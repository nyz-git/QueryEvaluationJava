package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.Map;

public class QueryEvaluationProcessor {
	public Map<Integer,ArrayList<String>> selectQueryProcessor(QueryEvaluationParameter queryEvaluationParameter, String[] headers, CsvFileReader csvFileReader) {
		
		if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {

			csvFileReader.fetchData(queryEvaluationParameter);
		}
		return null;
}
}
