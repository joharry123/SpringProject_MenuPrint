package menu.com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeeklyMenu {

	private List<MealSectionMenu> mealSections = new ArrayList<MealSectionMenu>();
	private String restaurantName;
	private String startDate;
	private String endDate;    
	private List<String> calSelectedDaysForWeek = new ArrayList<String>();
	private List<LocalDate> calSelectedDaysForSort = new ArrayList<LocalDate>();
	private long selectedDailyFoodIngredientsCount;

	
	
	       
	
	public List<MealSectionMenu> getMealSections() {
		return mealSections;
	}
	public void setMealSections(List<MealSectionMenu> mealSections) {
		this.mealSections = mealSections;
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
	public List<String> getCalSelectedDaysForWeek() {
		return calSelectedDaysForWeek;
	}
	public void setCalSelectedDaysForWeek(List<String> calSelectedDaysForWeek) {
		this.calSelectedDaysForWeek = calSelectedDaysForWeek;
	}
	public List<LocalDate> getCalSelectedDaysForSort() {
		return calSelectedDaysForSort;
	}
	public void setCalSelectedDaysForSort(List<LocalDate> calSelectedDaysForSort) {
		this.calSelectedDaysForSort = calSelectedDaysForSort;
	}
	public long getSelectedDailyFoodIngredientsCount() {
		return selectedDailyFoodIngredientsCount;
	}
	public void setSelectedDailyFoodIngredientsCount(long selectedDailyFoodIngredientsCount) {
		this.selectedDailyFoodIngredientsCount = selectedDailyFoodIngredientsCount;
	}
  

	
	

}
