package first.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestForEquations {

  @Test
  public void verifyNumberOfRoots() {
    QuadraticEquation qe1 = new QuadraticEquation(1,2,1);
    Assert.assertEquals(qe1.rootNumber(), 1);

    QuadraticEquation qe0 = new QuadraticEquation(2,1,3);
    Assert.assertEquals(qe0.rootNumber(), 0);

    QuadraticEquation qe3 = new QuadraticEquation(2,5,2);
    Assert.assertEquals(qe3.rootNumber(), 2);
  }
}
