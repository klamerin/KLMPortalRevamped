package com.klm.KLMPortal.data;

import java.sql.Connection;
import java.sql.SQLException;

import com.klm.KLMPortal.data.dao.IGeneralInfoDAO;
import com.klm.KLMPortal.data.dao.IMovieDAO;
import com.klm.KLMPortal.data.dao.IMusicDAO;
import com.klm.KLMPortal.data.daoimpl.GeneralInfoDAOImpl;
import com.klm.KLMPortal.data.daoimpl.MovieDAOImpl;
import com.klm.KLMPortal.data.daoimpl.MusicDAOImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MYSQLDAOFactory extends DAOFactory {
	
	
	public MYSQLDAOFactory() {
        initDataSourceProperties();
        initDataSource();
    }


    @Override
    protected void initDataSourceProperties() {
        if (dataSourceProperties == null) {
        	dataSourceProperties = new PropertyFile(getClass().getResourceAsStream("/" + IConstant.DATASOURCE_PROPERTY_FILE_NAME));
        }
    }


    @Override
    protected void initDataSource() {
        if (dataSource == null) {
    		MysqlDataSource mysqlDS = new MysqlDataSource();
    		dataSource = mysqlDS;
			mysqlDS.setServerName(dataSourceProperties.getValue("serverName"));
			mysqlDS.setUser(dataSourceProperties.getValue("username"));
            mysqlDS.setPassword(dataSourceProperties.getValue("password"));
            mysqlDS.setDatabaseName(dataSourceProperties.getValue("databaseName"));
            mysqlDS.setPortNumber(Integer.parseInt(dataSourceProperties.getValue("databasePort")));
        }
    }


    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    @Override
    public IMovieDAO getMovieDAO() {
        return new MovieDAOImpl();
    }


	@Override
	public IGeneralInfoDAO getGeneralInfoDAO() {
		return new GeneralInfoDAOImpl();
	}


	@Override
	public IMusicDAO getMusicDAO() {
		return new MusicDAOImpl();
	}
}
