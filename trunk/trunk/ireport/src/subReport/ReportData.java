package subReport;

import java.util.ArrayList;
import java.util.List;

public class ReportData {

	public List getData() {
		/**
		 * 测试数据，在使用中，不需要继承JRAbstractBeanDataSourceProvider，
		 * 只需要把集合类封装到JRBeanCollectionDataSource中就可以了
		 */
		ArrayList<MainVO> mainList = new ArrayList<MainVO>();

		for (int i = 0; i < 5; i++) {
			MainVO mvo = new MainVO();
			mvo.setTitle("title标题 -" + i);
			mvo.setTime("time时间 -" + i);

			ArrayList<SubVO> subList = new ArrayList<SubVO>();
			for (int j = 0; j < 5; j++) {
				SubVO svo = new SubVO();
				svo.setName("name名字 -" + i + "-" + j);

				subList.add(svo);
			}

			mvo.setSubList(subList);
			mainList.add(mvo);
		}

		return mainList;
	}
}
