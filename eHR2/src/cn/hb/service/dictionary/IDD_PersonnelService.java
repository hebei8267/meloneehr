package cn.hb.service.dictionary;

import java.util.List;

import cn.hb.core.service.IService;
import cn.hb.entity.dictionary.personnel.Currculum;

public interface IDD_PersonnelService extends IService {
    /**
     * 添加学历信息
     * 
     * @param obj 学历
     * @return 0-添加成功 1-添加失败
     */
    public abstract Integer addCurrculumInfo_Service(Currculum obj);

    /**
     * 删除学历信息
     * 
     * @param countryID 学历ID
     * @return 0-删除成功 1-删除失败
     */
    public abstract Integer delCurrculumInfo_Service(String countryID);

    /**
     * 取得学历信息列表
     */
    public abstract List<Currculum> getCurrculumInfoList_Service();

    /**
     * 更新学历信息
     * 
     * @param obj 学历
     * @return 0-更新成功 1-更新失败(数据不存在) 2-更新失败(存在类似的学历)
     */
    public abstract Integer updateCurrculumInfo_Service(Currculum obj);
}
