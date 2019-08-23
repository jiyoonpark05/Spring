package com.ehr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		  String url    = "jdbc:oracle:thin:@211.238.142.124:1521:orcl";
		  String userId = "HRSPRING";
		  String passwd = "HRSPRING1026";
		  
	    
	    //----------------01. DB 연결---------------------------------
		Connection c = DriverManager.getConnection(url,userId,passwd);
		
		return c;
	}
	/**
	 * @Method Name  : main
	 * @작성일   : 2019. 8. 19.
	 * @작성자   : sist
	 * @변경이력  : 최초작성
	 * @Method 설명 :
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.debug("=================================");
		LOG.debug("=main=");
		LOG.debug("=================================");
		
		//j01_ip: j01_139
		UserDao dao =new NUserDao();
		User user01 = new User("j01_139","박지윤","1234");
		try {
			int flag = dao.add(user01);
			LOG.debug("=================================");
			LOG.debug("=01.01 add flag\n="+flag);
			LOG.debug("=================================");
			
			if(flag==1) {
				LOG.debug("=================================");
				LOG.debug("=단건등록 성공="+user01.getU_id());
				LOG.debug("=================================");
			}
			
			LOG.debug("=================================");
			LOG.debug("=02단건조회=");
			LOG.debug("=================================");
			
			User  userOne = dao.get(user01.getU_id());
			if(user01.getU_id().equals(userOne.getU_id())
			  && user01.getName().equals(userOne.getName())		
			  && user01.getPasswd().equals(userOne.getPasswd())				
					) {
				LOG.debug("==============================");
				LOG.debug("=02.01 단건조회 성공="+user01.getU_id());
				LOG.debug("==============================");					
			}else {
				LOG.debug("==============================");
				LOG.debug("=02.01 단건조회 실패="+user01.getU_id());
				LOG.debug("==============================");				
			}
				
				
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
