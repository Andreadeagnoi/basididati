package com.basi;

import java.util.Date;

public class SaveData {
	
	private Date creationTime;
	private Date lastSaveTime;
	private int totalPlayTime;
	private String saveName;
	
	/** Data structure for the save files. 
	 * @param creationTime
	 * @param lastSaveTime
	 * @param totalPlayTime
	 * @param saveName
	 */
	public SaveData(Date creationTime, Date lastSaveTime, int totalPlayTime,String saveName) {
		this.creationTime = creationTime;
		this.lastSaveTime = lastSaveTime;
		this.totalPlayTime = totalPlayTime;
		this.saveName = saveName;
	}

	public Date getCreationTime() {
		return creationTime;
	}
	public Date getLastSaveTime() {
		return lastSaveTime;
	}
	public void setLastSaveTime(Date lastSaveTime) {
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
}
