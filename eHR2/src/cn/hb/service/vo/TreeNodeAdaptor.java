package cn.hb.service.vo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class TreeNodeAdaptor<T> implements ITreeNode<T> {
    /** 父节点 */
    private ITreeNode<T> parent;
    /** 子节点Map */
    private Map<Object, ITreeNode<T>> childrenMap = new LinkedHashMap<Object, ITreeNode<T>>();

    public TreeNodeAdaptor() {

    }

    public void addChild(Object identifier, ITreeNode<T> child) {
        child.setParent(this);
        childrenMap.put(identifier, child);
    }

    public ITreeNode<T> getChild(Object identifier) {
        return (ITreeNode<T>) childrenMap.get(identifier);
    }

    public Iterator<Entry<Object, ITreeNode<T>>> getChildren() {
        return childrenMap.entrySet().iterator();
    }

    @SuppressWarnings("unchecked")
    public T getData() {
        return (T) this;
    }

    public ITreeNode<T> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return childrenMap.isEmpty();
    }

    public void removeChild(Object identifier) {
        ITreeNode<T> treeNode = (ITreeNode<T>) childrenMap.remove(identifier);
        if (treeNode != null) {
            treeNode.setParent(null);
        }
    }

    public void setData(T parent) {
        // empty imp
    }

    public void setParent(ITreeNode<T> parent) {
        this.parent = (ITreeNode<T>) parent;
    }

    public Map<Object, ITreeNode<T>> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<Object, ITreeNode<T>> childrenMap) {
        this.childrenMap = childrenMap;
    }
}
