package dad.imc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IMC extends Application {

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de IMC");

        VBox mainLayout = new VBox();
        mainLayout.setSpacing(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);

        Label weightLabel = new Label("Peso:");
        HBox weightBox = new HBox();
        TextField weightField = new TextField();
        Label weightUnitLabel = new Label("Kg");
        weightBox.getChildren().addAll(weightLabel, weightField, weightUnitLabel);
        weightBox.setSpacing(5);

        Label heightLabel = new Label("Altura:");
        HBox heightBox = new HBox();
        TextField heightField = new TextField();
        Label heightUnitLabel = new Label("cm");
        heightBox.getChildren().addAll(heightLabel, heightField, heightUnitLabel);
        heightBox.setSpacing(5);

        Button calculateButton = new Button("Calcular");
        Label resultLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(1, weightBox);
        grid.addRow(2, heightBox);
        grid.addRow(3, calculateButton);
        grid.addRow(4, resultLabel);

        calculateButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightField.getText().replace(",", "."));
                double height = Double.parseDouble(heightField.getText().replace(",", ".")) / 100.0; // Convertir cm a m
                double bmi = weight / (height * height);

                String classification;
                if (bmi < 18.5) {
                    classification = "Bajo peso";
                } else if (bmi < 25) {
                    classification = "Normal";
                } else if (bmi < 30) {
                    classification = "Sobrepeso";
                } else {
                    classification = "Obeso";
                }

                resultLabel.setText(classification);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Por favor, ingrese números válidos.");
            }
        });

        mainLayout.getChildren().addAll(grid);

        Scene scene = new Scene(mainLayout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}