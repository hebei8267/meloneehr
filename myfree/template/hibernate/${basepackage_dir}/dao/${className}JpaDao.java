<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ${className}JpaDao extends CrudRepository<${className}, Integer> {

	@SuppressWarnings("rawtypes")
	public Iterable findAll(Sort sort);
}
