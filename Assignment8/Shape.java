/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Andrew Castro - 000771147, Scott DaSilva - 000771150
 */

//Shape class which is the super class
public abstract class Shape {
    
    //Declaring variables for use in sub classes
    private double x1, x2, y1, y2;
    Color fillColor;
    Color strokeColor;
    int strokeWidth;
    Canvas canvas = new Canvas(1000, 1000);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    //Constructor for Shape which takes in x and y positions, colors and stroke size
    public Shape(double x1, double x2, double y1, double y2, Color fillColor, Color strokeColor, int strokeWidth) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    
    //Generic getters and setters to retrieve and store information about the variables above
    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public int getStrokeWidth() {
        return  strokeWidth;
    }

    public void draw(GraphicsContext gc) {
    }
    
    
}
