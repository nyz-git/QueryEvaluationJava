package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.Map;

public class QueryEvaluationProcessor {

	public Map<Integer, ArrayList<String>> selectQueryProcessor(QueryEvaluationParameter queryEvaluationParameter,
			String[] headers, CsvFileReader csvFileReader) {
		// String csvName = queryEvaluationParameter.getCsvName();

		boolean isColumnNames = queryEvaluationParameter.getColumnNames() != null;
		boolean isCsvName = queryEvaluationParameter.getCsvName() != null;
		boolean isGroupByColumn = queryEvaluationParameter.getGroupByColumn() != null;
		boolean isOrderByColumn = queryEvaluationParameter.getOrderByColumn() != null;
		boolean isWhereClause = queryEvaluationParameter.getWhereClause() != null;
		boolean isWhereCondition = queryEvaluationParameter.getWhereCondition() != null;
		// if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*"))
		// {
		//
		// csvFileReader.fetchData(queryEvaluationParameter);
		// } else {
		// System.out.println("in type 2 select");
		// System.out.println();
		// csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),
		// csvFileReader.fetchHeader(csvName));
		// }
		System.out.println("column : " + isColumnNames);
		System.out.println("csv : " + isCsvName);
		System.out.println("where condition : " + isWhereCondition);
		System.out.println("where clause : " + isWhereClause);
		System.out.println("order by : " + isOrderByColumn);
		System.out.println("group by : " + isGroupByColumn);

		if (isColumnNames && isCsvName) {

			if (!isGroupByColumn && !isWhereClause && !isOrderByColumn) {

				if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {

					csvFileReader.fetchData(queryEvaluationParameter);
				} else {

					csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),
							csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()));
				}
			} else if (isWhereCondition && isWhereClause) {

				if (queryEvaluationParameter.getColumnNames()[0].trim().equals("*")) {

					csvFileReader.fetchData(csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()),
							csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()),
							queryEvaluationParameter.getWhereClause());
				} else {

					csvFileReader.fetchData(queryEvaluationParameter.getColumnNames(),
							csvFileReader.fetchHeader(queryEvaluationParameter.getCsvName()),
							queryEvaluationParameter.getWhereClause(), queryEvaluationParameter.getWhereCondition());
				}
				/*
				 * else if (!isWhereClause ) { System.out.println(""); }
				 */
			}

		}
		return null;
	}

}
