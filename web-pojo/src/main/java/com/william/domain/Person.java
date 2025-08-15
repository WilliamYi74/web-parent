/**
 * @projectName web-parent
 * @package com.williamyi.xml.code_01
 * @className com.william.domain.Person
 */
package com.william.domain;

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