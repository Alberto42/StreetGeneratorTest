package primitiveCity;

import java.awt.geom.*;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.*;
import primitiveCity.Street;
import other.Other;
import geometry.Geo;
import primitiveCity.street.UsualStreet;
import primitiveCity.street.BlindStreet;
import primitiveCity.street.DoubleBlindStreet;
import geometry.GeoDraw;

public class PrimitiveCity{
	public ArrayList<Street>S;
	public List<Intersection>I;
	private	Random R = Other.R;
	private double height,width,minDistStreets,minDistIntersection,
			minDistBlind,minStreetLength,maxStreetLength,maxAngle;
	public PrimitiveCity(double _width,double _height
			) {
//	public PrimitiveCity(double _width,double _height,
//			double _minDistStreets = 0.0, double _minDistIntersection = 0.0,
//			double _minDistBlind = 0.0, double _minStreetLength = 0.0, 
//			double maxStreetLength = 0.0, double maxAngle = 0.0 ) {

		height = _height;
		width = _width;
		I = new LinkedList<Intersection>();
		S = new ArrayList<Street>();
	}
	
	public double getHeight() { return height; }
	public double getWidth() { return width; }
	public double getMaxAngle() { return maxAngle; }
	public double getMaxStreetLength() { return maxStreetLength; }
	public double getMinStreetLength() { return minStreetLength; }
	public double getMinDistStreets() { return minDistStreets; }

	public Point2D.Double RPoint() {
		Point2D.Double p = new Point2D.Double();
		p.setLocation(R.nextDouble()*width,R.nextDouble()*height );
		return p;
	}
	public void addRandomDoubleBlindStreet() {
		Street s = new DoubleBlindStreet();
		ArrayList<Street> Sex = new ArrayList<Street>();
		do {
			s.setLocation(RPoint(),RPoint() );
		}
		while(!s.TooClose(S,minDistStreets,Sex) );
		S.add(s);
	}	
	public void addRandomIntersection() {
		Intersection i;
		while(true) {
			Street s = S.get(R.nextInt()%S.size());
			i = new Intersection(Geo.RandomPointInLine(s,R), s);
			if (i.TooClose(I,minDistIntersection) == false )
				continue;
			if (s.checkBlindStreetDist((Point2D.Double)i, minDistBlind) ==false)
				continue;
			break;
		}
		I.add(i);
	}
	public void addRandomBlindStreet() {
		Collections.shuffle(I, R);
		while(true) {
			ListIterator<Intersection> it = I.listIterator(0);
			while(it.hasNext()) {
				Street s = it.next().getRandomBlindStreet(this,S,R);
				if (s != null)
					return;
			}
		}
	}
	public void addRandomUsualStreet() {
		Collections.shuffle(I, R);
		List<Intersection>IA = new ArrayList<Intersection>(I);
		//IA nie bede od tej pory modyfikowal
		while(true) {
			for(int i=0;i<IA.size();i++) {
				for(int j=i+1;j<IA.size();j++) {
					UsualStreet s = new UsualStreet(IA.get(i),IA.get(j) );
					ArrayList<Street>Sex = new ArrayList<Street>();
					Sex.add(IA.get(i).Parent);
					Sex.add(IA.get(j).Parent);
					if (!s.checkAngles(maxAngle))
						continue;
					if (!s.TooClose(S,minDistStreets,Sex))
						continue;
					
					S.add(s);
					return;
				}
			}
		}
	}
	public void paintPrimitiveCity(Graphics2D g) {
		for(int i=0;i<S.size();i++) {
			S.get(i).DrawPrimitive(g);
		}
		ListIterator<Intersection> it = I.listIterator(0);
		while(it.hasNext()) {
			GeoDraw.drawPoint2(it.next(),g,Color.ORANGE);
		}
	}
}
