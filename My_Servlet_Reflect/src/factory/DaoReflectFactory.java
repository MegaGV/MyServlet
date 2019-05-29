package factory;

import dao.TeacherDao;
import practice3.dao.TeacherReflectDao;

public class DaoReflectFactory implements DaoFactory{

    @Override
    public TeacherDao getTeacherDao() {
        return new TeacherReflectDao();
    }
}
