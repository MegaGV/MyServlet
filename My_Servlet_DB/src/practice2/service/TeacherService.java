package practice2.service;

import practice2.entity.Teacher;
import practice2.dao.TeacherDao;

import java.util.List;

public class TeacherService {

    public List<Teacher> getTeachers(int page, int num_every_page)
    {
        TeacherDao dao = new TeacherDao();
        return dao.getTeachers(page, num_every_page);
    }

    public void saveTeacher(String id, String name, String college, String major, String birthday, String salary)
    {
        TeacherDao dao = new TeacherDao();
        dao.saveTeacher(new Teacher(id, name, college, major, Integer.valueOf(birthday), Integer.valueOf(salary)));
    }

    public void addTeacher(String id, String name, String college, String major, String birthday, String salary)
    {
        TeacherDao dao = new TeacherDao();
        dao.addTeacher(new Teacher(id, name, college, major, Integer.valueOf(birthday), Integer.valueOf(salary)));
    }

    public void deleteTeacher(String deletes){
        TeacherDao dao = new TeacherDao();
        String[] dels = deletes.split(" ");
        dao.deleteTeacher(dels);
    }

    public void deleteAll(){
        TeacherDao dao = new TeacherDao();
        dao.deleteAll();
    }

    public int size(){
        TeacherDao dao = new TeacherDao();
        return dao.size();
    }

    public List<String> getAllID(){
        TeacherDao dao = new TeacherDao();
        return dao.getAllID();
    }

    public Teacher findTeacherById(String id){
        TeacherDao dao = new TeacherDao();
        return dao.findTeacherById(id);
    }

    public void sort(String sort){
        TeacherDao dao = new TeacherDao();
        dao.sort(sort);
    }
}
