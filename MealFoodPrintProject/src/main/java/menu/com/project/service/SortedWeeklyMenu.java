package menu.com.project.service;

import java.util.ArrayList;
import java.util.List;

import menu.com.project.service.MealSectionMenu;
import menu.com.project.service.WeeklyMenu;

public class SortedWeeklyMenu {  
	
	private List<WeeklyMenu> sortedMealSectionMenuMenu = new ArrayList<WeeklyMenu>();
	
	

	public List<WeeklyMenu> getSortedMealSectionMenuMenu() {
		return sortedMealSectionMenuMenu;
	}

	public void setSortedMealSectionMenuMenu(List<WeeklyMenu> sortedMealSectionMenuMenu) {
		this.sortedMealSectionMenuMenu = sortedMealSectionMenuMenu;
	}


}
