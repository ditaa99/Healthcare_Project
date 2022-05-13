package healthcare;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 *  XML Reader and Writer
 */
public class XmlReaderWriter {


    /**
     * @param patients patients list
     * @param filepath file path to xml file
     */
    public void saveUsersToXml(ArrayList<Patient> patients, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("patients");
            document.appendChild(rootElement);

            for (Patient patient : patients) {
                Element patientElement = document.createElement("patient");
                rootElement.appendChild(patientElement);
                createChildElement(document, patientElement, "name", patient.getName());
                createChildElement(document, patientElement, "age", String.valueOf(patient.getAge()));
                createChildElement(document, patientElement, "gender", String.valueOf(patient.getGender()));
                createChildElement(document, patientElement, "Condition", String.valueOf(patient.getCondition()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

    /**
     * @param filepath to xml file
     * @return array list of patients from the xml file
     */
    public ArrayList<Patient> readUsersFromXml(String filepath) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            /*System.out.println(rootElement.getNodeName());
            System.out.println(rootElement.getNodeType());
            System.out.println("Element node short value: " + Node.ELEMENT_NODE);
            System.out.println("Text node short value: " + Node.TEXT_NODE);*/
            //System.out.println(rootElement.getTextContent());
            NodeList childNodesList = rootElement.getChildNodes();
            /*System.out.println(childNodesList.getLength());
            System.out.println("---------------");*/
            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //System.out.println(node.getNodeName());
                    //System.out.println(node.getTextContent());
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", age = "", gender = "", Condition = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        /*System.out.println(childNodesOfUserTag.item(j).getNodeType()
                                  + " " + childNodesOfUserTag.item(j).getNodeName());*/
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "age" -> age = childNodesOfUserTag.item(j).getTextContent();
                                case "gender" -> gender = childNodesOfUserTag.item(j).getTextContent();
                                case "Condition" -> Condition = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    patients.add(new Patient(name, Integer.parseInt(age),Gender.valueOf(gender),
                            Condition));
                }
            }
            //System.out.println("Number of element nodes: " + numberOfElementNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    /**
     * @param filepath xml file path
     * @param tagName
     * @return number of occurence xml element same with defined tag name
     */
    public int numberOfOccurrence(String filepath, String tagName) {
        int numberOfOccurrence = 0;
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);
            Element rootElement = document.getDocumentElement();
            NodeList tagNameNodeList = rootElement.getElementsByTagName(tagName);
            numberOfOccurrence = tagNameNodeList.getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfOccurrence;
    }

    
}
