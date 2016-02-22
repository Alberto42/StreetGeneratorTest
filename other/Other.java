package other;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;
public class Other {
	public static final int Sw=800,Sh=700;
	public static double width=30;
	public static int seed=0;
	public static Random R = new Random(seed);
	public static void swapPoints(Point2D.Double p1,Point2D.Double p2) {
		Point2D.Double tmp = new Point2D.Double(p1.getX(),p1.getY());
		p1.setLocation(p2);
		p2.setLocation(tmp);
	}
	//************************************************************************
	public static Point2D.Double [] Af2P(Point2D.Double p1, Point2D.Double p2) {
		//Point2D.Double [] p = {p1,p2};
		Point2D.Double [] p = new Point2D.Double [2];
		p[0]=p1;
		p[1]=p2;
		return p;
	}
	//************************************************************************
	public void RandomShuffle(ArrayList<Object> A,int Number ) {
		for(int i=0;i<Number;i++) {
			int s = A.size();
			int a=R.nextInt()%s,b=R.nextInt()%s;
			Object o = A.get(a);
			A.set(a,A.get(b));
			A.set(b,o);
		}
	}	
	public static void main(String [] args) {
		primitiveCity.PrimitiveCity p = new primitiveCity.PrimitiveCity(35,50);
		
		System.out.println("klasa nazywa sie <"+p.getClass().getName()+">" );
	}
}
