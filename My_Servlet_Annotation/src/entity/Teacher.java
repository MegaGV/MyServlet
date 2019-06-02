package entity;

import annotation.Entity;
import annotation.Field;

@Entity(tableName="teacherTable",label="教师")
public class Teacher{
    @Field(isPK=true,label="Id")
    private String id;

    @Field(label="姓名")
    private String name;

    @Field(label="学院")
    private String college;

    @Field(label="专业")
    private String major;

    @Field(label="生日")
    private Integer birthday;

    @Field(label="薪水")
    private Integer salary;

    public Teacher(){ super(); }

    public Teacher(String id, String name, String college, String major, Integer birthday, Integer salary){
        super();
        this.id = id;
        this.name = name;
        this.college = college;
        this.major = major;
        this.birthday = birthday;
        this.salary = salary;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setCollege(String college) { this.college = college; }

    public void setMajor(String major) { this.major = major; }

    public void setBirthday(Integer birthday) { this.birthday = birthday; }

    public void setSalary(Integer salary) { this.salary = salary; }

    public String getId(){ return id; }

    public String getName() { return name; }

    public String getCollege() { return college; }

    public String getMajor() { return major; }

    public Integer getBirthday() { return birthday; }

    public Integer getSalary() { return salary; }

}