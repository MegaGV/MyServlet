package practice4.dao;

import java.util.List;

public interface EntityDao {

    public List<Object> getEntities(Class entityClazz,int page, int num_every_page);

    public Object findEntityByPK(Class entityClazz, String PK);

    public void saveEntity(Object entity);

    public void addEntity(Object entity);

    public void deleteEntity(Class entityClazz, String pk);

    public void deleteAll(Class entityClazz);

    public void sort(String sort);

    public List<String> getAllPK(Class entityClazz);

    public int size(Class entityClazz);
}
