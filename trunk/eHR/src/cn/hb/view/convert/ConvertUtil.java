package cn.hb.view.convert;

import java.util.ArrayList;
import java.util.List;

import cn.hb.core.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConvertUtil<T> {
    public final static String BLANK = " ";

    /**
     * 组和名字
     * 
     * @param firstName 名字
     * @param lastName 名字
     * @return 全名字
     */
    public static String formatName(String firstName, String lastName) {
        return firstName + BLANK + lastName;
    }

    /**
     * 把[对象列表]对象转换为JSONArray对象
     * 
     * @param objListSize 对象列表的实际大小（分页列表对象的全部数据大小）
     * @param objList 对象列表
     * @return JSONArray对象
     */
    public JSONObject javaListToJSONObject(int totalProperty, List<T> objList) {

        PageDataListBean<T> pageData = new PageDataListBean<T>();
        pageData.setTotalProperty(totalProperty);
        pageData.setDataList(objList);

        JSONObject jSONObject = JSONObject.fromObject(pageData);
        return jSONObject;

    }

    /**
     * 把[对象列表]对象转换为JSONArray对象
     * 
     * @param objListSize 对象列表的实际大小（分页列表对象的全部数据大小）
     * @param objList 对象列表
     * @return JSONArray对象
     */
    public JSONObject javaListToJSONObject(List<T> objList) {
        PageDataListBean<T> pageData = new PageDataListBean<T>();
        if (objList != null) {
            pageData.setTotalProperty(objList.size());
        } else {
            pageData.setTotalProperty(0);
        }
        pageData.setDataList(objList);

        JSONObject jSONObject = JSONObject.fromObject(pageData);
        return jSONObject;
    }

    /**
     * 把JsonString对象转换为List对象
     * 
     * @param jsonString JsonString对象
     * @param pojoClass
     * @return List对象
     */
    @SuppressWarnings("unchecked")
    public static List<Object> jsonStringToPojoList(String jsonString, Class pojoClass) {
        String _jsonString = formatJsonString(jsonString);
        List<Object> list = new ArrayList<Object>();

        if (!StringUtil.isEmpty(_jsonString)) {
            JSONArray jsonArray = JSONArray.fromObject(_jsonString);

            JSONObject jsonObject = null;
            Object pojoValue = null;

            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                pojoValue = JSONObject.toBean(jsonObject, pojoClass);
                list.add(pojoValue);
            }
        }
        return list;
    }

    private static String formatJsonString(String jsonString) {
        if (!StringUtil.isEmpty(jsonString)) {
            if (jsonString.startsWith("")) {
                String reString = jsonString.substring(1, jsonString.length() - 1);
                return reString.replaceAll("\\\\\"", "\"");
            }
        }
        return "";
    }
}
