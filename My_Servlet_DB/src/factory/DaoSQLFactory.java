package factory;

import dao.TeacherDao;
import practice2.dao.TeacherSQLDao;

public class DaoSQLFactory implements DaoFactory {

    @Override
    public TeacherDao getTeacherDao() {
        return new TeacherSQLDao();
    }

}
