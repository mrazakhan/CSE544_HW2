package simpledb;

public class Table {
	public Table(DbFile dbF,String name, String primaryKeyField) {
		super();
		this.name = name;
		this.setDbFile(dbF);
		this.primaryKeyField = primaryKeyField;
	}
	private String name;
	private DbFile dbFile;
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
	public DbFile getDbFile() {
		return dbFile;
	}
	public void setDbFile(DbFile dbFile) {
		this.dbFile = dbFile;
	}
	private String primaryKeyField;
	

}
