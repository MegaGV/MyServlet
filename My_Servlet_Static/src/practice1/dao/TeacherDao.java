package practice1.dao;

import java.util.*;
import java.io.*;

import practice1.entity.Teacher;

public class TeacherDao {

    private static Map<String, Teacher> teachers = new LinkedHashMap<>();

    static
    {
        File file = new File("C:\\Users\\59565\\IdeaProjects\\My_Servlet\\web\\data.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                String[] data = tempString.split(" ");
                teachers.put(data[0], new Teacher(data[0], data[1], data[2], data[3], Integer.valueOf(data[4]), Integer.valueOf(data[5])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(){
        File file = new File("C:\\Users\\59565\\IdeaProjects\\My_Servlet\\web\\data.txt");
        try {
            FileWriter writer = new FileWriter(file, false);
            List<Teacher> teacherList = getAllTeacher();
            for (Teacher teacher : teacherList){
                writer.write(teacher.getId() + " " + teacher.getName() + " " + teacher.getCollege() + " "
                        + teacher.getMajor() + " " + teacher.getBirthday() + " " + teacher.getSalary() + " \r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeacher()
    {
        return new ArrayList<>(teachers.values());
    }

    public Teacher findTeacherById(String id)
    {
        return teachers.get(id);
    }

    public void saveTeacher(Teacher teacher) {
        Teacher origin = findTeacherById(teacher.getId());
        origin.setId(teacher.getId());
        origin.setName(teacher.getName());
        origin.setCollege(teacher.getCollege());
        origin.setMajor(teacher.getMajor());
        origin.setBirthday(teacher.getBirthday());
        origin.setSalary(teacher.getSalary());
        writeToFile();
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
        writeToFile();
    }

    public void deleteTeacher(String[] deletes){
        for(String del : deletes){ teachers.remove(del); }
        writeToFile();
    }

    public void deleteAll(){
        teachers.clear();
        writeToFile();
    }

    public void sort(String sort){
        List<Teacher> teacherList = getAllTeacher();

        if(sort.equals("id")) {
            for (int i = 0; i < teacherList.size() - 1; i++) {
                for (int j = i; j < teacherList.size(); j++) {
                    if (teacherList.get(i).getId().compareTo(teacherList.get(j).getId()) > 0)
                        Collections.swap(teacherList, i, j);
                }
            }
        }
        else if (sort.equals("age")) {
            for (int i = 0; i < teacherList.size() - 1; i++) {
                for (int j = i; j < teacherList.size(); j++) {
                    if (teacherList.get(i).getBirthday() > teacherList.get(j).getBirthday())
                        Collections.swap(teacherList, i, j);
                }
            }
        }

        teachers.clear();
        for(Teacher teacher : teacherList){
            teachers.put(teacher.getId(), teacher);
        }

        writeToFile();
    }

    /*private String checkTeacher(String id, String name, String college, String major, String birthday, String salary){
        if (id.equals(""))
            return "Id不能为空";
        else if (isNumeric(id))
            return "Id必须为数字";
        else if (name.equals(""))
            return "姓名不能为空";
        else if (college.equals(""))
            return "学院不能为空";
        else if (major.equals(""))
            return "专业不能为空";
        else if ( birthday.equals(""))
            return "生日不能为空";
        else if (isNumeric(birthday))
            return "生日必须为数字";
        else if (salary.equals(""))
            return "薪水不能为空";
        else if (isNumeric(salary))
            return "薪水必须为数字";
        else
            return "";
    }

    private static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }*/
}
