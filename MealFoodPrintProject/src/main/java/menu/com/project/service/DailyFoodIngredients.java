package menu.com.project.service;

import java.util.ArrayList;
import java.util.List;

public class DailyFoodIngredients {

	private List<FoodIngredients> foodIngredientsList = new ArrayList<FoodIngredients>();
	private String date;

	public DailyFoodIngredients(String date) {
		super();
		this.date = date;
	}

	public List<FoodIngredients> getFoodIngredientsList() {
		return foodIngredientsList;
	}

	public void setFoodIngredientsList(List<FoodIngredients> foodIngredientsList) {
		this.foodIngredientsList = foodIngredientsList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
     
}
