package sierpinski.rendering;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import sierpinski.Posn;

public class IncrementalPointPlotPanel extends JPanel {

  private static final int POINT_RADIUS = 1;
  private static final Color BACKGROUND_COLOR = Color.WHITE;
  private static final Color POINT_COLOR = Color.BLACK;

  private final List<Posn> points = new ArrayList<>();

  public IncrementalPointPlotPanel(int width, int height) {
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(BACKGROUND_COLOR);
  }

  @Override
  public void paint(Graphics g) {
    super.paintComponent(g);
    drawGraph(this.points, (Graphics2D) g);
  }

  public void addPoint(Posn newPoint) {
    this.points.add(newPoint);
  }

  /**
   * Draws the graph as 2D shapes onto the given graphics by drawing points and lines connecting
   * them.
   *
   * @param positions the positions of all the points to draw.
   * @param graphics the graphics on which to draw.
   */
  private void drawGraph(List<Posn> positions, Graphics2D graphics) {
    graphics.drawString("Num points: " + positions.size(), 10, 10);
    // For each point, draw that point and a line connecting it to all remaining points
    for (Posn origin : positions) {
      graphics.setColor(POINT_COLOR);
      int size = 2 * POINT_RADIUS;
      graphics.fillOval((int) origin.x - POINT_RADIUS, (int) origin.y - POINT_RADIUS, size, size);
    }
  }
}
