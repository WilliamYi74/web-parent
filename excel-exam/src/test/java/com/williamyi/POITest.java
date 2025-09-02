/**
 * @projectName web-parent
 * @package com.williamyi
 * @className com.williamyi.POITest
 */
package com.williamyi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.williamyi.domain.Student;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * POITest
 * @description
 * @author william
 * @date 2025/9/2 21:22
 * @version 1.0
 */
public class POITest {

    private List<Student> studentList;

    @BeforeEach
    public void init() {
        studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student stu = new Student();
            stu.setId(i + 1);
            stu.setUserName("williamyi小宝贝哈哈哈哈" + i);
            stu.setAge(i + 2);
            stu.setGenderName((i & 1) == 1 ? "男" : "女");
            stu.setPhone("123456" + i);
            stu.setSchool("深圳大学");
            studentList.add(stu);
        }
    }

    /**
     * 导入一个工作簿
     *
     * @throws IOException
     */
    @Test
    void poiWriteFile() throws IOException {
        // 在内存中创建一个.xlsx 工作簿
        XSSFWorkbook sheets = new XSSFWorkbook();

        // 创建一个sheet标签页
        XSSFSheet sheet1 = sheets.createSheet("第一个sheet");
        // 创建行和列 行和列索引都是从0开始
        // 创建第0行为表头行
        XSSFRow head = sheet1.createRow(0);
        head.createCell(0).setCellValue("ID");
        head.createCell(1).setCellValue("姓名");
        head.createCell(2).setCellValue("性别");
        head.createCell(3).setCellValue("年龄");
        head.createCell(4).setCellValue("学校");
        head.createCell(5).setCellValue("电话");
        // 填充每一行每一列的数据
        for (int i = 0; i < studentList.size(); i++) {
            int rowNum = i + 1; // 第一行是表头 不能覆盖了 从第二行开始
            XSSFRow row = sheet1.createRow(rowNum);
            row.createCell(0).setCellValue(studentList.get(i).getId());
            row.createCell(1).setCellValue(studentList.get(i).getUserName());
            row.createCell(2).setCellValue(studentList.get(i).getAge());
            row.createCell(3).setCellValue(studentList.get(i).getGender());
            row.createCell(4).setCellValue(studentList.get(i).getSchool());
            row.createCell(5).setCellValue(studentList.get(i).getPhone());
            // 设置列尺寸自动适应
            sheet1.autoSizeColumn(1);
        }
        sheets.write(new FileOutputStream("/Users/suk/Documents/java-workspace/web-parent/学生薄.xlsx"));
    }

    /**
     * 读取一个工作簿
     * @throws IOException
     */
    @Test
    void poiReadFile() throws IOException {
        // 获取工作簿的对象
        XSSFWorkbook sheets = new XSSFWorkbook("/Users/suk/Documents/java-workspace/web-parent/学生薄.xlsx");
        // 获取第一个sheet标签页
        XSSFSheet sheet1 = sheets.getSheetAt(0);
        List<Student> studentList = new ArrayList<>();
        // 遍历每一行非空行
        for (int i = 0; i < sheet1.getPhysicalNumberOfRows(); i++) {
            if (i == 0) {
                continue;
            }
            XSSFRow row = sheet1.getRow(i);
            if (row == null) continue;
            int id = (int) row.getCell(0).getNumericCellValue(); // 返回的是一个double
            String userName = row.getCell(1).getStringCellValue();
            int age = (int) row.getCell(2).getNumericCellValue();
            int gender = (int) row.getCell(3).getNumericCellValue();
            String school = row.getCell(4).getStringCellValue();
            String phone = row.getCell(5).getStringCellValue();
            Student student = new Student(id, userName, age, gender, school, phone);
            studentList.add(student);
        }
        studentList.forEach(System.out::println);
    }

    @Test
    void easyExcelWrite() {
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        contentCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        EasyExcel.write("/Users/suk/Documents/java-workspace/web-parent/学生薄.xlsx", Student.class)
                .registerWriteHandler(new HorizontalCellStyleStrategy(null, contentCellStyle))
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("第一个sheet")
                .doWrite(studentList);
    }
}