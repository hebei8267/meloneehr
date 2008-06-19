package cn.hb.view.domain.ui;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.richfaces.model.TreeNode;

/**
 * @author kaka
 * 
 */
public abstract class AbstractUITreeNodeBean<T> implements TreeNode<T> {

    private static final long serialVersionUID = 2792061542205411520L;
    /** 父节点 */
    private TreeNode<T> parent;
    /** 子节点Map */
    private Map<Object, TreeNode<T>> childrenMap = new LinkedHashMap<Object, TreeNode<T>>();

    public AbstractUITreeNodeBean() {

    }

    @Override
    public void addChild(Object identifier, TreeNode<T> child) {
        child.setParent(this);
        childrenMap.put(identifier, child);
    }

    @Override
    public TreeNode<T> getChild(Object identifier) {
        return (TreeNode<T>) childrenMap.get(identifier);
    }

    @Override
    public Iterator<Entry<Object, TreeNode<T>>> getChildren() {
        return childrenMap.entrySet().iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getData() {
        return (T) this;
    }

    @Override
    public TreeNode<T> getParent() {
        return parent;
    }

    @Override
    public boolean isLeaf() {
        return childrenMap.isEmpty();
    }

    @Override
    public void removeChild(Object identifier) {
        TreeNode<T> treeNode = (TreeNode<T>) childrenMap.remove(identifier);
        if (treeNode != null) {
            treeNode.setParent(null);
        }
    }

    @Override
    public void setData(T parent) {
        // empty imp
    }

    @Override
    public void setParent(TreeNode<T> parent) {
        this.parent = (TreeNode<T>) parent;
    }

    public Map<Object, TreeNode<T>> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<Object, TreeNode<T>> childrenMap) {
        this.childrenMap = childrenMap;
    }

}
