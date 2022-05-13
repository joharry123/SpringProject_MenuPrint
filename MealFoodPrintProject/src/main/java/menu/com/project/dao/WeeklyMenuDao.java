package menu.com.project.dao;

import java.util.List;

import menu.com.project.vo.CookFoodVo;

public interface WeeklyMenuDao {

	List<CookFoodVo> getfood();

	List<CookFoodVo> GetSelectedMenu(CookFoodVo cookFoodVo);







} 
 