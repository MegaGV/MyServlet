package service;

import entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers(int page, int num_every_page);

    Teacher findTeacherById(String id);

    void saveTeacher(Teacher teacher);

    void addTeacher(Teacher teacher);

    void deleteTeacher(String deletes);

    void deleteAll();

    void sort(String sort);

    List<String> getAllID();

    int size();
}
