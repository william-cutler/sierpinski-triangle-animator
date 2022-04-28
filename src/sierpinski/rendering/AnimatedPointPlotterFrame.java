package sierpinski.rendering;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import sierpinski.Posn;

/**
 * A non-interactive visual view built using JFrame that plays the animation at the specified tick
 * rate when asked to render. This view supports models with shapeTypes of "ellipse" and
 * "rectangle"
 */
public class AnimatedPointPlotterFrame extends JFrame {

  private Timer frameTimer;

  private double tickRate;
  private IncrementalPointPlotPanel aPanel;
  private final List<Posn> allPoints;
  private int pointIndex = 0;

  /**
   * Initializes an un-renderable view that must be initialized with initializeView.
   */
  public AnimatedPointPlotterFrame(List<Posn> points, int width, int height, int tickRate) {
    this.allPoints = points;
    this.frameTimer = new Timer();
    this.aPanel = new IncrementalPointPlotPanel(width, height);
    this.tickRate = tickRate;
    this.setLayout(new BorderLayout());
    this.add(aPanel, BorderLayout.CENTER);
    this.setTitle("Sierpinski Animation");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
  }

  public void plotPoints() {
    this.frameTimer.schedule(
        new TimerTask() {
          @Override
          public void run() {
            incrementAnimationPanel();
          }
        },
        0,
        this.tickPeriodInMillis());
    this.setVisible(true);
  }

  /**
   * Updates and refreshes the animation panel to the next tick.
   */
  private void incrementAnimationPanel() {
    if (this.pointIndex < this.allPoints.size()) {
      if (this.pointIndex < 100) {
        this.aPanel.addPoint(this.allPoints.get(this.pointIndex));
        this.pointIndex++;
      } else {
        for (int numAdded = 0; numAdded < 5; numAdded ++) {
          this.aPanel.addPoint(this.allPoints.get(this.pointIndex));
          this.pointIndex++;
        }
      }
    }
    this.aPanel.repaint();
  }

  /**
   * Calculates the number of milliseconds between each tick.
   *
   * @return the number of milliseconds between each tick as a long.
   */
  private long tickPeriodInMillis() {
    return (long) (1000 * (1.0 / this.tickRate));
  }
}

