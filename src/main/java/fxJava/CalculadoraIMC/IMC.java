package fxJava.CalculadoraIMC;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application{
	
	private Label pesoLabel;
	private TextField pesoText;
	private Label kg;
	private Label alturaLabel;
	private TextField alturaText;
	private Label cmLabel;
	private Label imcLabel;
	private Label imcnLabel;
	private Label resLabel;
	private DoubleProperty res = new SimpleDoubleProperty(0);
	private DoubleProperty peso = new SimpleDoubleProperty(0);
	private DoubleProperty altura = new SimpleDoubleProperty(0);

	public void start(Stage primaryStage) throws Exception {

		pesoLabel = new Label("Peso:  ");

		pesoText = new TextField();
		pesoText.setMaxWidth(50);

		kg = new Label("kg");

		alturaLabel = new Label("Altura: ");

		alturaText = new TextField();
		alturaText.setMaxWidth(50);

		cmLabel = new Label("cm");

		imcLabel = new Label("IMC:");

		imcnLabel = new Label();

		resLabel = new Label();

		HBox uno = new HBox();
		uno.setSpacing(5);
		uno.setAlignment(Pos.CENTER);
		uno.getChildren().addAll(pesoLabel, pesoText, kg);

		HBox dos = new HBox();
		dos.setSpacing(5);
		dos.setAlignment(Pos.CENTER);
		dos.getChildren().addAll(alturaLabel, alturaText, cmLabel);

		HBox tres = new HBox();
		tres.setSpacing(5);
		tres.setAlignment(Pos.CENTER);
		tres.getChildren().addAll(imcLabel, imcnLabel);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(uno, dos, tres, resLabel);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();

		Bindings.bindBidirectional(alturaText.textProperty(), altura, new NumberStringConverter());
		Bindings.bindBidirectional(pesoText.textProperty(), peso, new NumberStringConverter());

		DoubleBinding alturaM = altura.divide(100);
		DoubleBinding alturaCuadrado = alturaM.multiply(alturaM);
		DoubleBinding op = peso.divide(alturaCuadrado);

		res.bind(op);
		imcnLabel.textProperty().bind(res.asString("%.2f"));

		imcnLabel.textProperty().addListener((o, ov, nv) -> {
			Double n = res.doubleValue();

			if (n < 18.5) {
				resLabel.setText("Bajo peso");
			}
			if (n >= 18.5 && n < 25) {
				resLabel.setText("Normal");
			}
			if (n >= 25 && n < 30) {
				resLabel.setText("Sobrepeso");
			}
			if (n > 30) {
				resLabel.setText("Obeso");
			}
		});
	}
	
	public static void main(String[] args) {

        launch(args);
    }

}
