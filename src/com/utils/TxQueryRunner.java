package com.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/*
		 * 1.得到连接
		 * 2.执行父类方法,传递连接对象
		 * 3.释放连接
		 * 4.返回值
		 */
		Connection conn = JDBCUtils.getConnection();
		int[] result = super.batch(conn, sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int execute(String sql, Object... params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		int result = super.execute(conn, sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		List<T> result = super.execute(conn, sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		T result = super.insert(conn, sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		T result = super.insert(conn, sql, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		T result = super.insertBatch(conn, sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, rsh, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		T result = super.query(conn, sql, rsh);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql, params);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql, param);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		int result = super.update(conn, sql);
		JDBCUtils.releaseConnection(conn);
		return result;
	}

}
