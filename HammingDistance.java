package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HammingDistance {
	
	private ArrayList<String> stations = new ArrayList<String>();
	

	
	public HammingDistance() throws IOException {
		read();
	}
	
	
	public int findHammingDist(String stid1, String stid2) {
		int hammingDist = 0;
		
		// Loop through the station and compare each char.
		for (int i = 0; i < 4; ++i) {
			if (stid1.charAt(i) != stid2.charAt(i)) {
				++hammingDist;
			}
		}
		return hammingDist;
	}
	
	public ArrayList<String> stationsWithHammingDist(String stid, int hammingDist) {
		ArrayList<String> stationsWith = new ArrayList<String>();
		int comparedHammDist;
		for (String s : stations) {
			comparedHammDist = findHammingDist(stid, s);
			
			if (comparedHammDist == hammingDist) {
				stationsWith.add(s);
			}
		}
		
		return stationsWith;
	}
	
	public int numberOfStationsWithHammingDist(int hammingDist) {
		return 0;
	}
	
	public void addStation() {
		
	}
	
	
	/*
	 * Loads a list of station Id's from a txt file. Each line repersents one station.
	 * 
	 *  @Param filename name of txt file that will be read in.
	 */
	public void read() throws IOException
	{
		// Declare a BufferReader to read in file.
	   BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"));
       String strg;
       
       // Read in and throw out the first five lines of the file to get to station Id's.
       for (int i = 0; i < 3; ++i) {
       	strg = br.readLine();
       }
       
       //read in first line of station Id's.
       strg = br.readLine();
       
     
       while (strg != null) {
       
       	// Get the station Id from the line and add to staion array.
       	String stationID = strg.substring(2,6);
       	stations.add(stationID);
       	
       	//Read next line and increase lineNum and index.
       	strg = br.readLine();      	
       }
      
       br.close();
		
	}

}