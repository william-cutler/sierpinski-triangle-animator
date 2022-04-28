package sierpinski.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sierpinski.Posn;

public class SierpinskiPointGenerator {
  private static final int BUFFER = 10;
  private List<Posn> startingPoints;

  public SierpinskiPointGenerator(int canvasWidth, int canvasHeight, int numSides) {
    Posn bottomLeft = new Posn(BUFFER, canvasHeight - BUFFER);
    Posn top = new Posn(canvasWidth / 2, BUFFER);
    Posn bottomRight = new Posn(canvasWidth - BUFFER, canvasHeight - BUFFER);
    List<Posn> startingPoints = setPolygonSides(canvasWidth / 2, canvasHeight / 2,
        Math.min(canvasHeight, canvasWidth) / 2 - BUFFER, numSides);

    this.startingPoints = new ArrayList<>(startingPoints);
  }

  private static List<Posn> setPolygonSides(int centerX, int centerY, int radius, int sides) {
    System.out.println(radius);
    List<Posn> result = new ArrayList<>();
    final double angleStep = Math.PI * 2 / sides;
    double angle = Math.PI; // assumes one point is located directly beneath the center point
    result.add(new Posn(centerX, centerY));
    for (int i = 0; i < sides; i++, angle += angleStep) {
      result.add(new Posn((int)(Math.sin(angle) * radius + centerX), (int) (Math.cos(angle) * radius + centerY)));
    }
    return result;
  }

  public List<Posn> generateSierpinskiPoints(int numPoints) {
    Random rand = new Random();
    List<Posn> result = new ArrayList<>(this.startingPoints);

    Posn focalPoint = this.startingPoints.get(this.startingPoints.size() - 1);

    for (int shot = this.startingPoints.size(); shot < numPoints; shot++) {
      int pt1Index = rand.nextInt(this.startingPoints.size());

      Posn posn1 = this.startingPoints.get(pt1Index);
      Posn newPosn = new Posn((posn1.x + focalPoint.x) / 2, (posn1.y + focalPoint.y) / 2);
      result.add(newPosn);
      focalPoint = newPosn;
    }
    return result;
  }
}
