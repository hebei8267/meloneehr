package cn.hb.core.query.queryCommandImp.hibernate3;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.type.ImmutableType;

/**
 * 2008/02/15
 * 
 * @author 何 貝
 * 
 * 动态NativeSQL查询Command
 */
public abstract class AbstractNativeSQLQueryCommand extends AbstractQueryCommand {
	private Map<String, ImmutableType> queryItemMap = new HashMap<String, ImmutableType>();
	@SuppressWarnings("unchecked")
	private Class domainClass = null;

	public AbstractNativeSQLQueryCommand() {

	}

	@SuppressWarnings("unchecked")
	public Class getDomainClass() {
		return domainClass;
	}

	public Map<String, ImmutableType> getQueryItemMap() {
		return queryItemMap;
	}

	protected AbstractNativeSQLQueryCommand addQueryItemMap(String itemKey, ImmutableType itemType) {
		this.queryItemMap.put(itemKey, itemType);
		return this;
	}

	@SuppressWarnings("unchecked")
	protected void setDomainClass(Class domainClass) {
		this.domainClass = domainClass;
	}

	protected void setQueryItemMap(Map<String, ImmutableType> queryItemMap) {
		this.queryItemMap = queryItemMap;
	}
}
