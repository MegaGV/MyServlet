package factory;

import dao.TeacherDao;
import practice3.dao.TeacherReflectDao;
import practice4.dao.EntityDao;
import practice4.service.EntityImplDao;

public class DaoReflectFactory implements DaoFactory{

    @Override
    public TeacherDao getTeacherDao() {
        return new TeacherReflectDao();
    }

    @Override
    public EntityDao getEntityDao() {
        return new EntityImplDao();
    }
}
