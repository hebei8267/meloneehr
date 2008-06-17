/**
 *	2007/11/19
 *	@author 何　貝
 */

package cn.hb.core.query.queryImpl.hibernate3;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hb.core.query.IQueryCommand;

/**
 * HQL动态查询实现
 */
public class HQLQueryDaoImpl extends AbstractQueryDaoImpl {

	@SuppressWarnings("unchecked")
	public List<Object> executeQuery(IQueryCommand queryCommand) {
		queryCommand.createQuerySql();

		if (availableQueryParamMap(queryCommand) && availableQuerySql(queryCommand)) {
			Set<Map.Entry<String, Object>> paramSet = queryCommand.getQueryParamMap().entrySet();

			String[] paramNames = new String[paramSet.size()];
			Object[] values = new Object[paramSet.size()];
			int index = 0;
			for (Map.Entry<String, Object> entry : paramSet) {

				paramNames[index] = (String) entry.getKey();
				values[index] = entry.getValue();
				index++;
			}

			return getHibernateTemplate().findByNamedParam(queryCommand.getQuerySql(), paramNames, values);
		}

		return null;
	}

}
