# Project-5
For this project I had to create a GUI that will calculate different types of Hamming Distances of a list of station ids. The GUI had a slider that you could use to adjust the Hamming Distance from 1-4, the program also had a drop down box that had the list of all the stations that you could choose. Then the program had a show station button that when pressed it would show a list of stion that had the hamming distance you choose from the slider to the station you choose from the drop box. Then the was a button that would calculate the number of stations that had each hamming distance 0-4 and a button that would add a station. Then I had to create a unique solution. The first thing I did was create the hamming distance slider and I made a Label and TextField to display the hamming distance. The next thing I did was create a class for calculating the hamming distances, this class had three methods one would calculate the hamming distance between two stations, another method that would create an arraylist of station ids that share the same given hamming distance for a given station id, the llast method this class had was a method that would get the number of the stations that have a given hamming distance froma station. The next thing I did was create the show station button that would show the stations in a ListView and then ChoiceBox that had the stations. After that I did was create the calculateHammingDistance button and the TextFields to display the number of stations that had the gieven hamming distances, then next thing I did was create the add station button that would add a station from a TextField to the list of stations. The Last thing I did was create a bar graph that would display the total occurances of each hamminng distances. To do this I created a button called createGraph that when pressed would add up the number of each hamming distances and then add then to a bar graph to display.  

HammingDistance Class

private ArrayList<String> stations - This ArrayList stores the stations ids of all the stations from the Mesonet.txt file.

public void read() 
  This method would use a BufferedReader to read in the file Mesonet.txt and would store the station Id for every staion in a String array called stations.
  
public int findHammingDist(String stid1, String stid2)
  This method takes two Strings repersenting station ids and then finds the hamming distance between them by looping through them and comparing each char.
  
public ArrayList<String> stationsWitHammingDist(String stid, int hammingDist)
  This method takes a String repersent a station id and an int repersenting a hamming distance and would loop through each station getting the hamming distance by calling the findHammingDist method and comparing that hamming distance with the given distance and if they match the station gets added to the ArrayList that is returned.
 
public int numberOfStationsWithHammDist(String stid, int hammingDist) 
  This method takes a String repersent a station id and an int repersenting a hamming distance and would loop through each station getting the hamming distance by calling the findHammingDist method and comparing that hamming distance with the given distance and if they match the numStations variable is increased by one and at the end is returned.
  
Main Class

private int hammingDist - This varaible contains the hamming distance from the slider
private ArrayList<String> stationList - This ArrayList contains all the station ids
	
private Label hammingDistance - This label prompts the user to enter hamming distance from the slider
private Label compareWith - This label prompts the user to choose a station to compare with
private Label distance0 - This label repersents the number of stations with hamming distance 0
private Label distance1 - This label repersents the number of stations with hamming distance 1
private Label distance2 - This label repersents the number of stations with hamming distance 2
private Label distance3 - This label repersents the number of stations with hamming distance 3
private	Label distance4 - This label repersents the number of stations with hamming distance 4
	
private TextField hammingDistField - This TextField holds the hamming distance from the slider
private TextField distanceField0 - This TextField holds the number of stations that have the hamming distance 0
private TextField distanceField1 - This TextField holds the number of stations that have the hamming distance 1
private TextField distanceField2 - This TextField holds the number of stations that have the hamming distance 2
private TextField distanceField3 - This TextField holds the number of stations that have the hamming distance 3
private TextField distanceField4 - This TextField holds the number of stations that have the hamming distance 4
private TextField stationToAdd - This TextField holds the station to add to the list
	
private Slider slider - Slider for hamming distance that ranges from 1-4
private Button showStation - Button that when pressed 
private Button calculateHD
private Button addStation
private Button createGraph
	
private ListView<String> listStations
private ChoiceBox stations
private BarChart hdGraph
