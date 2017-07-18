package com.nyz.queryEvaluator;

public class Criteria {

	String columnNameOfWhere;
	String value;
	@Override
	public String toString() {
		return "Criteria [columnNameOfWhere=" + columnNameOfWhere + ", value=" + value + ", operatorOfWhere="
				+ operatorOfWhere + "]";
	}
	String operatorOfWhere;
	public String getColumnNameOfWhere() {
		return columnNameOfWhere;
	}
	public void setColumnNameOfWhere(String columnNameOfWhere) {
		this.columnNameOfWhere = columnNameOfWhere;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOperatorOfWhere() {
		return operatorOfWhere;
	}
	public void setOperatorOfWhere(String operatorOfWhere) {
		this.operatorOfWhere = operatorOfWhere;
	}
	
	
}
