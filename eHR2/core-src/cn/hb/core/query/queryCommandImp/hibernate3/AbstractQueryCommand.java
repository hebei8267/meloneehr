/**
 *	2007/11/24
 *	@author 何 貝
 */
package cn.hb.core.query.queryCommandImp.hibernate3;

import java.util.HashMap;
import java.util.Map;

import cn.hb.core.query.IQueryCommand;

/**
 * 动态HQL查询Command
 */
public abstract class AbstractQueryCommand implements IQueryCommand {
	/** Query Param Map */
	private Map<String, Object> queryParamMap = new HashMap<String, Object>();

	/** Query Param Map */
	private Map<String, Object> bindValues = new HashMap<String, Object>();

	/** HQL */
	protected String querySql = "";

	protected int firstResultPost = 0;
	protected int maxResultCount = Integer.MAX_VALUE;

	/***/
	public AbstractQueryCommand() {

	}

	public Map<String, Object> getBindValues() {
		return bindValues;
	}

	public Object getQueryParam(String key) {
		return this.queryParamMap.get(key);
	}

	public Map<String, Object> getQueryParamMap() {
		return queryParamMap;
	}

	public String getQuerySql() {
		return querySql;
	}

	public int getFirstResultPost() {
		return firstResultPost;
	}

	public int getMaxResultCount() {
		return maxResultCount;
	}

	public void setFirstResultPost(int firstResultPost) {
		this.firstResultPost = firstResultPost;
	}

	public void setMaxResultCount(int maxResultCount) {
		this.maxResultCount = maxResultCount;
	}

	protected void setBindValues(Map<String, Object> bindValues) {
		this.bindValues = bindValues;
	}

	protected void setQueryParam(String key, Object value) {
		this.queryParamMap.put(key, value);
	}

	protected void setQueryParamMap(Map<String, Object> queryParamMap) {
		this.queryParamMap.putAll(queryParamMap);
	}

	protected void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

}
