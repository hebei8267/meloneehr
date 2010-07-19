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

import com.jquery.core.controller.AbstractController;
import com.jquery.core.modules.tree.CheckBoxTreeNodeAttributes;
import com.jquery.core.modules.tree.DefaultTreeNodeAttributes;
import com.jquery.core.modules.tree.TreeNode;

@Controller
public class TreeController extends AbstractController {

    @RequestMapping
    public void getTreeData(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<TreeNode> cList = new ArrayList<TreeNode>();
        for (int i = 1; i < 11; i++) {
            TreeNode node = new TreeNode("000" + i, "1级子系统管理" + i, new DefaultTreeNodeAttributes("1级子系统管理URL" + i));
            cList.add(node);
        }

        // 注意该根节点为不可视,从下级节点开始编译成JSON数据
        writeTreeJsonView(response, cList);
    }

    @RequestMapping
    public void getTreeData2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        String id = ServletRequestUtils.getStringParameter(request, "id");
        System.out.println("id=" + id);
        if (null == id) {// id为空时不返回数据
            writeTreeJsonView(response, new ArrayList<TreeNode>());
            return;
        }

        List<TreeNode> cList = new ArrayList<TreeNode>();
        for (int i = 1; i < 11; i++) {
            TreeNode node = new TreeNode("000" + i, "1级子系统管理" + i, new CheckBoxTreeNodeAttributes(("1级子系统管理URL" + i), "ckNameTest",
                    i % 2 == 0 ? true : false));
            cList.add(node);
        }
        // 注意该根节点为不可视,从下级节点开始编译成JSON数据
        writeTreeJsonView(response, cList);
    }

}
