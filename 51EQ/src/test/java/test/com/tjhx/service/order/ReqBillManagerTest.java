package test.com.tjhx.service.order;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.globals.SysConfig;
import com.tjhx.service.order.ReqBillManager;

public class ReqBillManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ReqBillManager reqBillManager;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;

	// ##############################################
	// ??????????????????????????????????????????????
	private static String batchId = "20130707";

	@Test
	@Rollback(false)
	public void test00() throws InvalidFormatException, IOException, SAXException {
		// 处理编号
		reqBillManager.cleanBatchInfo(batchId);
	}

	@Test
	@Rollback(false)
	public void test01() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\01D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "01", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test02() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\02D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "02", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test03() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\03D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "03", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test04() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\04D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "04", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test05() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\05D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "05", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test06() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\06D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "06", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test07() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\07D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "07", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test08() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\08D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "08", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test09() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\09D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "09", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test10() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\10D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "10", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test11() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\11D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "11", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test12() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\12D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "12", reqBillList);
	}

	@Test
	@Rollback(false)
	public void test13() throws InvalidFormatException, IOException, SAXException {

		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\门店要货单-输入\\13D.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		reqBillManager.saveReqBillFile(batchId, "13", reqBillList);
	}

	private String getOrgId(List<ReqBill> list) {
		List<String> defOrgIdArr = new ArrayList<String>();
		defOrgIdArr.add("01");
		defOrgIdArr.add("02");
		defOrgIdArr.add("03");
		defOrgIdArr.add("04");
		defOrgIdArr.add("05");
		defOrgIdArr.add("06");
		defOrgIdArr.add("07");
		defOrgIdArr.add("08");
		defOrgIdArr.add("09");
		defOrgIdArr.add("10");
		defOrgIdArr.add("11");
		defOrgIdArr.add("12");
		defOrgIdArr.add("13");

		List<String> orgIdArr = new ArrayList<String>();
		for (ReqBill reqBill : list) {
			orgIdArr.add(reqBill.getOrgId());
		}

		StringBuffer orgArray = new StringBuffer();
		for (String org : defOrgIdArr) {
			if (orgIdArr.contains(org)) {
				orgArray.append(org);
			} else {
				orgArray.append("  ");
			}
			orgArray.append("      ");
		}

		return orgArray.toString();
	}

	@Test
	public void output1() throws ParsePropertyException, InvalidFormatException, IOException {

		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);

		List<ReqBill> _list = new ArrayList<ReqBill>();
		for (Supplier supplier : supList) {

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());

			List<ReqBill> list = reqBillMyBatisDao.getOrgListBySupplier(reqBill);

			_index++;
			ReqBill _reqBill = new ReqBill(); // 最终统计单个供应商本次供应多少个门店
			_reqBill.setIndex(_index);
			_reqBill.setSupplierName(supplier.getName());
			_reqBill.setOrgId(getOrgId(list));
			_list.add(_reqBill);

			reqBillManager.writeReqBillFileToHeadOffice(batchId, supplier.getName(), _list);
		}
	}

	// 生产供应商文件
	@Test
	public void output2() throws InvalidFormatException, IOException, SAXException {
		int _index = 0;
		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);
		for (Supplier supplier : supList) {

			System.out.println(supplier.getName());

			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);
			list = addBlankRow(list);
			reqBillManager.writeReqBillFileToSupplier(batchId, supplier.getName(), list);

			for (ReqBill reqBill2 : list) {
				_index++;
				System.out.print(reqBill2.getOrgId() + "\t");
				System.out.print(reqBill2.getProductName() + "\t");
				System.out.println();
			}
			System.out.println("############################################################");
		}

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@全部数据行" + _index);
	}

	// 生产供应商文件
	@Test
	public void output3() throws FileNotFoundException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId(batchId);
		for (Supplier supplier : supList) {
			ReqBill reqBill = new ReqBill();
			reqBill.setBatchId(batchId);
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);

			reqBillManager.writeReqBillImageFileToSupplier(sysConfig.getReqBillSupplierOutputPath() + batchId + "/"
					+ supplier.getName() + "_" + batchId + ".xls",
					getImagePathList(sysConfig.getProductImgPath(), list));
		}
	}

	private static List<String> getImagePathList(String productImgPath, List<ReqBill> list) {
		list = addBlankRow(list);
		List<String> imagePathList = new ArrayList<String>();
		for (ReqBill reqBill : list) {

			// System.out.println(productImgPath + reqBill.getProductNo() +
			// ".jpg");
			imagePathList.add(productImgPath + reqBill.getProductNo() + ".jpg");
		}
		return imagePathList;
	}

	private static List<ReqBill> addBlankRow(List<ReqBill> list) {
		List<ReqBill> _list = new ArrayList<ReqBill>();

		String _tmpOrgId = null;
		for (ReqBill reqBill : list) {
			if (!reqBill.getOrgId().equals(_tmpOrgId)) {
				if (null != _tmpOrgId) {
					_list.add(new ReqBill());
					_list.add(new ReqBill());
				}
				_tmpOrgId = reqBill.getOrgId();
			}
			_list.add(reqBill);
		}
		return _list;
	}
}