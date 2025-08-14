/**
 * @projectName web-parent
 * @package com.williamyi.xml.code_01
 * @className com.williamyi.xml.code_01.Person
 */
package com.williamyi.xml.code_01;

import lombok.Data;

/**
 * Person
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/14 11:46
 */
@Data
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
}