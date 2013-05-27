package com.tjhx.service.receipt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.receipt.InvoiceJpaDao;
import com.tjhx.entity.member.User;
import com.tjhx.entity.receipt.Invoice;

@Service
@Transactional(readOnly = true)
public class InvoiceApplyManager {
	@Resource
	private InvoiceJpaDao invoiceJpaDao;

	@Transactional(readOnly = false)
	public void addNewInvoiceApply(Invoice invoice, User user) {

		// 机构编号
		invoice.setOrgId(user.getOrganization().getId());
		// 设置申请日期
		invoice.setAppDateShow(DateUtils.getCurFormatDate("yyyy-MM-dd"));
		invoice.setAppDateY(DateUtils.getCurFormatDate("yyyy"));
		invoice.setAppDateM(DateUtils.getCurFormatDate("MM"));
		invoice.setAppDate(DateUtils.getCurrentDateShortStr());
		// 设置发票状态--申请
		invoice.setInvoiceStatus("1");
		// 取得是否邮寄客户1-需要0-不需要
		if ("0".equals(invoice.getNeedPost())) {
			invoice.setCustomerName(null);
			invoice.setCustomerTel(null);
			invoice.setCustomerAdd(null);
		}
		invoiceJpaDao.save(invoice);
	}

	public List<Invoice> getInvoiceApplyList(String orgId, String currentDate) {
		String appDateY = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "yyyy");
		String appDateM = DateUtils.transDateFormat(currentDate, "yyyyMMdd", "MM");

		List<Invoice> _list = (List<Invoice>) invoiceJpaDao.findByOrgId_AppDateY_AppDateM(orgId, appDateY, appDateM,
				new Sort(new Sort.Order(Sort.Direction.DESC, "appDate"), new Sort.Order(Sort.Direction.DESC, "uuid")));

		return _list;
	}

	@Transactional(readOnly = false)
	public void delInvoiceApplyByUuid(int uuid) {
		invoiceJpaDao.delete(uuid);
	}

	public Invoice getinvoiceApplyByUuid(Integer uuid) {
		return invoiceJpaDao.findOne(uuid);
	}
}
