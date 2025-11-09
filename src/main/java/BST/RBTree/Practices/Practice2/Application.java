package BST.RBTree.Practices.Practice2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Application {

	public RBTree<Integer, Person> createTree(List<Person> personList) {

		RBTree<Integer, Person> tree = new RBTree<>();

		// ########## YOUR CODE STARTS HERE ##########
		if (personList == null || personList.isEmpty()) {
			return tree; // 返回空树，防止空指针
		}

		for (Person p : personList) {
			tree.insert(p.getId(), p);
		}
		// ########## YOUR CODE ENDS HERE ##########

		return tree;
	}

	public List<Person> readCsv(String fileName) {

		List<Person> list = new LinkedList<>();

		// ########## YOUR CODE STARTS HERE ##########
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine(); // 跳过表头
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if (parts.length == 4) {
					Integer id = Integer.parseInt(parts[0].trim());
					String name = parts[1].trim();
					Integer age = Integer.parseInt(parts[2].trim());
					String occupation = parts[3].trim();
					list.add(new Person(id, name, age, occupation));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		// ########## YOUR CODE ENDS HERE ##########

		return list;
	}

	public void saveXML(RBTree<Integer, Person> tree) {

		if (tree == null) {
			return;
		}

		List<Node> list = tree.levelTraversal();

		// ########## YOUR CODE STARTS HERE ##########
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// 根节点 <people>
			Element root = doc.createElement("people");
			doc.appendChild(root);

			for (Node n : list) {
				Person p = (Person) n.getData();

				Element personElem = doc.createElement("person");

				Element idElem = doc.createElement("id");
				idElem.setTextContent(p.getId().toString());
				personElem.appendChild(idElem);

				Element nameElem = doc.createElement("name");
				nameElem.setTextContent(p.getName());
				personElem.appendChild(nameElem);

				Element ageElem = doc.createElement("age");
				ageElem.setTextContent(p.getAge().toString());
				personElem.appendChild(ageElem);

				Element occElem = doc.createElement("occupation");
				occElem.setTextContent(p.getOccupation());
				personElem.appendChild(occElem);

				root.appendChild(personElem);
			}

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

			transformer.transform(new DOMSource(doc), new StreamResult(new File("people.xml")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// ########## YOUR CODE ENDS HERE ##########
	}

}
