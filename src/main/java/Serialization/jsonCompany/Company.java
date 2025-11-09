package Serialization.jsonCompany;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author nanwang
 * 
The goal of this task is to write a program that loads/stores a college in JSON format. The `Company.java` has a company name and a 
list of `Employee` instances. Each employee has an employee name and a list of skills. There are some duplicates of the employees in 
the `company.json` file. Your job is to implement the three methods in the `Company` class:

* `loadFromJsonFile`
* `mergeEmployees`
* `saveToJsonFile`

in order to load from the given `company.json` file, merge the skills of the employees with the same names, and write the processed 
data into a JSON file, which should be the same as the given `company_processed.json` file. A `CompanyTest.java` is given to help you 
test your solutions. You are allowed to add additional asserts and test cases to test your solutions. **Please upload `Company.java` 
file to wattle!**
 */
public class Company {

	private String name;
	private List<Employee> employees;

	/**
	 * Implement this method to load json data from file to create a company 
	 * 
	 * @param file
	 * @return
	 */
	public static Company loadFromJsonFile(File file) {

		// START YOUR CODE
		try (FileReader reader = new FileReader(file)) {
			Gson gson = new Gson();
			return gson.fromJson(reader, Company.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		// END YOUR CODE
	}

	/**
	 * Implement this method to merge the skills of the employees with the same
	 * names. Please refer to the given `company_processed.json`, which presents the
	 * data after merging.
	 */
	public void mergeEmployees() {

		// START YOUR CODE
		if (employees == null) return;

		Map<String, Employee> map = new HashMap<>();

		for (Employee e : employees) {
			// 如果不存在此员工名，直接放入 map
			if (!map.containsKey(e.getName())) {
				// 深拷贝技能列表，防止引用冲突
				map.put(e.getName(), new Employee(e.getName(), new ArrayList<>(e.getSkills())));
			} else {
				// 若已存在 → 合并技能（去重）
				Employee existing = map.get(e.getName());
				for (String skill : e.getSkills()) {
					if (!existing.getSkills().contains(skill)) {
						existing.getSkills().add(skill);
					}
				}
			}
		}

		// 更新合并后的员工列表
		employees = new ArrayList<>(map.values());
		// END YOUR CODE
	}

	/**
	 * Implement this method to save this company object into a JSON file
	 * 
	 * @param file
	 */
	public void saveToJsonFile(File file) {

		// START YOUR CODE
		try (FileWriter writer = new FileWriter(file)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(this, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// END YOUR CODE
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof Company) {
			Company company = (Company) o;

			return this.name.equals(company.name) && this.employees.equals(company.employees);
		}

		return false;
	}
}
