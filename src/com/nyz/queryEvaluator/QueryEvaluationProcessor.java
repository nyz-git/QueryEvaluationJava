package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.Map;

public class QueryEvaluationProcessor {
	
	public Map<Integer,ArrayList<String>> selectQueryProcessor(QueryEvaluationParameter queryEvaluationParameter, String[] headers, CsvFileReader csvFileReader) {
		String csvName = queryEvaluationParameter.getCsvName();
		
		boolean isColumnNames = queryEvaluationParameter.getColumnNames()!=null;
		boolean isCsvName = queryEvaluationParameter.getCsvName()!=null;
		boolean isGroupByColumn = queryEvaluationParameter.getGroupByColumn()!=null;
		boolean isOrderByColumn = queryEvaluationParameter.getOrderByColumn()!=null;
		boolean isWhereClause = queryEvaluationParameter.getWhereClause()!=null;
		
//		if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {
//
//			csvFileReader.fetchData(queryEvaluationParameter);
//		}
//		else {
//			/*System.out.println("in type 2 select");
//			System.out.println(queryEvaluationParameter.getColumnNames());
//			System.out.println();*/
//			csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),csvFileReader.fetchHeader(csvName));
//		}
		if (isColumnNames && isCsvName) {
			
			if (!isGroupByColumn && !isWhereClause && !isOrderByColumn) {
				
				if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*") /*|| queryParameter.getCountFunction().trim().equals("*")*/) {

					csvFileReader.fetchData(queryEvaluationParameter);
				} else {
					
					csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()));
				}
			} else if (isWhereClause && !isOrderByColumn && !isGroupByColumn) {
				if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {
					
					//csvFileReader.fetchData(csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()),csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()), queryEvaluationParameter.getWhereClause());
				} else {
					
					//csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()), queryEvaluationParameter.getWhereClause(),queryEvaluationParameter.getWhereCondition());
				}
			}
		} 
		return null;
}
}
