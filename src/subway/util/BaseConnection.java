package subway.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseConnection {
	
	 public static Connection getConnection(){//用这个方法获取mysql的连接
    	 Connection conn=null;
    	 String driver = "com.mysql.jdbc.Driver";
    	 String url = "jdbc:mysql://localhost:3306/sql?characterEncoding=utf8&useSSL=true";
    	 String user = "root";
    	 String password = "gy1212";
    	 try{
    		 Class.forName(driver);//加载驱动类
    		 conn=DriverManager.   
    				 getConnection(url,user,password);//（url数据库的IP地址，user数据库用户名，password数据库密码）
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
    	 return conn;
     }
	 
	 public static void close (Statement state, Connection conn) {
			if (state != null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void close (ResultSet rs, Statement state, Connection conn) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (state != null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


	
}
