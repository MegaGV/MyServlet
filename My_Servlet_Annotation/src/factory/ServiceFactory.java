package factory;

import practice4.service.EntityService;
import service.TeacherService;

public interface ServiceFactory {
    public TeacherService getTeacherService();
    public EntityService getEntityService();
}