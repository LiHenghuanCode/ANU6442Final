package Serialization.TreeCollect;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
The goal of this task it to write a program that loads/stores a list of trees in XML format. `TreeCollection.java` class contains a
list of `Tree` instances. Each tree has its own `kind`, which needs to be saved as an attribute of XML node. Additionally, each tree
can have three possible properties: `dimension`, `color` and `types`. `dimension` property has two integer attributes: `diameter` and
`height`. `types` property has a list of `type` elements. Note that not every tree has all three properties. Some properties of trees
may be missing (for example, see the test cases in TreesTest.java). You job is to implement the below methods in `TreeCollection.java`:

 * `saveToFile`
 * `loadFromFile`

Note that these methods should take into account the available properties of a given tree. You are allowed to add additional asserts
and test cases to test your solutions. You can use `getAttribute(String name)` and `setAttribute(String name, String value)` of `Element`
class to get and set the attributes of XML node. **Please upload `TreeCollection.java` file to wattle!**
 *
 */
public class TreeCollection {

	private final List<Tree> trees;

	public TreeCollection(List<Tree> trees) {
		this.trees = trees;
	}

	public List<Tree> getTrees() {
		return trees;
	}

	/**
	 * Implement this method to save the list of trees to XML file
	 * HINT: `setAttribute(String name, String value)` in `Element` can be used to set `kind`, `diameter` and `height` properties
	 * @param file
	 */
	public void saveToFile(File file) {
		//START YOUT CODE
		try {
			// 1. 创建 DOM 文档
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// 2. 创建根节点 <trees>
			Element root = doc.createElement("trees");
			doc.appendChild(root);

			// 3. 遍历每一棵树对象
			for (Tree t : trees) {
				// <tree kind="Oak">
				Element treeElem = doc.createElement("tree");
				treeElem.setAttribute("kind", t.getKind());
				root.appendChild(treeElem);

				// 如果有 dimension，则添加 <dimension diameter="..." height="..."/>
				if (t.getDimension() != null) {
					Element dimElem = doc.createElement("dimension");
					dimElem.setAttribute("diameter", t.getDimension().getDiameter().toString());
					dimElem.setAttribute("height", t.getDimension().getHeight().toString());
					treeElem.appendChild(dimElem);
				}

				// 如果有 color，则添加 <color>xxx</color>
				if (t.getColor() != null) {
					Element colorElem = doc.createElement("color");
					colorElem.appendChild(doc.createTextNode(t.getColor()));
					treeElem.appendChild(colorElem);
				}

				// 如果有 types，则添加 <types><type>t1</type>...</types>
				if (t.getTypes() != null && !t.getTypes().isEmpty()) {
					Element typesElem = doc.createElement("types");
					for (String type : t.getTypes()) {
						Element typeElem = doc.createElement("type");
						typeElem.appendChild(doc.createTextNode(type));
						typesElem.appendChild(typeElem);
					}
					treeElem.appendChild(typesElem);
				}
			}

			// 4. 写出到文件（带缩进）
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(doc), new StreamResult(file));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//END YOUT CODE
	}

	/**
	 * Implement this method to load from the XML file to create a `TreeCollection`
	 * HINT: `getAttribute(String name)`in `Element` can be used to get `kind`, `diameter` and `height` properties
	 * @param file
	 * @return
	 */
	public static TreeCollection loadFromFile(File file) {
		//START YOUT CODE
		List<Tree> trees = new ArrayList<>();

		try {
			// 1. 解析 XML 文件为 Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			// 2. 获取所有 <tree> 节点
			NodeList treeNodes = doc.getElementsByTagName("tree");

			for (int i = 0; i < treeNodes.getLength(); i++) {
				Element treeElem = (Element) treeNodes.item(i);
				Tree tree = new Tree();

				// 读取 kind 属性
				tree.withKind(treeElem.getAttribute("kind"));

				// 读取 dimension 节点（可选）
				NodeList dimList = treeElem.getElementsByTagName("dimension");
				if (dimList.getLength() > 0) {
					Element dimElem = (Element) dimList.item(0);
					Integer diameter = Integer.parseInt(dimElem.getAttribute("diameter"));
					Integer height = Integer.parseInt(dimElem.getAttribute("height"));
					tree.withDimension(new Dimension(diameter, height));
				}

				// 读取 color 节点（可选）
				NodeList colorList = treeElem.getElementsByTagName("color");
				if (colorList.getLength() > 0) {
					String color = colorList.item(0).getTextContent();
					tree.withColor(color);
				}

				// 读取 types 节点（可选）
				NodeList typesList = treeElem.getElementsByTagName("types");
				if (typesList.getLength() > 0) {
					Element typesElem = (Element) typesList.item(0);
					NodeList typeNodes = typesElem.getElementsByTagName("type");
					for (int j = 0; j < typeNodes.getLength(); j++) {
						String typeValue = typeNodes.item(j).getTextContent();
						tree.addType(typeValue);
					}
				}

				trees.add(tree);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new TreeCollection(trees);
		//END YOUT CODE	
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof TreeCollection) {
			TreeCollection treeCollection = (TreeCollection) o;
			return this.trees.equals(treeCollection.trees);
		}

		return false;
	}
}
