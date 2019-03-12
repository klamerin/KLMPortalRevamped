package com.klm.KLMPortal.beans;

import java.sql.Timestamp;
import java.time.LocalDate;

public class MovieBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Double rating;
	private String comment;
	private boolean watched;
	private LocalDate watchedDate;
	private Timestamp lastModified;
	private boolean recommend;
	private String watchedBecause;
	private Double sadnessLevel;
	private Integer desireLevel;
	private boolean rewatchNeeded;
	private int watchCount;
	private boolean social;

	public MovieBean() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}

	public LocalDate getWatchedDate() {
		return watchedDate;
	}

	public void setWatchedDate(LocalDate watchedDate) {
		this.watchedDate = watchedDate;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isRecommend() {
		return recommend;
	}

	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}

	public Double getSadnessLevel() {
		return sadnessLevel;
	}

	public void setSadnessLevel(Double sadnessLevel) {
		this.sadnessLevel = sadnessLevel;
	}

	public String getWatchedBecause() {
		return watchedBecause;
	}

	public void setWatchedBecause(String watchedBecause) {
		this.watchedBecause = watchedBecause;
	}

	public Integer getDesireLevel() {
		return desireLevel;
	}

	public void setDesireLevel(Integer desireLevel) {
		this.desireLevel = desireLevel;
	}

	public boolean isRewatchNeeded() {
		return rewatchNeeded;
	}

	public void setRewatchNeeded(boolean rewatchNeeded) {
		this.rewatchNeeded = rewatchNeeded;
	}

	public int getWatchCount() {
		return watchCount;
	}

	public void setWatchCount(int watchCount) {
		this.watchCount = watchCount;
	}

	public void incrementWatchCount() {
		watchCount++;
	}

	public boolean isSocial() {
		return social;
	}

	public void setSocial(boolean social) {
		this.social = social;
	}
	
	
}
