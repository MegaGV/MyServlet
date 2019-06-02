package factory;

import practice3.service.TeacherServiceImp;
import practice4.service.EntityService;
import practice4.service.EntityServiceImpl;
import service.TeacherService;

public class ServiceFactoryImp implements ServiceFactory {

    @Override
    public TeacherService getTeacherService() {
        return new TeacherServiceImp();
    }

    @Override
    public EntityService getEntityService() {
        return new EntityServiceImpl();
    }
}