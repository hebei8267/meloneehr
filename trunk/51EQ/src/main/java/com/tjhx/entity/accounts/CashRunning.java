package com.tjhx.entity.accounts;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tjhx.entity.IdEntity;

/**
 * 现金情况
 */
@Entity
@Table(name = "T_CASH_RUNNING")
// 默认的缓存策略.
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CashRunning extends IdEntity{

//	日期 
//	全天班 
//	头天余额  
//	当前销售   
//	应有总额（？）  
//	实际现金--交班 
//	
//	  刷卡金额@@@@@@  
//	  盈亏金额（？）  
//	  存款金额  
//	  存款人
//	  存款银行（选择）  
//	  卡号（选择）
//	  
//	  当班人 
//	   
//	留存金额    
//	
//	备注
//	
//	
//	天气情况  温度   

}
