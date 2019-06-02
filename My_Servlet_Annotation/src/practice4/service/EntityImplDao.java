package practice4.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import practice4.dao.EntityDao;

public class EntityImplDao implements EntityDao {

    private static Connection getConnection()  {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/myservlet";
        String userName = "root";
        String password = "123456";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con =null;
        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private static String sort = null;

    @Override
    public List<Object> getEntities(Class entityClazz, int page, int num_every_page) {
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        String SQL = "select * from " + entityDefinition.getTableName() + " order by " + sort + " limit "  + (page-1)*num_every_page + "," + num_every_page;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            List<Object> entities = new ArrayList<>();
            while(resultSet.next()) {
                Object entity = EntityHelper.toEntity(resultSet, entityDefinition);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object findEntityByPK(Class entityClazz, String PK){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        String SQL = "select * from " + entityDefinition.getTableName();

        String[] pks = PK.split(" ");
        Object entity = null;

        SQL += createWhere(entityDefinition, pks);

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next())
                entity = EntityHelper.toEntity(resultSet, entityDefinition);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void saveEntity(Object entity){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entity.getClass());
        StringBuilder SQL = new StringBuilder("update ").append(entityDefinition.getTableName()).append(" set ");
        StringBuilder judge = new StringBuilder(" where ");

        for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions()) {
            Object value = null;
            try {
                value = fieldDefinition.getField().get(entity);

                if(fieldDefinition.isPK()){
                    if(!judge.toString().equals(" where "))
                        judge.append(" and ");
                    if(value.getClass().equals(String.class))
                        judge.append(fieldDefinition.getFieldName()).append("=\"").append(value).append("\"");
                    else
                        judge.append(fieldDefinition.getFieldName()).append("=").append(value);
                }
                else{
                    if(value.getClass().equals(String.class))
                        SQL.append(fieldDefinition.getFieldName()).append("=\"").append(value).append("\",");
                    else
                        SQL.append(fieldDefinition.getFieldName()).append("=").append(value).append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        SQL.deleteCharAt(SQL.length()-1);
        SQL.append(judge.toString());

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEntity(Object entity){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entity.getClass());
        StringBuilder SQL = new StringBuilder("insert into ").append(entityDefinition.getTableName()).append("(");

        for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions())
            SQL.append(fieldDefinition.getFieldName()).append(",");
        SQL.deleteCharAt(SQL.length()-1);
        SQL.append(") value(");

        for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions()) {
            Object value = null;
            try{
                value = fieldDefinition.getField().get(entity);
                if(value.getClass().equals(String.class))
                    SQL.append("\"").append(value).append("\"").append(",");
                else
                    SQL.append(value).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        SQL.deleteCharAt(SQL.length()-1);
        SQL.append(")");

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntity(Class entityClazz, String pk){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        String[] pks = pk.split(" ");
        StringBuilder SQL = new StringBuilder("delete from " + entityDefinition.getTableName());
        SQL.append(createWhere(entityDefinition, pks));

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String createWhere(EntityDefinition entityDefinition, String[] pks){
        StringBuilder judge = new StringBuilder(" where ");
        int i = 0;
        for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions()) {
            if(i == pks.length)
                break;
            if(fieldDefinition.isPK()) {
                if(i != 0)
                    judge.append(" and ");

                if(fieldDefinition.isString())
                    judge.append(fieldDefinition.getFieldName()).append("=\"").append(pks[i++]).append("\"");
                else
                    judge.append(fieldDefinition.getFieldName()).append("=").append(pks[i++]);
            }
        }
        return judge.toString();
    }

    @Override
    public void deleteAll(Class entityClazz){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        String sql = "delete from " + entityDefinition.getTableName();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(String newsort){
        sort = newsort;
    }

    @Override
    public List<String> getAllPK(Class entityClazz){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        List<String> pks = new ArrayList<>();
        StringBuilder fields = new StringBuilder();

        for(FieldDefinition fieldDefinition:entityDefinition.getFieldDefinitions()) {
            if(fieldDefinition.isPK())
                fields.append(fieldDefinition.getFieldName()).append(",");
        }
        fields.deleteCharAt(fields.length()-1);
        fields.append(" ");

        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            String sql = "select " + fields.toString() + " from " + entityDefinition.getTableName();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next())
                pks.add(resultSet.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pks;
    }

    @Override
    public int size(Class entityClazz){
        EntityDefinition entityDefinition = EntityHelper.generateEntityDefinition(entityClazz);
        String sql = "select COUNT(*) from " + entityDefinition.getTableName();
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            if(resultSet.next())
                return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}

