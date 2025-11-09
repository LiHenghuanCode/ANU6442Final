package Serialization.jsonCollege;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author nanwang
 * 
 The goal of this task is to write a program that loads/stores students of some college in JSON format. The `College.java` class has 
 a college name and a list of `Student` instances. Each student has a student name and a list of courses. There are some duplicates 
 of the students in the `college.json` file. You job is to implement the three methods in the `College.java` class:

* `loadFromJsonFile`  
* `mergeStudents`
* `saveToJsonFile`

in order to load from the given `college.json` file, merge the courses of the students with the same names and write the processed 
data into a JSON file, which should be the same as the given `college_processed.json` file. A `CollegeTest.java` is given to help you 
test your solutions. You are allowed to add additional asserts and test cases to test your solutions. **Please upload `College.java` 
file to wattle!**
 */
public class College {

	private String name;
	private List<Student> students;

	/**
	 * Implement this method to load json data from file to create a college
	 * 
	 * @param file
	 * @return
	 */
	public static College loadFromJsonFile(File file) {

		// START YOUR CODE
		try (FileReader reader = new FileReader(file)) {
			Gson gson = new Gson();
			Type collegeType = new TypeToken<College>() {}.getType();
			return gson.fromJson(reader, collegeType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}


		// END YOUR CODE

	}

	/**
	 * Implement this method to merge the courses of the students with the same
	 * names. Please refer to the given `college_processed.json`, which presents the
	 * data after merging.
	 */
	public void mergeStudents() {

		// START YOUR CODE
		if (students == null || students.isEmpty()) return;

		Map<String, List<String>> map = new HashMap<>();

		for (Student s : students) {
			// 如果学生名已存在，直接追加课程
			if (map.containsKey(s.getName())) {
				List<String> existingCourses = map.get(s.getName());
				for (String course : s.getCourses()) {
					// 防止重复课程
					if (!existingCourses.contains(course)) {
						existingCourses.add(course);
					}
				}
			} else {
				// 新学生则新建列表
				map.put(s.getName(), new ArrayList<>(s.getCourses()));
			}
		}

		// 构建合并后的学生列表
		List<Student> merged = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			merged.add(new Student(entry.getKey(), entry.getValue()));
		}

		this.students = merged;


		// END YOUR CODE
	}

	/**
	 * Implement this method to write this college into a json file
	 * 
	 * @param file
	 */
	public void saveToJsonFile(File file) {

		// START YOUR CODE
		try (FileWriter writer = new FileWriter(file)) {
			Gson gson = new Gson();
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof College) {
			College college = (College) o;

			return this.name.equals(college.name) && this.students.equals(college.students);
		}

		return false;
	}
}
