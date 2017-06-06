package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import tabuSearch.TabuSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private TabuSearch tabuSearchSalesman;
    private int start = 2;
    private Line[][] pathsMatrix = new Line[5][5];
    private TextField[][] weightFieldsMatrix = new TextField[5][5];
    private List<Line> paths = new ArrayList<>();
    private List<TextField> weightFields = new ArrayList<>();
    private final BooleanProperty firstTime = new SimpleBooleanProperty(true);
    private int iterations = 100;
    private int tabuLength = 10;

    @FXML
    private Label cost;
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox iterationCount;
    @FXML
    private ComboBox tabuListLength;

    public void initialize() {
        drawPaths();
        drawCities();
        drawWeights();
        fillIterationCounts();
        fillTabuListCounts();
    }

    private void fillIterationCounts() {
        iterationCount.getItems().addAll(50, 100, 500);
        iterationCount.setValue(iterations);
        iterationCount.valueProperty().addListener(((observable, oldValue, newValue) -> {
            iterations = (int) newValue;
        }));
    }

    private void fillTabuListCounts() {
        tabuListLength.getItems().addAll(5, 10, 20);
        tabuListLength.setValue(tabuLength);
        tabuListLength.valueProperty().addListener(((observable, oldValue, newValue) -> {
            tabuLength = (int) newValue;
        }));
    }

    private void drawPaths() {
        // 0 Los Angeles - 1 Lima
        Line line0 = new Line(10 + 35, 110 + 20, 80 + 20, 310 + 10);
        pathsMatrix[0][1] = line0;
        pathsMatrix[1][0] = line0;
        paths.add(line0);
        root.getChildren().add(line0);
        // 0 Los Angeles - 2 London
        Line line1 = new Line(10 + 35, 110 + 30, 320 + 40, 60 + 40);
        pathsMatrix[0][2] = line1;
        pathsMatrix[2][0] = line1;
        root.getChildren().add(line1);
        paths.add(line1);
        // 0 Los Angeles - 3 Rome
        Line line6 = new Line(10 + 35, 110 + 30, 430 + 20, 260 + 20);
        pathsMatrix[0][3] = line6;
        pathsMatrix[3][0] = line6;
        root.getChildren().add(line6);
        paths.add(line6);
        // 0 Los Angeles - 4 Prague
        Line line5 = new Line(10 + 35, 110 + 30, 510 + 12, 160 + 10);
        pathsMatrix[0][4] = line5;
        pathsMatrix[4][0] = line5;
        root.getChildren().add(line5);
        paths.add(line5);
        // 1 Lima - 2 London
        Line line8 = new Line(80 + 20, 310 + 10, 320 + 40, 60 + 40);
        pathsMatrix[1][2] = line8;
        pathsMatrix[2][1] = line8;
        root.getChildren().add(line8);
        paths.add(line8);
        // 1 Lima - 3 Rome
        Line line4 = new Line(80 + 20, 310 + 10, 430 + 20, 260 + 20);
        pathsMatrix[1][3] = line4;
        pathsMatrix[3][1] = line4;
        root.getChildren().add(line4);
        paths.add(line4);
        // 1 Lima - 4 Prague
        Line line9 = new Line(80 + 20, 310 + 10, 510 + 20, 160 + 18);
        pathsMatrix[1][4] = line9;
        pathsMatrix[4][1] = line9;
        root.getChildren().add(line9);
        paths.add(line9);
        // 2 London - 3 Rome
        Line line7 = new Line(320 + 40, 60 + 40, 430 + 20, 260 + 20);
        pathsMatrix[2][3] = line7;
        pathsMatrix[3][2] = line7;
        root.getChildren().add(line7);
        paths.add(line7);
        // 2 London - 4 Prague
        Line line2 = new Line(320 + 40, 60 + 40, 510 + 12, 160 + 8);
        pathsMatrix[2][4] = line2;
        pathsMatrix[4][2] = line2;
        root.getChildren().add(line2);
        paths.add(line2);
        // 3 Rome - 4 Prague
        Line line3 = new Line(430 + 20, 260 + 20, 510 + 20, 160 + 18);
        pathsMatrix[3][4] = line3;
        pathsMatrix[4][3] = line3;
        root.getChildren().add(line3);
        paths.add(line3);
    }

    private void drawCities() {
        List<StackPane> citiesContainers = new ArrayList<>();
        Color notSelected = Color.web("#F0F0FF");
        Color selected = Color.web("#9595C6");

        // 0 Los Angeles
        StackPane stack0 = new StackPane();
        stack0.setTranslateX(10);
        stack0.setTranslateY(110);
        stack0.setId("city0");
        Circle city0 = new Circle(20);
        city0.setFill(notSelected);
        city0.setStroke(Color.BLACK);
        Text cityLabel0 = new Text("Los Angeles");
        cityLabel0.setFont(Font.font("Amble Cn", FontWeight.BOLD, 14));
        cityLabel0.setFill(Color.BLACK);
        stack0.getChildren().addAll(city0, cityLabel0);
        citiesContainers.add(stack0);

        // 1 Lima
        StackPane stack1 = new StackPane();
        stack1.setTranslateX(80);
        stack1.setTranslateY(310);
        stack1.setId("city1");
        Circle city1 = new Circle(10);
        city1.setFill(notSelected);
        city1.setStroke(Color.BLACK);
        Text cityLabel1 = new Text("Lima");
        cityLabel1.setFont(Font.font("Amble Cn", FontWeight.BOLD, 14));
        cityLabel1.setFill(Color.BLACK);
        stack1.getChildren().addAll(city1, cityLabel1);
        citiesContainers.add(stack1);

        // 2 London
        StackPane stack2 = new StackPane();
        stack2.setTranslateX(320);
        stack2.setTranslateY(60);
        stack2.setId("city2");
        Circle city2 = new Circle(40);
        city2.setFill(selected);
        city2.setStroke(Color.BLACK);
        Text cityLabel2 = new Text("London");
        cityLabel2.setFont(Font.font("Amble Cn", FontWeight.BOLD, 14));
        cityLabel2.setFill(Color.BLACK);
        stack2.getChildren().addAll(city2, cityLabel2);
        citiesContainers.add(stack2);

        // 3 Rome
        StackPane stack3 = new StackPane();
        stack3.setTranslateX(430);
        stack3.setTranslateY(260);
        stack3.setId("city3");
        Circle city3 = new Circle(18);
        city3.setFill(notSelected);
        city3.setStroke(Color.BLACK);
        Text cityLabel3 = new Text("Rome");
        cityLabel3.setFont(Font.font("Amble Cn", FontWeight.BOLD, 14));
        cityLabel3.setFill(Color.BLACK);
        stack3.getChildren().addAll(city3, cityLabel3);
        citiesContainers.add(stack3);

        // 4 Prague
        StackPane stack4 = new StackPane();
        stack4.setTranslateX(510);
        stack4.setTranslateY(160);
        stack4.setId("city4");
        Circle city4 = new Circle(10);
        city4.setFill(notSelected);
        city4.setStroke(Color.BLACK);
        Text cityLabel4 = new Text("Prague");
        cityLabel4.setFont(Font.font("Amble Cn", FontWeight.BOLD, 14));
        cityLabel4.setFill(Color.BLACK);
        stack4.getChildren().addAll(city4, cityLabel4);
        citiesContainers.add(stack4);

        citiesContainers.forEach(city -> city.setOnMouseClicked(e -> {
            citiesContainers.forEach(otherCity -> {
                Circle otherCircle = (Circle) otherCity.getChildren().get(0);
                otherCircle.setFill(notSelected);
            });
            Circle circle = (Circle) city.getChildren().get(0);
            circle.setFill(selected);
            start = Integer.valueOf(city.getId().replaceAll("\\D", ""));
            defaultLines();
        }));
        citiesContainers.forEach(city -> root.getChildren().add(city));
    }

    private TextField getTextField(Line path, String id) {
        TextField textField = new TextField();
        textField.setTranslateX(path.getStartX() + ((path.getEndX() - path.getStartX()) / 2) - 10);
        textField.setTranslateY(path.getStartY() + ((path.getEndY() - path.getStartY()) / 2) - 10);
        textField.setMaxWidth(25);
        weightFields.add(textField);
        textField.setId(id);
        textField.setOnMouseClicked(event -> {
            defaultLines();
            String clickedId = ((Control) event.getSource()).getId();
            int i = Integer.valueOf(clickedId.substring(6, 7));
            int j = Integer.valueOf(clickedId.substring(8, 9));
            Line picked = pathsMatrix[i][j];
            picked.setStrokeWidth(3);
            picked.setStroke(Color.web("#505092"));
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String correctVal = newValue.replaceAll("[^\\d]", "");
            if (correctVal.length() == 0) {
                correctVal = "5";
            }
            textField.setText(correctVal);
        });
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                root.requestFocus();
                firstTime.setValue(false);
            }
        });
        textField.getStyleClass().add("tf");
        root.getChildren().add(textField);
        return textField;
    }

    private void drawWeights() {
        // 0 Los Angeles - 1 Lima
        TextField textField0 = getTextField(pathsMatrix[0][1], "field-0-1");
        weightFieldsMatrix[0][1] = textField0;
        weightFieldsMatrix[1][0] = textField0;
        // 0 Los Angeles - 2 London
        TextField textField1 = getTextField(pathsMatrix[0][2], "field-0-2");
        weightFieldsMatrix[0][2] = textField1;
        weightFieldsMatrix[2][0] = textField1;
        // 0 Los Angeles - 3 Rome
        TextField textField6 = getTextField(pathsMatrix[0][3], "field-0-3");
        weightFieldsMatrix[0][3] = textField6;
        weightFieldsMatrix[3][0] = textField6;
        textField6.setTranslateX(textField6.getTranslateX() + 25);
        textField6.setTranslateY(textField6.getTranslateY() + 8);
        // 0 Los Angeles - 4 Prague
        TextField textField5 = getTextField(pathsMatrix[0][4], "field-0-4");
        weightFieldsMatrix[0][4] = textField5;
        weightFieldsMatrix[4][0] = textField5;
        textField5.setTranslateX(textField5.getTranslateX() - 40);
        textField5.setTranslateY(textField5.getTranslateY() - 5);
        // 1 Lima - 2 London
        TextField textField8 = getTextField(pathsMatrix[1][2], "field-1-2");
        weightFieldsMatrix[1][2] = textField8;
        weightFieldsMatrix[2][1] = textField8;
        textField8.setTranslateX(textField8.getTranslateX() - 40);
        textField8.setTranslateY(textField8.getTranslateY() + 28);
        // 1 Lima - 3 Rome
        TextField textField4 = getTextField(pathsMatrix[1][3], "field-1-3");
        weightFieldsMatrix[1][3] = textField4;
        weightFieldsMatrix[3][1] = textField4;
        // 1 Lima - 4 Prague
        TextField textField9 = getTextField(pathsMatrix[1][4], "field-1-4");
        weightFieldsMatrix[1][4] = textField9;
        weightFieldsMatrix[4][1] = textField9;
        textField9.setTranslateX(textField9.getTranslateX() - 30);
        textField9.setTranslateY(textField9.getTranslateY() + 10);
        // 2 London - 3 Rome
        TextField textField7 = getTextField(pathsMatrix[2][3], "field-2-3");
        weightFieldsMatrix[2][3] = textField7;
        weightFieldsMatrix[3][2] = textField7;
        // 2 London - 4 Prague
        TextField textField2 = getTextField(pathsMatrix[2][4], "field-2-4");
        weightFieldsMatrix[2][4] = textField2;
        weightFieldsMatrix[4][2] = textField2;
        // 3 Rome - 4 Prague
        TextField textField3 = getTextField(pathsMatrix[3][4], "field-3-4");
        weightFieldsMatrix[3][4] = textField3;
        weightFieldsMatrix[4][3] = textField3;

        for (int i = 0; i < weightFieldsMatrix.length; i++) {
            for (int j = 0; j < weightFieldsMatrix.length; j++) {
                if (i != j) {
                    Random rand = new Random();
                    weightFieldsMatrix[i][j].setText(String.valueOf(rand.nextInt((9 - 1) + 1) + 1));
                }
            }
        }
    }

    private void defaultLines() {
        cost.setText("0");
        paths.forEach(path -> {
            path.setStroke(Color.BLACK);
            path.setStrokeWidth(1);
        });
    }

    private void colorSolution() {
        int[] solution = tabuSearchSalesman.getSolution();
        for (int i = 0; i < solution.length - 1; i++) {
            int row = solution[i];
            int col = solution[i + 1];
            Line path = pathsMatrix[row][col];
            path.setStroke(Color.web("#505092"));
            path.setStrokeWidth(2);
        }
    }

    public void solveProblem() {
        defaultLines();
        int[][] oldDistances = new int[][]{
                {0, Integer.valueOf(weightFieldsMatrix[0][1].getText()), Integer.valueOf(weightFieldsMatrix[0][2].getText()), Integer.valueOf(weightFieldsMatrix[0][3].getText()), Integer.valueOf(weightFieldsMatrix[0][4].getText())},
                {Integer.valueOf(weightFieldsMatrix[1][0].getText()), 0, Integer.valueOf(weightFieldsMatrix[1][2].getText()), Integer.valueOf(weightFieldsMatrix[1][3].getText()), Integer.valueOf(weightFieldsMatrix[1][4].getText())},
                {Integer.valueOf(weightFieldsMatrix[2][0].getText()), Integer.valueOf(weightFieldsMatrix[2][1].getText()), 0, Integer.valueOf(weightFieldsMatrix[2][3].getText()), Integer.valueOf(weightFieldsMatrix[3][4].getText())},
                {Integer.valueOf(weightFieldsMatrix[3][0].getText()), Integer.valueOf(weightFieldsMatrix[3][1].getText()), Integer.valueOf(weightFieldsMatrix[3][2].getText()), 0, Integer.valueOf(weightFieldsMatrix[3][4].getText())},
                {Integer.valueOf(weightFieldsMatrix[4][0].getText()), Integer.valueOf(weightFieldsMatrix[4][1].getText()), Integer.valueOf(weightFieldsMatrix[4][2].getText()), Integer.valueOf(weightFieldsMatrix[4][3].getText()), 0}
        };
        tabuSearchSalesman = new TabuSearch(oldDistances, start, iterations, tabuLength);
        tabuSearchSalesman.start();
        cost.setText(String.valueOf(tabuSearchSalesman.getCost()));
        colorSolution();
    }

}
