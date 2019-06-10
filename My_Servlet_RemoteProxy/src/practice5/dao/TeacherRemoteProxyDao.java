package practice5.dao;

import dao.TeacherDao;
import entity.Teacher;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TeacherRemoteProxyDao implements TeacherDao {

    @Override
    public List<Teacher> getTeachers(int page, int num_every_page){
        List<Teacher> teachers = new ArrayList<>();

        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("getTeachers");
            printWriter.println(page);
            printWriter.println(num_every_page);

            String id = bufferedReader.readLine();
            while(!id.equals("")){
                String name = bufferedReader.readLine();
                String college = bufferedReader.readLine();
                String major = bufferedReader.readLine();
                Integer birthday = Integer.valueOf(bufferedReader.readLine());
                Integer salary = Integer.valueOf(bufferedReader.readLine());
                teachers.add(new Teacher(id, name, college, major, birthday, salary));

                id = bufferedReader.readLine();
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    @Override
    public Teacher findTeacherById(String id){
        Teacher teacher = new Teacher();

        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("findTeacherById");
            printWriter.println(id);

            teacher.setId(bufferedReader.readLine());
            teacher.setName(bufferedReader.readLine());
            teacher.setCollege(bufferedReader.readLine());
            teacher.setMajor(bufferedReader.readLine());
            teacher.setBirthday(Integer.valueOf(bufferedReader.readLine()));
            teacher.setSalary(Integer.valueOf(bufferedReader.readLine()));

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    @Override
    public void saveTeacher(Teacher teacher){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("saveTeacher");
            printWriter.println(teacher.getId());
            printWriter.println(teacher.getName());
            printWriter.println(teacher.getCollege());
            printWriter.println(teacher.getMajor());
            printWriter.println(teacher.getBirthday());
            printWriter.println(teacher.getSalary());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeacher(Teacher teacher){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("addTeacher");
            printWriter.println(teacher.getId());
            printWriter.println(teacher.getName());
            printWriter.println(teacher.getCollege());
            printWriter.println(teacher.getMajor());
            printWriter.println(teacher.getBirthday());
            printWriter.println(teacher.getSalary());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(String[] deletes){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("deleteTeacher");
            for(String s : deletes)
                printWriter.println(s);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("deleteAll");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(String sort){
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("sort");
            printWriter.println(sort);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllID(){
        List<String> ids = new ArrayList<>();

        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("getAllID");
            for(int i = 0, size = size(); i < size; i++)
                ids.add(bufferedReader.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ids;
    }

    @Override
    public int size(){
        int size = -1;

        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printWriter.println("size");

            size = Integer.valueOf(bufferedReader.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size;
    }
}
