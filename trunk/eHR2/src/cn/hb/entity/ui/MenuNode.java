package cn.hb.entity.ui;

/** 菜单树结点 */
public class MenuNode extends AbstractEntityBean  {
	public MenuNode() {
	}

 
    /** 编号 */
	private String 节点id(PK);
    /** 节点编号 */
	private String id(PK);
	 
    /** 节点描述 */
	private String nodeTxt;
	 
    /** 节点类型 */
	private String nodeType;
	 
	/**
	 *"true"无访问限制 "false"有访问限制
	 */
    /** 默认权限 */
	private String defaultPermit;
	 
    /** 页面迁移内容 */
	private String actionContent;
	 
    /** 菜单树结点 */
	private MenuNode[] MenuNode;
	 
    /** 菜单树结点 */
	private MenuNode MenuNode;
	 
	/**
	 * 取得编号
	 * 
	 * @return 编号
	 */

	public String getId() {
		return id;
	}

	/**
	 * 取得节点编号
	 * 
	 * @return 节点编号
	 */

	public String getId() {
		return id;
	}

	/**
	 * 取得节点描述
	 * 
	 * @return 节点描述
	 */

	public String getNodeTxt() {
		return nodeTxt;
	}

	/**
	 * 取得节点类型
	 * 
	 * @return 节点类型
	 */

	public String getNodeType() {
		return nodeType;
	}

	/**
	 * 取得默认权限
	 * 
	 * @return 默认权限
	 */

	public String getDefaultPermit() {
		return defaultPermit;
	}

	/**
	 * 取得页面迁移内容
	 * 
	 * @return 页面迁移内容
	 */

	public String getActionContent() {
		return actionContent;
	}

	/**
	 * 取得菜单树结点
	 * 
	 * @return 菜单树结点
	 */

	public String getMenuNode() {
		return MenuNode;
	}

	/**
	 * 取得菜单树结点
	 * 
	 * @return 菜单树结点
	 */

	public String getMenuNode() {
		return MenuNode;
	}

	/**
	 * 设置编号
	 * 
	 * @param id 编号
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置节点编号
	 * 
	 * @param id 节点编号
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置节点描述
	 * 
	 * @param nodeTxt 节点描述
	 */

	public void setNodeTxt(String nodeTxt) {
		this.nodeTxt = nodeTxt;
	}

	/**
	 * 设置节点类型
	 * 
	 * @param nodeType 节点类型
	 */

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * 设置默认权限
	 * 
	 * @param defaultPermit 默认权限
	 */

	public void setDefaultPermit(String defaultPermit) {
		this.defaultPermit = defaultPermit;
	}

	/**
	 * 设置页面迁移内容
	 * 
	 * @param actionContent 页面迁移内容
	 */

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}

	/**
	 * 设置菜单树结点
	 * 
	 * @param MenuNode 菜单树结点
	 */

	public void setMenuNode(String MenuNode) {
		this.MenuNode = MenuNode;
	}

	/**
	 * 设置菜单树结点
	 * 
	 * @param MenuNode 菜单树结点
	 */

	public void setMenuNode(String MenuNode) {
		this.MenuNode = MenuNode;
	}

}
 
