package factory;

import dao.TeacherDao;
import practice5.dao.TeacherRemoteProxyDao;

public class DaoRemoteProxyFactory implements DaoFactory {

    @Override
    public TeacherDao getTeacherDao() {
        return new TeacherRemoteProxyDao();
    }
}
