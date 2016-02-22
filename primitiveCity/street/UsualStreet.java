package primitiveCity.street;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import primitiveCity.Street;
import other.Other;
import geometry.Geo;
import primitiveCity.Intersection;

public class UsualStreet extends Street{
	//getP1() zwroci poczatek
	private Intersection [] Parent;
	public UsualStreet(Intersection  _Parent1, Intersection _Parent2) {
		super();
		Parent = new Intersection [2];
		Parent[0]=_Parent1;
		Parent[1]=_Parent2;
	}
	public boolean checkBlindStreetDist(Point2D.Double p, double minDist) {
		return true;
	}
	public boolean checkAngles(double maxAngle ) {
		double minSin = Math.asin(maxAngle);
		if (minSin < 0)
			minSin*=-1;
		for(int i=0;i<2;i++) {
			Street s = Parent[i].Parent;
			double sin = Geo.SinAngle((Point2D.Double)Parent[(i+1)%2],
				(Point2D.Double)Parent[i],(Point2D.Double)s.getP1());
			if (sin < 0)sin*=-1;
			if (sin < minSin)return false;
		}
		return true;
	}
	public void DrawPrimitive(Graphics2D g) {
		g.setPaint(Color.BLUE);
		g.draw(this);
		g.setPaint(Color.BLACK);
	}		
}
