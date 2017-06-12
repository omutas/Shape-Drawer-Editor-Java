package paint;

// Rect.java
// Class for a rectangle.


import java.awt.*;

public class Square extends Shape {
  public int x;				// Leftmost x-coord of square
  public int y;				// Leftmost y-coord of square
  public int length;		// Width of square
  
  /**
   * Constructor. Calls the superclass's constructor to set the color. Sets x,
   * y, width, and height based on the parameters.
   * @param xPos
   * @param yPos
   * @param rectWidth
   * @param rectHeight
   * @param rectColor
   */
  public Square(int xPos,int yPos,int rectWidth,Color rectColor) {
  	super(rectColor);
  	x = xPos;
  	y = yPos;
  	length = rectWidth;
  }
  
  /**
   * Draws the rectangle.
   */
  public void drawShape(Graphics page) {
  	page.fillRect(x, y, length, length);
  }
  
  /**
   * Returns the center point of the rectangle.
   */
  public Point getCenter() {
  	return new Point(x+length/2,y+length/2);
  }
  
  /**
   * Moves the rectangle relative to deltaX and deltaY
   */
  public void move(int deltaX,int deltaY) {
  	x += deltaX;
  	y += deltaY;
  }
  
  /**
   * Returns whether a given point is within the rectangle
   */
  public boolean containsPoint(Point p) {
  	return
  		p.x >= x &&
  		p.x <= x + length &&
  		p.y >= y &&
  		p.y <= y + length;
  }
}
