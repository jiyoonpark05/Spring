package com.ehr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDaoJunit02 {
	private Logger LOG=Logger.getLogger(UserDaoJunit02.class);
	
	private UserDao dao;
	private User user01;
	private User user02;
	private User user03;
	
	@Before
	public void setUp() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("setUP()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^");
		user01=new User("j01_139","지윤","1234");
		user02=new User("j02_139","지윤지윤","1234");
		user03=new User("j03_139","지윤지윤지윤","1234");
		
		AbstractApplicationContext context = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		dao = context.getBean("userDao",UserDao.class);
		LOG.debug("=============================");
		LOG.debug("=01.context"+context);
		LOG.debug("=02.dao"+dao);
		LOG.debug("=============================");

	}
	
	@After
	public void tearDown() {
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^");
		LOG.debug("99 tearDown()");
		LOG.debug("^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	
	
	
	@Test(expected =EmptyResultDataAccessException.class)
	public void getFailure() throws ClassNotFoundException, SQLException {
	
		//-------------------------------------------
		//삭제
		//-------------------------------------------
		dao.deleteUser(user01);
		dao.deleteUser(user02);
		dao.deleteUser(user03);
		assertThat(dao.count("_139"), is(0));
		
		dao.get("unkownUserId");
	}
	
	@Test
	public void count() throws ClassNotFoundException, SQLException {
		
		//-------------------------------------------
		//삭제
		//-------------------------------------------
		dao.deleteUser(user01);
		dao.deleteUser(user02);
		dao.deleteUser(user03);
		assertThat(dao.count("_139"), is(0));
		
		//-------------------------------------------
		//1건추가
		//-------------------------------------------	
		dao.add(user01);
		assertThat(dao.count("_139"), is(1));
		//-------------------------------------------
		//1건추가
		//-------------------------------------------	
		dao.add(user02);
		assertThat(dao.count("_139"), is(2));
		//-------------------------------------------
		//1건추가
		//-------------------------------------------	
		dao.add(user03);
		assertThat(dao.count("_139"), is(3));
		
	}
		
		@Test(timeout = 1000) //1. Junit에게 테스트용 메소드임을 알려줌
		public void addAndGet() { //2.public
		
		
		try {
			
			dao.deleteUser(user01);
			dao.deleteUser(user02);
			dao.deleteUser(user03);
			assertThat(dao.count("_139"), is(0));
			
			LOG.debug("==============================");
			LOG.debug("=01 단건등록="); 
			LOG.debug("==============================");	
			
			int delFlag = dao.deleteUser(user01);
			
			LOG.debug("==============================");
			LOG.debug("=01 단건등록="); 
			LOG.debug("==============================");			
			
			int flag = dao.add(user01);
			flag = dao.add(user02);
			flag = dao.add(user03);
			assertThat(dao.count("_139"), is(3));
			LOG.debug("==============================");
			LOG.debug("=01.01 add flag="+flag);
			LOG.debug("==============================");
			
			if(flag==1) {
				LOG.debug("==============================");
				LOG.debug("=01.02 단건등록 성공="+user01.getU_id());
				LOG.debug("==============================");				
			}
			
			LOG.debug("==============================");
			LOG.debug("=02 단건조회");
			LOG.debug("==============================");			
			
			User  userOne = dao.get(user01.getU_id());
			
			assertThat(userOne.getU_id(), is(user01.getU_id()));
			assertThat(userOne.getName(), is(user01.getName()));
			assertThat(userOne.getPasswd(), is(user01.getPasswd()));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
