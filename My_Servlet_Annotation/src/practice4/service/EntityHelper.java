package practice4.service;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import annotation.Entity;

public class EntityHelper {

    private static Map<Class,EntityDefinition> entityDefinitions = new HashMap<>();

    public static EntityDefinition generateEntityDefinition(Class entityClazz)
    {
        if(entityDefinitions.containsKey(entityClazz)) {
            return entityDefinitions.get(entityClazz);
        }

        Entity entity = (Entity) entityClazz.getAnnotation(Entity.class);

        if(entity == null)
            return null;

        String tableName = entity.tableName();
        String entityLabel = entity.label();

        EntityDefinition entityDefinition = new EntityDefinition(entityClazz, tableName, entityLabel);

        for(Field field:entityClazz.getDeclaredFields()) {
            annotation.Field fieldAnnotation = field.getAnnotation(annotation.Field.class);
            if(fieldAnnotation == null)
                continue;
            FieldDefinition fieldDefinition = new FieldDefinition(field.getName(), fieldAnnotation.isPK(), fieldAnnotation.label(), field);
            entityDefinition.addFieldDefinition(fieldDefinition);
        }
        entityDefinitions.put(entityClazz, entityDefinition);
        return entityDefinition;
    }

    public static Class fromClassName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Object toEntity(ResultSet resultSet, EntityDefinition entityDefinition) {
        try {
            if(resultSet.isAfterLast() || resultSet.isBeforeFirst())
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        try {
            Object entity = entityDefinition.getClazz().newInstance();

            for(FieldDefinition fieldDefinition : entityDefinition.getFieldDefinitions()) {
                fieldDefinition.getField().setAccessible(true);
                fieldDefinition.getField().set(entity, resultSet.getObject(fieldDefinition.getFieldName()));
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
