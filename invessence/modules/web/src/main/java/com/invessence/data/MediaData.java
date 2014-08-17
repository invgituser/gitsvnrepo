package com.invessence.data;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class MediaData implements Serializable {
	
	private int mediaID = 0;
	private int mediaType = 0;
	private String mediaFile = null;
	private String mediaLink = null;
	private String mediaTitle = null;
	private String mediaDesc = null;
	//private int primaryMediaInd = 0;
	private String mediaCreator = null;
	private String mediaSource = null;
		
	private boolean primary = false;
	
	private MultipartFile multipartFile = null;
	private String mediaThumbnailFile = null;
	
	//this is unique
	private String code = null;
	
	
	
	public int getMediaID() {
		return mediaID;
	}
	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}
	public int getMediaType() {
		return mediaType;
	}
	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaFile() {
		return mediaFile;
	}
	public void setMediaFile(String mediaFile) {
		this.mediaFile = mediaFile;
	}
	public String getMediaLink() {
		return mediaLink;
	}
	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getMediaDesc() {
		return mediaDesc;
	}
	public void setMediaDesc(String mediaDesc) {
		this.mediaDesc = mediaDesc;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getMediaCreator() {
		return mediaCreator;
	}
	public void setMediaCreator(String mediaCreator) {
		this.mediaCreator = mediaCreator;
	}
	public String getMediaSource() {
		return mediaSource;
	}
	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}
	public String getMediaThumbnailFile() {
		return mediaThumbnailFile;
	}
	public void setMediaThumbnailFile(String mediaThumbnailFile) {
		this.mediaThumbnailFile = mediaThumbnailFile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	


	

}



