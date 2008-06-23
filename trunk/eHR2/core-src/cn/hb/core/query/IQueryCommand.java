/**
 *	2007/11/24
 *	@author 何　貝
 */
package cn.hb.core.query;

import java.util.Map;

import org.hibernate.type.ImmutableType;

/**
 * 查询命令对象
 */
public interface IQueryCommand {
	/**
	 * 设置查询结果的开始位置
	 */
	public void setFirstResultPost(int firstResultPost);

	/**
	 * 设置查询结果的最大数量
	 */
	public void setMaxResultCount(int maxResultCount);

	/**
	 * 取得查询结果的开始位置
	 */
	public int getFirstResultPost();

	/**
	 * 取得查询结果的最大数量
	 */
	public int getMaxResultCount();

	/**
	 * 生成查询语句
	 */
	public void createQuerySql();

	/**
	 * 生成查询结果对象影射集合
	 */
	public void createQueryItemMap();

	/**
	 * 生成查询结果对象类型
	 */
	public void createDomainClass();

	/**
	 * 取得查询语句
	 * 
	 * @return 查询语句
	 */
	public String getQuerySql();

	/**
	 * 取得查询参数集合
	 * 
	 * @return 查询参数集合
	 */
	public Map<String, Object> getQueryParamMap();

	/**
	 * 取得查询结果对象影射集合
	 * 
	 * @return 查询结果对象影射集合
	 */
	public Map<String, ImmutableType> getQueryItemMap();

	/**
	 * 取得查询结果对象类型
	 * 
	 * @return 查询结果对象类型
	 */
	public Class<Object> getDomainClass();
}
