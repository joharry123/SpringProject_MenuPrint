package menu.com.project.service;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private List<MealSectionMenu> page = new ArrayList<MealSectionMenu>();
	private int pageNum;

	public Page(int pageNum) {
		super();
		this.pageNum = pageNum;
		  
	}
	



	public List<MealSectionMenu> getPage() {
		return page;
	}     

	public void setPage(List<MealSectionMenu> page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



}
