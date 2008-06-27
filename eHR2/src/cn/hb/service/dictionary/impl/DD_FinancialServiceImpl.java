package cn.hb.service.dictionary.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.entity.dictionary.financial.SalaryType;
import cn.hb.service.dictionary.IDD_FinancialService;

/**
 * @author kaka
 * 
 * 数据字典-财务服务
 */
@Component("DD_FinancialService")
@Scope("prototype")
public class DD_FinancialServiceImpl implements IDD_FinancialService {

    public Integer addSalaryTypeInfo_Service(SalaryType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delSalaryTypeInfo_Service(String salaryTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SalaryType> getSalaryTypeInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateSalaryTypeInfo_Service(SalaryType obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
