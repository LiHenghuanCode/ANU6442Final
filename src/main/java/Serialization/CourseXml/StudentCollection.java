package Serialization.CourseXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nanwang
 *
The goal of this task is to write a program that loads/stores a list of students in XML format. `StudentCollection.java` class contains
a list of `Student` instances. Each student has his/her `age` and `name`, which need to be saved as attributes of XML node. Additionally,
each student can have three possible properties: `height`, `weight` and `courses`. `courses` property contains a list of `course` elements.
Each course has a course `name` attribute and a `grade` value. Note that not every student has all three properties. Some properties of students may be missing
(for example, see the test cases in StudentsTest.java). You job is to implement the below two methods in `StudentCollection.java`:

* `saveToFile`
* `loadFromFile`

Note that these methods should take into account the available properties of a given student. You are allowed to add additional asserts
and test cases to test your solutions. You can use `getAttribute(String name)` and `setAttribute(String name, String value)` of `Element`
class to get and set the attributes of XML node. **Please upload `StudentCollection.java` to wattle!**
 */
public class StudentCollection {

	private final List<Student> students;

	public StudentCollection(List<Student> students) {
		this.students = students;
	}

	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Implement this method to save the list of students to XML file
	 * HINT: `setAttribute(String name, String value)` in `Element` can be used to set `name` and `age` properties
	 * @param file
	 */
	public void saveToFile(File file) {
		//START YOUR CODE
		try {
			// 创建 XML 文档构造器
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// 创建根节点 <students>
			Element root = doc.createElement("students");
			doc.appendChild(root);

			// 遍历所有学生
			for (Student s : students) {
				// 创建 <student> 节点并设置属性 name, age
				Element stuElem = doc.createElement("student");
				stuElem.setAttribute("name", s.getName());
				stuElem.setAttribute("age", s.getAge().toString());

				// 如果有身高则添加 <height> 节点
				if (s.getHeight() != null) {
					Element heightElem = doc.createElement("height");
					heightElem.setTextContent(s.getHeight().toString());
					stuElem.appendChild(heightElem);
				}

				// 如果有体重则添加 <weight> 节点
				if (s.getWeight() != null) {
					Element weightElem = doc.createElement("weight");
					weightElem.setTextContent(s.getWeight().toString());
					stuElem.appendChild(weightElem);
				}

				// 如果有课程列表则添加 <courses> 节点
				if (s.getCourses() != null && !s.getCourses().isEmpty()) {
					Element coursesElem = doc.createElement("courses");

					// 每门课创建 <course> 节点，包含 name 属性与分数值
					for (Course c : s.getCourses()) {
						Element courseElem = doc.createElement("course");
						courseElem.setAttribute("name", c.getName());
						courseElem.setTextContent(c.getGrade().toString());
						coursesElem.appendChild(courseElem);
					}
					stuElem.appendChild(coursesElem);
				}

				// 将该学生节点加入根节点
				root.appendChild(stuElem);
			}

			// 使用 Transformer 输出为格式化 XML 文件
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 缩进格式化
			transformer.transform(new DOMSource(doc), new StreamResult(file));

		} catch (Exception e) {
			e.printStackTrace();
		//END YOUR CODE
	}}

	/**
	 * Implement this method to load from the XML file to create a `StudentCollection`
	 * HINT: `getAttribute(String name)`in `Element` can be used to get `name` and `age` properties
	 * @param file
	 * @return
	 */
	public static StudentCollection loadFromFile(File file) {
		//START YOUR CODE
		List<Student> studentList = new ArrayList<>();
		try {
			// 解析 XML 文档
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			// 获取所有 <student> 节点
			NodeList studentNodes = doc.getElementsByTagName("student");
			for (int i = 0; i < studentNodes.getLength(); i++) {
				Element sElem = (Element) studentNodes.item(i);

				// 获取学生基础属性 name 和 age
				Student s = new Student()
						.withName(sElem.getAttribute("name"))
						.withAge(Integer.parseInt(sElem.getAttribute("age")));

				// 遍历子节点，读取 height、weight、courses 等信息
				NodeList children = sElem.getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {
					Node n = children.item(j);
					if (n instanceof Element) {
						Element e = (Element) n;

						// 根据标签名判断类型
						switch (e.getTagName()) {
							case "height":
								s.withHeight(Integer.parseInt(e.getTextContent()));
								break;
							case "weight":
								s.withWeight(Integer.parseInt(e.getTextContent()));
								break;
							case "courses":
								// 解析 <courses> 下的所有 <course> 节点
								NodeList courseNodes = e.getElementsByTagName("course");
								for (int k = 0; k < courseNodes.getLength(); k++) {
									Element cElem = (Element) courseNodes.item(k);
									String cname = cElem.getAttribute("name");
									Integer grade = Integer.parseInt(cElem.getTextContent());
									s.addCourse(new Course(cname, grade));
								}
								break;
						}
					}
				}

				// 将学生加入集合
				studentList.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回新的 StudentCollection
		return new StudentCollection(studentList);
		//END YOUR CODE
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof StudentCollection) {
			StudentCollection studentCollection = (StudentCollection) o;
			return this.students.equals(studentCollection.students);
		}

		return false;
	}
}
