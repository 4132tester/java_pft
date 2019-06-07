package first.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsDistanceCalculationTest {

  @Test
  public void distanceVerification() {
    Point a = new Point(-1,-1);
    Point b = new Point(2,3);
    Assert.assertEquals(Point.distance(a,b),5.0);
  }
}
