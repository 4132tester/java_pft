package first.test;

public class MyFirstProgram {
  public static void main(String[] args) {
    System.out.println("Hello world!\n");

    double r = 2;
    System.out.println("Площадь круга с раудисом " + r + " равна " + area(r));

    double a = 2;
    double b = 3;
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " равна " + area(a, b));
  }

  public static double area(double r) {
    return 3.1416 * r * r / 2;
  }
  public static double area(double a, double b) {
    return a * b;
  }
}