package sierpinski.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sierpinski.Posn;

public class SierpinskiPointGenerator {
  private static final int BUFFER = 10;
  private final List<Posn> startingPoints;

  public SierpinskiPointGenerator(int canvasWidth, int canvasHeight, int numSides) {
    List<Posn> startingPoints = setPolygonSides(canvasWidth / 2.0, canvasHeight / 2.0,
        Math.min(canvasHeight, canvasWidth) / 2.0 - BUFFER, numSides);
    this.startingPoints = new ArrayList<>(startingPoints);
  }

  private static List<Posn> setPolygonSides(double centerX, double centerY, double radius, int sides) {
    System.out.println(radius);
    List<Posn> result = new ArrayList<>();
    final double angleStep = Math.PI * 2 / sides;
    double angle = Math.PI; // assumes one point is located directly beneath the center point
    for (int i = 0; i < sides; i++, angle += angleStep) {
      result.add(new Posn((Math.sin(angle) * radius + centerX), (Math.cos(angle) * radius + centerY)));
    }
    return result;
  }

  public List<Posn> generateSierpinskiPoints(int numPoints) {
    Random rand = new Random();
    List<Posn> result = new ArrayList<>(this.startingPoints);

    Posn focalPoint = this.startingPoints.get(0);

    for (int shot = this.startingPoints.size(); shot < numPoints; shot++) {
      int pt1Index = rand.nextInt(this.startingPoints.size());

      Posn posn1 = this.startingPoints.get(pt1Index);
      focalPoint = midpoint(posn1, focalPoint);
      result.add(focalPoint);
    }
    return result;
  }

  private static Posn midpoint(Posn p1, Posn p2) {
    return new Posn((p1.x + p2.x) / 2.0, (p1.y + p2.y) / 2.0);
  }
}
