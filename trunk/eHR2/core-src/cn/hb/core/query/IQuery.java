/**
 *	2007/11/15
 *
 *	@author 何　貝
 */

package cn.hb.core.query;

import java.util.List;

/**
 * 
 * 动态查询接口定义
 */
public interface IQuery {
	/**
	 * 执行查询,返回查询结果
	 * 
	 * @param queryCommand 查询命令对象
	 * @return 查询结果
	 */
	public List<Object> executeQuery(IQueryCommand queryCommand);
}
