/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.form.DataBean;
import struts.form.ListActionForm;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class InitListAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("InitListAction execute");

        List<DataBean> dataList = new ArrayList<DataBean>();
        for (int i = 0; i < 10; i++) {
            DataBean dataBean = new DataBean();

            dataBean.setA("AA" + i);
            dataBean.setB("BB" + i);

            dataList.add(dataBean);
        }
        // request.setAttribute("dataList", dataList);

        ListActionForm listForm = (ListActionForm) form;
        listForm.setSsa("MMMMMMMM");
        listForm.setDataList(dataList);

        return mapping.findForward("initList");
    }
}
