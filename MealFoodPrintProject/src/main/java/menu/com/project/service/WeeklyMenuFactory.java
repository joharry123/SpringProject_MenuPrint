package menu.com.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.aspectj.lang.annotation.DeclareMixin;
import org.springframework.aop.config.AdvisorEntry;
import org.springframework.beans.support.PagedListHolder;

import menu.com.project.service.DailyFoodIngredients;
import menu.com.project.service.FoodIngredients;
import menu.com.project.service.MealSectionMenu;
import menu.com.project.service.WeeklyMenu;
import menu.com.project.vo.CookFoodVo;

public class WeeklyMenuFactory {

	WeeklyMenu mealSection = new WeeklyMenu();

	int weekcount = 0;
	int mealcount = 0;
	String foodName = "";

	// 데이터 정렬 함수
	public WeeklyMenu sortedMealSectionMenulyMenu(List<CookFoodVo> cookFoodVos) throws Exception {// 데이터 정렬 함수

		WeeklyMenu weeklyMenu = new WeeklyMenu(); // 조중석간을 담는다.

		int size = cookFoodVos.size();

		String prevMealTypeCode = "";
		String prevDay = "";

		for (int i = 0; i < size; i++) {

			CookFoodVo vo = cookFoodVos.get(i);

			// 식사구분을 생성하고 리스트에 넣는다
			if (prevMealTypeCode.equals(vo.getMealTypeCode()) == false) {
				MealSectionMenu mealSectionMenu = new MealSectionMenu(vo.getMealTypeCode(), vo.getMealTypeName());
				prevMealTypeCode = vo.getMealTypeCode();
				weeklyMenu.getMealSections().add(mealSectionMenu);
			}

			weeklyMenu.setRestaurantName(vo.getRestCode());
		}

		int weeklyMenuSize = weeklyMenu.getMealSections().size();

		for (int i = 0; i < weeklyMenuSize; i++) {

			prevDay = "";

			for (int j = 0; j < size; j++) {

				CookFoodVo cookFoodvo = cookFoodVos.get(j);

				// 요일을 생성하고 식사구분에 넣는다
				if (cookFoodvo.getMealTypeCode().equals(weeklyMenu.getMealSections().get(i).getMealTypeCode())) {

					if (prevDay.equals(cookFoodvo.getDayOfWeek()) == false) {

						DailyFoodIngredients dailyFoodIngredients = new DailyFoodIngredients(cookFoodvo.getStartDate());

						weeklyMenu.getMealSections().get(i).getDailyMenus().add(dailyFoodIngredients);

						prevDay = cookFoodvo.getDayOfWeek();
					}
				}

			}
		}

		// 음식을 식사구분별 요일에 넣는다.
		for (int i = 0; i < weeklyMenu.getMealSections().size(); i++) { // 4

			for (int j = 0; j < weeklyMenu.getMealSections().get(i).getDailyMenus().size(); j++) { // 3 1 2 3
				foodName = "";
				for (int k = 0; k < size; k++) {

					CookFoodVo cookFoodVo = cookFoodVos.get(k);

					if (weeklyMenu.getMealSections().get(i).getMealTypeCode().equals(cookFoodVo.getMealTypeCode())) {

						if (weeklyMenu.getMealSections().get(i).getDailyMenus().get(j).getDate()
								.equals(cookFoodVo.getStartDate())) {

							{

								if (foodName.equals("") || (foodName.equals(cookFoodVo.getFoodName()) == false)) {

									weeklyMenu.getMealSections().get(i).getDailyMenus().get(j).getFoodIngredientsList()
											.add(new FoodIngredients(cookFoodVo.getFoodName(), cookFoodVo.getFoodName(),
													"food"));
									foodName = cookFoodVo.getFoodName();

								}

								weeklyMenu.getMealSections().get(i).getDailyMenus().get(j).getFoodIngredientsList()
										.add(new FoodIngredients(cookFoodVo.getFoodName(),
												cookFoodVo.getIngredientsName(), "ingredients"));
								foodName = cookFoodVo.getFoodName();

							}
						}
					}
				}
			}
		}

		System.out.println("정렬된 데이터 출력");
		for (int k = 0; k < weeklyMenu.getMealSections().size(); k++) {

			for (int j = 0; j < weeklyMenu.getMealSections().get(k).getDailyMenus().size(); j++) {
				System.out.println("----구분 -----" + weeklyMenu.getMealSections().get(k).getMealTypeName() + ""
						+ weeklyMenu.getMealSections().get(k).getDailyMenus().get(j).getDate() + "번째 요일");
				for (int i = 0; i < weeklyMenu.getMealSections().get(k).getDailyMenus().get(j).getFoodIngredientsList()
						.size(); i++) {
					System.out.print(weeklyMenu.getMealSections().get(k).getDailyMenus().get(j).getFoodIngredientsList()
							.get(i).getFoodName());
					System.out.print(",");
					System.out.println(weeklyMenu.getMealSections().get(k).getDailyMenus().get(j)
							.getFoodIngredientsList().get(i).getIngredientsName());
				}
			}

		}

		return weeklyMenu;

	}

	public PagedWeeklyMenu getPaged(WeeklyMenu sortedWeeklyMenu) {

		PagedWeeklyMenu pageList = new PagedWeeklyMenu();// 페이지의 리스트를 담는다.

		ArrayList<List<Integer>> WeeklymenuCounts = getWeeklyMenuCount(sortedWeeklyMenu); // 조중석간 각각의 메뉴 수

		ArrayList<List<String>> weeklyMenuDates = getWeeklyMenuDates(sortedWeeklyMenu);

		for (int i = 0; i < WeeklymenuCounts.size(); i++) {
			System.out.println(WeeklymenuCounts.get(i));
		}

		for (int i = 0; i < weeklyMenuDates.size(); i++) {
			System.out.println(weeklyMenuDates.get(i));
		}

		ArrayList<Integer> weeklyMenuMaxSizes = getWeelyMenuMaxSizes(WeeklymenuCounts);
		// 식사구분중 가장 큰 요일의 메뉴 갯수
		for (int i = 0; i < weeklyMenuMaxSizes.size(); i++) {
			System.out.println("식사구분중 가장 큰 요일의 메뉴 갯수 : " + weeklyMenuMaxSizes.get(i));
		}
		// 조중석간 가장 큰 메뉴의 합
		int WeeklyMenuMaxSizesSum = getWeeklyMenuMaxSizesSum(weeklyMenuMaxSizes);

		System.out.println("조중석간 가장 큰 메뉴의 합  : " + WeeklyMenuMaxSizesSum);

		int pageNumber = 0;
		int remainder = 0;
		int totalpageNum = 0;

		// 1. 총 메뉴의 합이 20개가 넘지 않는경우의 페이지수
		if (WeeklyMenuMaxSizesSum < 20) {
			pageNumber = 1;
		}

		// 2. 총 메뉴의 값이 20개인 경우 페이지수
		if (pageNumber == 0) {
			pageNumber = 1;
		}

		// 3. 총 메뉴의 값이 20개가 넘는 경우 페이지수
		if (WeeklyMenuMaxSizesSum > 20) {
			pageNumber = WeeklyMenuMaxSizesSum / 20;
		}

		remainder = WeeklyMenuMaxSizesSum % 20;

		if (remainder > 0) {
			totalpageNum = pageNumber + 1;
		}

		pageList.setTotalPageNum(totalpageNum);

		System.out.println("WeeklyMenuMaxSizesSum: " + WeeklyMenuMaxSizesSum);
		System.out.println("pageNumber : " + pageNumber);
		System.out.println("remainder: " + remainder);

		System.out.println("----------------------페이징시작------------------------");

		int count = 0;
		int nowCount = 0; // 현재 식사구분을 나타내는 변수
		int newCount = 0; // 다른식사구분을 넣어줄때 사용하는 변수
		int nextCount = 0; // 같은 페이지안에 다른 식사구분을 넣어줄때 구분해주는 변수
		int insertMenucount = 20; // 넣어줘야하는 수
		int m01weeklyMenuMaxCount = 0;

		// 식사구분을 하나만 선택 한 경우
		if (sortedWeeklyMenu.getMealSections().size() == 1) {

			if (WeeklyMenuMaxSizesSum < 20) {

				Page page = new Page(pageNumber);
				page.getPage().addAll(sortedWeeklyMenu.getMealSections());
				pageList.getPageList().add(page);
				pageList.setTotalPageNum(pageNumber);

			}

			else {

				for (int i = 0; i < pageNumber; i++) {
					Page page = new Page(i + 1);
					pageList.getPageList().add(page);
				}

				if (remainder > 0) {
					Page page = new Page(totalpageNum);
					pageList.getPageList().add(page);

				}

				int divisionSize = sortedWeeklyMenu.getMealSections().size() - 1;
				int pageSize = pageList.getPageList().size();
				for (int i = 0; i < pageSize; i++) {
					MealSectionMenu division = new MealSectionMenu(
							sortedWeeklyMenu.getMealSections().get(divisionSize).getMealTypeCode(),
							sortedWeeklyMenu.getMealSections().get(divisionSize).getMealTypeName());
					pageList.getPageList().get(i).getPage().add(division);
				}

				System.out.println("pageList.getPageList().size()" + pageList.getPageList().size());

				for (int i = 0; i < pageSize; i++) {
					for (int j = 0; j < pageList.getPageList().get(i).getPage().size(); j++) {
						for (int k = 0; k < sortedWeeklyMenu.getMealSections().get(0).getDailyMenus().size(); k++) {// 요일의숫자만큼
							DailyFoodIngredients day = new DailyFoodIngredients(
									sortedWeeklyMenu.getMealSections().get(0).getDailyMenus().get(k).getDate());
							pageList.getPageList().get(i).getPage().get(j).getDailyMenus().add(day);
						}
					}

				}

//			
//
				int count1 = 0;
				int repeatCount = 0;
				int size = 0;
				for (int i = 0; i < pageList.getPageList().size(); i++) {// 2번

					repeatCount = count1 * 20;
//  
//				// 페이지
//
					for (int j = 0; j < pageList.getPageList().get(i).getPage().size(); j++) {// 1개

						// 중식
						for (int k = 0; k < pageList.getPageList().get(i).getPage().get(j).getDailyMenus()
								.size(); k++) { // 4번
							// 요일
							// 반복

							size = Math.min(20, sortedWeeklyMenu.getMealSections().get(j).getDailyMenus().get(k)
									.getFoodIngredientsList().size() - repeatCount);

							for (int l = repeatCount; l < size + repeatCount; l++) {

								pageList.getPageList().get(i).getPage().get(j).getDailyMenus().get(k)
										.getFoodIngredientsList().add(sortedWeeklyMenu.getMealSections().get(j)
												.getDailyMenus().get(k).getFoodIngredientsList().get(l));

							}

						}

					}
					count1++;
				}
			}

			System.out.println("pageList.getPageList().size()" + pageList.getPageList().size());

		}

		// 전체식사구분을 선택한 경우
		else {

			nowCount = 0;
			int a = 0;

			// 페이지 생성
			for (int i = 0; i < pageNumber; i++) { // 페이지 갯수만큼 페이지를 생성해서 넣어준다.
				Page page = new Page(i + 1);
				pageList.getPageList().add(page);
			}

			if (remainder > 0) { // 나머지 수가 있으면 페이지를 한개 더 추가해 준다.
				Page page = new Page(totalpageNum);
				pageList.getPageList().add(page);
			}

			if (WeeklyMenuMaxSizesSum > 20) {

				for (int i = 0; i < pageNumber; i++) {

					pageList.getPageList().get(i).getPage().add(new MealSectionMenu("M01", "조식"));
					pageList.getPageList().get(i).getPage().add(new MealSectionMenu("M02", "중식"));
					pageList.getPageList().get(i).getPage().add(new MealSectionMenu("M03", "석식"));
					pageList.getPageList().get(i).getPage().add(new MealSectionMenu("M04", "간식"));

				}

				for (int i = 0; i < pageNumber; i++) {
					int pageSize = pageList.getPageList().get(i).getPage().size();
					for (int j = 0; j < pageSize; j++) {
						int sortedWeeklyMenudaySize = sortedWeeklyMenu.getMealSections().get(j).getDailyMenus().size();
						for (int k = 0; k < sortedWeeklyMenudaySize; k++) {
							pageList.getPageList().get(i).getPage().get(j).getDailyMenus().add(new DailyFoodIngredients(
									sortedWeeklyMenu.getMealSections().get(j).getDailyMenus().get(k).getDate()));
						}

					}
				}

				int count1 = 0;
				int repeatCount = 0;
				int ccount = 20;
				int size = 0;
				for (int i = 0; i < pageList.getPageList().size(); i++) {// 2번

					repeatCount = count1 * 20;
//  
//				// 페이지
//
					for (int j = 0; j < pageList.getPageList().get(i).getPage().size(); j++) {
						

						// 중식
						for (int k = 0; k < pageList.getPageList().get(i).getPage().get(j).getDailyMenus()
								.size(); k++) { // 4번
							// 요일
							// 반복
							

							
							size = Math.min(20, sortedWeeklyMenu.getMealSections().get(j).getDailyMenus().get(k)
									.getFoodIngredientsList().size() - repeatCount);

							for (int l = repeatCount; l < size + repeatCount; l++) {
  

								pageList.getPageList().get(i).getPage().get(j).getDailyMenus().get(k)
										.getFoodIngredientsList().add(sortedWeeklyMenu.getMealSections().get(j)
												.getDailyMenus().get(k).getFoodIngredientsList().get(l));

							}

						}

						ccount = ccount - weeklyMenuMaxSizes.get(j);

					}
					count1++;
				}

			}

		}

		pageList.setRestaurantName(sortedWeeklyMenu.getRestaurantName());
		System.out.println(sortedWeeklyMenu.getRestaurantName());
		return pageList;

	}

	private ArrayList<List<String>> getWeeklyMenuDates(WeeklyMenu sortedWeeklyMenu) {

		ArrayList<List<String>> dayList = new ArrayList<List<String>>();

		for (int i = 0; i < sortedWeeklyMenu.getMealSections().size(); i++) {
			ArrayList<String> dates = new ArrayList<String>();
			for (int j = 0; j < sortedWeeklyMenu.getMealSections().get(i).getDailyMenus().size(); j++) {
				String date = sortedWeeklyMenu.getMealSections().get(i).getDailyMenus().get(j).getDate();
				dates.add(date);
			}
			dayList.add(dates);
		}
		return dayList;
	}

	// 조중석간 요일별 음식의 갯수를 구하는 함수
	private ArrayList<List<Integer>> getWeeklyMenuCount(WeeklyMenu weeklyMenu) {

		ArrayList<List<Integer>> menuCounts = new ArrayList<List<Integer>>();
		for (int i = 0; i < weeklyMenu.getMealSections().size(); i++) {
			ArrayList<Integer> menuCount = new ArrayList<Integer>();
			for (int j = 0; j < weeklyMenu.getMealSections().get(i).getDailyMenus().size(); j++) {
				int getMenuCount = weeklyMenu.getMealSections().get(i).getDailyMenus().get(j).getFoodIngredientsList()
						.size();
				menuCount.add(getMenuCount);
			}

			menuCounts.add(menuCount);
		}

		return menuCounts;
	}

	// 조중석간 별 가장 많은 음식 데이터가 저장된 요일을 찾는 함수
	private ArrayList<Integer> getWeelyMenuMaxSizes(ArrayList<List<Integer>> menuCounts) {
		// TODO Auto-generated method stub
		int max = 0;

		ArrayList<Integer> maxList = new ArrayList<Integer>();

		for (int i = 0; i < menuCounts.size(); i++) {
			max = 0;
			for (int j = 0; j < menuCounts.get(i).size(); j++) {
				if (menuCounts.get(i).get(j) > max) {
					max = menuCounts.get(i).get(j);
				}

			}
			maxList.add(max);
		}
		return maxList;
	}

	// 조중석간 별 가장 많은 음식데이터가 저장된 요일 음식개수의 합
	private int getWeeklyMenuMaxSizesSum(ArrayList<Integer> maxList) {
		int max = 0;
		for (int i = 0; i < maxList.size(); i++) {
			max += maxList.get(i);
		}

		return max;
	}

	public static int GetMealName(String meal) {
		int m = 0;

		switch (meal) {
		case "M01":
			m = 0;
			break;
		case "M02":
			m = 1;
			break;
		case "M03":
			m = 2;
			break;
		case "M04":
			m = 3;
			break;
		}
		return m;

	}

	public static int GetDateDailyFoodIngredientsName(Date date) throws Exception {

		int day = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ymdstring = dateFormat.format(date);

		java.util.Date nDate = dateFormat.parse(ymdstring);
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayNum) {
		case 1:
			day = 6;
			break;
		case 2:
			day = 0;
			break;
		case 3:
			day = 1;
			break;
		case 4:
			day = 2;
			break;
		case 5:
			day = 3;
			break;
		case 6:
			day = 4;
			break;
		case 7:
			day = 5;
			break;

		}

		return day;

	}

	private int getDailyFoodIngredientsName(String startDate) throws ParseException {
		// TODO Auto-generated method stub
		int day = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date nDate = dateFormat.parse(startDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);
		switch (dayNum) {
		case 1:
			day = 6;
			break;
		case 2:
			day = 0;
			break;
		case 3:
			day = 1;
			break;
		case 4:
			day = 2;
			break;
		case 5:
			day = 3;
			break;
		case 6:
			day = 4;
			break;
		case 7:
			day = 5;
			break;

		}

		return day;

	}

}