package com.nyz.queryEvaluator;

public class QueryEvaluationEngine {

	public void executeQuery(String query) {
		// TODO Auto-generated method stub
		QueryEvaluationProcessor queryEvaluationProcessor = new QueryEvaluationProcessor();
		CsvFileReader csvFileReader = new CsvFileReader();
		QueryEvaluationParameter queryEvaluationParameter = new QueryEvaluationParameter();
		
		System.out.println(queryEvaluationParameter.extractParam(query));
		
		queryEvaluationProcessor.selectQueryProcessor(queryEvaluationParameter.extractParam(query),
				csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()), csvFileReader);
	}

}
