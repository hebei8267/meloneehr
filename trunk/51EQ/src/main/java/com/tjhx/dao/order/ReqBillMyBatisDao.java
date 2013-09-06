package com.tjhx.dao.order;

import java.util.List;

import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReqBill;

public interface ReqBillMyBatisDao {
	/**
	 * 根据批次号删除门店要货单信息
	 * 
	 * @param batchId
	 */
	public void delReqBillByBatchId(String batchId);

	/**
	 * 根据批次号取得供应商信息
	 * 
	 * @param batchId
	 * @return
	 */
	public List<Supplier> getSupplierListByBatchId(String batchId);

	/**
	 * 根据参数(批次号、门店号、供应商名称)取得门店要货单信息
	 * 
	 * @param reqBill
	 * @return
	 */
	public List<ReqBill> getReqBillList(ReqBill reqBill);

	/**
	 * 根据参数(批次号、供应商名称)取得门店信息
	 * 
	 * @param reqBill
	 * @return
	 */
	public List<ReqBill> getOrgListBySupplier(ReqBill reqBill);
}