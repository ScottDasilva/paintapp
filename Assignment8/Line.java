/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Andrew Castro - 000771147, Scott DaSilva - 000771150
 */

//Line is a shape and extends the Shape class
public class Line extends Shape {

    //Constructor for Line that takes in x and y coordinates, fill and stroke color as well as strokeWidth
    public Line(double x1, double x2, double y1, double y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, x2, y1, y2, fillColor, strokeColor, strokeWidth);
    }

    
    /**
     * draw overrides same method in super class. Takes in GraphicsContext gc and draws a Line on the screen
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.strokeLine(getX1(), getY1(), getX2(), getY2());

    }

}
