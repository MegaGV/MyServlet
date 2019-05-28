package practice2.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.TeacherDao;
import entity.Teacher;

public class TeacherSQLDao implements TeacherDao {

    private static Connection getConnection()  throws Exception{
        String driverName = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/myservlet";
        String userName = "root";
        String password = "123456";

        Class.forName(driverName);

        return DriverManager.getConnection(url, userName, password);
    }
    private static Statement getStatement() throws Exception{
        Connection  connection = getConnection();
        return connection.createStatement();
    }

    static String sort = "id";

    public List<Teacher> getTeachers(int page, int num_every_page) {
        List<Teacher> teachers = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "select * from teachers order by " + sort + " limit "  + (page-1)*num_every_page + "," + num_every_page;
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()) {
                teachers.add(new Teacher(resultSet.getString("id"), resultSet.getString("name"),
                                resultSet.getString("college"), resultSet.getString("major"),
                                resultSet.getInt("birthday"), resultSet.getInt("salary")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void saveTeacher(Teacher teacher) {
        Statement stmt = null;
        try{
            stmt = getStatement();
            String sql = "update teachers set name=\"" + teacher.getName() + "\", college=\"" + teacher.getCollege() + "\", major=\"" +
                    teacher.getMajor() + "\", birthday=" + teacher.getBirthday() + ", salary=" + teacher.getSalary() +
                    " where id=" + teacher.getId() + "";
            stmt.execute(sql);

            System.out.println(sql);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void addTeacher(Teacher teacher) {
        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "insert into teachers(id, name, college, major, birthday, salary) values(\"" +
                    teacher.getId() + "\", \"" + teacher.getName() + "\", \"" + teacher.getCollege() + "\", \"" +
                    teacher.getMajor() + "\", " + teacher.getBirthday() + ", " + teacher.getSalary() +  ")";
            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(String[] deletes){
        Statement stmt = null;
        try {
            for(String del : deletes) {
                stmt = getStatement();
                String sql = "delete from teachers where id=\"" + del + "\"";
                stmt.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(){

        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "delete from teachers";
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size(){
        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "select COUNT(*) from teachers ";
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getAllID(){
        List<String> ids = new ArrayList<>();

        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "select id from teachers ";
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next())
                ids.add(resultSet.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public Teacher findTeacherById(String id){
        Teacher teacher = new Teacher();
        Statement stmt = null;
        try {
            stmt = getStatement();
            String sql = "select * from teachers where id=" + id;
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next()){
                teacher.setId(resultSet.getString("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setCollege(resultSet.getString("college"));
                teacher.setMajor(resultSet.getString("major"));
                teacher.setBirthday(resultSet.getInt("birthday"));
                teacher.setSalary(resultSet.getInt("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacher;
    }

    public void sort(String newsort){ sort = newsort; }

}
