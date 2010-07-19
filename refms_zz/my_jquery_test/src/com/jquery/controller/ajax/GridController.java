package com.jquery.controller.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jquery.controller.modules.ListModule1;
import com.jquery.core.controller.AbstractController;
import com.jquery.core.modules.grid.DataGridList;

@Controller
public class GridController extends AbstractController {
    @RequestMapping
    public void getGridData(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {
        String page = ServletRequestUtils.getStringParameter(request, "page");
        String rows = ServletRequestUtils.getStringParameter(request, "rows");
        System.out.println("page=" + page);
        System.out.println("rows=" + rows);
        String xxx1 = ServletRequestUtils.getStringParameter(request, "xxx1");
        String xxx2 = ServletRequestUtils.getStringParameter(request, "xxx2");
        String xxx3 = ServletRequestUtils.getStringParameter(request, "xxx3");
        System.out.println("xxx1=" + xxx1);
        System.out.println("xxx2=" + xxx2);
        System.out.println("xxx3=" + xxx3);

        List<ListModule1> list = new ArrayList<ListModule1>();
        for (int i = 0; i < 10; i++) {
            ListModule1 lm = new ListModule1();
            lm.setItemid("itemid" + i);
            lm.setProductid("productid" + i);
            lm.setUnitcost("unitcost" + i);
            lm.setStatus("status" + i);
            list.add(lm);
        }

        DataGridList dgl = new DataGridList(100, list);

        writeDataGridJsonView(response, dgl);
    }
}
