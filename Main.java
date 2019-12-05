package application;
	


import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	// Int value that stores the hamming distance from the slider.
	private int hammingDist;
	
	// ArrayList that stores all the station ids.
	private ArrayList<String> stationList;
	
	private Label hammingDistance;
	private Label compareWith;
	private Label distance0;
	private Label distance1;
	private Label distance2;
	private Label distance3;
	private	Label distance4;
	
	private TextField hammingDistField;
	private TextField distanceField0;
	private TextField distanceField1;
	private TextField distanceField2;
	private TextField distanceField3;
	private TextField distanceField4;
	private TextField stationToAdd;
	
	private Slider slider;
	private Button showStation;
	private Button calculateHD;
	private Button addStation;
	private Button createGraph;
	
	private ListView<String> listStations;
	private ChoiceBox stations;
	private BarChart hdGraph;
	private int numOf0 = 0;
	private int numOf1 = 0;
	private int numOf2 = 0;
	private int numOf3 = 0;
	private int numOf4 = 0;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			// GirdPane used to store components
			GridPane gridPane = new GridPane();
			Scene scene = new Scene(gridPane);
			Insets gridPadding = new Insets(10, 10, 10, 10);
			
			// Label and TextField that show the hamming distance entered from the slider
			hammingDistance = new Label("Enter Hamming Dist:");
			hammingDistField = new TextField();
			
			// create hamming distance slider
			slider = new Slider(1,4,1);
			slider.setMajorTickUnit(1);
			slider.setMinorTickCount(0);
			slider.setSnapToTicks(true);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			
			// Create buttons
			showStation = new Button("Show Station");
			addStation = new Button("Add Station");
			calculateHD = new Button("Calculate HD");
			listStations = new ListView<String>();
			compareWith = new Label("Compare with:");
			stations = new ChoiceBox();
			createGraph = new Button("Create graph");
			
			// Create labels and textFields.
			distance0 = new Label("Distance 0");
			distance1 = new Label("Distance 1");
			distance2 = new Label("Distance 2");
			distance3 = new Label("Distance 3");
			distance4 = new Label("Distance 4");
			distanceField0 = new TextField();
			distanceField1 = new TextField();
			distanceField2 = new TextField();
			distanceField3 = new TextField();
			distanceField4 = new TextField();
			stationToAdd = new TextField();
			
			createGraph = new Button("Create graph");
			
			CategoryAxis xAxis = new CategoryAxis();
			xAxis.setLabel("Hamming Distance");
			NumberAxis yAxis = new NumberAxis();
			yAxis.setLabel("Total Occurances");
			XYChart.Series hammDistData = new XYChart.Series();
			hdGraph = new BarChart(xAxis, yAxis);
		
			
			//create a HammingDistance Object
			HammingDistance hammDistance = new HammingDistance();
			
			// Add all the stations to the ChoiceBox
			stationList = hammDistance.getStations();
			for (String s : stationList) {
				stations.getItems().add(s);
			}
			
			/*
			 * When the slider is moved the value hammingDist will be changed to value 
			 * if the slider and then the hammingDistField will show the value of the 
			 * hammingDist.
			 */
			slider.valueProperty().addListener(new ChangeListener<Number>() {
		         public void changed(ObservableValue <? extends Number >  
                 observable, Number oldValue, Number newValue) 
		         {
		        	 hammingDist = (int)slider.getValue();
		        	 hammingDistField.setText(Integer.toString(hammingDist));
		         }
			});
			
			/*
			 * When the button is pressed a the list of stations that have the same 
			 * given hamming distance from the given station are show on the listView. 
			 */
			showStation.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				 public void handle(ActionEvent event) {
					// Get the stid from the ChoiceBox
					String stid = (String)stations.getValue();
					
					// Get the List of Stations that share the same Hamming distance 
					// with the stid
					ArrayList<String> stationsWithHammDist = 
							hammDistance.stationsWithHammingDist(stid, hammingDist);
					
					// Clear all the text from the listView of the stations
					listStations.getItems().clear();
					// Add all the stations that share the same Hamming distance 
					// to the ListView
					for (String s : stationsWithHammDist) {
						listStations.getItems().add(s);
					}
				}
			});
			
			/*
			 * When the calculateHD is pressed it calculates the number of stations that have 
			 * the given hamming distance from the station and outputs them to the TextFields.
			 */
			calculateHD.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Get the station id
					String stid = (String)stations.getValue();
					
					// Calculate the number of stations that have the given HD from the stid
					distanceField0.setText(Integer.toString(
							hammDistance.numberOfStationsWithHammingDist(stid, 0)));
					distanceField1.setText(Integer.toString(
							hammDistance.numberOfStationsWithHammingDist(stid, 1)));
					distanceField2.setText(Integer.toString(
							hammDistance.numberOfStationsWithHammingDist(stid, 2)));
					distanceField3.setText(Integer.toString(
							hammDistance.numberOfStationsWithHammingDist(stid, 3)));
					distanceField4.setText(Integer.toString(
							hammDistance.numberOfStationsWithHammingDist(stid, 4)));
				}
			});
			
			/*
			 * When addStation is pressed the test entered in the stationToAdd Field is added 
			 * to the list of stations.
			 */
			addStation.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Get the new staton from the TextField
					String newStid = stationToAdd.getText();
					
					// Add station to the list of stations in the HammingDistance object
					hammDistance.addStation(newStid);
					// Add station to ChoiceBox list 
					stations.getItems().add(newStid.toUpperCase());
					stationToAdd.clear();
				}
			});
			
			/*
			 * When createGraph is presssed it creates a bar graph that
			 */
			createGraph.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					numOf0 += Integer.parseInt(distanceField0.getText());
					hammDistData.getData().add(new XYChart.Data("0",numOf0));
					numOf1 += Integer.parseInt(distanceField1.getText());
					hammDistData.getData().add(new XYChart.Data("1",numOf1));
					numOf2 += Integer.parseInt(distanceField2.getText());
					hammDistData.getData().add(new XYChart.Data("2",numOf2));
					numOf3 += Integer.parseInt(distanceField3.getText());
					hammDistData.getData().add(new XYChart.Data("3",numOf3));
					numOf4 += Integer.parseInt(distanceField4.getText());
					hammDistData.getData().add(new XYChart.Data("4",numOf4));
					hdGraph.getData().add(hammDistData);
					
				}
			});
			
			
			
			gridPane.add(slider, 0,1);
			gridPane.add(hammingDistance, 0, 0);
			gridPane.add(hammingDistField, 1, 0);
			gridPane.add(showStation, 0, 2);
			gridPane.add(listStations, 0, 3);
			gridPane.add(compareWith, 0, 4);
			gridPane.add(stations, 1, 4);
			gridPane.add(calculateHD, 0, 5);
			gridPane.add(distance0, 0, 6);
			gridPane.add(distance1, 0, 7);
			gridPane.add(distance2, 0, 8);
			gridPane.add(distance3, 0, 9);
			gridPane.add(distance4, 0, 10);
			gridPane.add(distanceField0, 1, 6);
			gridPane.add(distanceField1, 1, 7);
			gridPane.add(distanceField2, 1, 8);
			gridPane.add(distanceField3, 1, 9);
			gridPane.add(distanceField4, 1, 10);
			gridPane.add(addStation, 0, 11);
			gridPane.add(stationToAdd, 1, 11);
			gridPane.add(createGraph, 3, 0);
			gridPane.add(hdGraph, 3, 1, 1, 4);
			gridPane.setPadding(gridPadding);
			gridPane.setHgap(5);
			gridPane.setVgap(5);
						
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hamming Distance");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
