package practice3.dao;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import practice3.entity.Teacher;

public class TeacherDao {

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

    public List<Teacher> getTeachers(int page, int num_every_page)
    {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Statement stmt = getStatement();
            String sql = "select * from " + "Teacher" + " order by " + sort + " limit "  + (page-1)*num_every_page + "," + num_every_page;
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()) {
                Class clazz = Class.forName("practice3.entity.Teacher");
                Object obj = clazz.newInstance();
                Method method = clazz.getMethod("setId", String.class);
                method.invoke(obj, resultSet.getString("id"));

                Method method1 = clazz.getMethod("setName", String.class);
                method1.invoke(obj, resultSet.getString("name"));

                Method method2 = clazz.getMethod("setCollege", String.class);
                method2.invoke(obj, resultSet.getString("college"));

                Method method3 = clazz.getMethod("setMajor", String.class);
                method3.invoke(obj, resultSet.getString("major"));

                Method method4 = clazz.getMethod("setBirthday", int.class);
                method4.invoke(obj, resultSet.getInt("birthday"));

                Method method5 = clazz.getMethod("setSalary", int.class);
                method5.invoke(obj, resultSet.getInt("salary"));

                teachers.add((Teacher)obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void saveTeacher(Teacher teacher) {
        try{
            Statement stmt = getStatement();

            Class clazz = teacher.getClass();
            StringBuilder sql = new StringBuilder();
            StringBuilder judge = new StringBuilder();
            sql.append("update ").append(clazz.getSimpleName()).append(" set ");
            judge.append("where ");
            for(Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(teacher);
                if(field.getName().equals("id")) {
                    judge.append(field.getName()).append("=").append(value).append(",");
                }
                else {
                    if (value.getClass().equals(String.class))
                        sql.append(field.getName()).append("=\"").append(value).append("\"").append(",");
                    else
                        sql.append(field.getName()).append("=").append(value).append(",");
                }
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(" ");
            judge.deleteCharAt(judge.length()-1);
            sql.append(judge.toString());

            stmt.execute(sql.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher) {
        try {
            Statement stmt = getStatement();

            Class clazz = teacher.getClass();
            StringBuilder sql = new StringBuilder();
            sql.append("insert into ").append(clazz.getSimpleName()).append("(");
            for(Field field : clazz.getDeclaredFields()) {
                sql.append(field.getName()).append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(") values(");
            for(Field field : clazz.getDeclaredFields())
            {
                field.setAccessible(true);
                Object value = field.get(teacher);
                if(value.getClass().equals(String.class))
                    sql.append("\"").append(value).append("\"").append(",");
                else
                    sql.append(value).append(",");
            }
            sql.deleteCharAt(sql.length()-1);
            sql.append(")");

            stmt.execute(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(String[] deletes){
        try {
            Statement stmt = getStatement();
            for(String del : deletes) {
                String sql = "delete from " + "Teacher" + " where id=\"" + del + "\"";
                stmt.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(){
        try {
            Statement stmt = getStatement();
            String sql = "delete from " + "Teacher";
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size(){
        try {
            Statement stmt = getStatement();
            String sql = "select COUNT(*) from " + "Teacher";
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
        try {
            Statement stmt = getStatement();
            String sql = "select id from " + "Teacher";
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
        try {
            Statement stmt = getStatement();
            String sql = "select * from " + "Teacher" + " where id=" + id;
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
