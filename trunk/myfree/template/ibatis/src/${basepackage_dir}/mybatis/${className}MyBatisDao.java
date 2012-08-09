<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao.myBatis;

import java.util.List;

import com.tjhx.entity.${className};

public interface ${className}MyBatisDao {

	public List<${className}> get${className}List(${className} ${classNameLower});

}
