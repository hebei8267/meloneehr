package test.com.tjhx.service.order;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.springside.modules.test.spring.SpringTransactionalTestCase;
import org.xml.sax.SAXException;

import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.info.Supplier;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.service.order.ReqBillManager;

public class ReqBillManagerTest extends SpringTransactionalTestCase {
	@Resource
	private ReqBillManager reqBillManager;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;

	@Test
	public void test00() throws InvalidFormatException, IOException, SAXException {

		List<Supplier> supList = reqBillMyBatisDao.getSupplierListByBatchId("20120604");
		for (Supplier supplier : supList) {

			System.out.println(supplier.getName());

			ReqBill reqBill = new ReqBill();
			reqBill.setBarcode("20120604");
			reqBill.setSupplierName(supplier.getName());
			List<ReqBill> list = reqBillMyBatisDao.getReqBillList(reqBill);
			reqBillManager.writeReqBillFileToSupplier("20120604", supplier.getName(), list);
			for (ReqBill reqBill2 : list) {
				System.out.print(reqBill2.getOrgId() + "\t");
				System.out.print(reqBill2.getProductName() + "\t");
				System.out.println();
			}
			System.out.println("############################################################");
		}
	}

	// @Test
	// @Rollback(false)
	// public void test00() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// reqBillManager.cleanBatchInfo("20120604");
	// }
	//
	// @Test
	// @Rollback(false)
	// public void test01() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// List<ReqBill> reqBillList =
	// reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\BBBB.xls");
	//
	// reqBillManager.saveReqBillFile("20120604", "01", reqBillList);
	// }
	//
	// @Test
	// @Rollback(false)
	// public void test02() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// List<ReqBill> reqBillList =
	// reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\AAA.xls");
	//
	// reqBillManager.saveReqBillFile("20120604", "02", reqBillList);
	// }
	//
	// @Test
	// @Rollback(false)
	// public void test03() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// List<ReqBill> reqBillList =
	// reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\CC.xls");
	// if (null == reqBillList || reqBillList.size() == 0) {
	// System.out.println("############无效文件");
	// }
	// reqBillManager.saveReqBillFile("20120604", "03", reqBillList);
	// }
	//
	// @Test
	// @Rollback(false)
	// public void test04() throws InvalidFormatException, IOException,
	// SAXException {
	//
	// List<ReqBill> reqBillList =
	// reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\DD.xls");
	// if (null == reqBillList || reqBillList.size() == 0) {
	// System.out.println("############无效文件");
	// }
	// reqBillManager.saveReqBillFile("20120604", "04", reqBillList);
	// }
	// select org_id,product_name,supplier_name from t_req_bill order by
	// supplier_name,org_id
}
