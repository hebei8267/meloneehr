import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class TestReport {
	public static void main(String[] args) {
		TestReport.showReport();
	}

	@SuppressWarnings("unchecked")
	private static void showReport() {
		String reportPath = "Untitled_report_1.jasper";
		Map parameters = new HashMap();
		// 如果报表中有用到变量，在这里给它赋值．
		// parameters.put("ReportTitle", " 报表标题 ");
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new DailySalesDataSource());

			JasperExportManager.exportReportToPdfFile(jasperPrint, "simple_report.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
