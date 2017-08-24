package com.schooleducation.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.singletons.BuildStatus;


public class BuildThread implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(BuildThread.class);
	
	//private StringBuffer buildThread = new StringBuffer();
	private boolean isBuildStarted = false;
	public String buildId="";
	
	public BuildThread(String buildId)
	{
		this.buildId=buildId;
		
		
	}
	
	

	@Override
	public void run() {
		
		try {
			
			//System.setProperty("log.name",buildId+".log" );
			
			System.out.println("build"+buildId);
			isBuildStarted= true;
			String PATH="H:\\SchoolEducation";
			executecommad(PATH);
			//executecommad("cmd.exe /c  H: && cd SchoolEducation && mvn clean install");
			/*for (int i = 0; i < 10; i++) {
				Thread.sleep(3000l);
				// buildThread.append("Build Is processing.......");
			}*/
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		

	}
	
	
	
	private  void executecommad(String PATH)throws Exception
	 {
			
		Process p;
		String line = "";
		
		 p = Runtime.getRuntime().exec("cmd.exe /c mvn clean install", null, new java.io.File(PATH));
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		 BuildStatus buildStatus = BuildStatus.getBuildStatuIns();
		 Map<String, StringBuffer> mapBuildStatus = buildStatus.getMapBuildStatus();
		 String threadName = Thread.currentThread().getName();
		
		 if (!mapBuildStatus.containsKey(threadName)) {
			StringBuffer innBuildStatus = new StringBuffer();
			 while ((line = reader.readLine())!= null) {
				 innBuildStatus.append(line+"<br>");
				 mapBuildStatus.put(threadName, innBuildStatus);
				 Thread.sleep(2000L);
				}
			
			
			
			 
		}
		 else {
			 
		 }
		 
		 
	
		 
		
	 }



	public boolean isBuildStarted() {
		return isBuildStarted;
	}
	public String buildId()
	{
		return buildId;
	}
	
	
	

}
