package cn.hb.service.dictionary.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.hb.entity.dictionary.personnel.Currculum;
import cn.hb.entity.dictionary.personnel.EducateSpecialty;
import cn.hb.entity.dictionary.personnel.IdentifieationType;
import cn.hb.entity.dictionary.personnel.MarriageState;
import cn.hb.service.dictionary.IDD_PersonnelService;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_JsonBean;
import cn.hb.service.vo.dictionary.personnel.EducateSpecialty_TreeNode;

/**
 * @author kaka
 * 
 * 数据字典-个人信息服务
 */
@Component("DD_PersonnelService")
@Scope("prototype")
public class DD_PersonnelServiceImpl implements IDD_PersonnelService {

    public Integer addCurrculumInfo_Service(Currculum obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addEducateSpecialtyInfo_Service(EducateSpecialty obj, String educateSpecialtyID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addIdentifieationTypeInfo_Service(IdentifieationType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer addMarriageStateInfo_Service(MarriageState obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delCurrculumInfo_Service(String currculumID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delEducateSpecialtyInfo_Service(String educateSpecialtyID, String educateSpecialtyID2) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delIdentifieationTypeInfo_Service(String identifieationTypeID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer delMarriageStateInfo_Service(String marriageStateID) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Currculum> getCurrculumInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EducateSpecialty_JsonBean> getEducateSpecialtyInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public EducateSpecialty_TreeNode getEducateSpecialtyInfoTree_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IdentifieationType> getIdentifieationTypeInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MarriageState> getMarriageStateInfoList_Service() {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateCurrculumInfo_Service(Currculum obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateEducateSpecialtyInfo_Service(EducateSpecialty obj, String educateSpecialtyID) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateIdentifieationTypeInfo_Service(IdentifieationType obj) {
        // TODO Auto-generated method stub
        return null;
    }

    public Integer updateMarriageStateInfo_Service(MarriageState obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
