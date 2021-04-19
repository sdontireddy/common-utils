package com.ucbos.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

public class DBUtil extends StoredProcedure {

	public DBUtil(JdbcTemplate jdbcTemplate, String name) {

		super(jdbcTemplate, name);
		setFunction(false);
	}
	

}
