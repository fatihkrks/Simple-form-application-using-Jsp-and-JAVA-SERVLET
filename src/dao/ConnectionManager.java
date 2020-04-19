package dao;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionManager {
	public DataSource getMySqlDataSource() {
		Properties props = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties"); // src altından böyle
																							// okuyabiliriz .
		MysqlDataSource mySQLDataSource = null;
		try {
			props.load(is);
			mySQLDataSource = new MysqlDataSource();
			mySQLDataSource.setURL(props.getProperty("url"));
			mySQLDataSource.setUser(props.getProperty("username"));
			mySQLDataSource.setPassword(props.getProperty("password"));

		} catch (Exception e) {
			System.err.println("An Error Occured!\n Error is:" + e);
		}
		return mySQLDataSource;
	}
}
