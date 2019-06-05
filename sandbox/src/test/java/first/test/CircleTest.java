package first.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CircleTest {

  @Test
  public void testArea(){
    Circle c = new Circle(3);
    Assert.assertEquals(c.area() ,14.1372);
  }
}
