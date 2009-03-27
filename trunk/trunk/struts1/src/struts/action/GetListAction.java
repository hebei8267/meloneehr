/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package struts.action;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

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
public class GetListAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Enumeration en = request.getParameterNames();
        // while (en.hasMoreElements()) {
        // System.out.println(en.nextElement());
        // }
        System.out.println("GetListAction execute");
        ListActionForm listForm = (ListActionForm) form;
        System.out.println(listForm.getDataList().size());
        Map m = request.getParameterMap();
        System.out.println("ssa:" + listForm.getSsa());
        for (DataBean dataBean : listForm.getDataList()) {
            System.out.println("A: " + dataBean.getA());
            System.out.println("B: " + dataBean.getB());
            System.out.println();
        }
        return mapping.findForward("getList");
    }
}
