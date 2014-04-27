
/****************@author Simant Purohit*********************************/

public enum ClassroomLocation {
	//List of all locations
	LOCATION1,
	LOCATION2,
	LOCATION3;
//	LOCATION4,
//	LOCATION5,
//	LOCATION6
//	LOCATION7,
//	LOCATION8,
//	LOCATION9,
//	LOCATION10,
//	LOCATION11,
//	LOCATION12
	
	public static String[] getAllLocations(){
		ClassroomLocation[] cl = ClassroomLocation.values();
		String[] locs= new String[cl.length];
		for(int i=0;i<cl.length;i++){
			locs[i] = cl[i].toString();
		}
		
		return locs;
	}

}

