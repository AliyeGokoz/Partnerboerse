package de.hdm.partnerboerse.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.*;


public interface PartnerboerseAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	public Profile createProfile(String first, String last)
		      throws IllegalArgumentException;
	
	
	SearchProfile createSearchProfileFor(Profile p);
	
	public Blocking createBlocking() throws IllegalArgumentException;
	
	public Description createDescription() throws IllegalArgumentException;
	
	public FavoritesList createFavoriteList() throws IllegalArgumentException;
	
	public Property createProperty() throws IllegalArgumentException;
	
	public Info createInfo() throws IllegalArgumentException;
	
	public Selection createSelection() throws IllegalArgumentException;
	
	public Similarity createSimilarity() throws IllegalArgumentException;
	
	public VisitList createVisitList() throws IllegalArgumentException;
	
	
	public Profile getProfile() throws IllegalArgumentException;
	
	public Blocking getBlocking() throws IllegalArgumentException;
	
	public Description getDescription() throws IllegalArgumentException;
	
	public FavoritesList getFavoritesList() throws IllegalArgumentException;
	
	public Property getProperty() throws IllegalArgumentException;
	
	public Info getInfo() throws IllegalArgumentException;
	
	public Selection getSelection() throws IllegalArgumentException;
	
	public Similarity getSimilarity() throws IllegalArgumentException;
	
	public SimilarityList getVisistList() throws IllegalArgumentException;
	
}

