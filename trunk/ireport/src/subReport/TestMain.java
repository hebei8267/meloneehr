package subReport;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestMain {
	public static void main(String[] args) {
		String reportPath = "main_report_1.jasper";

		Map parameters = new HashMap();

		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(new ReportData().getData()));

			JasperExportManager.exportReportToPdfFile(jasperPrint, "sub_report.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
