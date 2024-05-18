package org.nsu.oop.task5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XMLMessageParser {

    public static String createMessage(String commandName, String... params) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element root = document.createElement("command");
            root.setAttribute("name", commandName);

            for (int i = 0; i < params.length; i += 2) {
                Element param = document.createElement(params[i]);
                param.setTextContent(params[i + 1]);
                root.appendChild(param);
            }

            document.appendChild(root);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(
                    new javax.xml.transform.dom.DOMSource(document),
                    new javax.xml.transform.stream.StreamResult(outputStream)
            );

            return outputStream.toString("UTF-8");
        } catch (ParserConfigurationException | IOException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMessageParameter(String message, String parameterName) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(message.getBytes("UTF-8")));

            NodeList nodeList = document.getElementsByTagName(parameterName);
            if (nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            }
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }

        return null;
    }
}