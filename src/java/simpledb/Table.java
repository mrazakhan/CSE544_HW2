package simpledb;

public class Table {
	public Table(String name, String primaryKeyField) {
		super();
		this.name = name;
		this.primaryKeyField = primaryKeyField;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrimaryKeyField() {
		return primaryKeyField;
	}
	public void setPrimaryKeyField(String primaryKeyField) {
		this.primaryKeyField = primaryKeyField;
	}
	private String primaryKeyField;
	

}
