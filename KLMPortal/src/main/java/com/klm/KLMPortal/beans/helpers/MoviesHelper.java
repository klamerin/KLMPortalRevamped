package com.klm.KLMPortal.beans.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.klm.KLMPortal.beans.MovieBean;

public class MoviesHelper {
	
	public static MovieBean fillMovieWithResult(ResultSet rslt) throws SQLException {
		MovieBean film = new MovieBean();
		film.setId(rslt.getInt("ID"));
		film.setName(rslt.getString("NAME"));
		film.setRating(rslt.getDouble("RATING"));
		film.setComment(rslt.getString("COMMENT"));
		film.setWatched(rslt.getBoolean("WATCHED"));
		film.setWatchedDate(rslt.getDate("LAST_WATCHED") != null ? rslt.getDate("LAST_WATCHED").toLocalDate() : null);
		film.setLastModified(rslt.getTimestamp("LAST_MODIFIED"));
		film.setRecommend(rslt.getBoolean("RECOMMEND"));
		film.setWatchedBecause(rslt.getString("WATCHED_BECAUSE"));
		film.setSadnessLevel(rslt.getDouble("SADNESS_LEVEL"));
		film.setDesireLevel(rslt.getInt("DESIRE_LEVEL"));
		film.setRewatchNeeded(rslt.getBoolean("REWATCH_NEEDED"));
		film.setWatchCount(rslt.getInt("WATCH_COUNT"));
		film.setSocial(rslt.getBoolean("SOCIAL"));
		return film;
	}
}
