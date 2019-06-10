package factory;

import dao.TeacherDao;

public interface DaoFactory {
    public TeacherDao getTeacherDao();
}
