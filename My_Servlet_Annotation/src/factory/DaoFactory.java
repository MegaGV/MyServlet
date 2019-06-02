package factory;

import dao.TeacherDao;
import practice4.dao.EntityDao;

public interface DaoFactory {
    public TeacherDao getTeacherDao();
    public EntityDao getEntityDao();
}
