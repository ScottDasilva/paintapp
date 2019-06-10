/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Andrew Castro - 000771147, Scott DaSilva 000771150
 */
public class Assignment8 extends Application {

    //Declaraing all our graphics variables for buttons, textfields, labels, colorpickers etc
    private Button btnRect, btnElipse, btnCircle, btnSquare, btnLine, btnClear, btnUndo;
    private TextField txtRed, txtGreen, txtBlue, txtStroke;
    private Label lblRed, lblGreen, lblBlue, lblMessage, lblStroke, lblShape, lblFill;
    private ColorPicker colorPicker, strokePicker;
    int strokeWidth;
    Canvas canvas = new Canvas(1000, 1000);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    int selected = 0;
    double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    //Declaration for ArrayList = which holds the shape objects
    List<Shape> shape = new ArrayList<>();

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        // Set canvas Size in Pixels
        stage.setTitle("Painting Application"); // Set window title
        //EventHandlers for mouse events for dragged, pressed and released
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::whenDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::whenPressed);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::whenReleased);
        root.getChildren().add(canvas);
        stage.setScene(scene);

        // YOUR CODE STARTS HERE 
        screenDraw(root);
        // TextField length = new TextField ();
        // YOUR CODE STOPS HERE
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * screenDraw is a method that holds all the code for GUI components
     * @param root 
     */
    public void screenDraw(Group root) {
        //Background of menu
        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(0);
        r.setHeight(200);
        r.setWidth(1000);
        r.setFill(Color.LIGHTGREY);
        root.getChildren().add(r);

        btnRect = new Button("Rectangle");
        btnRect.setLayoutX(50);
        btnRect.setLayoutY(40);
        btnRect.setPrefSize(100, 25);
        btnRect.setId("btnRect");
        btnRect.setFont(Font.font("Verdana", 12));
        btnRect.setOnAction(this::whenClicked);
        root.getChildren().add(btnRect);
        
        btnUndo = new Button("Undo");
        btnUndo.setLayoutX(550);
        btnUndo.setLayoutY(100);
        btnUndo.setPrefSize(100, 50);
        btnUndo.setId("btnUndo");
        btnUndo.setFont(Font.font("Verdana", 12));
        btnUndo.setOnAction(this::whenClicked);
        root.getChildren().add(btnUndo);

        btnElipse = new Button("Elipse");
        btnElipse.setLayoutX(50);
        btnElipse.setLayoutY(70);
        btnElipse.setPrefSize(100, 25);
        btnElipse.setId("btnElipse");
        btnElipse.setFont(Font.font("Verdana", 12));
        btnElipse.setOnAction(this::whenClicked);
        root.getChildren().add(btnElipse);

        btnSquare = new Button("Square");
        btnSquare.setLayoutX(50);
        btnSquare.setLayoutY(100);
        btnSquare.setPrefSize(100, 25);
        btnSquare.setId("btnSquare");
        btnSquare.setFont(Font.font("Verdana", 12));
        btnSquare.setOnAction(this::whenClicked);
        root.getChildren().add(btnSquare);

        btnCircle = new Button("Circle");
        btnCircle.setLayoutX(50);
        btnCircle.setLayoutY(130);
        btnCircle.setPrefSize(100, 25);
        btnCircle.setId("btnCircle");
        btnCircle.setFont(Font.font("Verdana", 12));
        btnCircle.setOnAction(this::whenClicked);
        root.getChildren().add(btnCircle);

        btnLine = new Button("Line");
        btnLine.setLayoutX(50);
        btnLine.setLayoutY(160);
        btnLine.setPrefSize(100, 25);
        btnLine.setId("btnLine");
        btnLine.setFont(Font.font("Verdana", 12));
        btnLine.setOnAction(this::whenClicked);
        root.getChildren().add(btnLine);

        btnClear = new Button("Clear");
        btnClear.setLayoutX(300);
        btnClear.setLayoutY(100);
        btnClear.setPrefSize(200, 50);
        btnClear.setId("btnClear");
        btnClear.setFont(Font.font("Verdana", 12));
        btnClear.setOnAction(this::whenClicked);
        root.getChildren().add(btnClear);

        //Adds label above shapes menu
        lblShape = new Label("Shape Selection");
        lblShape.setLayoutX(50);
        lblShape.setLayoutY(0);
        lblShape.setPrefSize(100, 50);
        lblShape.setAlignment(Pos.CENTER);
        lblShape.setFont(Font.font("Verdana", 10));
        lblShape.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblShape);

        lblStroke = new Label("Stroke Width");
        lblStroke.setLayoutX(150);
        lblStroke.setLayoutY(0);
        lblStroke.setPrefSize(150, 50);
        lblStroke.setAlignment(Pos.CENTER);
        lblStroke.setFont(Font.font("Verdana", 10));
        lblStroke.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblStroke);

        txtStroke = new TextField("0");
        txtStroke.setLayoutX(175);
        txtStroke.setLayoutY(40);
        txtStroke.setPrefSize(100, 25);
        root.getChildren().add(txtStroke);

        //Label above the stroke color picker
        lblStroke = new Label("Stroke Color");
        lblStroke.setLayoutX(150);
        lblStroke.setLayoutY(60);
        lblStroke.setPrefSize(150, 50);
        lblStroke.setAlignment(Pos.CENTER);
        lblStroke.setFont(Font.font("Verdana", 10));
        lblStroke.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblStroke);

        lblFill = new Label("Fill Color");
        lblFill.setLayoutX(150);
        lblFill.setLayoutY(122);
        lblFill.setPrefSize(150, 50);
        lblFill.setAlignment(Pos.CENTER);
        lblFill.setFont(Font.font("Verdana", 10));
        lblFill.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblFill);

        colorPicker = new ColorPicker();
        colorPicker.setLayoutX(175);
        colorPicker.setLayoutY(160);
        colorPicker.setPrefSize(100, 25);
        root.getChildren().add(colorPicker);

        strokePicker = new ColorPicker();
        strokePicker.setLayoutX(175);
        strokePicker.setLayoutY(100);
        strokePicker.setPrefSize(100, 25);
        root.getChildren().add(strokePicker);

        lblFill = new Label("Custom RGB Values");
        lblFill.setLayoutX(400);
        lblFill.setLayoutY(0);
        lblFill.setPrefSize(150, 50);
        lblFill.setAlignment(Pos.CENTER);
        lblFill.setFont(Font.font("Verdana", 10));
        lblFill.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblFill);

        lblRed = new Label("Red");
        lblRed.setLayoutX(325);
        lblRed.setLayoutY(40);
        lblRed.setPrefSize(100, 25);
        lblRed.setAlignment(Pos.CENTER);
        lblRed.setFont(Font.font("Verdana", 10));
        lblRed.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblRed);

        lblGreen = new Label("Green");
        lblGreen.setLayoutX(425);
        lblGreen.setLayoutY(40);
        lblGreen.setPrefSize(100, 25);
        lblGreen.setAlignment(Pos.CENTER);
        lblGreen.setFont(Font.font("Verdana", 10));
        lblGreen.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblGreen);

        lblBlue = new Label("Blue");
        lblBlue.setLayoutX(525);
        lblBlue.setLayoutY(40);
        lblBlue.setPrefSize(100, 25);
        lblBlue.setAlignment(Pos.CENTER);
        lblBlue.setFont(Font.font("Verdana", 10));
        lblBlue.setStyle("-fx-font-weight: bold;");
        root.getChildren().add(lblBlue);

        txtBlue = new TextField("0");
        txtBlue.setLayoutX(540);
        txtBlue.setLayoutY(65);
        txtBlue.setPrefSize(70, 25);
        root.getChildren().add(txtBlue);

        txtRed = new TextField("0");
        txtRed.setLayoutX(340);
        txtRed.setLayoutY(65);
        txtRed.setPrefSize(70, 25);
        root.getChildren().add(txtRed);

        txtGreen = new TextField("0");
        txtGreen.setLayoutX(440);
        txtGreen.setLayoutY(65);
        txtGreen.setPrefSize(70, 25);
        root.getChildren().add(txtGreen);

    }

//        lblMessage = new Label("Message will be displayed here");
//        lblMessage.setLayoutX(500);
//        lblMessage.setLayoutY(100);
//        lblMessage.setPrefSize(500, 50);
//        lblMessage.setAlignment(Pos.CENTER);
//        lblMessage.setStyle("-fx-background-color: lightyellow;");
//        root.getChildren().add(lblMessage);
    //  }
    
    /**
     * whenPressed is a method that takes in a mouseevent and gets the starting x and y positions to draw the object
     * @param me 
     */
    private void whenPressed(MouseEvent me) {
        x1 = me.getX();
        y1 = me.getY();
    }
    
    /**
     * whenReleased is a method that takes in a MouseEvent and gets the end poitn for x and y.
     * it has an if statement that sets the color picker to set value if RGB values are left set to default
     * @param me 
     */
    private void whenReleased(MouseEvent me) {
        x2 = me.getX();
        y2 = me.getY();
        if (txtRed.getText().equals("0") && txtGreen.getText().equals("0") && txtBlue.getText().equals("0")) {
            gc.setFill(colorPicker.getValue());
            //Try catch to verify that the stroke is taking in a integer, otherwise a alert is tiggered
            try{
                gc.setStroke(strokePicker.getValue());
                strokeWidth = Integer.parseInt(txtStroke.getText());
            }catch(Exception e){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Please enter an integer");
                alert.showAndWait();
            }
            
        } else {
            int r, g, b;
            //Try catch to verify that the RGB is taking in a integer, otherwise a alert is tiggered
            try{
                r = Integer.parseInt(txtRed.getText());
                g = Integer.parseInt(txtGreen.getText());
                b = Integer.parseInt(txtBlue.getText());
                gc.setFill(Color.rgb(r, g, b));
            }catch(Exception e){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Please enter an integer");
                alert.showAndWait();
                
            }
        }
        //Switch statement that takes in parameter called selected and draws that specific shape and adds it to an ArrayList
        switch (selected) {
            //creates a rectangle and adds to ArrayList
            case 0:
                Shape rectangle = new Rect(x1, x2, y1, y2, colorPicker.getValue(), strokePicker.getValue(),strokeWidth);
                rectangle.draw(gc);
                shape.add(rectangle);
                //gc.fillRect(x1, y1, x2 - x1, y2 - y1);
                break;
            //creates an elipse and adds to ArrayList
            case 1:
                Shape elipse = new Elipse(x1, x2, y1, y2, colorPicker.getValue(), strokePicker.getValue(),strokeWidth);
                elipse.draw(gc);
                //gc.fillOval(x1, y1, x2 - x1, y2 - y1);
                shape.add(elipse);
                break;
            //creates a square and adds to ArrayList
            case 2:
                Shape square = new Square(x1, x2, y1, y2, colorPicker.getValue(), strokePicker.getValue(),strokeWidth);
                square.draw(gc);
                shape.add(square);
                //gc.fillRect(x1, y1, x2 - x1, x2 - x1);
                break;
            //creates a circle and adds to ArrayList
            case 3:
                Shape circle = new Circle(x1, x2, y1, y2, colorPicker.getValue(), strokePicker.getValue(),strokeWidth);
                circle.draw(gc);
                shape.add(circle);
                //gc.fillOval(x1, y1, x2 - x1, x2 - x1);
                break;
            //creates a line and adds to ArrayList
            case 4:

                Shape line = new Line(x1, x2, y1, y2, colorPicker.getValue(), strokePicker.getValue(),strokeWidth);
                line.draw(gc);
                shape.add(line);
                //gc.strokeLine(x1, y1, x2, y2);
                break;
        }
    }

    private void whenDragged(MouseEvent me) {
//        System.out.println(me.getX()+" "+me.getY());      
    }
    
    /**
     * whenCicked takes in an ActionEvent ae and selects that button based on matching ID's
     * this method is also responsible for "undo" and "clear" as it removed objects from the array
     * @param ae 
     */
    private void whenClicked(ActionEvent ae) {
        Button btn = (Button) ae.getSource();
        switch (btn.getId()) {
            case "btnRect":
                selected = 0;
                break;
            case "btnElipse":
                selected = 1;
                break;
            case "btnSquare":
                selected = 2;
                break;
            case "btnCircle":
                selected = 3;
                break;
            case "btnLine":
                selected = 4;
                break;
            case "btnClear":
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 1000, 1000);
                shape = new ArrayList<>();
                break;
            case "btnUndo":
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 1000, 1000);
                shape.remove(shape.size()-1);
                for(int i=0;i<shape.size();i++){
                    shape.get(i).draw(gc);
                }
                break;
            default:
                break;
        }
    }
}
