package practice1.service;

import java.util.List;

import dao.TeacherDao;
import entity.Teacher;
import factory.Factory;
import service.TeacherService;

public class TeacherServiceImp implements TeacherService {

    @Override
    public List<Teacher> getTeachers()
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.getTeachers();
    }

    @Override
    public Teacher findTeacherById(String id)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.findTeacherById(id);
    }

    @Override
    public void saveTeacher(String id, String name, String college, String major, String birthday, String salary)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.saveTeacher(new Teacher(id, name, college, major, Integer.valueOf(birthday), Integer.valueOf(salary)));
    }

    @Override
    public void addTeacher(String id, String name, String college, String major, String birthday, String salary)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.addTeacher(new Teacher(id, name, college, major, Integer.valueOf(birthday), Integer.valueOf(salary)));
    }

    @Override
    public void deleteTeacher(String deletes){
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        String[] dels = deletes.split(" ");
        dao.deleteTeacher(dels);
    }

    @Override
    public void deleteAll(){
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.deleteAll();
    }

    @Override
    public void sort(String sort){
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.sort(sort);
    }

}
