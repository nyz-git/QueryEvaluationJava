package com.nyz.queryEvaluator;

import java.util.Scanner;

public class QueryEvaluation {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		QueryEvaluationProcessor queryEvaluationProcessor = new QueryEvaluationProcessor();
		CsvFileReader csvFileReader = new CsvFileReader();
		QueryEvaluationParameter queryEvaluationParameter = new QueryEvaluationParameter();
		System.out.println("query");
		String query = sc.nextLine();
		queryEvaluationProcessor.selectQueryProcessor(queryEvaluationParameter.extractParam(query),
				csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()), csvFileReader);
	}

}
