package com.basi;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class SaveData {
	
	private String creationTime;
	private String lastSaveTime;
	private int totalPlayTime;
	private String saveName;
	
	/** Data structure for the save files. 
	 * @param creationTime
	 * @param lastSaveTime
	 * @param totalPlayTime
	 * @param saveName
	 */
	public SaveData(String creationTime, String lastSaveTime, int totalPlayTime,String saveName) {
		this.creationTime = creationTime;
		this.lastSaveTime = lastSaveTime;
		this.totalPlayTime = totalPlayTime;
		this.saveName = saveName;
	}

	public String getCreationTime() {
		return creationTime;
	}
	public String getLastSaveTime() {
		return lastSaveTime;
	}
	public void setLastSaveTime(String lastSaveTime) {
		this.lastSaveTime = lastSaveTime;
	}
	public int getTotalPlayTime() {
		return totalPlayTime;
	}
	public void setTotalPlayTime(int totalPlayTime) {
		this.totalPlayTime = totalPlayTime;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	@Override
	public String toString() {
		return "SaveData [creationTime=" + creationTime + ", lastSaveTime="
				+ lastSaveTime + ", totalPlayTime=" + totalPlayTime
				+ ", saveName=" + saveName + "]";
	}
	
//	private Date toDate(String stringDate) {
//		//Sql format 2015-03-21 11:28:35
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//		LocalDateTime date = LocalDateTime.parse(stringDate, formatter);
//		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
//		}
}
