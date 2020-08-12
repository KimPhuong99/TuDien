/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.viettuts.javaxml;

/**
 *
 * @author Admin
 */
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import java.io.*;
import java.util.List;
 
public class DOMCreateXMLExample {
     public DOMCreateXMLExample()
    {
    }
    public static int writeListStudents(List<Student> listStudents, String file) {
 
        try {
            DocumentBuilderFactory dbFactory = 
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
 
            // tạo phần tử gốc có tên class
            Element rootElement = doc.createElement("dictionary");
            // thêm thuộc tính totalStudents vào thẻ class
            doc.appendChild(rootElement);
            for(Student student: listStudents)
            {
                Element student1 = doc.createElement("record");
                rootElement.appendChild(student1);
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(student.getTu()));
                student1.appendChild(word);
                Element mean = doc.createElement("meaning");
                mean.appendChild(doc.createTextNode(student.getNghia()));
                student1.appendChild(mean);
            }
            // ghi nội dung vào file XML
            TransformerFactory transformerFactory = 
            TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(
                    new File(file));
            transformer.transform(source, result);
            return 1;
            // ghi kết quả ra console để kiểm tra
            //StreamResult consoleResult = new StreamResult(System.out);
           // transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
