package com.ucbos.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
/**
 * Spring based JDBC template
 * @author sdontireddy
 *
 */
public class JDBCTemplate {
	private DataSource dataSource;
	JdbcTemplate jdbcTemplateObject;

	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public SimpleJdbcCall getJdbcCall() {
		return jdbcCall;
	}

	private SimpleJdbcCall jdbcCall;

	public void setDataSource(DataSource dataSource, String procedureName) {
		this.dataSource = dataSource;
		jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(procedureName);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void execute(String procedureName) {
		this.jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(procedureName);
	}

}
