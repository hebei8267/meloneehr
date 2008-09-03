package com.sofmit.sim.wr.web;

import java.util.ArrayList;
import java.util.List;

public class JsonBeanViewObject {
    private List dateList = null;

    public JsonBeanViewObject() {
        dateList = new ArrayList();
    }

    public List getDateList() {
        return dateList;
    }

    public void setDateList(List dateList) {
        this.dateList = dateList;
    }
}
