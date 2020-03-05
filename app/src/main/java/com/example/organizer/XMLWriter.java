package com.example.organizer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class XMLWriter {

    public static void writeToXML(FileOutputStream fileOutputStream, Memento memento) throws Exception
    {
        /*
        id
        time
        title
        content
         */
        try {
            fileOutputStream.write(memento.getId().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(memento.getTime().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(memento.getTitle().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.write(memento.getContent().getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.close();
            System.out.println("dziala_zapis");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//        Document document = documentBuilder.parse("C:\\Users\\adria\\AndroidStudioProjects\\Organizer\\app\\src\\main\\assets\\mementos.xml");
//        Element root = document.getDocumentElement();
//
//        Element newMemento = document.createElement("memento");
//        Element time = document.createElement("time");
//        time.appendChild(document.createTextNode(memento.getTime()));
//        newMemento.appendChild(time);
//
//        Element title = document.createElement("title");
//        time.appendChild(document.createTextNode(memento.getTitle()));
//        newMemento.appendChild(title);
//
//        Element content = document.createElement("content");
//        time.appendChild(document.createTextNode(memento.getContent()));
//        newMemento.appendChild(content);
//
//        root.appendChild(newMemento);
//        DOMSource source = new DOMSource(document);
//
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        StreamResult result = new StreamResult("mementos.xml");
//        transformer.transform(source, result);

    }
}