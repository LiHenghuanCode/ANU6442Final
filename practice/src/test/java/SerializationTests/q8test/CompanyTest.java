package SerializationTests.q8test;

import org.junit.Test;
import SerializationPractice.q8serialization.Company;

import java.io.File;

import static org.junit.Assert.*;

/**
 * 
 * @author nanwang
 * 
 * You are allowed to add additional `asserts` and test cases to testify your programs in all test cases.
 *
 */
public class CompanyTest {

	private static final String JSON_FILE = "company.json";
	private static final String PROCESSED_JSON_FILE = "company_processed.json";
	private static final String COMPANY_NAME = "ACT Software";

	@Test
	public void testLoadFromFile1() throws Exception {

		File file = new File(JSON_FILE);

		Company company = Company.loadFromJsonFile(file);
		
		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 8);
	}
	
	@Test
	public void testLoadFromFile2() throws Exception {

		File file = new File(PROCESSED_JSON_FILE);

		Company company = Company.loadFromJsonFile(file);
		
		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 5);
	}

	@Test
	public void testSaveToFile() throws Exception {

		File file = new File(JSON_FILE);

		Company company = Company.loadFromJsonFile(file);

		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 8);
		
		File newfile = new File("new_company.json");
		newfile.delete();

		company.saveToJsonFile(newfile);
		
		assertTrue(newfile.exists());
		
		newfile.delete();
	}

	@Test
	public void testMerge() throws Exception {
		File file = new File(JSON_FILE);

		Company company = Company.loadFromJsonFile(file);

		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 8);
		
		company.mergeEmployees();
		
		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 5);
	}
	
	@Test
	public void testAll() throws Exception {
		File file = new File(JSON_FILE);

		Company company = Company.loadFromJsonFile(file);

		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 8);
		
		company.mergeEmployees();;
		
		assertNotNull(company);
		assertEquals(company.getName(), COMPANY_NAME);
		assertEquals(company.getEmployees().size(), 5);
		
		File newfile = new File("new_company_processed.json");
		newfile.delete();

		company.saveToJsonFile(newfile);
		
		assertTrue(newfile.exists());
				
		newfile.delete();
	}
}
