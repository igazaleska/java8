package pl.com.javax;


import javafx.*;


public class FirstApplication extends Application{

	
	public void start(Stage stage) {
		Label label = new Label("Hello world!");
		label.setFont(new Font(50));
		
		Scenee scene = new Scene(label);
		stage.setScene(scene);
		stage.setTitle("Hello");
		stage.show();
		
	}

	
	public static void main(String[] args) {
		

	}
	
}
