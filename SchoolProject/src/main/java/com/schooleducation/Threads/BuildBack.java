package com.schooleducation.Threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BuildBack implements Runnable {
	
	private StringBuffer buildThread = new StringBuffer();
	private boolean isBuildStarted = false;
	
	

	@Override
	public void run() {
		
		try {
			isBuildStarted= true;
			String s[]={"cmd.exe /c mvn clean install", null, "new java.io.File(\"H:\\SchoolEducation\")"};
			executecommad(s);
			//executecommad("cmd.exe /c  H: && cd SchoolEducation && mvn clean install");
			/*for (int i = 0; i < 10; i++) {
				Thread.sleep(3000l);
				// buildThread.append("Build Is processing.......");
			}*/
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		

	}
	
	
	
	private  void executecommad(String[] cmd)throws Exception
	 {
			
		Process p;
		String line = "";
		
		 p = Runtime.getRuntime().exec("cmd.exe /c mvn clean install", null, new java.io.File("H:\\SchoolEducation"));
		 
		 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		 while ((line = reader.readLine())!= null) {
			 buildThread.append(line+"<br>");
			 System.out.println(line);
			 //Thread.sleep(3000l);
				//logger.info("<br>"+line + "\n");
				
			}
		 
		 
		/* BufferedReader reader =
                 new BufferedReader(new InputStreamReader(p.getInputStream()));*/
		 
		
		 /*StringBuffer output = new StringBuffer();
		
		 Thread t = new Thread(new Runnable() {
		 @Override
		 public void run() {
		 String str = null;
		 
		 try {
			
				
				//p.waitFor();
				

	                        String line = "";
				while ((line = reader.readLine())!= null) {
					output.append("<br>"+line + "\n");
					logger.info("<br>"+line + "\n");
					
				}
				 System.out.println(output.toString());
		 } catch (IOException e) { 
		 e.printStackTrace();
		 }
		 }});
		 t.start();*/
	 }



	public StringBuffer getBuildThread() {
		return buildThread;
	}



	public boolean isBuildStarted() {
		return isBuildStarted;
	}
	
	
	

}
