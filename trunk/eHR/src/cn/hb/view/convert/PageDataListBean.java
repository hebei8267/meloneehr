package cn.hb.view.convert;

import java.util.List;

/**
 * 2008-2-24
 * 
 * @author 何 貝
 */
public class PageDataListBean<T> {
    private int totalProperty;
    private List<T> dataList;

    public PageDataListBean() {

    }

    public int getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(int totalProperty) {
        this.totalProperty = totalProperty;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
