package dao;

import java.util.List;

import entity.Teacher;

public interface TeacherDao {

    List<Teacher> getTeachers(int page, int num_every_page);

    Teacher findTeacherById(String id);

    void saveTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void deleteTeacher(String[] deletes);

    void deleteAll();

    void sort(String sort);

    List<String> getAllID();

    int size();
}
