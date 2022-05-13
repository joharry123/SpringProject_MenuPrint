package menu.com.project.service;

import java.util.Date;
import java.util.List;

public class FoodIngredients {

	private String foodName;
	private String ingredientsName;
	private String foodIngredientsDataType;

	public FoodIngredients(String foodName, String ingredientsName, String foodIngredientsDataType) {
		super();
		this.foodName = foodName;
		this.ingredientsName = ingredientsName;
		this.foodIngredientsDataType = foodIngredientsDataType;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getIngredientsName() {
		return ingredientsName;
	}

	public void setIngredientsName(String ingredientsName) {
		this.ingredientsName = ingredientsName;
	}

	public String getFoodIngredientsDataType() {
		return foodIngredientsDataType;
	}

	public void setFoodIngredientsDataType(String foodIngredientsDataType) {
		this.foodIngredientsDataType = foodIngredientsDataType;
	}

}
