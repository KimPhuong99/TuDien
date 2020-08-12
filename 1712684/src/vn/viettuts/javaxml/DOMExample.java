/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.viettuts.javaxml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DOMExample {
   
    public DOMExample()
    {
    }
    public static List<Student> readListStudents(String s) {
        List<Student> listStudents = new ArrayList<>();
        Student student = null;
 
        try {
            // đọc file input.xml
            File inputFile = new File(s);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
 
            // in phần tử gốc ra màn hình
            //System.out.println("Phần tử gốc:"
              //      + doc.getDocumentElement().getNodeName());
 
            // đọc tất cả các phần tử có tên thẻ là "student"
            NodeList nodeListStudent = doc.getElementsByTagName("record");
 
            // duyệt các phần tử student
            for (int i = 0; i < nodeListStudent.getLength(); i++) {
                // tạo đối tượng student
                student = new Student();
                // đọc các thuộc tính của student
                Node nNode = nodeListStudent.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    student.setTu(eElement.getElementsByTagName("word")
                            .item(0).getTextContent());
                    student.setNghia(eElement.getElementsByTagName("meaning")
                            .item(0).getTextContent());
                }
                // add đối tượng student vào listStudents
                listStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }
}