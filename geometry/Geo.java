//Klasa zawiara potrzebne funkcje geometryczne
package geometry;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Random;

public class Geo {
	//*************************************************************************	
	public static double CosAngle(Point2D.Double a, Point2D.Double m, 
			Point2D.Double b ) {
		double x=m.distance(a),y=m.distance(b),z=a.distance(b);
		return (x*x+y*y-z*z)/(2*x*y);
	}

	//*************************************************************************	
	public static double SinAngle(Point2D.Double a, Point2D.Double m,
			Point2D.Double b ) {
		double A=Math.sqrt( (a.getX()-m.getX())*(a.getX()-m.getX())
				+ (a.getY()-m.getY())*(a.getY()-m.getY()));
		double B=Math.sqrt( (b.getX()-m.getX())*(b.getX()-m.getX())
				+ (b.getY()-m.getY())*(b.getY()-m.getY()));
		return ( (a.getX()-m.getX())*(b.getY()-m.getY()) 
				- (a.getY()-m.getY())*(	b.getX()-m.getX()))/(A*B);
	}

	//*************************************************************************	
	//zostaly zmienione typy ( mam nadzieje ze to nie zepsuje streeta )
	public static boolean PointExists(Point2D a,double Sw,double Sh) {
		return a.getX() >=0 && a.getX() <= Sw && 
			a.getY() >=0 && a.getY() <= Sh;
	}

	//*************************************************************************	
	public static boolean RightAngle(Point2D.Double a, 
			Point2D.Double m, Point2D.Double b) {
		if (a == b ) return true; 
		if (CosAngle(a,m,b) <= 0 )return false;
		return 0 < SinAngle(a,m,b) && SinAngle(a,m,b) < 1;
	}

	//*************************************************************************	
	//znajduje drugi punkt oddalnony od p1 o "k" w strone punktu p2
	public static Point2D.Double FindSecondPoint(Point2D.Double p1,
			Point2D.Double p2,double k) {
		Point2D.Double xy,a;
		double o = p1.distance(p2);
		a=new Point2D.Double(p2.getX()-p1.getX(),p2.getY()-p1.getY() );
		xy=new Point2D.Double(a.getX()*k/o,a.getY()*k/o);
		return new Point2D.Double(p1.getX()+xy.getX(),p1.getY()+xy.getY());
	}
	//*************************************************************************	
	public static Point2D.Double RandomPointInLine(Line2D.Double l, Random R) {
		double k = l.getP1().distance(l.getP2() );
		return FindSecondPoint( (Point2D.Double)l.getP1(),
				(Point2D.Double)l.getP2(), k*R.nextDouble());
	}
	
	//*************************************************************************
	//zwraca prawde jezeli sie przecinaja
	public static boolean CheckIntersect(Point2D.Double a1,Point2D.Double a2, 
			Point2D.Double b1, Point2D.Double b2 ) {
		Line2D [] l = {new Line2D.Double(a1,a2),new Line2D.Double(b1,b2) };
		return l[0].intersectsLine(l[1]);
	}
	//*************************************************************************
	//obraca p o kat angle wzgledem poczatku ukladu wspolrzednych
	//jesli kat jest dodatni to obraca w lewo(przeciwnie do ruch wskazowek)
	public static Point2D.Double PointRotate(Point2D.Double p, double angle) {
		double s=Math.sin(angle),c=Math.cos(angle),
			x=p.getX(),y=p.getY();
		return new Point2D.Double(x*c - y*s,x*s+y*c);
	}

	//*************************************************************************	
	public static Point2D.Double Subtract(Point2D.Double p1,
			Point2D.Double p2) {	
		return new Point2D.Double(p1.getX()-p2.getX(),p1.getY()-p2.getY() );
	}
	public static Point2D.Double Add(Point2D.Double p1,
			Point2D.Double p2) {	
		return new Point2D.Double(p1.getX()+p2.getX(),p1.getY()+p2.getY() );
	}

	//*************************************************************************	
	public static void TestAngle() {
		Point2D.Double a = new Point2D.Double(2,10);
		Point2D.Double m = new Point2D.Double(1,1);
		Point2D.Double b = new Point2D.Double(10,2);
		//a powinno byc na prawo od b to kat bedzie OK
		System.out.println(SinAngle(a,m,b) );
		System.out.println(CosAngle(b,m,a) );
	}

	//*************************************************************************	
	public static void main(String arg[]) {
		TestAngle();
	}
}
