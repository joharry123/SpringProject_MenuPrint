package menu.com.project.service;

import java.util.ArrayList;
import java.util.List;

public class MealSectionMenu {

	private List<DailyFoodIngredients> dailyMenus = new ArrayList<DailyFoodIngredients>();
	private String mealTypeCode;
	private String mealTypeName;
	
	

//	public void addDailyFoodIngredients(DailyFoodIngredients dailyFoodIngredients) {
//
//		dailyMenus.add(dailyFoodIngredients);
//
//	};

	public MealSectionMenu(String mealTypeCode, String mealTypeName) {
		super();

		this.mealTypeCode = mealTypeCode;
		this.mealTypeName = mealTypeName;
	}

	public String getMealTypeCode() {
		return mealTypeCode;
	}

	public void setMealTypeCode(String mealTypeCode) {
		this.mealTypeCode = mealTypeCode;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}

	public void setMealTypeName(String mealTypeName) {
		this.mealTypeName = mealTypeName;
	}

	public List<DailyFoodIngredients> getDailyMenus() {
		return dailyMenus;
	}

	public void setDailyMenus(List<DailyFoodIngredients> dailyMenus) {
		this.dailyMenus = dailyMenus;
	}

}
