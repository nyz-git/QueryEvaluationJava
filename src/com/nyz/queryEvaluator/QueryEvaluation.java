package com.nyz.queryEvaluator;

import java.util.Scanner;

public class QueryEvaluation {

	private static Scanner sc;
	private static QueryEvaluationEngine queryEvaluationEngine;

	public static void main(String[] args) {
		queryEvaluationEngine = new QueryEvaluationEngine();
		String query;
		sc = new Scanner(System.in);
		query = sc.nextLine();
		queryEvaluationEngine.executeQuery(query);
	}

}
