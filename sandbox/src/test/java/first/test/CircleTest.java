package first.test;

import org.testng.annotations.Test;

public class CircleTest {

  @Test
  public void testArea(){
    Circle c = new Circle(3);
    assert c.area() == 13.1372;
  }
}
