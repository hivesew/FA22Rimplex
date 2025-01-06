package utilities;

import java.awt.*;
import javax.swing.*;

/**
 * Graphic class.
 * @author Ewen Hives F22TeamA
 *
 */
public class Graphing extends JPanel
{
  private static final long serialVersionUID = 1L;
  private ComplexNumber num;
  private Color color;
  private int width;
  private int height;
  
  /**
   * Graphing class constructor.
   * @param complex the complex number to attempt to graph
   * @param color the background color of the graph
   * @param w width of the panel
   * @param h height of the panel
   */
  public Graphing(final ComplexNumber complex, final Color color, final int w, final int h)
  {
    this.num = new ComplexNumber(complex.getReal(), complex.getImaginary());
    this.color = color;
    this.width = w;
    this.height = h;
  }
  
  /**
   * paintComponent method.
   * Attempts to paint the panel using 2D graphics
   * @param g graphics to paint
   */
  public void paintComponent(final Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    double scale = width / 15;
    // axes are lines spliting the canvas into 4 quadrants
    int yAxis = width / 2;
    int xAxis = height / 2;

    g2.setPaint(color);
    g2.fillRect(0, 0, width, height);
    // Drawing axes.    
    g2.setPaint(Color.BLACK);
    g2.drawLine(xAxis, 0, xAxis, height); // y-axis line
    g2.drawLine(0, yAxis, width, yAxis); // x-axis line

    int x = xAxis;
    int y = xAxis;

    int deltaX = (int) (scale * num.getReal());
    int deltaY = (int) (scale * num.getImaginary());

    // since x increases to the right we add deltaX
    // since y increases downward we subtract deltaY
    x += deltaX;
    y -= deltaY;

    g2.fillOval(x - 5, y - 5, 10, 10);
    g2.setPaint(Color.BLACK);
    g2.drawLine(xAxis, yAxis, x, y);
  }
}
