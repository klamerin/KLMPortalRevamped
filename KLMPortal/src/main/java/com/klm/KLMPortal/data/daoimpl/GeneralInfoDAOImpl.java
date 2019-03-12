package com.klm.KLMPortal.data.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import com.klm.KLMPortal.beans.GeneralEventBean;
import com.klm.KLMPortal.beans.InfoBean;
import com.klm.KLMPortal.beans.MonthlyEventBean;
import com.klm.KLMPortal.beans.PostEventBean;
import com.klm.KLMPortal.beans.TodoEventBean;
import com.klm.KLMPortal.components.ErrorNotification;
import com.klm.KLMPortal.data.AbstractDAO;
import com.klm.KLMPortal.data.MSSQLDAOFactory;
import com.klm.KLMPortal.data.dao.IGeneralInfoDAO;

public class GeneralInfoDAOImpl extends AbstractDAO implements IGeneralInfoDAO {

	public GeneralInfoDAOImpl() {
		initSQLMapping();
	}

	@Override
	public ArrayList<InfoBean> getAllInfos() {
		ArrayList<InfoBean> infos = new ArrayList<InfoBean>();
		String sql = sqlMapping.getValue("GeneralInfo.getAllInfos");
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				InfoBean info = new InfoBean();
				info.setId(rslt.getInt("ID"));
				info.setInfoKey(rslt.getString("INFO_KEY"));
				info.setInfoValue(rslt.getString("INFO_VALUE"));
				info.setComment(rslt.getString("COMMENT"));
				infos.add(info);
			}
			rslt.close();
		} catch (SQLException e) {
			System.out.println("Query getAllInfos failed \n" + e);
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		return infos;
	}

	@Override
	public void addNewInfo(String infoKey, String infoValue, String comment) {
		String sql = sqlMapping.getValue("GeneralInfo.addNewInfo");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, infoKey);
			if (infoValue == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, infoValue);
			}
			if (comment == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, comment);
			}

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query addNewInfo failed \n" + e);
			ErrorNotification.show("Faled to add New Info, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query addNewInfo updated " + result + " rows");

	}

	@Override
	public void setInfoData(Integer infoId, String infoValue, String comment) {
		String sql = sqlMapping.getValue("GeneralInfo.setInfoData");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			if (infoValue == null) {
				ps.setNull(1, Types.VARCHAR);
			} else {
				ps.setString(1, infoValue);
			}
			if (comment == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, comment);
			}
			ps.setInt(3, infoId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query setInfoValue failed \n" + e);
			ErrorNotification.show("Faled to set Info data, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query setInfoData updated " + result + " rows");

	}

	@Override
	public void deleteInfo(Integer infoId) {
		String sql = sqlMapping.getValue("GeneralInfo.deleteInfo");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, infoId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query deleteInfo failed \n" + e);
			ErrorNotification.show("Faled to delete Info, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query deleteInfo updated " + result + " rows");
	}


	@Override
	public ArrayList<GeneralEventBean> getAllGeneralEvents() {
		ArrayList<GeneralEventBean> events = new ArrayList<GeneralEventBean>();
		String sql = sqlMapping.getValue("GeneralInfo.getAllGeneralEvents");
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				GeneralEventBean event = new GeneralEventBean();
				event.setId(rslt.getInt("ID"));
				event.setEventName(rslt.getString("EVENT_NAME"));
				event.setEventDescription(rslt.getString("EVENT_DESCRIPTION"));
				event.setComment(rslt.getString("COMMENT"));
				event.setEventDate(rslt.getDate("EVENT_DATE") != null ? rslt.getDate("EVENT_DATE").toLocalDate() : null);
				events.add(event);
			}
			rslt.close();
		} catch (SQLException e) {
			System.out.println("Query getAllGeneralEvents failed \n" + e);
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		return events;
	}

	@Override
	public void addNewGeneralEvent(String eventName, String eventDescription, String comment, LocalDate eventDate) {
		String sql = sqlMapping.getValue("GeneralInfo.addNewGeneralEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, eventName);

			if (eventDescription == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, eventDescription);
			}
			if (comment == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, comment);
			}
			if (eventDate == null) {
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(4, java.sql.Date.valueOf(eventDate));
			}

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query addNewGeneralEvent failed \n" + e);
			ErrorNotification.show("Faled to add New General Event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query addNewGeneralEvent updated " + result + " rows");
	}

	@Override
	public void updateGeneralEvent(Integer eventId, String eventName, String eventDescription, String comment,
			LocalDate eventDate) {
		String sql = sqlMapping.getValue("GeneralInfo.updateGeneralEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, eventName);

			if (eventDescription == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, eventDescription);
			}

			if (comment == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, comment);
			}
			if (eventDate == null) {
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(4, java.sql.Date.valueOf(eventDate));
			}

			ps.setInt(5, eventId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query updateNewGeneralEvent failed \n" + e);
			ErrorNotification.show("Faled to update General Event comment, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query updateNewGeneralEvent updated " + result + " rows");
	}

	@Override
	public void deleteGeneralEvent(Integer eventId) {
		String sql = sqlMapping.getValue("GeneralInfo.deleteGeneralEvent");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, eventId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query deleteGeneralEvent failed \n" + e);
			ErrorNotification.show("Faled to delete general event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query deleteGeneralEvent updated " + result + " rows");
	}

	@Override
	public ArrayList<MonthlyEventBean> getAllMonthlyEvents() {
		ArrayList<MonthlyEventBean> events = new ArrayList<MonthlyEventBean>();
		String sql = sqlMapping.getValue("GeneralInfo.getAllMonthlyEvents");
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				MonthlyEventBean event = new MonthlyEventBean();
				event.setId(rslt.getInt("ID"));
				event.setName(rslt.getString("NAME"));
				event.setDescription(rslt.getString("DESCRIPTION"));
				event.setAmount(rslt.getInt("AMOUNT"));
				event.setComment(rslt.getString("COMMENT"));
				events.add(event);
			}
			rslt.close();
		} catch (SQLException e) {
			System.out.println("Query getAllMonthlyEvents failed \n" + e);
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		return events;
	}

	@Override
	public void addNewMonthlyEvent(MonthlyEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.addNewMonthlyEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getComment() == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, bean.getComment());
			}
			if (bean.getAmount() == null) {
				ps.setInt(4, Types.INTEGER);
			} else {
				ps.setInt(4, bean.getAmount());
			}

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query addNewMonthlyEvent failed \n" + e);
			ErrorNotification.show("Faled to add New Monthly Event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query addNewMonthlyEvent updated " + result + " rows");
	}

	@Override
	public void updateMonthlyEvent(MonthlyEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.updateMonthlyEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getComment() == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, bean.getComment());
			}
			if (bean.getAmount() == null) {
				ps.setInt(4, Types.INTEGER);
			} else {
				ps.setInt(4, bean.getAmount());
			}

			ps.setInt(5, bean.getId());

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query updateMonthlyEvent failed \n" + e);
			ErrorNotification.show("Faled to update Monthly Event comment, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query updateMonthlyEvent updated " + result + " rows");
	}

	@Override
	public void deleteMonthlyEvent(Integer eventId) {
		String sql = sqlMapping.getValue("GeneralInfo.deleteMonthlyEvent");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, eventId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query deleteMonthlyEvent failed \n" + e);
			ErrorNotification.show("Faled to delete monthly event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query deleteMonthlyEvent updated " + result + " rows");
	}

	@Override
	public ArrayList<PostEventBean> getPostEvents(Boolean received) {
		ArrayList<PostEventBean> events = new ArrayList<PostEventBean>();
		String sql = null;
		if (received == null) {
			sql = sqlMapping.getValue("GeneralInfo.getAllPostEvents");
		} else {
			sql = sqlMapping.getValue("GeneralInfo.getPostEventsByType");
		}
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);
			if (received != null) {
				ps.setBoolean(1, received);
			}

			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				PostEventBean event = new PostEventBean();
				event.setId(rslt.getInt("ID"));
				event.setName(rslt.getString("NAME"));
				event.setDescription(rslt.getString("DESCRIPTION"));
				event.setEventSetDate(rslt.getDate("SET_DATE") != null ? rslt.getDate("SET_DATE").toLocalDate() : null);
				event.setReceived(rslt.getBoolean("RECEIVED"));
				events.add(event);
			}
			rslt.close();
		} catch (SQLException e) {
			System.out.println("Query getPostEvents failed \n" + e);
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		return events;
	}

	@Override
	public void addNewPostEvent(PostEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.addNewPostEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getEventSetDate() == null) {
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(3, java.sql.Date.valueOf(bean.getEventSetDate()));
			}
			if (bean.getReceived() == null) {
				ps.setBoolean(4, false);
			} else {
				ps.setBoolean(4, bean.getReceived());
			}

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query addNewPostEvent failed \n" + e);
			ErrorNotification.show("Faled to add New Post Event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query addNewPostEvent updated " + result + " rows");
	}

	@Override
	public void updatePostEvent(PostEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.updatePostEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getEventSetDate() == null) {
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(3, java.sql.Date.valueOf(bean.getEventSetDate()));
			}
			if (bean.getReceived() == null) {
				ps.setNull(4, Types.BOOLEAN);
			} else {
				ps.setBoolean(4, bean.getReceived());
			}

			ps.setInt(5, bean.getId());

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query updatePostEvent failed \n" + e);
			ErrorNotification.show("Faled to update Post Event comment, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query updatePostEvent updated " + result + " rows");
	}

	@Override
	public void deletePostEvent(Integer eventId) {
		String sql = sqlMapping.getValue("GeneralInfo.deletePostEvent");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, eventId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query deletePostEvent failed \n" + e);
			ErrorNotification.show("Faled to delete post event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query deletePostEvent updated " + result + " rows");
	}
	
	@Override
	public ArrayList<TodoEventBean> getToDoEvents(Boolean done) {
		ArrayList<TodoEventBean> events = new ArrayList<TodoEventBean>();
		String sql = null;
		if (done == null) {
			sql = sqlMapping.getValue("GeneralInfo.getAllToDoEvents");
		} else {
			sql = sqlMapping.getValue("GeneralInfo.getToDoEventsByType");
		}
		Connection con = null;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);
			if (done != null) {
				ps.setBoolean(1, done);
			}
			ResultSet rslt = ps.executeQuery();
			con.commit();
			while (rslt.next()) {
				TodoEventBean event = new TodoEventBean();
				event.setId(rslt.getInt("ID"));
				event.setName(rslt.getString("EVENT_NAME"));
				event.setDescription(rslt.getString("EVENT_DESCRIPTION"));
				event.setComment(rslt.getString("COMMENT"));
				event.setSetDate(rslt.getDate("EVENT_SET_DATE") != null ? rslt.getDate("EVENT_SET_DATE").toLocalDate() : null);
				event.setEtaDate(rslt.getDate("EVENT_ETA_DATE") != null ? rslt.getDate("EVENT_ETA_DATE").toLocalDate() : null);
				event.setDoneDate(rslt.getDate("EVENT_DONE_DATE") != null ? rslt.getDate("EVENT_DONE_DATE").toLocalDate() : null);
				event.setDone(rslt.getBoolean("DONE"));
				events.add(event);
			}
			rslt.close();
		} catch (SQLException e) {
			System.out.println("Query getAllToDoEvents failed \n" + e);
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		return events;
	}

	@Override
	public void addNewToDoEvent(TodoEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.addNewToDoEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getComment() == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, bean.getComment());
			}
			if (bean.getSetDate() == null) {
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(4, java.sql.Date.valueOf(bean.getSetDate()));
			}
			if (bean.getEtaDate() == null) {
				ps.setNull(5, Types.DATE);
			} else {
				ps.setDate(5, java.sql.Date.valueOf(bean.getEtaDate()));
			}
			if (bean.getDoneDate() == null) {
				ps.setNull(6, Types.DATE);
			} else {
				ps.setDate(6, java.sql.Date.valueOf(bean.getDoneDate()));
			}
			if (bean.getDone() == null) {
				ps.setBoolean(7, false);
			} else {
				ps.setBoolean(7, bean.getDone());
			}
			
			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query addNewToDoEvent failed \n" + e);
			ErrorNotification.show("Faled to add New Post Event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query addNewToDoEvent updated " + result + " rows");
	}

	@Override
	public void updateToDoEvent(TodoEventBean bean) {
		String sql = sqlMapping.getValue("GeneralInfo.updateToDoEvent");
		Connection con = null;
		int result = 0;
		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bean.getName());

			if (bean.getDescription() == null) {
				ps.setNull(2, Types.VARCHAR);
			} else {
				ps.setString(2, bean.getDescription());
			}
			if (bean.getComment() == null) {
				ps.setNull(3, Types.VARCHAR);
			} else {
				ps.setString(3, bean.getComment());
			}
			if (bean.getSetDate() == null) {
				ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			} else {
				ps.setDate(4, java.sql.Date.valueOf(bean.getSetDate()));
			}
			if (bean.getEtaDate() == null) {
				ps.setNull(5, Types.DATE);
			} else {
				ps.setDate(5, java.sql.Date.valueOf(bean.getEtaDate()));
			}
			if (bean.getDoneDate() == null) {
				ps.setNull(6, Types.DATE);
			} else {
				ps.setDate(6, java.sql.Date.valueOf(bean.getDoneDate()));
			}
			if (bean.getDone() == null) {
				ps.setNull(7, Types.BOOLEAN);
			} else {
				ps.setBoolean(7, bean.getDone());
			}
			ps.setInt(8, bean.getId());

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query updateToDoEvent failed \n" + e);
			ErrorNotification.show("Faled to update todo Event comment, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query updateToDoEvent updated " + result + " rows");
	}

	@Override
	public void deleteToDoEvent(Integer eventId) {
		String sql = sqlMapping.getValue("GeneralInfo.deleteToDoEvent");
		Connection con = null;
		int result = 0;

		try {
			con = MSSQLDAOFactory.getConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, eventId);

			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("Query deleteToDoEvent failed \n" + e);
			ErrorNotification.show("Faled to delete todo event, sorry...");
			if (con != null) {
				try {
					System.out.println("The transaction is rolled back \n" + e);
					con.rollback();
					con.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			} else {
				System.out.println("Unable to establish DB connection: " + e.getMessage());
				System.out.println(e);
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Query deleteToDoEvent updated " + result + " rows");
	}


}
