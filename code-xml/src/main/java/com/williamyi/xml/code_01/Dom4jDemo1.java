/**
 * @projectName web-parent
 * @package com.williamyi.xml.code_01
 * @className com.williamyi.xml.code_01.Dom4jDemo1
 */
package com.williamyi.xml.code_01;

import com.william.domain.Person;
import com.william.domain.Student;
import com.william.utils.XmlWriter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dom4jDemo1
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/14 11:37
 */
public class Dom4jDemo1 {
    public static void main(String[] args) {
        // 创建SaxReader对象
        SAXReader saxReader = new SAXReader();
        try {
            InputStream resourceAsStream = Dom4jDemo1.class.getClassLoader().getResourceAsStream("person.xml");
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> list = rootElement.elements("person");
            List<Person> personList = new ArrayList<>(list.size());
            for (Element element : list) {
                Person person = new Person();
                Integer id = Integer.valueOf(element.attribute("id").getValue());
                String name = element.elementText("name");
                Integer age = Integer.valueOf(element.elementText("age"));
                String gender = element.elementText("gender");
                person.setId(id);
                person.setName(name);
                person.setAge(age);
                person.setGender(gender);
                personList.add(person);
            }
            XmlWriter xmlWriter = new XmlWriter("/Users/edy/Documents/list.xml");
            xmlWriter.appendXmlHead();
            xmlWriter.appendLine("<data>");
            xmlWriter.appendList(personList, "personList");
            Student student = new Student();
            student.setId(1);
            student.setName("小明");
            student.setSchool("明德小学");
            student.setAddress("黄沙街");
            student.setAge(12);
            xmlWriter.appendOne(student);
            xmlWriter.appendLine("</data>");
            xmlWriter.write();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            System.out.println("operate xml error");
        }
    }

}