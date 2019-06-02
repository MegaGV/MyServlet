package practice4.service;

import java.util.List;

import factory.Factory;
import practice4.dao.EntityDao;

public class EntityServiceImpl implements EntityService {

    @Override
    public List<Object> getEntities(Class entityClazz, int page, int num_every_page) {
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        return entityDao.getEntities(entityClazz, page, num_every_page);
    }

    @Override
    public Object findEntityByPK(Class entityClazz, String PK) {
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        return entityDao.findEntityByPK(entityClazz, PK);
    }

    @Override
    public void saveEntity(Object entity){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        entityDao.saveEntity(entity);
    }

    @Override
    public void addEntity(Object entity){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        entityDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(Class entityClazz, String pk){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        entityDao.deleteEntity(entityClazz,pk);
    }

    @Override
    public void deleteAll(Class entityClazz){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        entityDao.deleteAll(entityClazz);
    }

    @Override
    public void sort(String sort){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        entityDao.sort(sort);
    }

    @Override
    public List<String> getAllPK(Class entityClazz) {
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        return entityDao.getAllPK(entityClazz);
    }

    @Override
    public int size(Class entityClazz){
        EntityDao entityDao = Factory.getInstance().getDaoFactory().getEntityDao();
        return entityDao.size(entityClazz);
    }

}

