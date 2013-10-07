package com.tjhx.entity.info;

import java.math.BigDecimal;

public class SalesDayTotal {
	/** 机构编号 */
	private String id;// TODO
	/** 机构资金编号 */
	private String branchNo;
	/** 日期 */
	private String optDate;// TODO
	/** 日期-年 */
	private String optDateY;
	/** 日期-月 */
	private String optDateM;
	/** 类别编号 */
	private String itemClsNo;// TODO
	/** 类别名称 */
	private String itemClsName;
	/** 销售数量 */
	private BigDecimal saleQty;
	/** 销售金额 */
	private BigDecimal saleAmt;
	/** 退货数量 */
	private BigDecimal retQty;
	/** 退货金额 */
	private BigDecimal retAmt;
	/** 赠送数量 */
	private BigDecimal giveQty;
	/** 实销数量 */
	private BigDecimal saleRqty;
	/** 实销金额 */
	private BigDecimal saleRamt;

}
