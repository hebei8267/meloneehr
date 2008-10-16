package subReport;

import java.util.List;

public class MainVO {
	private String title;
	private String time;
	private List<SubVO> subList;

	private List<SubVO2> sub2List;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<SubVO> getSubList() {
		return subList;
	}

	public void setSubList(List<SubVO> subList) {
		this.subList = subList;
	}

	public List<SubVO2> getSub2List() {
		return sub2List;
	}

	public void setSub2List(List<SubVO2> sub2List) {
		this.sub2List = sub2List;
	}
}
