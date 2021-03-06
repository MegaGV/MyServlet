package dao;

import java.util.List;

import entity.Teacher;

public interface TeacherDao {

    List<Teacher> getTeachers();

    Teacher findTeacherById(String id);

    void saveTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void deleteTeacher(String[] deletes);

    void deleteAll();

    void sort(String sort);
}
