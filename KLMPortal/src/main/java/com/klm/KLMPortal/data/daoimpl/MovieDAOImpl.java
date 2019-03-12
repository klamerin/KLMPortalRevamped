package com.klm.KLMPortal.data.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.klm.KLMPortal.beans.MovieBean;
import com.klm.KLMPortal.beans.helpers.MoviesHelper;
import com.klm.KLMPortal.components.ErrorNotification;
import com.klm.KLMPortal.data.AbstractDAO;
import com.klm.KLMPortal.data.MSSQLDAOFactory;
import com.klm.KLMPortal.data.dao.IMovieDAO;


public class MovieDAOImpl extends AbstractDAO implements IMovieDAO {

	private final static Logger LOGGER = Logger.getLogger(MovieDAOImpl.class.getName());
	
	public MovieDAOImpl() {
		initSQLMapping();
	}

	@Override
	public List<String> getAllMovieNames() {
		List<String> films = new ArrayList<String>();
		String sql = sqlMapping.getValue("Movie.getAllFilmNames");
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				films.add(rslt.getString("NAME"));
			}
			rslt.close();
		} catch (SQLException e) {
			LOGGER.error("Query getAllFilmNames failed \n", e);
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		return films;
	}
	
	private List<MovieBean> getMoviesFromDB(String sqlQuery, String sql) {
		List<MovieBean> films = new ArrayList<MovieBean>();
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				MovieBean film = MoviesHelper.fillMovieWithResult(rslt);
				films.add(film);
			}
			rslt.close();
		} catch (SQLException e) {
			LOGGER.error("Query " + sqlQuery + " failed", e);
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		return films;
	}

	@Override
	public List<MovieBean> getAllMovies() {
		String sqlQuery = "Movie.getAllFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	}

	@Override
	public List<MovieBean> getAllWatchedMovies() {
		String sqlQuery = "Movie.getAllWatchedFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	}

	@Override
	public List<MovieBean> getAllUnwatchedMovies() {
		String sqlQuery = "Movie.getAllUnwatchedFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	}

	@Override
	public List<MovieBean> getToWatchAloneFilms() {
		String sqlQuery = "Movie.getToWatchAloneFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	}

	@Override
	public List<MovieBean> getToWatchWithTinaFilms() {
		String sqlQuery = "Movie.getToWatchWithTinaFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	};

	@Override
	public List<MovieBean> getAllRecommendedMovies() {
		String sqlQuery = "Movie.getAllRecommendedFilms";
		String sql = sqlMapping.getValue(sqlQuery);
		return getMoviesFromDB(sqlQuery, sql);
	}

	@Override
	public void addNewMovie(String name, Boolean watched, Double rating, String comment, LocalDate date,
			Boolean recommend, String watchedBecause, Double sadnessLevel, Integer desireLevel, Boolean rewatchNeeded,
			Boolean social) {
		String sql = sqlMapping.getValue("Movie.addMovie");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			if (rating != null) {
				ps.setDouble(2, rating);
			} else {
				ps.setNull(2, Types.FLOAT);
			}
			if (comment != null) {
				ps.setString(3, comment);
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			ps.setBoolean(4, watched);
			if (date != null) {
				ps.setDate(5, Date.valueOf(date));
			} else {
				ps.setNull(5, Types.TIMESTAMP);
			}
			if (recommend != null) {
				ps.setBoolean(6, recommend);
			} else {
				ps.setNull(6, Types.BIT);
			}
			if (watchedBecause != null) {
				ps.setString(7, watchedBecause);
			} else {
				ps.setNull(7, Types.VARCHAR);
			}
			if (sadnessLevel != null) {
				ps.setDouble(8, sadnessLevel);
			} else {
				ps.setNull(8, Types.DOUBLE);
			}

			if (desireLevel != null) {
				ps.setInt(9, desireLevel);
			} else {
				ps.setNull(9, Types.INTEGER);
			}
			if (rewatchNeeded != null) {
				ps.setBoolean(10, rewatchNeeded);
			} else {
				ps.setNull(10, Types.BIT);
			}
			if (watched) {
				ps.setInt(11, 1);
			} else {
				ps.setInt(11, 0);
			}
			if (social != null) {
				ps.setBoolean(12, social);
			} else {
				if (!watched) {
					ps.setBoolean(12, false);
				} else {
					ps.setNull(12, Types.BIT);
				}
			}

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOGGER.error("Query addMovie failed \n", e);
			ErrorNotification.show("Faled to add Movie, sorry...");
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		LOGGER.info("Query addMovie updated " + result + " rows");
	};

	@Override
	public void editMovie(String name, Boolean watched, Double rating, String comment, LocalDate date,
			Boolean recommend, String watchedBecause, Double sadnessLevel, Integer desireLevel, Boolean rewatchNeeded,
			Boolean social, Integer watchCount, Integer id) {
		String sql = sqlMapping.getValue("Movie.editMovie");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			if (comment != null) {
				ps.setString(1, comment);
			} else {
				ps.setNull(1, Types.VARCHAR);
			}
			if (rating != null) {
				ps.setDouble(2, rating);
			} else {
				ps.setNull(2, Types.FLOAT);
			}
			if (recommend != null) {
				ps.setBoolean(3, recommend);
			} else {
				ps.setNull(3, Types.BIT);
			}
			ps.setBoolean(4, watched);
			if (date != null) {
				ps.setDate(5, Date.valueOf(date));
			} else {
				ps.setNull(5, Types.TIMESTAMP);
			}
			ps.setString(6, name);
			if (watchedBecause != null) {
				ps.setString(7, watchedBecause);
			} else {
				ps.setNull(7, Types.VARCHAR);
			}
			if (sadnessLevel != null) {
				ps.setDouble(8, sadnessLevel);
			} else {
				ps.setNull(8, Types.DOUBLE);
			}
			if (desireLevel != null) {
				ps.setInt(9, desireLevel);
			} else {
				ps.setNull(9, Types.INTEGER);
			}
			if (rewatchNeeded != null) {
				ps.setBoolean(10, rewatchNeeded);
			} else {
				ps.setNull(10, Types.BIT);
			}
			if (social != null) {
				ps.setBoolean(11, social);
			} else {
				if (!watched) {
					ps.setBoolean(11, false);
				} else {
					ps.setNull(11, Types.BIT);
				}
			}
			if (watchCount != null) {
				ps.setInt(12, watchCount);
			} else {
				ps.setInt(12, 0);
			}
			
			ps.setInt(13, id);
			
			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOGGER.error("Query editMovie failed \n", e);
			ErrorNotification.show("Faled to edit Movie, sorry...");
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		LOGGER.info("Query editMovie updated " + result + " rows");
	};

	@Override
	public void deleteMovie(Integer id) {
		String sql = sqlMapping.getValue("Movie.deleteMovie");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOGGER.error("Query deleteMovie failed \n", e);
			ErrorNotification.show("Faled to delete movie, sorry...");
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		LOGGER.info("Query deleteMovie updated " + result + " rows");
	}

	@Override
	public void incrementWatchCount(Integer id) {
		String sql = sqlMapping.getValue("Movie.incrementWatchCount");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOGGER.error("Query incrementWatchCount failed \n", e);
			ErrorNotification.show("Faled to edit Movie, sorry...");
			if (con != null) {
				try {
					LOGGER.error("The transaction is rolled back.");
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					LOGGER.error(ex);
				}
			} else {
				LOGGER.error("Unable to establish DB connection");
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
		LOGGER.info("Query incrementWatchCount updated " + result + " rows");
	}

}
