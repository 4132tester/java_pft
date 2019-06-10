package first.test;

public class QuadraticEquation {

  public double a;
  public double b;
  public double c;

  private int n;

  public QuadraticEquation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;

    // discriminant
    double d = b * b - 4 * a * c;

    if (d < 0) {
      n = 0;
    } else if (d == 0) {
      n = 1;
    } else n = 2;
  }

  public int rootNumber() {
    return n;
  }
}
