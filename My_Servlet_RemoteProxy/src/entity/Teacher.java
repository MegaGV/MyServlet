package entity;

public class Teacher{
    private String id;
    private String name;
    private String college;
    private String major;
    private Integer birthday;
    private Integer salary;

    public Teacher(){
        super();
    }

    public Teacher(String id, String name, String college, String major, Integer birthday, Integer salary){
        super();
        this.id = id;
        this.name = name;
        this.college = college;
        this.major = major;
        this.birthday = birthday;
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public String getMajor() {
        return major;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public Integer getSalary() {
        return salary;
    }

}