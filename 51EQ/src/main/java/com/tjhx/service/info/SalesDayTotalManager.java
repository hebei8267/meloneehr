package com.tjhx.service.info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.info.SalesDayTotalJpaDao;
import com.tjhx.dao.info.SalesDayTotalMyBatisDao;
import com.tjhx.daobw.DailySaleTotalMyBatisDao;
import com.tjhx.entity.bw.DailySaleTotal;
import com.tjhx.entity.info.SalesDayTotal;
import com.tjhx.entity.struct.Organization;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.struct.OrganizationManager;

@Service
@Transactional(readOnly = true)
public class SalesDayTotalManager {
	@Resource
	private OrganizationManager orgManager;
	@Resource
	private DailySaleTotalMyBatisDao dailySaleTotalMyBatisDao;
	@Resource
	private SalesDayTotalMyBatisDao salesDayTotalMyBatisDao;
	@Resource
	private SalesDayTotalJpaDao salesDayTotalJpaDao;

	/**
	 * 取得百威系统各门店日销售信息
	 * 
	 * @throws ParseException
	 */
	public void getOrgSalesDayTotal() throws ParseException {
		// 取得门店日销售计算-重计算天数
		List<String> optDateList = calOptDate();
		List<Organization> _orgList = orgManager.getSubOrganization();

		for (String optDate : optDateList) {
			// 删除指定日期的所有门店销售信息
			salesDayTotalMyBatisDao.delSalesDayTotalInfo(optDate);

			for (Organization org : _orgList) {
				DailySaleTotal _param = new DailySaleTotal();
				_param.setOptDate(optDate);
				_param.setBranchNo(org.getBwBranchNo());

				List<DailySaleTotal> _dailySaleTotalList = dailySaleTotalMyBatisDao.getDailySaleTotalList(_param);

				for (DailySaleTotal _bwDailySaleTotal : _dailySaleTotalList) {
					SalesDayTotal _salesDay = new SalesDayTotal();

					// 机构编号
					_salesDay.setOrgId(org.getId());
					// 机构资金编号
					_salesDay.setBranchNo(org.getBwBranchNo());
					// 日期
					_salesDay.setOptDate(optDate);
					// 日期-年
					String optDateY = DateUtils.transDateFormat(optDate, "yyyyMMdd", "yyyy");
					String optDateM = DateUtils.transDateFormat(optDate, "yyyyMMdd", "MM");
					_salesDay.setOptDateY(optDateY);
					// 日期-月
					_salesDay.setOptDateM(optDateM);
					// 类别编号
					_salesDay.setItemClsNo(_bwDailySaleTotal.getItemClsNo());
					// 销售数量
					_salesDay.setSaleQty(_bwDailySaleTotal.getSaleQty());
					// 销售金额
					_salesDay.setSaleAmt(_bwDailySaleTotal.getSaleAmt());
					// 退货数量
					_salesDay.setRetQty(_bwDailySaleTotal.getRetQty());
					// 退货金额
					_salesDay.setRetAmt(_bwDailySaleTotal.getRetAmt());
					// 赠送数量
					_salesDay.setGiveQty(_bwDailySaleTotal.getGiveQty());
					// 实销数量
					_salesDay.setSaleRqty(_bwDailySaleTotal.getSaleRqty());
					// 实销金额
					_salesDay.setSaleRamt(_bwDailySaleTotal.getSaleRamt());
					// 实销价格
					if (_bwDailySaleTotal.getSaleRqty().compareTo(BigDecimal.ZERO) == 0) {
						_salesDay.setSalePrice(new BigDecimal(0));
					} else {
						_salesDay.setSalePrice(_bwDailySaleTotal.getSaleRamt().divide(_bwDailySaleTotal.getSaleRqty(),
								2, BigDecimal.ROUND_UP));
					}

					salesDayTotalJpaDao.save(_salesDay);
				}
			}
		}
	}

	/**
	 * 取得门店日销售计算-重计算天数
	 * 
	 * @return
	 * @throws ParseException
	 */
	List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurrentDateShortStr();

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getSalesDayTotalDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyyMMdd"));
		}

		_optDateList.add(_now);

		return _optDateList;
	}

	/**
	 * 取得合计实销金额（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotal> getSumSaleRamtList(SalesDayTotal salesDayTotal) {
		return salesDayTotalMyBatisDao.getSumSaleRamtList(salesDayTotal);
	}

	/**
	 * 取得合计实销数量（指定时间区间/机构）
	 * 
	 * @param salesDayTotal
	 * @return
	 */
	public List<SalesDayTotal> getSumSaleRqtyList(SalesDayTotal salesDayTotal) {
		return salesDayTotalMyBatisDao.getSumSaleRqtyList(salesDayTotal);
	}

	/**
	 * 取得合计实销数量（指定年/月/机构）
	 * 
	 * @param _param
	 * @return
	 */
	public List<SalesDayTotal> getSumSalesMonthTotalList(SalesDayTotal salesDayTotal) {
		return	salesDayTotalMyBatisDao.getSumSalesMonthTotalList(salesDayTotal);
	}
}
