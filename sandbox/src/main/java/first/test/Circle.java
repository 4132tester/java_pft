package first.test;

public class Circle {

  double r;

  public Circle (double r){
    this.r = r;
  }

  public double area() {
    return 3.1416 * this.r * this.r / 2;
  }
}
