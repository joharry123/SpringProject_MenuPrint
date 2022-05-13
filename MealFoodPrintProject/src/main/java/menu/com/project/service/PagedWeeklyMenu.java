package menu.com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


  
public class PagedWeeklyMenu { 

	private List<Page> pageList = new ArrayList<Page>();
	private int pageNumber;
	private int totalPageNum;
	private List<LocalDate> calSelectedDaysForSort = new ArrayList<LocalDate>();
	private List<String> calSelectedDaysForWeek = new ArrayList<String>();
	private long selectedDailyFoodIngredientsCount;
	private String restaurantName;
	private String startDate; 
	private String endDate;  
	
	
	public List<Page> getPageList() {
		return pageList; 
	}
	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public List<LocalDate> getCalSelectedDaysForSort() {
		return calSelectedDaysForSort;
	}
	public void setCalSelectedDaysForSort(List<LocalDate> calSelectedDaysForSort) {
		this.calSelectedDaysForSort = calSelectedDaysForSort;
	}
	public List<String> getCalSelectedDaysForWeek() {
		return calSelectedDaysForWeek;
	}
	public void setCalSelectedDaysForWeek(List<String> calSelectedDaysForWeek) {
		this.calSelectedDaysForWeek = calSelectedDaysForWeek;
	}
	public long getSelectedDailyFoodIngredientsCount() {
		return selectedDailyFoodIngredientsCount;
	}
	public void setSelectedDailyFoodIngredientsCount(long selectedDailyFoodIngredientsCount) {
		this.selectedDailyFoodIngredientsCount = selectedDailyFoodIngredientsCount;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	  
	      
	

	

}
