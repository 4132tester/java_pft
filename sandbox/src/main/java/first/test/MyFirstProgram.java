package first.test;

  public class MyFirstProgram {
  public static void main(String[] args) {
    System.out.println("Hello world!\n");

    Circle c = new Circle(3);
    System.out.println("Площадь круга с раудисом " + c.r + " равна " + c.area());

    Rectangle r = new Rectangle(3, 4);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());

    Point a = new Point(0,0);
    Point b = new Point(3,4);
    System.out.println("Расстояние между двумя точками А и В равно: " + Point.distance(a,b));
  }

  }