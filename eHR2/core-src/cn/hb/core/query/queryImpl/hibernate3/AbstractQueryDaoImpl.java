/**
 * 2008-2-17
 * @author 何 貝
 */
package cn.hb.core.query.queryImpl.hibernate3;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.hb.core.query.IQuery;
import cn.hb.core.query.IQueryCommand;

/**
 * 抽象动态查询实现
 */
public abstract class AbstractQueryDaoImpl extends HibernateDaoSupport implements IQuery {
	/**
	 * 效验查询语句正确性
	 * 
	 * @param queryCommand 查询命令对象
	 * @return 效验结果
	 */
	protected boolean availableQuerySql(IQueryCommand queryCommand) {
		if (queryCommand.getQuerySql() != null && !queryCommand.getQuerySql().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 效验查询参数正集合确性
	 * 
	 * @param queryCommand 查询命令对象
	 * @return 效验结果
	 */
	protected boolean availableQueryParamMap(IQueryCommand queryCommand) {
		if (queryCommand.getQueryParamMap() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 效验查询结果对象影射集合正确性
	 * 
	 * @param queryCommand 查询命令对象
	 * @return 效验结果
	 */
	protected boolean availableQueryItemMap(IQueryCommand queryCommand) {
		if (queryCommand.getQueryItemMap() != null) {
			return true;
		}
		return false;
	}

	/**
	 * 效验查询结果对象类型正确性
	 * 
	 * @param queryCommand 查询命令对象
	 * @return 效验结果
	 */
	protected boolean availableDomainClass(IQueryCommand queryCommand) {
		if (queryCommand.getDomainClass() != null) {
			return true;
		}
		return false;
	}
}
