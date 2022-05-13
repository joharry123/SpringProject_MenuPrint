package menu.com.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import menu.com.project.dao.impl.WeeklyMenuDaoImpl;
import menu.com.project.service.MealSectionMenu;
import menu.com.project.service.PagedWeeklyMenu;
import menu.com.project.service.WeeklyMenu;
import menu.com.project.service.WeeklyMenuService;
import menu.com.project.vo.CookFoodVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class WeeklyMenuController {

	private static final Logger logger = LoggerFactory.getLogger(WeeklyMenuController.class);

	@Autowired
	private WeeklyMenuService weeklyMenuService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";    
	}

	// 조건을 검색하는 페이지로 이동
	@RequestMapping(value = "ConditionSelect.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String ConditionSelect(Locale locale, Model model, CookFoodVo CookFoodVo) {
		logger.info("조건선택 페이지 이동", locale);
		return "ConditionSelectPage";
	} 

	@RequestMapping(value = "PrintWeeklyMenu.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String PrintWeeklyMenu(Locale locale, Model model, CookFoodVo CookFoodVo, String startDate, String endDate,
			HttpServletRequest request) throws Exception {
		
		logger.info("페이징된 리스트 출력", locale);
		WeeklyMenu sortedWeeklyMenu = weeklyMenuService.getSortedMenu(CookFoodVo, startDate, endDate);   
		PagedWeeklyMenu pagedList = weeklyMenuService.getPagedList(CookFoodVo, startDate, endDate); 

		
		model.addAttribute("sortedWeeklyMenu", sortedWeeklyMenu);
		request.setAttribute("sortedWeeklyMenu", sortedWeeklyMenu);  
		
		
		
		
		model.addAttribute("pagedList", pagedList);          
		request.setAttribute("pagedList", pagedList);     
  
		return "ResultPage";                        
	}  
}
