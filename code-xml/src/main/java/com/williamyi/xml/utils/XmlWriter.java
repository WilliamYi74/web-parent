/**
 * @projectName web-parent
 * @package com.williamyi.xml.utils
 * @className com.williamyi.xml.utils.XmlManager
 */
package com.williamyi.xml.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * XmlManager
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/14 16:18
 */
public class XmlWriter {
    private Writer writer;
    private StringBuilder sb;

    public XmlWriter() throws IOException {
        this.sb = new StringBuilder();
    }

    public XmlWriter(String file) throws IOException {
        this(new File(file));
    }

    public XmlWriter(File file) throws IOException {
        this.writer = new PrintWriter(new FileWriter(file));
        this.sb = new StringBuilder();
    }

    public void appendXmlHead() {
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    }

    public void appendLine(String line) {
        sb.append(line).append("\n");
    }

    public <T> void appendOne(T obj) throws IOException {
        Class<?> clazz = obj.getClass();
        String className = clazz.getSimpleName().toLowerCase();
        Field[] declaredFields = clazz.getDeclaredFields();
        sb.append("<").append(className).append(">").append("\n");
        for (Field declaredField : declaredFields) {
            String getter = "get" + String.valueOf(declaredField.getName().charAt(0)).toUpperCase() + declaredField.getName().substring(1);
            try {
                Method declaredMethod = clazz.getDeclaredMethod(getter);
                String fieldName = declaredField.getName();
                Object fieldValue = declaredMethod.invoke(obj);
                sb.append("<").append(fieldName).append(">").append(fieldValue).append("</").append(fieldName).append(">\n");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("getter " + getter + " not exist");
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("call getter " + getter + " error");
            }
        }
        sb.append("</").append(className).append(">\n");
    }

    public <T> void appendList(List<T> list, String listTagName) throws IOException {
        if (writer != null) {
            sb.append("<").append(listTagName).append(">\n");
            for (Object obj : list) {
                appendOne(obj);
            }
            sb.append("</").append(listTagName).append(">\n");
        }
    }

    public void write() throws IOException {
        writer.write(sb.toString());
        writer.flush();
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}