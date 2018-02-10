package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	//使用文件的默认配置！要求必须给出c3p0-config.xml
	private static ComboPooledDataSource dataSource = 
			new ComboPooledDataSource();
	//使用oracle的数据库连接池
//	private static ComboPooledDataSource dataSource = 
//			new ComboPooledDataSource("oracle-config");
	//事务专用连接
//	private static Connection conn = null;
	/*
	 * 应用于多事务，每一个线程有一个自己专用的connection
	 */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//使用连接池返回一个连接对象
	public static Connection getConnection() throws SQLException
	{
		/*
		 * 获取自己线程的connection
		 */
		Connection conn = tl.get();
		//当conn不等于null，说明已经调用过beginTransaction(),表示已经开启了事务。
		if(conn!=null) return conn;
		
		return dataSource.getConnection();
	}
	//返回连接池对象！
	public static DataSource getDataSource()
	{
		return dataSource;
	}
	/*
	 * 开始事务
	 * 1.获取一个connection，设置它的setAutoCommit(false)
	 * 2.还要保证dao中使用的连接是我们刚刚创建的
	 */
	public static void beginTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn!=null) throw new RuntimeException("已经开启了事务，不要重复开启！");
		conn = getConnection();//给conn赋值，表示事务已经开始了
		conn.setAutoCommit(false);
		//把当前线程的连接保存起来
		tl.set(conn);
	}
	/*
	 * 提交事务
	 * 1.获取beginTransaction提供的Connection,然后调用commit方法
	 */
	public static void commitTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn==null) throw new RuntimeException("还没有开启事务，不能提交！");
		conn.commit();
		conn.close();
//		conn = null;//把它设置为null,表示事务已经结束了！
//		//下次再去调用getConnection()返回的就不是conn了。
		tl.remove();//把连接从tl中移除
	}
	/*
	 * 回滚事务
	 * 1.获取beginTransaction提供的Connection,然后调用rollback方法
	 */
	public static void rollbackTransaction() throws SQLException
	{
		Connection conn = tl.get();
		
		if(conn==null) throw new RuntimeException("还没有开启事务，不能回滚！");
		conn.rollback();
		conn.close();
//		conn = null;
		tl.remove();
	}
	
	/*
	 * 释放连接
	 */
	public static void releaseConnection(Connection connection) throws SQLException
	{
		Connection conn = tl.get();
		/*
		 * 判断它是不是事务专用，如果是就不关闭
		 * 如果不是事务专用，就要将其关闭
		 */
		//如果conn==null，说明现在没有事务，那么connection一定不是事务专用的
		if(conn==null) connection.close();
		//如果conn!=null,说明现在有事务，需要判断参数连接是否与conn相等
		//如果不等，说明参数连接不是事务专用连接，将其关闭
		if(conn!=connection) connection.close();
	}
}
