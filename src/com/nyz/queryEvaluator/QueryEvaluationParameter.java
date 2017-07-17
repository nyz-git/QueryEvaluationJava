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
		/*String[] FromSplit = query.split("from");
		pattern = Pattern.compile("select (.*?) from (.*)+?");
		matcher = pattern.matcher(query.trim());
		if (matcher.find()) {
			columnNames = matcher.group(1).split("[\\s,]+");
			String[] csvName = FromSplit[1].split("[\\s.]+");
			this.csvName = csvName[1];
		} else {
			System.out.println("MATCHED FAILED");
		}*/
		
		

		String[] SplitAtFrom = query.split("from");
		
		pattern = Pattern.compile("select (.*?) from (.*)+?");
		matcher = pattern.matcher(query.trim());
		if (matcher.find()) {
			if (matcher.group(1).trim().contains("sum")) {
				pattern = Pattern.compile("(\\(([a-zA-Z]+)\\))");
				matcher = pattern.matcher(matcher.group(1));
				if (matcher.find()) {
					sumFunction = matcher.group(2);
				}

			}
			if (matcher.group(1).trim().contains("count")) {
				pattern = Pattern.compile("(\\(([a-zA-Z\\*]+)\\))");
				matcher = pattern.matcher(matcher.group(1));
				if (matcher.find()) {
					countFunction = matcher.group(2);
				}
			}
			columnNames = matcher.group(1).split("[\\s,]+");
			String[] tableName = SplitAtFrom[1].split("[\\s.]+");
			this.csvName = tableName[1];
		} else {
			System.out.println("NO MATCH");
		}

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
			String[] whereLogicalOperator = whereString.split("\\s+");
			
			for (String s : whereLogicalOperator) {
				if (s.equals("and")) {
					whereCondition.add("and");
				} else if (s.equals("or")) {
					whereCondition.add("or");
				}
			}
			String whereClauseElement[] = whereString.split(" and | or ", 2);
			while (whereClauseElement.length != 1) {
				fillCriteria(whereClauseElement[0].trim());
				whereClauseElement = whereClauseElement[1].split(" and | or ", 2);
			}
			fillCriteria(whereClauseElement[0].trim());

		} // where clause ends here
		return this;
	}// extractParam ends here

	public void fillCriteria(String whereElement) {
		Criteria criteria = new Criteria();
		pattern = Pattern.compile("(.*) ([!=|>=|<=|>|<|=]+) (.*)");
		//pattern=Pattern.compile("(.)([<>|>=|<=|>|<|=]+) (.*)");
		
		matcher = pattern.matcher(whereElement);
		if (matcher.find()) {
			criteria.setColumnNameOfWhere(matcher.group(1).trim());
			criteria.setOperatorOfWhere(matcher.group(2).trim());
			criteria.setValue(matcher.group(3).trim());
		}
		whereClause.add(criteria);
	}

	public void printWhereClause() {
		Iterator itr = whereClause.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	// getter and setter of all variables
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
		return "QueryEvaluationParameter [columnNames=" + Arrays.toString(columnNames) + ", csvName=" + csvName
				+ ", whereClause=" + whereClause + ", whereCondition=" + whereCondition + ", sumFunction=" + sumFunction
				+ ", countFunction=" + countFunction + ", orderByColumn=" + orderByColumn + ", groupByColumn="
				+ groupByColumn + "]";
	}

}
