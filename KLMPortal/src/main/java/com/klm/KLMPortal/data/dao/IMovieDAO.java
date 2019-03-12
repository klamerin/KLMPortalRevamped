package com.klm.KLMPortal.data.dao;

import java.time.LocalDate;
import java.util.List;

import com.klm.KLMPortal.beans.MovieBean;

public interface IMovieDAO {

	public List<String> getAllMovieNames();
	public List<MovieBean> getAllMovies();
	public List<MovieBean> getAllWatchedMovies();
	public List<MovieBean> getAllUnwatchedMovies();
	public List<MovieBean> getToWatchAloneFilms();
	public List<MovieBean> getToWatchWithTinaFilms();
	public List<MovieBean> getAllRecommendedMovies();
	
//	public void addToWatchMovie(String name, String comment);
	public void addNewMovie(String name, Boolean watched, Double rating, String comment, LocalDate date, Boolean recommend, String watchedBecause, Double sadnessLevel, Integer desireLevel, Boolean rewatchNeeded, Boolean withTina);
	public void editMovie(String name, Boolean watched, Double rating, String comment, LocalDate date, Boolean recommend, String watchedBecause, Double sadnessLevel, Integer desireLevel, Boolean rewatchNeeded, Boolean withTina, Integer watchCount, Integer id);
	public void deleteMovie(Integer id);
	public void incrementWatchCount(Integer id);
	
	
	
	
}
