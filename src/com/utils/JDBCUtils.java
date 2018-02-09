package com.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	//使用xml文件的默认配置创建连接池
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	//返回连接
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	//返回连接池对象
	public static DataSource getDataSource()
	{
		return dataSource;
	}
}
