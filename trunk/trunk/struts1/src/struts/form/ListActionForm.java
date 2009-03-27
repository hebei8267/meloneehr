/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class ListActionForm extends ActionForm {
    private List<DataBean> dataList = new ExtraArrayList<DataBean>() {
        @Override
        protected DataBean newElement() {
            return new DataBean();
        }
    };

    private String ssa;

    public ListActionForm() {
     
    }

    /**
     * @return the dataList
     */
    public List<DataBean> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<DataBean> dataList) {
        this.dataList = dataList;
    }

    /**
     * @return the ssa
     */
    public String getSsa() {
        return ssa;
    }

    /**
     * @param ssa the ssa to set
     */
    public void setSsa(String ssa) {
        this.ssa = ssa;
    }

}
