package com.nyz.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nyz.queryEvaluator.CsvFileReader;
import com.nyz.queryEvaluator.QueryEvaluationEngine;

public class QueryEvaluationTestCases {

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */

	static QueryEvaluationEngine queryEvaluationEngine;
	static CsvFileReader csvFileReader;

	@BeforeClass
	public static void init() {
		queryEvaluationEngine = new QueryEvaluationEngine();
	}

	/*@Test
	public void headerfetcher() {
		String header[] = csvFileReader.fetchHeader("emp");
		//assertNotNull(csvFileReader);
		assertEquals(5, header.length);
	}*/
	
	@Test
	public void fetchAllDataWithoutWhere() {
		queryEvaluationEngine.executeQuery("select * from emp");
		assertNotNull(queryEvaluationEngine);
	}

	@Test
	public void selectColumnsWithoutWhereTestCase(){
		
		queryEvaluationEngine.executeQuery("select city,dept,name from emp.csv");
		assertNotNull(queryEvaluationEngine);
		
	}
}
