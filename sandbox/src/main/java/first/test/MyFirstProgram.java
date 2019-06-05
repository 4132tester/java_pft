package first.test;

public class MyFirstProgram {
  public static void main(String[] args) {
    System.out.println("Hello world!\n");

    Circle c = new Circle(3.1416);
    System.out.println("Площадь круга с раудисом " + c.r + " равна " + area(c));

    Rectangle r = new Rectangle(3, 4);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + area(r));

    Point a = new Point(0,0);
    Point b = new Point(3,4);
    System.out.println("Расстояние между двумя точками А и В равно: " + distance(a,b));
  }

  public static double area(Circle c) {
    return 3.1416 * c.r * c.r / 2;
  }
  public static double area(Rectangle r) {
    return r.a * r.b;
  }

  public static double distance(Point a, Point b){
    double cathetus1 = a.x - b.x;
    double cathetus2 = a.y - b.y;
    return Math.sqrt(cathetus1 * cathetus1 + cathetus2 * cathetus2);
  }
}