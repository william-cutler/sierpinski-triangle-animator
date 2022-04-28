package sierpinski;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import sierpinski.algorithm.SierpinskiPointGenerator;
import sierpinski.rendering.AnimatedPointPlotterFrame;

public class Sierpinski {
  static String pointString = "50,50 60,50 70,50 80,50 90,50 100,50 110,50 120,50";
  static int width = 300;
  static int height = 300;
  static int numPoints = 5000;
  static int tickRate = 60;
  static int numSides = 3;

  public static void main(String[] args) {
    List<Posn> points = new SierpinskiPointGenerator(width, height, numSides).generateSierpinskiPoints(
        numPoints);
    AnimatedPointPlotterFrame frame = new AnimatedPointPlotterFrame(points, width, height, tickRate);
    frame.plotPoints();
  }

  static List<Posn> points(String ptsString) {
    String[] points = ptsString.split(" ");
    return Arrays.stream(points).map(Sierpinski::point).collect(Collectors.toList());
  }
  static Posn point(String ptString) {
    String[] pt = ptString.split(",");
    return new Posn(Integer.parseInt(pt[0]), Integer.parseInt(pt[1]));
  }
}
