package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.financial.SalaryType;

/**
 * @author kaka
 * 
 * 数据字典-财务服务
 */
public interface IDD_FinancialService extends IService {
    /**
     * 添加薪水类型信息
     * 
     * @param obj 薪水类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addSalaryTypeInfo_Service(SalaryType obj);

    /**
     * 删除薪水类型信息
     * 
     * @param salaryTypeID 薪水类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delSalaryTypeInfo_Service(String salaryTypeID);

    /**
     * 取得薪水类型信息列表
     */
    public abstract List<SalaryType> getSalaryTypeInfoList_Service();

    /**
     * 更新薪水类型信息
     * 
     * @param obj 薪水类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的薪水类型)
     */
    public abstract Integer updateSalaryTypeInfo_Service(SalaryType obj);

}
