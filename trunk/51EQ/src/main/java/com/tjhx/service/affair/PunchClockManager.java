package com.tjhx.service.affair;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;

import com.tjhx.common.utils.DateUtils;
import com.tjhx.dao.affair.PunchClockJpaDao;
import com.tjhx.dao.affair.PunchClockMyBatisDao;
import com.tjhx.daozk.CheckInOutMyBatisDao;
import com.tjhx.entity.affair.PunchClock;
import com.tjhx.entity.affair.PunchClock_List_Show;
import com.tjhx.entity.affair.PunchClock_Show;
import com.tjhx.entity.member.Employee;
import com.tjhx.entity.zknet.CheckInOut;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class PunchClockManager {

	@Resource
	private PunchClockJpaDao punchClockJpaDao;
	@Resource
	private PunchClockMyBatisDao punchClockMyBatisDao;
	@Resource
	private CheckInOutMyBatisDao checkInOutMyBatisDao;

	/**
	 * 取得考勤计算-重计算天数（含明天，列表第一个对象）
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<String> calOptDate() throws ParseException {
		List<String> _optDateList = new ArrayList<String>();

		String _now = DateUtils.getCurFormatDate("yyyy-MM-dd");
		String _tomorrow = DateUtils.getNextDateFormatDate(_now, 1, "yyyy-MM-dd");

		_optDateList.add(_tomorrow);
		_optDateList.add(_now);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");
		for (int i = 1; i < sysConfig.getPunchClockRecalDays(); i++) {
			_optDateList.add(DateUtils.getNextDateFormatDate(_now, -i, "yyyy-MM-dd"));
		}
		return _optDateList;
	}

	/**
	 * 删除已有打卡记录（按预订的重计算天数）
	 * 
	 * @param optDateList
	 */
	@Transactional(readOnly = false)
	private void delPunchClock(List<String> optDateList) {
		for (int i = 1; i < optDateList.size(); i++) {
			punchClockMyBatisDao.delPunchClockInfo(new PunchClock(optDateList.get(i)));
		}
	}

	/**
	 * 重计算打卡记录
	 * 
	 * @throws ParseException
	 */

	public void recalPunchClock() throws ParseException {
		// 取得考勤计算-重计算天数（含明天，列表第一个对象）
		List<String> optDateList = calOptDate();
		// 删除已有打卡记录（按预订的重计算天数）
		delPunchClock(optDateList);

		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		List<List<CheckInOut>> _tmpList = new ArrayList<List<CheckInOut>>();
		// 重计算打卡记录
		for (int i = 1; i < optDateList.size(); i++) {
			CheckInOut checkInOut = new CheckInOut();

			checkInOut.setOptDateStart(optDateList.get(i) + " " + sysConfig.getPunchClockStart());
			checkInOut.setOptDateEnd(optDateList.get(i - 1) + " " + sysConfig.getPunchClockEnd());

			List<CheckInOut> cList = checkInOutMyBatisDao.getCheckInOutList(checkInOut);

			int _userId = -1;

			List<CheckInOut> _tmpSubList = null;

			for (CheckInOut c : cList) {

				if (!c.getUserid().equals(_userId)) {
					_userId = c.getUserid();

					_tmpSubList = new ArrayList<CheckInOut>();
					_tmpList.add(_tmpSubList);

				}
				_tmpSubList.add(c);
			}
		}

		// 保存首尾打卡记录
		savePunchClock(_tmpList);
	}

	/**
	 * 保存首尾打卡记录
	 * 
	 * @param checkInOutpList
	 */
	@Transactional(readOnly = false)
	private void savePunchClock(List<List<CheckInOut>> checkInOutpList) {

		for (List<CheckInOut> _cList : checkInOutpList) {
			if (null == _cList || _cList.size() == 0) {
				continue;
			}

			String _y = null;
			String _m = null;
			String _d = null;

			if (_cList.size() > 0) {// 保存首条打卡记录
				CheckInOut _checkInOut = _cList.get(0);

				_y = DateUtils.transDateFormat(_checkInOut.getChecktime(), "yyyy");
				_m = DateUtils.transDateFormat(_checkInOut.getChecktime(), "MM");
				_d = DateUtils.transDateFormat(_checkInOut.getChecktime(), "dd");

				PunchClock p = new PunchClock();

				p.setZkid(_checkInOut.getUserid());
				p.setClockTime(_checkInOut.getChecktime());
				p.setClockTimeY(_y);
				p.setClockTimeM(_m);
				p.setClockTimeD(_d);
				p.setSn(_checkInOut.getSn());

				punchClockJpaDao.save(p);
			}

			if (_cList.size() > 1) {// 有两条以上的记录，保存最后的打卡记录
				CheckInOut _checkInOut = _cList.get(_cList.size() - 1);

				PunchClock p = new PunchClock();

				p.setZkid(_checkInOut.getUserid());
				p.setClockTime(_checkInOut.getChecktime());
				p.setClockTimeY(_y);
				p.setClockTimeM(_m);
				p.setClockTimeD(_d);
				p.setSn(_checkInOut.getSn());

				punchClockJpaDao.save(p);
			}

		}
	}

	/**
	 * 取得机构指定月份考勤信息表
	 * 
	 * @param orgId 机构编号
	 * @param clockTimeY 打卡时间-年
	 * @param clockTimeM 打卡时间-月
	 * @return
	 */
	public List<PunchClock_List_Show> getPunchClockList(String orgId, String clockTimeY, String clockTimeM,
			List<Employee> empList) {

		PunchClock punchClock = new PunchClock();
		punchClock.setOrgId(orgId);
		punchClock.setClockTimeY(clockTimeY);
		punchClock.setClockTimeM(clockTimeM);

		List<PunchClock> _dbList = punchClockMyBatisDao.getPunchClockList(punchClock);

		return formatPunchClockList(clockTimeY, clockTimeM, _dbList, empList);
	}

	/**
	 * 调整考勤信息表,准备页面显示
	 * 
	 * @param punchClockList
	 * @return
	 */
	private List<PunchClock_List_Show> formatPunchClockList(String clockTimeY, String clockTimeM,
			List<PunchClock> punchClockList, List<Employee> empList) {
		int days = DateUtils.getMonthDays(Integer.parseInt(clockTimeY), Integer.parseInt(clockTimeM));

		List<PunchClock_Show> _punchClockList = new ArrayList<PunchClock_Show>();
		for (int i = 1; i <= days; i++) {
			for (Employee employee : empList) {
				_punchClockList.add(new PunchClock_Show(clockTimeY, clockTimeM, String.format("%02d", i), employee
						.getUuid()));
			}
		}

		for (PunchClock_Show punchClock_Show : _punchClockList) {
			for (PunchClock punchClock : punchClockList) {

				if (myEquals(punchClock_Show, punchClock)) {
					punchClock_Show.copy(punchClock);
				}
			}
		}

		return formatPunchClockList(_punchClockList, empList.size());

	}

	/**
	 * 自定义比较方法
	 * 
	 * @param punchClock_Show
	 * @param punchClock
	 * @return
	 */
	private boolean myEquals(PunchClock_Show punchClock_Show, PunchClock punchClock) {
		if (punchClock_Show.getEmpUuid() != punchClock.getEmpUuid()) {
			return false;
		}

		if (!punchClock_Show.getClockTimeY().equals(punchClock.getClockTimeY())) {
			return false;
		}
		if (!punchClock_Show.getClockTimeM().equals(punchClock.getClockTimeM())) {
			return false;
		}
		if (!punchClock_Show.getClockTimeD().equals(punchClock.getClockTimeD())) {
			return false;
		}

		return true;
	}

	/**
	 * 整理考勤信息列表（准备输出）
	 * 
	 * @param punchClockList
	 * @param empListSize
	 * @return
	 */
	private List<PunchClock_List_Show> formatPunchClockList(List<PunchClock_Show> punchClockList, int empListSize) {
		List<PunchClock_List_Show> _list = new ArrayList<PunchClock_List_Show>();

		PunchClock_List_Show pList = null;
		for (int i = 0; i < punchClockList.size(); i++) {
			if (i % empListSize == 0) {
				pList = new PunchClock_List_Show(punchClockList.get(i).getClockTimeY() + "-"
						+ punchClockList.get(i).getClockTimeM() + "-" + punchClockList.get(i).getClockTimeD());

				_list.add(pList);
			}
			pList.addPunchClockShowObj(punchClockList.get(i));
		}

		return _list;
	}
}
