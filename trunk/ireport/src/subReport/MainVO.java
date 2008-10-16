package subReport;

import java.util.ArrayList;
import java.util.List;

public class MainVO {
	private String title;
	private String time;
	private ArrayList<SubVO> subList;

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

	public ArrayList<SubVO> getSubList() {
		return subList;
	}

	public void setSubList(ArrayList<SubVO> subList) {
		this.subList = subList;
	}
}
