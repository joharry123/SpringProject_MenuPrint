package menu.com.project.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menu.com.project.dao.WeeklyMenuDao;
import menu.com.project.service.PagedWeeklyMenu;
import menu.com.project.service.WeeklyMenu;
import menu.com.project.service.WeeklyMenuFactory;
import menu.com.project.service.WeeklyMenuService;
import menu.com.project.vo.CookFoodVo;

@Service
public class WeeklyMenuServiceImpl implements WeeklyMenuService {

	@Autowired
	private WeeklyMenuDao weeklyMenuDao;

	@Override
	public PagedWeeklyMenu getPageList(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception {
		// TODO Auto-generated method stub
		List<CookFoodVo> cookFoodVos = weeklyMenuDao.GetSelectedMenu(cookFoodVo);
		WeeklyMenuFactory factory = new WeeklyMenuFactory();
		System.out.println("데이터테스트");
		WeeklyMenu ddd = factory.sortedMealSectionMenulyMenu(cookFoodVos);

		return null;

		// 데이터 정렬
//		WeeklyMenu sortedMealSectionMenulyMenu = factory.sortedMealSectionMenulyMenu(cookFoodVos);
//
//		PagedWeeklyMenu pageList = factory.pagedMealSectionMenulyMenu(sortedMealSectionMenulyMenu);
//
//		pageList.setEndDate(endDate);
//		pageList.setStartDate(startDate);
//		pageList.setRestaurantId(cookFoodVo.getRestCode());
//		pageList.setCalDailyFoodIngredients(selectedDailyFoodIngredientsCount(startDate, endDate));
//		pageList.setCalSelectedDailyFoodIngredientss(CalSelectedDays(startDate, endDate));
//
//		return pageList;
	}
	
	@Override
	public WeeklyMenu getSortedMenu(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception {
		List<CookFoodVo> cookFoodVos = weeklyMenuDao.GetSelectedMenu(cookFoodVo);
		WeeklyMenuFactory factory = new WeeklyMenuFactory();
		WeeklyMenu sortedWeeklyMenu = factory.sortedMealSectionMenulyMenu(cookFoodVos);
		sortedWeeklyMenu.setStartDate(startDate);
		sortedWeeklyMenu.setEndDate(endDate);
		sortedWeeklyMenu.setCalSelectedDaysForWeek(getCalSelectedDaysForWeek(startDate, endDate));
		sortedWeeklyMenu.setCalSelectedDaysForSort(calSelectedDaysForSort(startDate, endDate));
		sortedWeeklyMenu.setSelectedDailyFoodIngredientsCount(selectedDailyFoodIngredientsCount(startDate, endDate));
//		PagedWeeklyMenu pageList = factory.getPaged(sortedWeeklyMenu);    
		return sortedWeeklyMenu;      
	}

	@Override
	public PagedWeeklyMenu getPagedList(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception {
		List<CookFoodVo> cookFoodVos = weeklyMenuDao.GetSelectedMenu(cookFoodVo);

		WeeklyMenuFactory factory = new WeeklyMenuFactory();
		WeeklyMenu sortedWeeklyMenu = factory.sortedMealSectionMenulyMenu(cookFoodVos);
//		sortedWeeklyMenu.setStartDate(startDate);
//		sortedWeeklyMenu.setEndDate(endDate);
//		sortedWeeklyMenu.setCalSelectedDaysForWeek(getCalSelectedDaysForWeek(startDate, endDate));
//		sortedWeeklyMenu.setCalSelectedDaysForSort(calSelectedDaysForSort(startDate, endDate));
//		sortedWeeklyMenu.setSelectedDailyFoodIngredientsCount(selectedDailyFoodIngredientsCount(startDate, endDate));
		
		
		PagedWeeklyMenu pageList = factory.getPaged(sortedWeeklyMenu);
		pageList.setStartDate(startDate);    
		pageList.setEndDate(endDate);
		pageList.setCalSelectedDaysForSort(calSelectedDaysForSort(startDate, endDate));
		pageList.setCalSelectedDaysForWeek(getCalSelectedDaysForWeek(startDate, endDate));
		pageList.setSelectedDailyFoodIngredientsCount(selectedDailyFoodIngredientsCount(startDate, endDate));
		
		for(int i = 0 ; i < pageList.getCalSelectedDaysForWeek().size() ; i ++ ) {
			System.out.println( pageList.getCalSelectedDaysForWeek().get(i));
		}      
	
	
		return pageList;
	}

	// 정렬을 위한 날짜리스트
	private List<LocalDate> calSelectedDaysForSort(String startDate, String endDate) {
		// TODO Auto-generated method stub

		LocalDate ListCaldate = LocalDate.parse(startDate);

		DayOfWeek dayOfWeek = ListCaldate.getDayOfWeek();
		// 숫자 요일 구하기
		int dayOfWeekNumber = dayOfWeek.getValue();

		List<LocalDate> dateS = new ArrayList<LocalDate>();

		for (int i = 1; i < 8; i++) {

			if (dayOfWeekNumber == i) { // 1이면
				ListCaldate = ListCaldate.minusDays(i - 1);
				for (int j = 0; j < 7; j++) {
					dateS.add(ListCaldate);
					ListCaldate = ListCaldate.plusDays(1);
				}
			}
		}
		return dateS;
	}

	// 주간고정을 위한 날짜리스트
	@Override
	public List<String> getCalSelectedDaysForWeek(String startDate, String endDate) throws ParseException {
		// TODO Auto-generated method stub
		LocalDate ListCaldate = LocalDate.parse(startDate);
		String dateTOString = "";
		String day = "";
		String dayPlusDate = "";

		DayOfWeek dayOfWeek = ListCaldate.getDayOfWeek();
		// 숫자 요일 구하기
		int dayOfWeekNumber = dayOfWeek.getValue();
		// 숫자 요일 출력

		List<String> selectedDateToString = new ArrayList<String>();

		for (int i = 1; i < 8; i++) {

			if (dayOfWeekNumber == i) { // 1이면
				ListCaldate = ListCaldate.minusDays(i - 1);
				for (int j = 0; j < 7; j++) {

					dateTOString = ListCaldate.toString();
					dateTOString = dateTOString.substring(5, 10);

					if (ListCaldate.getDayOfWeek().getValue() == 1) {
						day = "月(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 2) {
						day = "火(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 3) {
						day = "水(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 4) {
						day = "木(";  
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 5) {
						day = "金(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 6) {
						day = "土(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					} else if (ListCaldate.getDayOfWeek().getValue() == 7) {
						day = "日(";
						dayPlusDate = day.concat(dateTOString + ")");
						selectedDateToString.add(dayPlusDate);
					}
					ListCaldate = ListCaldate.plusDays(1);
				}
			}
		}

		return selectedDateToString;
	}

//	@Override
//	public WeeklyMenu getSortedMenu(CookFoodVo cookFoodVo, String startDate, String endDate) throws Exception {
//		// TODO Auto-generated method stub
//
//		List<CookFoodVo> weeklyMenuS = weeklyMenuDao.GetSelectedMenu(cookFoodVo);
//		MealSectionMenulyMenuFactory factory = new MealSectionMenulyMenuFactory();
//		
//		//데이터 정렬
//		WeeklyMenu sortedMealSectionMenulyMenu = factory.sortedMealSectionMenulyMenu(weeklyMenuS);
//
//		//출력할 날짜 리스트
//		sortedMealSectionMenulyMenu.setCalSelectedDailyFoodIngredientss(CalSelectedDailyFoodIngredientss(startDate, endDate));
//		//총 선택일 수 
//		sortedMealSectionMenulyMenu.setCalDailyFoodIngredients(selectedDailyFoodIngredientsCount(startDate, endDate));
//		//식당이름
//		sortedMealSectionMenulyMenu.setRestaurantId(cookFoodVo.getRestCode());
//		//시작일
//		sortedMealSectionMenulyMenu.setStartDate(startDate);
//		//종료일 
//		sortedMealSectionMenulyMenu.setEndDate(endDate);
//
//		return sortedMealSectionMenulyMenu;
//	}

	// 총 날짜 계산 함수
	private long selectedDailyFoodIngredientsCount(String startDate, String endDate) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date stDt = format.parse(startDate);
		Date edDt = format.parse(endDate);

		long diff = stDt.getTime() - edDt.getTime();
		long diffDailyFoodIngredientss = diff / (24 * 60 * 60 * 1000);
		diffDailyFoodIngredientss = Math.abs(diffDailyFoodIngredientss) + 1;

		return diffDailyFoodIngredientss;

	}



	// view에 고정시킬 날짜 리스트 만드는 함수

}
