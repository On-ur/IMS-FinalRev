package com.qa.ims.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtils {

	private static final Logger LOGGER = LogManager.getLogger();

	private final String dbUser = "root";

	private final String dbPass = "ochroot256";

	private final String dbURL = "jdbc:mysql://rds-free.crktn2ycvrwh.eu-west-2.rds.amazonaws.com:3306";

	private DBUtils(String username, String password) {


		init();
	}

	public int init() {
		return this.init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");
	}

	public int init(String... paths) {
		int modified = 0;

		for (String path : paths) {
			modified += executeSQLFile(path);
		}

		return modified;
	}

	public int executeSQLFile(String file) {
		int modified = 0;
		try (Connection connection = this.getConnection();
				BufferedReader br = new BufferedReader(new FileReader(file));) {
			String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					LOGGER.debug(e);
					return 0;
				}
			}).reduce((acc, next) -> acc + next).orElse(0);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return modified;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbURL, dbUser, dbPass);
	}

	public static DBUtils instance;

	public static DBUtils connect(String username, String password) {
		instance = new DBUtils(username, password);
		return instance;
	}

	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils("root", "ochroot256");
		}
		return instance;
	}

}