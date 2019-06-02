package practice3.service;

import dao.TeacherDao;
import entity.Teacher;
import factory.Factory;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImp implements TeacherService {

    @Override
    public List<Teacher> getTeachers(int page, int num_every_page)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.getTeachers(page, num_every_page);
    }

    @Override
    public Teacher findTeacherById(String id)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.findTeacherById(id);
    }

    @Override
    public void saveTeacher(Teacher teacher)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.saveTeacher(teacher);
    }

    @Override
    public void addTeacher(Teacher teacher)
    {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        dao.addTeacher(teacher);
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

    @Override
    public List<String> getAllID() {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.getAllID();
    }

    @Override
    public int size() {
        TeacherDao dao = Factory.getInstance().getDaoFactory().getTeacherDao();
        return dao.size();
    }

}
