package practice4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityDefinition {

    private Class clazz;
    private String tableName;
    private String label;

    private List<FieldDefinition> fieldDefinitions;
    private Map<String, FieldDefinition> fields;
    private Map<String,FieldDefinition> labels;

    public EntityDefinition(Class clazz, String tableName, String label) {
        super();
        this.clazz = clazz;
        this.tableName = tableName;
        this.label = label;

        this.fieldDefinitions = new ArrayList<FieldDefinition>();
        this.fields = new HashMap<String, FieldDefinition>();
        this.labels = new HashMap<String, FieldDefinition>();
    }

    public void addFieldDefinition(FieldDefinition definition) {
        this.fieldDefinitions.add(definition);
        this.fields.put(definition.getFieldName(), definition);
        this.labels.put(definition.getLabel(), definition);
    }
    public Class getClazz() { return clazz; }
    public void setClazz(Class clazz) { this.clazz = clazz; }
    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public List<FieldDefinition> getFieldDefinitions() { return fieldDefinitions; }
    public void setFieldDefinitions(List<FieldDefinition> fieldDefinitions) { this.fieldDefinitions = fieldDefinitions; }
    public Map<String, FieldDefinition> getFields() { return fields; }
    public void setFields(Map<String, FieldDefinition> fields) { this.fields = fields; }
    public Map<String, FieldDefinition> getLabels() { return labels; }
    public void setLabels(Map<String, FieldDefinition> labels) { this.labels = labels; }
}

