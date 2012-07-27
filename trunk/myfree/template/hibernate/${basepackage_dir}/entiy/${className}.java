<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entiy;

import javax.persistence.Entity;
import javax.persistence.Table;

<#include "/java_imports.include">

/** ${table.tableAlias}对应实体类 */
@Entity
@Table(name = "${table.sqlName}")
public class ${className} {
	
}