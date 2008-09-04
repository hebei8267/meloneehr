/**
 * 2008/02/15
 * 
 * @author 何 貝
 */
package cn.hb.core.query.queryImpl.hibernate3;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.SQLQuery;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.ImmutableType;

import cn.hb.core.query.IQueryCommand;

/**
 * NativeSQL动态查询实现
 */
public class NativeSQLQueryDaoImpl extends AbstractQueryDaoImpl {

	@SuppressWarnings("unchecked")
	public List<Object> executeQuery(IQueryCommand queryCommand) {
		queryCommand.createQuerySql();
		queryCommand.createQueryItemMap();
		queryCommand.createDomainClass();

		if (availableQueryParamMap(queryCommand) && availableQuerySql(queryCommand)
				&& availableQueryItemMap(queryCommand) && availableDomainClass(queryCommand)) {
			Set<Map.Entry<String, Object>> paramSet = queryCommand.getQueryParamMap().entrySet();

			String[] paramNames = new String[paramSet.size()];
			Object[] values = new Object[paramSet.size()];
			int index = 0;
			for (Map.Entry<String, Object> entry : paramSet) {

				paramNames[index] = entry.getKey();
				values[index] = entry.getValue();
				index++;
			}

			// 创建动态查询对象
			SQLQuery query = getSession(false).createSQLQuery(queryCommand.getQuerySql());
			// 查询参数注入
			for (int i = 0; i < values.length; i++) {
				query.setParameter(paramNames[i], values[i]);
			}

			Set<Map.Entry<String, ImmutableType>> queryItemSet = queryCommand.getQueryItemMap().entrySet();
			// 查询结果映射注入
			for (Map.Entry<String, ImmutableType> entry : queryItemSet) {
				query.addScalar(entry.getKey(), entry.getValue());
			}
			// 查询结果对象注入
			query.setResultTransformer(new AliasToBeanResultTransformer(queryCommand.getDomainClass()));

			query.setFirstResult(queryCommand.getFirstResultPost());
			query.setMaxResults(queryCommand.getMaxResultCount());

			return query.list();
		}
		return null;
	}

}
