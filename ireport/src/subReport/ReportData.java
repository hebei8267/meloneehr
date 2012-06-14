package subReport;

import java.util.ArrayList;
import java.util.List;

//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportData {

	public List<MainVO> getData() {
		/**
		 * 测试数据，在使用中，不需要继承JRAbstractBeanDataSourceProvider，
		 * 只需要把集合类封装到JRBeanCollectionDataSource中就可以了
		 */
		List<MainVO> mainList = new ArrayList<MainVO>();

		for (int i = 0; i < 5; i++) {
			MainVO mvo = new MainVO();
			mvo.setTitle("title标题 -" + i);
			mvo.setTime("time时间 -" + i);

			List<SubVO> subList = new ArrayList<SubVO>();
			for (int j = 0; j < 5; j++) {
				SubVO svo = new SubVO();
				svo.setName("SubVO1 name名字 -" + i + "-" + j);

				subList.add(svo);
			}
			
			List<SubVO2> subList2 = new ArrayList<SubVO2>();
			for (int j = 0; j < 5; j++) {
				SubVO2 svo2 = new SubVO2();
				svo2.setName("SubVO2 name名字 -" + i + "-" + j);

				subList2.add(svo2);
			}

			mvo.setSubList(subList);
			mvo.setSub2List(subList2);
			mainList.add(mvo);
		}

		return mainList;
	}
}
