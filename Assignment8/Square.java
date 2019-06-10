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

//Square is a shape and extends Shape class
public class Square extends Shape {

    //Constructor for Square that takes in x and y coordinates, fill and stroke color as well as strokeWidth
    public Square(double x1, double x2, double y1, double y2, Color fillColor, Color strokeColor,int strokeWidth) {
        super(x1, x2, y1, y2, fillColor, strokeColor,strokeWidth);
    }
    
    /**
     * draw overrides same method in super class. Takes in GraphicsContext gc and draws a square on the screen
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setStroke(getStrokeColor());
        gc.setLineWidth(getStrokeWidth());
        gc.fillRect(getX1(), getY1(), getX2() - getX1(), getX2() - getX1());
        gc.strokeRect(getX1(), getY1(), getX2() - getX1(), getX2() - getX1());
    }
}
