package cn.hb.view.domain.ui;


/**
 * @author kaka
 * 
 * 籍贯信息树节点
 */
public class UIDefaultTreeNodeBean extends AbstractUITreeNodeBean<UIDefaultTreeNodeBean> {

    private static final long serialVersionUID = -4514259996461825546L;

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    public UIDefaultTreeNodeBean() {

    }

    public UIDefaultTreeNodeBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
