package com.nyz.queryEvaluator;

import java.util.Scanner;

public class QueryEvaluation {

	public static void main(String[] args) {
		QueryEvaluationEngine queryEvaluationEngine = new QueryEvaluationEngine();
		String query;
		Scanner sc = new Scanner(System.in);
		System.out.println("query");
		query = sc.nextLine();
		queryEvaluationEngine.executeQuery(query);
	}

}
