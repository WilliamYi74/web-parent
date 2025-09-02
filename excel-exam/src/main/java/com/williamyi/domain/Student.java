/**
 * @projectName web-parent
 * @package com.william.domain
 * @className com.william.domain.Student
 */
package com.williamyi.domain;


/**
 * Student
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/9/1 23:16
 */
public class Student {
    private Integer id;
    private String userName;
    private Integer gender;
    private Integer age;
    private String school;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}