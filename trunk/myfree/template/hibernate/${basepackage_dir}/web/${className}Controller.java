<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${basepackage}.entity.${className};
import ${basepackage}.globals.Constants;
import ${basepackage}.service.ServiceException;
import ${basepackage}.service.${className}Manager;
import ${basepackage}.web.BaseController;

@Controller
@RequestMapping(value = "/${classNameLower}")
public class ${className}Controller extends BaseController {
	@Autowired
	private ${className}Manager ${classNameLower}Manager;
	
	/**
	 * 取得${table.tableAlias}信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String ${classNameLower}List_Action(Model model) {
		List<${className}> ${classNameLower}List = ${classNameLower}Manager.getAll${className}();

		model.addAttribute("${classNameLower}List", ${classNameLower}List);

		return "${classNameLower}List";
	}
	
	/**
	 * 编辑${table.tableAlias}信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}")
	public String edit${className}_Action(@PathVariable("id") Integer id, Model model) {

		${className} ${classNameLower} = ${classNameLower}Manager.get${className}ByUuid(id);
		if (null == ${classNameLower}) {
			return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/${classNameLower}/list";
		} else {
			model.addAttribute("${classNameLower}", ${classNameLower});
			return "${classNameLower}Form";
		}

	}

	/**
	 * 删除${table.tableAlias}信息
	 * 
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "del")
	public String del${className}_Action(@RequestParam("uuids") String ids, Model model) {
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			${classNameLower}Manager.del${className}ByUuid(Integer.parseInt(idArray[i]));
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/${classNameLower}/list";
	}

	/**
	 * 新增${table.tableAlias}初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "new")
	public String init${className}_Action(Model model) {
		// TODO 修改点
		${className} ${classNameLower} = new ${className}();
		model.addAttribute("${classNameLower}", ${classNameLower});
		
		return "${classNameLower}Form";
	}

	/**
	 * 新增/修改 ${table.tableAlias}
	 * 
	 * @param ${classNameLower}
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@RequestMapping(value = "save")
	public String save${className}_Action(@ModelAttribute("${classNameLower}") ${className} ${classNameLower}, Model model)
			throws IllegalAccessException, InvocationTargetException {

		if (null == ${classNameLower}.getUuid()) {// 新增操作
			${classNameLower}Manager.addNew${className}(${classNameLower});
		} else {// 修改操作
			try {
				${classNameLower}Manager.update${className}(${classNameLower});
			} catch (ServiceException ex) {
				// 添加错误消息
				addInfoMsg(model, ex.getMessage());
			}
		}

		return "redirect:/" + Constants.PAGE_REQUEST_PREFIX + "/${classNameLower}/list";
	}
}