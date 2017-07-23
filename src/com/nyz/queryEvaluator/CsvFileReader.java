package com.nyz.queryEvaluator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvFileReader {
	private BufferedReader bufferedReader;
	String line = "";
	private String[] headerColumn;
	private double value;
	private double lineValue;
	String[] lineData;

	// for displaying output for the query (all fetchdata() will call this
	// method for printing )
	public void display(Map<Integer, ArrayList<String>> rowSet) {
		for (Map.Entry<Integer, ArrayList<String>> m : rowSet.entrySet()) {
			ArrayList<String> data = m.getValue();
			for (String s : data) {
				System.out.print(s + "\t\t");
			}
			System.out.println();

		}
	}

	// for fetching header of the csv file
	public String[] fetchHeader(String csvName) {
		try {
			bufferedReader = new BufferedReader(new FileReader("D:\\" + csvName + ".csv"));
			line = bufferedReader.readLine();
			headerColumn = line.split(",");
		} catch (IOException e) {
			System.out.println("Fetching Header Failed");
		}
		return headerColumn;
	}

	// fetching data for select all columns with no where clause
	public void fetchData(QueryEvaluationParameter queryEvaluationParameter) {
		// TODO Auto-generated method stub
		Map<Integer, ArrayList<String>> rowSet = new LinkedHashMap<Integer, ArrayList<String>>();
		int counterForRowID = 0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				lineData = line.split(",");
				ArrayList<String> rowData = new ArrayList<>();
				for (String s : lineData) {
					rowData.add(s);
				}
				rowSet.put(counterForRowID, rowData);
				counterForRowID++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		display(rowSet);
	}

	// fetching data for select multiple/selected columns with no where clause
	public Map<Integer, ArrayList<String>> fetchData(String selectedColumns[], String[] headers) {
		Map<Integer, ArrayList<String>> rowSet = new LinkedHashMap<Integer, ArrayList<String>>();
		int rowCount = 0;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				lineData = line.split(",");
				ArrayList<String> rowData = new ArrayList<>();
				for (int i = 0; i < selectedColumns.length; i++) {
					for (int j = 0; j < headers.length; j++) {
						if (selectedColumns[i].trim().equals(headers[j].trim())) {
							rowData.add(lineData[j]);
						}
					}
				}

				rowSet.put(rowCount, rowData);
				rowCount++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		display(rowSet);
		return null;
	}

	// Method to Obtain Selected Column Indexes
	public ArrayList<Integer> fetchIndexes(String selectedColumns[], String[] headers) {
		ArrayList<Integer> index = new ArrayList<>();
		for (int i = 0; i < selectedColumns.length; i++) {
			for (int j = 0; j < headers.length; j++) {
				if (selectedColumns[i].trim().equals(headers[j].trim())) {
					index.add(j);
				}
			}
		}
		return index;
	}

	// Method to Obtain WhereClause Column Indexes
	public ArrayList<Integer> getWhereClauseIndexes(ArrayList<Criteria> whereClause, String[] headers) {
		ArrayList<Integer> indexes = new ArrayList<>();
		for (Criteria criteria : whereClause) {
			for (int i = 0; i < headers.length; i++) {
				if (criteria.getColumnNameOfWhere().trim().equals(headers[i])) {
					indexes.add(i);
				}
			}

		}
		return indexes;
	}

	private boolean evaluateWhereCondition(String operator, String whereClauseValue, String lineData) {
		boolean flag = false;
		switch (operator) {
		case ">":
			value = Double.parseDouble(whereClauseValue);
			lineValue = Double.parseDouble(lineData);
			if (lineValue > value) {
				flag = true;
			}
			break;

		case "<":
			value = Double.parseDouble(whereClauseValue);
			lineValue = Double.parseDouble(lineData);
			if (lineValue < value) {
				flag = true;
			}
			break;

		case "=":
			String stringValue = (whereClauseValue);
			String stringLineValue = (lineData);
			if (stringLineValue.equals(stringValue)) {
				flag = true;
			}
			break;

		case ">=":
			value = Double.parseDouble(whereClauseValue);
			lineValue = Double.parseDouble(lineData);
			if (lineValue >= value) {
				flag = true;
			}
			break;

		case "<=":
			value = Double.parseDouble(whereClauseValue);
			lineValue = Double.parseDouble(lineData);
			if (lineValue <= value) {
				flag = true;
			}
			break;

		case "!=":
			String newValue = (whereClauseValue);
			String newLineValue = (lineData);
			if (!(newLineValue.equals(newValue))) {
				flag = true;
			}
			break;
		}
		return flag;
	}

	public Map<Integer, ArrayList<String>> fetchData(String selectedColumns[], String[] headers,
			ArrayList<Criteria> whereClause) {
		System.out.println("where");
		Map<Integer, ArrayList<String>> rowSet = new LinkedHashMap<Integer, ArrayList<String>>();
		int rowCount = 0;
		ArrayList<Integer> indexes = fetchIndexes(selectedColumns, headers);
		try {
			while ((line = bufferedReader.readLine()) != null) {
				lineData = line.split(",");
				ArrayList<String> rowData = new ArrayList<>();
				boolean flag = false;
				for (int i = 0; i < selectedColumns.length; i++) {
					for (int j = 0; j < headers.length; j++) {
						if (selectedColumns[i].trim().equals(headers[j].trim())) {

							if (whereClause.get(0).getColumnNameOfWhere().equals(headers[j])) {
								flag = evaluateWhereCondition(whereClause.get(0).getOperatorOfWhere(),
										whereClause.get(0).getValue(), lineData[j]);
							}
						}
					}
				}
				if (flag) {
					for (Integer i : indexes) {
						rowData.add(lineData[i]);
					}
				}
				if (flag) {
					rowSet.put(rowCount, rowData);
					rowCount++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		display(rowSet);
		return null;
	}

	public Map<Integer, ArrayList<String>> fetchData(String selectedColumns[], String headers[], ArrayList<Criteria> whereClause,
			ArrayList<String> whereCondition) {
		// TODO Auto-generated method stub
		System.out.println("Where clause with condition");
		Map<Integer, ArrayList<String>> rowSet = new LinkedHashMap<Integer, ArrayList<String>>();
		int rowCount = 0;
		ArrayList<Integer> indexes = fetchIndexes(selectedColumns, headers);
		ArrayList<Integer> whereIndexes = getWhereClauseIndexes(whereClause, headers);

		try {
			while ((line = bufferedReader.readLine()) != null) {
				String[] lineData = line.split(",");
				ArrayList<String> rowData = new ArrayList<>();
				ArrayList<Boolean> flags = new ArrayList<>();

				boolean flag = false;

				if (whereCondition.size() > 0) {
					for (int counter = 0; counter <= whereCondition.size(); counter++) {
						if (whereClause.get(counter).getColumnNameOfWhere().equals(headers[whereIndexes.get(counter)])) {
							flags.add(evaluateWhereCondition(whereClause.get(counter).getOperatorOfWhere(),
									whereClause.get(counter).getValue(), lineData[whereIndexes.get(counter)]));
						}
					}
					for (int counter = 0; counter < whereCondition.size(); counter++) {
						if (whereCondition.get(counter).equals("and")) {
							flag = flags.get(counter) && flags.get(counter + 1);
						} else if (whereCondition.get(counter).equals("or")) {
							flag = flags.get(counter) || flags.get(counter + 1);
						}
					}

				} else {
					if (whereClause.get(0).getColumnNameOfWhere().trim().equals(headers[whereIndexes.get(0)].trim())) {
						flag = evaluateWhereCondition(whereClause.get(0).getOperatorOfWhere(), whereClause.get(0).getValue(),
								lineData[whereIndexes.get(0)]);
					}
				}
				if (flag) {
					for (Integer i : indexes) {
						rowData.add(lineData[i]);
					}

					if (flag) {
						rowSet.put(rowCount, rowData);
						rowCount++;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		display(rowSet);
		return null;
	}
}
