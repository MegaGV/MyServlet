package practice2.entity;

public class Teacher{
    private String id;
    private String name;
    private String college;
    private String major;
    private int birthday;
    private int salary;

    public Teacher(){
        super();
    }

    public Teacher(String id, String name, String college, String major, int birthday, int salary){
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

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setSalary(int salary) {
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

    public int getBirthday() {
        return birthday;
    }

    public int getSalary() {
        return salary;
    }

}