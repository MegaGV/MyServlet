package factory;

import dao.TeacherDao;
import practice1.dao.TeacherStaticDao;

public class DaoStaticFactory implements DaoFactory {

    @Override
    public TeacherDao getTeacherDao() {
        return new TeacherStaticDao();
    }

}
