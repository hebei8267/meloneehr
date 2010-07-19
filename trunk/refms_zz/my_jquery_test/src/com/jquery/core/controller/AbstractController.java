package com.jquery.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jquery.core.modules.tree.TreeNode;

public abstract class AbstractController {
    /** Ajax处理结果 默认页面编码UTF-8 */
    public static String RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8;";

    /**
     * Tree Ajax响应方法(该方法仅对Ajax调用有效)
     * 
     * @param response HttpServletResponse
     * @param cList 要格式化的成JSON数据的List对象
     * @throws IOException
     */
    protected void writeTreeJsonView(HttpServletResponse response, List<TreeNode> cList) throws IOException {
        JSONArray jSONObject = JSONArray.fromObject(cList);
        // System.out.println(jSONObject.toString());
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    /**
     * DataGrid Ajax响应方法(该方法仅对Ajax调用有效)
     * 
     * @param response HttpServletResponse
     * @param obj 要格式化的成JSON数据的DataGrid对象
     * @throws IOException
     */
    protected void writeDataGridJsonView(HttpServletResponse response, Object obj) throws IOException {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }
}
