package primitiveCity.street;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import primitiveCity.Street;
import other.Other;
import primitiveCity.Intersection;
import geometry.GeoDraw;

public class BlindStreet extends Street{
	//getP1() zwroci poczatek
	//getP2() zwraca slepa koncowke
	private Intersection Parent;
	public BlindStreet(Intersection _Parent) {
		super();
		Parent=_Parent;
	}
	public BlindStreet(Intersection _Parent, Point2D.Double end) {
		super();
		this.setLine(_Parent,end);
		Parent=_Parent;
	}
	public boolean checkBlindStreetDist(Point2D.Double p, double minDist) {
		return (getP2().distance(p) >= minDist);
	}
	public void DrawPrimitive(Graphics2D g) {
		g.draw(this);
		GeoDraw.drawPoint2((Point2D.Double)getP2(),g,Color.GREEN);
	}		
}
