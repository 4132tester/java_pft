package first.test;

public class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double distance(Point a, Point b){
    double cathetus1 = a.x - b.x;
    double cathetus2 = a.y - b.y;
    return Math.sqrt(cathetus1 * cathetus1 + cathetus2 * cathetus2);
  }
}
