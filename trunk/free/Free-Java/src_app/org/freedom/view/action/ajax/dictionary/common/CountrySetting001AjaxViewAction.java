/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.view.action.ajax.dictionary.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.freedom.core.view.action.AbstractViewAction;
import org.freedom.core.view.vo.ajax.DataListBean;
import org.freedom.core.view.vo.ajax.JosnViewObject;
import org.freedom.entity.dictionary.common.Country;
import org.freedom.services.dictionary.ICountryService;
import org.freedom.view.action.SecurityMesssageID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 国家信息(数据字典)
 * 
 * @author 何贝
 * @since JDK1.5
 */
@Controller
public class CountrySetting001AjaxViewAction extends AbstractViewAction {

    /**
     * 
     */
    private static final long serialVersionUID = 6436481646978636220L;
    @Autowired
    private ICountryService countryService;

    @RequestMapping("/dictionary/common/countrySetting/001/getAllCountryInfoListAction.ajax")
    public void getAllCountryInfoListAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Country> countryList = countryService.getAllCountryInfoListService();

        // Json对象格式化
        DataListBean<Country> dataList = new DataListBean<Country>();
        dataList.setDataList(countryList);

        JSONObject jSONObject = JSONObject.fromObject(dataList);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    @RequestMapping("/dictionary/common/countrySetting/001/updateCountryInfoAction.ajax")
    public void updateCountryInfoAction(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException,
            InvocationTargetException, IOException, ServletRequestBindingException {
        Country country = new Country();
        // 取得request里面的参数
        BeanUtils.populate(country, request.getParameterMap());

        boolean _result = countryService.updateCountryInfoService(country);
        JosnViewObject outObj = new JosnViewObject();
        if (!_result) {
            outObj.setProcessResult(false);

            outObj.setResultMsg(getMessage(request, SecurityMesssageID.ERROR_DATA_NO_SYNCHRONIZATION));

        }
        JSONObject jSONObject = JSONObject.fromObject(outObj);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(jSONObject.toString());
    }

    public ICountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }
}
