package com.lcs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSource implements DataSource {
	
	private static LinkedList<Connection> pool = new LinkedList<>();
	
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/logintest";
	private static String user = "root";
	private static String password = "root";
	static{
		try {
			
			/*Properties properties = new Properties();
			//1 创建源对象
			File srcFile = new File("com\\lcs\\util\\jdbc.properties");
			//2 创建文件字节输入流对象，并接在源上
			FileInputStream in = new FileInputStream(srcFile);
			
			properties.load(in);
			driverClass = properties.getProperty("jdbc.driverClass");
//			jdbcUrl = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");*/

			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyDataSource() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcUrl, user, password);
			for (int i = 0; i < 2; i++) {
				pool.add(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		final Connection conn = pool.size()==0?DriverManager.getConnection(jdbcUrl, user, password):pool.removeLast();
		Connection proxyConn = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if ("close".equals(method.getName())) {
							pool.add(conn);
							return null;
						} else {
							return method.invoke(conn, args);
						}
					}
				});
		return proxyConn;
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
