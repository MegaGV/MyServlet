package factory;

import service.TeacherService;

public interface ServiceFactory {

    public TeacherService getTeacherService();
}