package JsonClass;

import java.sql.Date;

public class UserInformation {
    //获取学生信息的json对象
    private String meg;
    private long id;
    private String name;
    private String gender;
    private int age;
    private  String birthday;
    private String enrollemt;
    private String class_name;
    private String department;
    private String teacher;
    private String lastLogTime;

    public UserInformation(String meg){
        this.meg=meg;
    }

    public UserInformation(){}

    public String getMeg() {
        return meg;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public  String getBirthday() {
        return birthday;
    }

    public  String getEnrollemt() {
        return enrollemt;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getDepartment() {
        return department;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getLastLogTime() {
        return lastLogTime;
    }

    public void setMeg(String meg) {

        this.meg = meg;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday( String birthday) {
        this.birthday = birthday;
    }

    public void setEnrollemt(String enrollemt) {
        this.enrollemt = enrollemt;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setLastLogTime(String lastLogTime) {
        this.lastLogTime = lastLogTime;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "meg='" + meg + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", enrollemt=" + enrollemt +
                ", class_name='" + class_name + '\'' +
                ", department='" + department + '\'' +
                ", teacher='" + teacher + '\'' +
                ", lastLogTime=" + lastLogTime +
                '}';
    }
}