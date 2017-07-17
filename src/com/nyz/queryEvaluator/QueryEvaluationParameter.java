package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryEvaluationParameter {
	
	Pattern pattern;
	Matcher matcher;
	private String[] columnNames = null;
	private String csvName = null;
	private ArrayList<Criteria> whereClause = null;
	//getter and setter of all variables
	public String[] getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	public String getCsvName() {
		return csvName;
	}
	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}
	public ArrayList<Criteria> getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(ArrayList<Criteria> whereClause) {
		this.whereClause = whereClause;
	}
	public ArrayList<String> getWhereCondition() {
		return whereCondition;
	}
	public void setWhereCondition(ArrayList<String> whereCondition) {
		this.whereCondition = whereCondition;
	}
	public String getCountFunction() {
		return countFunction;
	}
	public void setCountFunction(String countFunction) {
		this.countFunction = countFunction;
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	public String getGroupByColumn() {
		return groupByColumn;
	}
	public void setGroupByColumn(String groupByColumn) {
		this.groupByColumn = groupByColumn;
	}
	private ArrayList<String> whereCondition = null;
	private String sumFunction = null;
	private String countFunction = null;
	private String orderByColumn = null;
	private String groupByColumn = null;
	
	
	
	
	
	
}