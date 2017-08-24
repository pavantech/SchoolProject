package com.core.singletons;
import java.util.HashMap;
import java.util.Map;

public class BuildStatus {
	
	private Map<String, StringBuffer> mapBuildStatus = new HashMap<String, StringBuffer>();
	
	private static BuildStatus instance;
	
	private BuildStatus() {
		
	}
	
	public static BuildStatus getBuildStatuIns() {
		
		if (instance == null) {
			instance = new BuildStatus();
		}
		return instance;
		
	}

	public Map<String, StringBuffer> getMapBuildStatus() {
		return mapBuildStatus;
	}

	public void setMapBuildStatus(Map<String, StringBuffer> mapBuildStatus) {
		this.mapBuildStatus = mapBuildStatus;
	}
	

}
