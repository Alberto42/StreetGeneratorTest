package primitiveCity;

import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;
import java.util.*;
import other.Other;
import primitiveCity.Street;
import primitiveCity.street.*;
import geometry.Geo;
import primitiveCity.PrimitiveCity;

public class Intersection extends Point2D.Double{
	public Street Parent;
	/*
	P1 - > P2    
	Children[0] na prawo od wektora P
	Children[1] na lewo od wektora P
	*/
	public Street [] Children = new Street[2];
	public Intersection(double a, double b, Street _Parent) {
		super(a,b);
		Parent=_Parent;
		for(int i=0;i<2;i++)Children[i]=null;
		//Children = new ArrayList();
	}
	public Intersection(Point2D.Double p, Street _Parent) {
		super(p.getX(),p.getY());
		Parent=_Parent;
		for(int i=0;i<2;i++)Children[i]=null; //nie wiem czy to potrzebne
	}
	public boolean TooClose(List<Intersection> I, 
			double minDistIntersection) {
		for(int i=0;i<I.size();i++) {
			if (distance(I.get(i)) < minDistIntersection)
				return false;
		}
		return true;
	}
	//probuje wygenerowac losowa ulice ktora jest poprawna lub zwraca null
	//Angle jest podany w radianach
	/*public Street getRandomBlindStreet(double maxAngle, double minLength,
			double maxLength,double minDistStreets, ArrayList<Street> S,
			Random R) {*/
	public Street getRandomBlindStreet(PrimitiveCity pc,ArrayList<Street>S,
		   	Random R) {
		boolean b = R.nextBoolean(); 
		double angle = 0.0;
		double maxAngle = pc.getMaxAngle();
		double maxLength = pc.getMaxStreetLength();
		double minLength = pc.getMinStreetLength();
		double height = pc.getHeight();
		double width = pc.getWidth();
		double minDistStreets = pc.getMinDistStreets();

		for(int i=0;i<2;i++) {

			if (!b && Children[0] != null ) {

				angle = Math.PI*3/2 -maxAngle
					+ R.nextDouble()*2*maxAngle;
			}
			if (b && Children[1] != null ) {
				angle = Math.PI/2 -maxAngle
					+ R.nextDouble()*2*maxAngle;
			}
			if (angle != 0.0) {
				Point2D.Double p = (Point2D.Double)Parent.getP2().clone();
				assert p != Parent.getP2();

				p = Geo.PointRotate(Geo.Subtract(p,this),angle);
				p = Geo.Add(this,p);
				double difference = maxLength - minLength;
				p = Geo.FindSecondPoint(this,p,minLength+
						R.nextDouble()*difference);
				Street s = new BlindStreet(this,p);

				ArrayList<Street> Sex = new ArrayList<Street>();
				Sex.add(Parent);
				if (!s.TooClose(S,minDistStreets,Sex) )	
					s=null;
				if (!Geo.PointExists(s.getP2(),width,
							height))
					s=null;
				int c = (b == true)? 1 : 0 ;
				Children[c] = s;
			}
			b = !b;
		}
		return null;
	}
}
