package application;
	


import java.util.ArrayList;



import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
	private TextField hammingDistField;
	private Slider slider;
	private Button showStation;
	private Button calculateHD;
	private ListView<String> listStations;
	private ChoiceBox stations;
	
	
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
			
			showStation = new Button("Show Station");
			listStations = new ListView<String>();
			compareWith = new Label("Compare with:");
			stations = new ChoiceBox();
			calculateHD = new Button("Calculate HD");
			Label distance0 = new Label("Distance 0");
			Label distance1 = new Label("Distance 1");
			Label distance2 = new Label("Distance 2");
			Label distance3 = new Label("Distance 3");
			Label distance4 = new Label("Distance 4");
			TextField distanceField0 = new TextField();
			TextField distanceField1 = new TextField();
			TextField distanceField2 = new TextField();
			TextField distanceField3 = new TextField();
			TextField distanceField4 = new TextField();
			Button addStation = new Button("Add Station");
			TextField stationToAdd = new TextField();
			
			
			
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
			
			
			
			gridPane.add(slider, 0,1);
			gridPane.add(hammingDistance, 0, 0);
			gridPane.add(hammingDistField, 1, 0);
			gridPane.add(showStation, 0, 2);
			gridPane.add(listStations, 0, 3);
			gridPane.add(compareWith, 0, 4);
			gridPane.add(stations, 1, 4);
			gridPane.add(distance0, 0, 5);
			gridPane.add(distance1, 0, 6);
			gridPane.add(distance2, 0, 7);
			gridPane.add(distance3, 0, 8);
			gridPane.add(distance4, 0, 9);
			gridPane.add(distanceField0, 1, 5);
			gridPane.add(distanceField1, 1, 6);
			gridPane.add(distanceField2, 1, 7);
			gridPane.add(distanceField3, 1, 8);
			gridPane.add(distanceField4, 1, 9);
			gridPane.add(addStation, 0, 10);
			gridPane.add(stationToAdd, 1, 10);
			gridPane.setPadding(gridPadding);
			gridPane.setHgap(5);
			gridPane.setVgap(10);
			
			
			
			
			
			
			
			
			
			
			
			
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
