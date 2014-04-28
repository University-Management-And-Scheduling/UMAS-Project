
/****************@author Simant Purohit*********************************/

public enum ClassroomLocation {
	//List of all locations
	LOCATION1,
	LOCATION2,
	LOCATION3;
	
	
	public static String[] getAllLocations(){
		ClassroomLocation[] cl = ClassroomLocation.values();
		String[] locs= new String[cl.length];
		for(int i=0;i<cl.length;i++){
			locs[i] = cl[i].toString();
		}
		
		return locs;
	}

}

