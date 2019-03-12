package com.klm.KLMPortal.data;

import javax.sql.DataSource;

import com.klm.KLMPortal.data.dao.IGeneralInfoDAO;
import com.klm.KLMPortal.data.dao.IMovieDAO;
import com.klm.KLMPortal.data.dao.IMusicDAO;

public abstract class DAOFactory {

	protected static DataSource dataSource;
	protected static PropertyFile dataSourceProperties;

	protected abstract void initDataSource();

	protected abstract void initDataSourceProperties();

	public static DAOFactory getMSSQLFactory() {
			return new MSSQLDAOFactory();
	}
	
	public static DAOFactory getMYSQLFactory() {
		return new MYSQLDAOFactory();
}

	public abstract IMovieDAO getMovieDAO();
	
	public abstract IGeneralInfoDAO getGeneralInfoDAO();
	
	public abstract IMusicDAO getMusicDAO();

}
