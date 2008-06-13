package cn.hb.view.domain;

/**
 * @author kaka
 * 
 * 籍贯信息树节点
 */
public class UINativeplaceTreeNodeBean extends AbstractUITreeNodeBean<UINativeplaceTreeNodeBean> {

    private static final long serialVersionUID = -4514259996461825546L;
    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 简称 */
    private String shortName;

    /** 详细描述 */
    private String description;

    public UINativeplaceTreeNodeBean() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
