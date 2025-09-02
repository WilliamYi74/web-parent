/**
 * @projectName web-parent
 * @package com.william.domain
 * @className com.william.domain.Student
 */
package com.williamyi.domain;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import lombok.Data;

/**
 * Student
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/9/1 23:16
 */
//@HeadRowHeight(20)
//@ContentRowHeight(20)
//@ColumnWidth(20)
@ExcelIgnoreUnannotated
@Data
public class Student {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty(value = "性别")
    private String genderName;

    private Integer gender;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("学校")
    private String school;

    @ExcelProperty("手机")
    private String phone;

    public Student() {
    }

    public Student(Integer id, String userName, Integer gender, Integer age, String school, String phone) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.school = school;
        this.phone = phone;
    }

    public static class GenderConverter implements Converter<Integer> {

        /**
         * 读到javabean时调用
         *
         * @param context
         * @return
         * @throws Exception
         */
        @Override
        public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
            Object data = context.getReadCellData().getData();
            return data.equals("男") ? 1 : 0;
        }

    }
}