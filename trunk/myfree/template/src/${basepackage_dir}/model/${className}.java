<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

<#include "/java_imports.include">

@SuppressWarnings("serial")
public class ${className} extends BaseEntity {

	// alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
	</#list>

	// date formats
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	public static final String FORMAT_${column.constantName} = DATE_TIME_FORMAT;
	</#if>
	</#list>

	// columns START
	<#list table.columns as column>
	/** ${column.remarks} */
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	// columns END

<@generateConstructor className/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
	/**
	 * 取得${column.remarks}
	 * 
	 * @return ${column.remarks}
	 */
	public String get${column.columnName}String() {
		return date2String(get${column.columnName}(), FORMAT_${column.constantName});
	}

	/**
	 * 设置${column.remarks}
	 * 
	 * @param ${column.columnNameLower} ${column.remarks}
	 */
	public void set${column.columnName}String(String ${column.columnNameLower}) {
		set${column.columnName}(string2Date(${column.columnNameLower}, FORMAT_${column.constantName},${column.javaType}.class));
	}

		</#if>

	/**
	 * 取得${column.remarks}
	 * 
	 * @return ${column.remarks}
	 */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}

	/**
	 * 设置${column.remarks}
	 * 
	 * @param ${column.columnNameLower} ${column.remarks}
	 */
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}

	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>

	private ${fkPojoClass} ${fkPojoClassVar};

	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}

	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
