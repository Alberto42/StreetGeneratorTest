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

public class DoubleBlindStreet extends Street{
	public DoubleBlindStreet() {
		super();
	}
	public boolean checkBlindStreetDist(Point2D.Double p, double minDist) {
		return (getP2().distance(p) >= minDist) &&
	 		(getP1().distance(p) >= minDist);
	}
	public void DrawPrimitive(Graphics2D g) {
		g.setPaint(Color.RED);
		g.draw(this);
		g.setPaint(Color.BLACK);
		GeoDraw.drawPoint2((Point2D.Double)getP1(),g,Color.GREEN);
		GeoDraw.drawPoint2((Point2D.Double)getP2(),g,Color.GREEN);
	}		
}
