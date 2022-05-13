package menu.com.project.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import menu.com.project.dao.WeeklyMenuDao;
import menu.com.project.vo.CookFoodVo; 

@Repository
public class WeeklyMenuDaoImpl implements WeeklyMenuDao {

	private String namespace = "menu.com.project.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private JdbcTemplate jdbcTemplate;

	public WeeklyMenuDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public WeeklyMenuDaoImpl() {
	}

	@Override
	public List<CookFoodVo> getfood() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(namespace + "get_cms_menu");
	}

	@Override
	public List<CookFoodVo> GetSelectedMenu(CookFoodVo cookFoodVo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(namespace + "GetSelectedMenu", cookFoodVo);
	}

}