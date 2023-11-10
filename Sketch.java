import processing.core.PApplet;
import java.util.Random;

public class Sketch extends PApplet {
	
  Random myRandom = new Random();
  public void settings() {
    size(500, 500);
  }

  public void setup() {
    background(77, 160, 255);
  }

  public void draw() {
    
    // Draws first layer of suns
    for (int sunX = 0; sunX <= 100; sunX += 10){
      drawSun(sunX, drawParabola(sunX, 0.02, 50, 0), 10, 3, 20);
    }
    // Draws second layer of suns
    for (int sunX = 0; sunX <= 100; sunX += 10){
      drawSun(sunX, drawParabola(sunX, 0.023, 50, 20), 10, 3, 20);
    }
    // Draws the third layer of suns
    for (int sunX = 0; sunX <= 100; sunX += 10){
      drawSun(sunX, drawParabola(sunX, 0.027, 50, 40), 10, 3, 20);
    }
    // Draws the ground
    fill(29, 201, 2);
    strokeWeight(0);
    rect(0, (float)(height * 0.6), width, (float)(height * 0.4));

    // Draws a grid of houses
    for (int intHouseY = 60; intHouseY <= 100; intHouseY += 20) {
      for (int intHouseX = 0; intHouseX <= 100; intHouseX += 20) {
        drawHouse(intHouseX, intHouseY, 14, 14);
      }
    }
  }


  /** 
   * Description: Draws a house given the location and size
   * @author: Joshua Yin
   * @param intHouseX The Location of the house along the X-axis as a percentage of the screen
   * @param intHouseY The Location of the house along the Y-axis as a percentage of the screen
   * @param intHouseWidth The Width of the house as a percetage of the screen
   * @param intHouseHeight The Height of the house as a percentage of the screen
   */

  public void drawHouse(int intHouseX, int intHouseY, int intHouseWidth, int intHouseHeight) {

    //Draws base of the house proportional to the house width and height 
    fill(255, 222, 59);
    noStroke();
    rect((float)(width * (double)intHouseX / 100), 
         (float)(height * (double)intHouseY / 100), 
         (float)(width * (double)intHouseWidth / 100), 
         (float)(height * (double)intHouseHeight / 100));

    //Draws door of the house proportional to the house width and height
    fill(122, 81, 58);
    stroke(185, 122, 87);
    strokeWeight((float)(width * (double)intHouseWidth / 2000));
    rect((float)(width * (double)intHouseX / 100 + width * (double)intHouseWidth / 800), 
         (float)(height * (double)intHouseY / 100 + width * (double)intHouseHeight / 210), 
         (float)(width * (double)intHouseWidth / 400), 
         (float)(height * (double)intHouseHeight / 200));
    
    //Draws window of the house proportional to the width and height of the house
    fill(255, 255, 255);
    stroke(0, 0, 0);
    strokeWeight(2);
    rect((float)(width * (double)intHouseX / 100 + width * (double)intHouseWidth / 200), 
         (float)(height * (double)intHouseY / 100 + width * (double)intHouseHeight / 210), 
         (float)(width * (double)intHouseWidth / 250), 
         (float)(height * (double)intHouseHeight / 250));

    //Draws the roof of the house proportional to the width and height of the house
    fill(166, 0, 0);
    strokeWeight(0);
    triangle((float)(width * (double)intHouseX / 100), 
             (float)(height * intHouseY / 100), 
             (float)(width * (double)intHouseX / 100 + width * (double)intHouseWidth / 100), 
             (float)(height * (double)intHouseY / 100),
             (float)(width * (double)intHouseX / 100 + width * (double)intHouseWidth / 200), 
             (float)(height * (double)intHouseY / 100 - height * (double)intHouseHeight / 300));

  }

  /**
   * Description: Draws a sun that changes colour based on its height
   * @author: Joshua Yin
   * @param intSunX X Coordinate of the sun in percent of the screen
   * @param intSunY Y Coordinate of the sun in percent of the screen
   * @param intSunDiameter Diameter of the Sun in percent of the screen
   * @param intRayLength Ray length of the sun in percent of the screen
   * @param intNumberOfRays The number of rays on the sun
   */

  public void drawSun(int intSunX, int intSunY, int intSunDiameter, int intRayLength, int intNumberOfRays) {

    // Draws the sun
    // Colour changes based on the Y position at which it is located
    fill(255, 255 - (int)((double)intSunY / 100 * 255), 0);
    ellipse((float)(width * (double)intSunX / 100), 
            (float)(height * (double)intSunY / 100), 
            (int)(width * (double)intSunDiameter / 100),
            (int)(height * (double)intSunDiameter / 100));

    // Draws the Rays
    // Calculates the angle which the ray should be rotated
    for(int intAngle = 0; intAngle < 360; intAngle += 360 / intNumberOfRays) {

      // Pushes matrix
      pushMatrix();

      // Translates the position to the middle of the sun
      translate((int)(width * (double)intSunX / 100), (int)(height * (double)intSunY / 100));
      // Rotates the ray based on the angle
      rotate((float)Math.toRadians(intAngle));
      // Sets the ray color based on the Y position of the sun
      stroke(255, 255 - (int)((double)intSunY / 100 * 255), 0);
      // Sets the stroke weight to equal the Ray length
      strokeWeight(intRayLength);

      // Draws the Ray
      line(0,0, (int)(width * (double)intSunDiameter / 200 + width * (double)intRayLength / 100), 0);
      popMatrix();
    }
  }

  /**
   * Description: Calculates for the Y coordinate on a parabola
   * @author: Joshua Yin
   * @param intX X coordinate of the point in percentage of the screen
   * @param dblA The a value of the parabola. Controls how steep the parabola is
   * @param intVertexX The X Coordinate of the vertex in percentage of the screen
   * @param intVertexY The Y Coordinate of the vertex in percentage of the screen
   * @return The Y coordinate of the point in percentage of the screen
  */
  public int drawParabola(int intX, double dblA, int intVertexX, int intVertexY) {

    // Calculates the Y coordinate using Quadratic function vertex form y = a(x - h)^2 + k
    int intY = (int)(dblA * (double)Math.pow(intX - intVertexX, 2) + intVertexY);
    return intY;
  }
}