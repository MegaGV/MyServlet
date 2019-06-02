package practice4.service;

import java.lang.reflect.Field;

public class FieldDefinition {
    private String fieldName;
    private boolean isPK;
    private String label;
    private Field field;
    private boolean isString;

    public FieldDefinition(String fieldName, boolean isPK, String label,Field field) {
        super();
        this.fieldName = fieldName;
        this.isPK = isPK;
        this.label = label;
        this.field = field;
        this.isString = this.field.getType() == String.class;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public boolean isPK() {
        return isPK;
    }
    public void setPK(boolean isPK) {
        this.isPK = isPK;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public Field getField() {
        return field;
    }
    public void setField(Field field) {
        this.field = field;
    }
    public boolean isString() {
        return isString;
    }
    public void setString(boolean isString) {
        this.isString = isString;
    }


}
