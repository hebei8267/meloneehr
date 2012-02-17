package cn.com.free.framework.generator.provider.db.model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.free.framework.generator.provider.db.DbTableFactory;
import cn.com.free.framework.generator.util.StringHelper;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Table {
	private String sqlName;
	private String remarks;
	private String customClassName;
	private String ownerSynonymName = null;
	private List columns = new ArrayList();
	private List primaryKeyColumns = new ArrayList();

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomClassName() {
		return customClassName;
	}

	public void setCustomClassName(String customClassName) {
		this.customClassName = customClassName;
	}

	public List getColumns() {
		return columns;
	}

	public void setColumns(List columns) {
		this.columns = columns;
	}

	public List getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}

	public void setPrimaryKeyColumns(List primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}

	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}

	// -------------------------------------------------------------------

	public void addColumn(Column column) {
		columns.add(column);
	}

	public void setClassName(String customClassName) {
		this.customClassName = customClassName;
	}

	public String getClassName() {
		String defaultValue = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(getSqlName()));
		return StringHelper.emptyIf(customClassName, defaultValue);
	}

	public String getTableAlias() {
		return StringHelper.emptyIf(getRemarks(), getClassName());
	}

	public String getClassNameLowerCase() {
		return getClassName().toLowerCase();
	}

	public String getClassNameFirstLower() {
		return StringHelper.uncapitalize(getClassName());
	}

	public String getConstantName() {
		return StringHelper.toUnderscoreName(getClassName()).toUpperCase();
	}

	public boolean isSingleId() {
		return getPkCount() == 1 ? true : false;
	}

	public boolean isCompositeId() {
		return getPkCount() > 1 ? true : false;
	}

	public boolean isNotCompositeId() {
		return !isCompositeId();
	}

	public int getPkCount() {
		int pkCount = 0;
		for (int i = 0; i < columns.size(); i++) {
			Column c = (Column) columns.get(i);
			if (c.isPk()) {
				pkCount++;
			}
		}
		return pkCount;
	}

	public List getPkColumns() {
		List results = new ArrayList();
		List columns = getColumns();
		for (int i = 0; i < columns.size(); i++) {
			Column c = (Column) columns.get(i);
			if (c.isPk())
				results.add(c);
		}
		return results;
	}

	public List getCompositeIdColumns() {
		return getPkColumns();
	}

	public List getNotPkColumns() {
		List results = new ArrayList();
		List columns = getColumns();
		for (int i = 0; i < columns.size(); i++) {
			Column c = (Column) columns.get(i);
			if (!c.isPk())
				results.add(c);
		}
		return results;
	}

	public Column getIdColumn() {
		List columns = getColumns();
		for (int i = 0; i < columns.size(); i++) {
			Column c = (Column) columns.get(i);
			if (c.isPk())
				return c;
		}
		return null;
	}

	/**
	 * This method was created in VisualAge.
	 */
	public void initImportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {

		// get imported keys a

		ResultSet fkeys = dbmd.getImportedKeys(catalog, schema, this.sqlName);

		while (fkeys.next()) {
			String pktable = fkeys.getString(PKTABLE_NAME);
			String pkcol = fkeys.getString(PKCOLUMN_NAME);
			String fkcol = fkeys.getString(FKCOLUMN_NAME);
			String seq = fkeys.getString(KEY_SEQ);
			Integer iseq = new Integer(seq);
			getImportedKeys().addForeignKey(pktable, pkcol, fkcol, iseq);
		}
		fkeys.close();
	}

	/**
	 * This method was created in VisualAge.
	 */
	public void initExportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
		// get Exported keys

		ResultSet fkeys = dbmd.getExportedKeys(catalog, schema, this.sqlName);

		while (fkeys.next()) {
			String pkcol = fkeys.getString(PKCOLUMN_NAME);
			String fktable = fkeys.getString(FKTABLE_NAME);
			String fkcol = fkeys.getString(FKCOLUMN_NAME);
			String seq = fkeys.getString(KEY_SEQ);
			Integer iseq = new Integer(seq);
			getExportedKeys().addForeignKey(fktable, fkcol, pkcol, iseq);
		}
		fkeys.close();
	}

	/**
	 * @return Returns the exportedKeys.
	 */
	public ForeignKeys getExportedKeys() {
		if (exportedKeys == null) {
			exportedKeys = new ForeignKeys(this);
		}
		return exportedKeys;
	}

	/**
	 * @return Returns the importedKeys.
	 */
	public ForeignKeys getImportedKeys() {
		if (importedKeys == null) {
			importedKeys = new ForeignKeys(this);
		}
		return importedKeys;
	}

	public String toString() {
		return "Database Table:" + getSqlName() + " to ClassName:" + getClassName();
	}

	String catalog = DbTableFactory.getInstance().getCatalog();
	String schema = DbTableFactory.getInstance().getSchema();

	private ForeignKeys exportedKeys;
	private ForeignKeys importedKeys;

	public static final String PKTABLE_NAME = "PKTABLE_NAME";
	public static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
	public static final String FKTABLE_NAME = "FKTABLE_NAME";
	public static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
	public static final String KEY_SEQ = "KEY_SEQ";
}
