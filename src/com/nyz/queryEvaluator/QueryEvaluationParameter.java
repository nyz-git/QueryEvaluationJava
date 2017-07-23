package com.nyz.queryEvaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryEvaluationParameter {

	Pattern pattern;
	Matcher matcher;
	private String[] columnNames = null;
	private String csvName = null;
	private ArrayList<Criteria> whereClause = null;
	String[] splitAtWhere = null;
	private ArrayList<String> whereCondition = null;
	private String sumFunction = null;
	private String countFunction = null;
	private String orderByColumn = null;
	private String groupByColumn = null;
	String[] splitAtGroupBy = null;

	public QueryEvaluationParameter extractParam(String query) {
		// TODO Auto-generated method stub

		String[] splitAtOrderBy = query.split("order by");

		if (splitAtOrderBy.length > 1) {
			orderByColumn = splitAtOrderBy[1].trim();

		}

		splitAtGroupBy = splitAtOrderBy[0].split("group by");

		if (splitAtGroupBy.length > 1) {
			groupByColumn = splitAtGroupBy[1].trim();

		}

		splitAtWhere = splitAtGroupBy[0].split("where");

		if (splitAtWhere.length > 1) {

			whereClause = new ArrayList<>();
			whereCondition = new ArrayList<>();

			String whereString = splitAtWhere[1].trim();
			String[] whereCond = whereString.split("\\s+");

			for (String s : whereCond) {
				if (s.equals("or")) {
					whereCondition.add("or");
				} else if (s.equals("and")) {
					whereCondition.add("and");
				}
			}
			String whereClauseElement[] = whereString.split(" and | or ", 2);
			initializeCriteria(whereClauseElement[0].trim());
			
			while (whereClauseElement.length != 1) {
				initializeCriteria(whereClauseElement[0].trim());
				whereClauseElement = whereClauseElement[1].split(" and | or ", 2);
			}
			

		} // end of where clause

		String[] splitAtFrom = query.split("from");
		pattern = Pattern.compile("select (.*?) from (.*)+?");
		matcher = pattern.matcher(query.trim());
		if (matcher.find()) {
			columnNames = matcher.group(1).split("[\\s,]+");
			String[] csvName = splitAtFrom[1].split("[\\s.]+");
			this.csvName = csvName[1];
		} else {
			System.out.println("NO MATCH");
		}
		return this;
	}// extractParam ends

	public void initializeCriteria(String whereElement) {
		Criteria criteria = new Criteria();
		pattern = Pattern.compile("(.*) ([!=|>=|<=|>|<|=]+) (.*)");

		matcher = pattern.matcher(whereElement);
		if (matcher.find()) {
			criteria.setColumnNameOfWhere(matcher.group(1).trim());
			criteria.setOperatorOfWhere(matcher.group(2).trim());
			criteria.setValue(matcher.group(3).trim());
		}
		whereClause.add(criteria);
	}

	/* --------GETTERS AND SETTER -------------------------- */
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

	@Override
	public String toString() {
		return "QueryEvaluationParameter [columnNames=" + Arrays.toString(columnNames) + ", csvName : " + csvName
				+ ", whereClause : " + whereClause + ", whereCondition : " + whereCondition + ", sumFunction : "
				+ sumFunction + ", countFunction : " + countFunction + ", orderByColumn : " + orderByColumn
				+ ", groupByColumn : " + groupByColumn + "]";
	}
}
