package factory;

import practice5.service.TeacherServiceImp;
import service.TeacherService;

public class ServiceFactoryImp implements ServiceFactory {

    @Override
    public TeacherService getTeacherService() {
        TeacherService teacherService= new TeacherServiceImp();
        return teacherService;
    }
}