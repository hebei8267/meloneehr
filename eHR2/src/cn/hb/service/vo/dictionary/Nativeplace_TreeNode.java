package cn.hb.service.vo.dictionary;

public class Nativeplace_TreeNode extends TreeNodeAdaptor<Nativeplace_TreeNode> {

    private static final long serialVersionUID = 6616771387093077123L;

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    public Nativeplace_TreeNode(String id, String name) {
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
