//Made by EwMikey

package com.ewmikey;

import java.io.File;

public class FileHandler {
	
	String Path = "plugins/Ranks";
	
	public void Setup() {
		File MainDirectory = new File("Path");
		if(!MainDirectory.exists()){
			MainDirectory.mkdir();
		}
		File PlayerData = new File(Path + "/PlayerData");
		if(!PlayerData.exists()){
			PlayerData.mkdir();
		}
	}
}
