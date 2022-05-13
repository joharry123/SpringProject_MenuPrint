package menu.com.project.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import menu.com.project.service.WeeklyMenu;
import menu.com.project.vo.CookFoodVo;
 
public interface WeeklyMenuService {    



//	WeeklyMenu getSortedMenu(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception;

	

	PagedWeeklyMenu getPageList(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception;

	

	WeeklyMenu getSortedMenu(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception;





	List<String> getCalSelectedDaysForWeek(String startDate, String endDate) throws ParseException;



	PagedWeeklyMenu getPagedList(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception;
   
//	WeeklyMenu getSortedMenu(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception;

} 
