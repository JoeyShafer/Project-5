package application;
	


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	// Int value that stores the hamming distance from the slider.
	private int hammingDist;
	@Override
	public void start(Stage primaryStage) {
		try {
			// GirdPane used to store components
			GridPane gridPane = new GridPane();
			Scene scene = new Scene(gridPane);
			Insets gridPadding = new Insets(10, 10, 10, 10);
			
			// Label and TextField that show the hamming distance entered from the slider
			Label hammingDistance = new Label("Enter Hamming Dist:");
			TextField hammingDistField = new TextField();
			
			// create hamming distance slider
			Slider slider = new Slider(1,4,2);
			slider.setMajorTickUnit(1);
			slider.setMinorTickCount(0);
			slider.setSnapToTicks(true);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			
			
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
			
			gridPane.add(slider, 0,1);
			gridPane.add(hammingDistance, 0, 0);
			gridPane.add(hammingDistField, 1, 0);
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
