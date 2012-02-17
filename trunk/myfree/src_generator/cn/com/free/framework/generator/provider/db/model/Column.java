package cn.com.free.framework.generator.provider.db.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.free.framework.generator.util.ActionScriptDataTypesUtils;
import cn.com.free.framework.generator.util.DatabaseDataTypesUtils;
import cn.com.free.framework.generator.util.JdbcType;
import cn.com.free.framework.generator.util.StringHelper;
import cn.com.free.framework.generator.util.TestDataGenerator;

public class Column {

	private final Table _table;
	private final int _sqlType;
	private final String _sqlTypeName;
	private final String _sqlName;
	private boolean _isPk;
	private boolean _isFk;
	private final int _size;
	private final int _decimalDigits;
	private final boolean _isNullable;
	private final boolean _isIndexed;
	private final boolean _isUnique;
	private final String _defaultValue;
	private final String _remarks;
	private static Log _log = LogFactory.getLog(Column.class);

	public Column(Table table, int sqlType, String sqlTypeName, String sqlName, int size, int decimalDigits, boolean isPk,
			boolean isNullable, boolean isIndexed, boolean isUnique, String defaultValue, String remarks) {
		_table = table;
		_sqlType = sqlType;
		_sqlName = sqlName;
		_sqlTypeName = sqlTypeName;
		_size = size;
		_decimalDigits = decimalDigits;
		_isPk = isPk;
		_isNullable = isNullable;
		_isIndexed = isIndexed;
		_isUnique = isUnique;
		_defaultValue = defaultValue;
		_remarks = remarks;

		_log.debug(sqlName + " isPk -> " + _isPk);
	}

	public int getSqlType() {
		return _sqlType;
	}

	public Table getTable() {
		return _table;
	}

	public int getSize() {
		return _size;
	}

	public int getDecimalDigits() {
		return _decimalDigits;
	}

	public String getSqlTypeName() {
		return _sqlTypeName;
	}

	public String getSqlName() {
		return _sqlName;
	}

	public boolean isPk() {
		return _isPk;
	}

	public boolean isFk() {
		return _isFk;
	}

	public final boolean isNullable() {
		return _isNullable;
	}

	public final boolean isIndexed() {
		return _isIndexed;
	}

	public boolean isUnique() {
		return _isUnique;
	}

	public final String getDefaultValue() {
		return _defaultValue;
	}

	public final String getRemarks() {
		return _remarks;
	}

	void setFk(boolean flag) {
		_isFk = flag;
	}

	// ----------------------------------------------------------------
	public int hashCode() {
		return (getTable().getSqlName() + "#" + getSqlName()).hashCode();
	}

	public boolean equals(Object o) {
		return this == o;
	}

	public String toString() {
		return getSqlName();
	}

	protected final String prefsPrefix() {
		return "tables/" + getTable().getSqlName() + "/columns/" + getSqlName();
	}

	public String getColumnName() {
		return StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(getSqlName()));
	}

	public String getColumnNameFirstLower() {
		return StringHelper.uncapitalize(getColumnName());
	}

	public String getColumnNameLowerCase() {
		return getColumnName().toLowerCase();
	}

	public String getColumnNameLower() {
		return getColumnNameFirstLower();
	}

	public String getJdbcSqlTypeName() {
		String result = JdbcType.getJdbcSqlTypeName(getSqlType());

		return result;
	}

	public String getColumnAlias() {
		return StringHelper.emptyIf(getRemarks(), getColumnNameFirstLower());
	}

	public String getConstantName() {
		return StringHelper.toUnderscoreName(getSqlName()).toUpperCase();
	}

	public boolean getIsNotIdOrVersionField() {
		return !isPk();
	}

	public String getValidateString() {
		String result = getNoRequiredValidateString();
		if (!isNullable()) {
			result = "required " + result;
		}
		return result;
	}

	public String getNoRequiredValidateString() {
		String result = "";
		if (getSqlName().indexOf("mail") >= 0) {
			result += "validate-email ";
		}
		if (DatabaseDataTypesUtils.isFloatNumber(getSqlType(), getSize(), getDecimalDigits())) {
			result += "validate-number ";
		}
		if (DatabaseDataTypesUtils.isIntegerNumber(getSqlType(), getSize(), getDecimalDigits())) {
			result += "validate-integer ";
			if (getJavaType().indexOf("Short") >= 0) {
				result += "max-value-" + Short.MAX_VALUE;
			} else if (getJavaType().indexOf("Integer") >= 0) {
				result += "max-value-" + Integer.MAX_VALUE;
			} else if (getJavaType().indexOf("Byte") >= 0) {
				result += "max-value-" + Byte.MAX_VALUE;
			}
		}

		return result;
	}

	public boolean getIsStringColumn() {
		return DatabaseDataTypesUtils.isString(getSqlType(), getSize(), getDecimalDigits());
	}

	public boolean getIsDateTimeColumn() {
		return DatabaseDataTypesUtils.isDate(getSqlType(), getSize(), getDecimalDigits());
	}

	public boolean getIsNumberColumn() {
		return DatabaseDataTypesUtils.isFloatNumber(getSqlType(), getSize(), getDecimalDigits())
				|| DatabaseDataTypesUtils.isIntegerNumber(getSqlType(), getSize(), getDecimalDigits());
	}

	public boolean isHtmlHidden() {
		return isPk() && _table.isSingleId();
	}

	public String getJavaType() {
		return DatabaseDataTypesUtils.getPreferredJavaType(getSqlType(), getSize(), getDecimalDigits());
	}

	public String getAsType() {
		return ActionScriptDataTypesUtils.getPreferredAsType(getJavaType());
	}

	public String getTestData() {
		return new TestDataGenerator().getTestData(getColumnName(), getJavaType(), getSize());
	}
}
