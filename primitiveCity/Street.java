package primitiveCity;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import java.util.*;
import other.Other;
import primitiveCity.Intersection;

public abstract class Street extends Line2D.Double{
	protected ArrayList<Intersection> MyIS;	
	public Street () {
		MyIS = new ArrayList<Intersection>(); 
	}
	double Distance(Street S) {
		if (intersectsLine(S))
			return 0;
		return Math.min(
				Math.min( ptLineDist( S.getP1() ),ptLineDist( S.getP2() ) ),
				Math.min( S.ptLineDist( getP1() ),S.ptLineDist( getP2() ) ) 
				);
	}
	//Zwraca prawde jesli odleglosc jest prawidlowa
	//Sex - Streets Excluded
	public boolean TooClose(ArrayList<Street>S, double minDistStreets, ArrayList<Street>Sex) {
		for(int i=0;i<S.size();i++) {
			for(int j=0;j<Sex.size();j++)
				if ( Sex.get(j) == S.get(i) )continue;
			if (Distance(S.get(i) ) <minDistStreets)
				return false;
		}
		return true;
	}
	public void addIntersection(Intersection i) {
		//mozna tu wstawic asercje
		MyIS.add(i);
	}
	public void setLocation(Point2D.Double p1, Point2D.Double p2) {
		setLine(p1.getX(),p1.getY(),p2.getX(),p2.getY() );
	}
	public abstract boolean checkBlindStreetDist(Point2D.Double p, 
			double minDistBlind);
	public abstract void DrawPrimitive(Graphics2D g);
}
