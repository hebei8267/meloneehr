package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.employment.ContractType;
import cn.hb.entity.dictionary.employment.EmployType;
import cn.hb.entity.dictionary.employment.EmployeeWorkState;
import cn.hb.entity.dictionary.employment.HoldingJobType;

/**
 * @author kaka
 * 
 * 数据字典-雇佣服务
 */
public interface IDD_EmploymentService extends IService {
    /**
     * 添加合同类型信息
     * 
     * @param obj 合同类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addContractTypeInfo_Service(ContractType obj);

    /**
     * 删除合同类型信息
     * 
     * @param contractTypeID 合同类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delContractTypeInfo_Service(String contractTypeID);

    /**
     * 取得合同类型信息列表
     */
    public abstract List<ContractType> getContractTypeInfoList_Service();

    /**
     * 更新合同类型信息
     * 
     * @param obj 合同类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的合同类型)
     */
    public abstract Integer updateContractTypeInfo_Service(ContractType obj);

    /**
     * 添加员工当前工作状态信息
     * 
     * @param obj 员工当前工作状态
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addEmployeeWorkStateInfo_Service(EmployeeWorkState obj);

    /**
     * 删除员工当前工作状态信息
     * 
     * @param employeeWorkStateID 员工当前工作状态ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delEmployeeWorkStateInfo_Service(String employeeWorkStateID);

    /**
     * 取得员工当前工作状态信息列表
     */
    public abstract List<EmployeeWorkState> getEmployeeWorkStateInfoList_Service();

    /**
     * 更新员工当前工作状态信息
     * 
     * @param obj 员工当前工作状态
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的员工当前工作状态)
     */
    public abstract Integer updateEmployeeWorkStateInfo_Service(EmployeeWorkState obj);

    /**
     * 添加雇佣类型信息
     * 
     * @param obj 雇佣类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addEmployTypeInfo_Service(EmployType obj);

    /**
     * 删除雇佣类型信息
     * 
     * @param employTypeID 雇佣类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delEmployTypeInfo_Service(String employTypeID);

    /**
     * 取得雇佣类型信息列表
     */
    public abstract List<EmployType> getEmployTypeInfoList_Service();

    /**
     * 更新雇佣类型信息
     * 
     * @param obj 雇佣类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的雇佣类型)
     */
    public abstract Integer updateEmployTypeInfo_Service(EmployType obj);

    /**
     * 添加任职类型信息
     * 
     * @param obj 任职类型
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addHoldingJobTypeInfo_Service(HoldingJobType obj);

    /**
     * 删除任职类型信息
     * 
     * @param holdingJobTypeID 任职类型ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delHoldingJobTypeInfo_Service(String holdingJobTypeID);

    /**
     * 取得任职类型信息列表
     */
    public abstract List<HoldingJobType> getHoldingJobTypeInfoList_Service();

    /**
     * 更新任职类型信息
     * 
     * @param obj 任职类型
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的任职类型)
     */
    public abstract Integer updateHoldingJobTypeInfo_Service(HoldingJobType obj);

}
