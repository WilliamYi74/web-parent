/**
 * @projectName web-parent
 * @package com.williamyi.xml.code_01
 * @className com.williamyi.xml.code_01.Student
 */
package com.williamyi.xml.code_01;

import lombok.Data;

/**
 * Student
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/14 16:34
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private String school;
    private String address;
    private Integer age;
}