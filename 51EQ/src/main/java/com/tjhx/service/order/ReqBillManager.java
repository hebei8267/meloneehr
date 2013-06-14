package com.tjhx.service.order;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.SpringContextHolder;
import org.xml.sax.SAXException;

import com.tjhx.common.utils.FileUtils;
import com.tjhx.dao.order.ReqBillJpaDao;
import com.tjhx.dao.order.ReqBillMyBatisDao;
import com.tjhx.entity.order.ReqBill;
import com.tjhx.globals.SysConfig;

@Service
@Transactional(readOnly = true)
public class ReqBillManager {

	@Resource
	private ReqBillJpaDao reqBillJpaDao;
	@Resource
	private ReqBillMyBatisDao reqBillMyBatisDao;

	private final static String XML_CONFIG_READ_REQ_BILL = "/excel/Req_Bill_Org_Read_CFG.xml";
	private final static String XML_CONFIG_WRITE_REQ_BILL = "/excel/Req_Bill_Supplier_Template.xls";

	public void writeReqBillFileToSupplier(String batchId, String supplierName, List<ReqBill> list)
			throws ParsePropertyException, InvalidFormatException, IOException {
		SysConfig sysConfig = SpringContextHolder.getBean("sysConfig");

		Map<String, List<ReqBill>> map = new HashMap<String, List<ReqBill>>();
		map.put("reqBillList", list);

		// 自动建立文件夹
		FileUtils.mkdir(sysConfig.getReqBillSupplierOutputPath() + batchId + "/");

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(sysConfig.getReqBillSupplierTemplatePath() + XML_CONFIG_WRITE_REQ_BILL, map,
				sysConfig.getReqBillSupplierOutputPath() + batchId + "/" + supplierName + ".xls");
	}

	/**
	 * 读取门店要货单信息
	 * 
	 * @param orgId 门店编号
	 * @param filePath 文件全路径
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public List<ReqBill> readReqBillFile(String filePath) throws IOException, SAXException, InvalidFormatException {

		InputStream inputXML = new BufferedInputStream(
				ReqBillManager.class.getResourceAsStream(XML_CONFIG_READ_REQ_BILL));

		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);

		InputStream inputXLS = new BufferedInputStream(new FileInputStream(filePath));

		List<ReqBill> reqBillList = new ArrayList<ReqBill>();
		Map<String, List<ReqBill>> map = new HashMap<String, List<ReqBill>>();
		map.put("reqBillList", reqBillList);
		XLSReadStatus readStatus = mainReader.read(inputXLS, map);

		if (Boolean.TRUE.equals(readStatus.isStatusOK())) {
			return reqBillList;
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false)
	public void cleanBatchInfo(String batchId) {
		reqBillMyBatisDao.delReqBillByBatchId(batchId);
	}

	/**
	 * 保存门店要货单信息
	 * 
	 * @param batchId
	 * @param orgId
	 * @param reqBillList
	 */
	@Transactional(readOnly = false)
	public void saveReqBillFile(String batchId, String orgId, List<ReqBill> reqBillList) {

		int _index = 1;
		for (ReqBill reqBill : reqBillList) {
			reqBill.setBatchId(batchId);
			reqBill.setOrgId(orgId);
			reqBill.setIndex(_index);

			reqBill.setSupplierName(reqBill.getSupplierName().replaceAll("/", "_"));
			_index++;

			reqBillJpaDao.save(reqBill);
		}
	}

	public static void main(String[] args) throws InvalidFormatException, IOException, SAXException {
		ReqBillManager reqBillManager = new ReqBillManager();
		List<ReqBill> reqBillList = reqBillManager.readReqBillFile("D:\\eclipse-cdoi\\workspace\\BBB\\src\\DD.xls");
		if (null == reqBillList || reqBillList.size() == 0) {
			System.out.println("############无效文件");
		}
		// reqBillManager.saveReqBillFile("20120604", "02", reqBillList);
		for (ReqBill reqBill : reqBillList) {
			System.out.print(reqBill.getProductNo() + "\t");
			System.out.print(reqBill.getBarcode() + "\t");
			System.out.print(reqBill.getProductName() + "\t");
			System.out.print(reqBill.getInventoryNum() + "\t");
			System.out.print(reqBill.getAppNum() + "\t");
			System.out.print(reqBill.getRefPrice() + "\t");
			System.out.print(reqBill.getSupplierName() + "\t");
			System.out.print(reqBill.getRemarks() + "\t");
			System.out.print(reqBill.getRemarks() == null);
			System.out.println();
		}
	}
}
