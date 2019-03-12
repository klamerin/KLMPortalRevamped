package com.klm.KLMPortal.data.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.klm.KLMPortal.beans.MusicBean;

public interface IMusicDAO {
	
	public void addListeningSession(String band, String album, String portion, String comment, LocalDate date, Boolean concept);
	
	public void editListeningSession(String band, String album, String portion, String comment, LocalDate date, Boolean concept, Integer id);
	
	public ArrayList<MusicBean> getAllListeningSessions();
	
	public List<String> getAllBands();
	
	public List<String> getAllBandsForAutocomplete(String _text);
	
	public List<String> getAllAlbums();
	
	public List<String> getAllAlbumsForAutocomplete(String _band, String _text);
	
	public List<String> getAllBandsForAlbum(String album);
	
	public List<String> getAllAlbumsForBand(String band);
}
