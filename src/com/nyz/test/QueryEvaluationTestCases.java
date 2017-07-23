package com.nyz.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nyz.queryEvaluator.CsvFileReader;
import com.nyz.queryEvaluator.QueryEvaluationEngine;

public class QueryEvaluationTestCases {

	static QueryEvaluationEngine queryEvaluationEngine;
	static CsvFileReader csvFileReader;

	@BeforeClass
	public static void init() {
		queryEvaluationEngine = new QueryEvaluationEngine();
	}

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
	
	@Test
	public void withWhereGreaterThanTestCase(){
		
		queryEvaluationEngine.executeQuery("select name from emp where salary > 30000");
		assertNotNull(queryEvaluationEngine);
		
		
	}
	
	@Test
	public void withWhereLessThanTestCase(){
		
		queryEvaluationEngine.executeQuery("select name from emp where salary < 30000");
		assertNotNull(queryEvaluationEngine);
		
		
	}
	
	@Test
	public void withWhereGreaterThanEqualTestCase(){
		
		queryEvaluationEngine.executeQuery("select name,salary from emp where salary >= 30000");
		assertNotNull(queryEvaluationEngine);
		
		
	}
	
	@Test
	public void withWherelessThanEqualTestCase(){
		
		queryEvaluationEngine.executeQuery("select name,salary from emp where salary <= 30000");
		assertNotNull(queryEvaluationEngine);
		
		
	}
	
	@Test
	public void withWhereEqualAndNotEqualTestCase(){
		
		queryEvaluationEngine.executeQuery("select city,name,salary from emp where salary >= 30000 and salary != 38000");
		assertNotNull(queryEvaluationEngine);		
	}
}
