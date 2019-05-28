package service;

import entity.Teacher;
import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    Teacher findTeacherById(String id);

    void saveTeacher(String id, String name, String college, String major, String birthday, String salary);

    void addTeacher(String id, String name, String college, String major, String birthday, String salary);

    void deleteTeacher(String deletes);

    void deleteAll();

    void sort(String sort);
}
