package street;

import java.awt.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.util.Random;
import other.Other;
import geometry.Geo;
import geometry.GeoDraw;

public class StreetD {
	static double width; //szerokosc wszystkich ulic jest taka sama
	static double shoulderW; //szerokosc pobocza
	Point2D.Double [] P; //zawiara punkty na ktore sklada sie ulica
	boolean SorS; // Slanting or Straight
	//0- Straight	1- Slanting
	//Sa 2 typy ulic
	static Graphics2D G;
	static int Sw,Sh;
//************************************************************************
	private Point2D.Double TriangleFrom2Points(Point2D.Double [] P,
			boolean Right ) {
		/*
		Funkcja liczy 3 wierzcholek trojkata prostokatnego w ktorym P[0] P[1] sa 
		wierzcholkami przeciwprostokatnej natomiast Right mowi czy 3 wierzcholek
	  	ma byc po prawo czy lewo. Jesli Right == 1 to po prawo.	

		va ,vb wektory odpowiednich odcinkow
		*/
		double a = P[0].distance(P[1]),b=width,c,sa,ca;
		c = Math.sqrt(a*a - b*b);
		System.err.println(a + " " + b + " " + c );

		Point2D.Double va = new Point2D.Double(
			P[1].getX() - P[0].getX(),P[1].getY() - P[0].getY()); 
		System.err.println(va.getX() + " " + va.getY() );

		//sinus i cosinus kata miedzy odcinkami a i b
		sa = c/a;
		ca = (a*a+b*b-c*c)/(2*a*b);
		System.err.println("sinus i cosinus " + sa +" " + ca );

		Point2D.Double vb= new Point2D.Double(va.getX()*b/a,va.getY()*b/a);

		//wykonuje obrot wedlug wzoru znalezionego w internecie
		int RL=1;
		if (Right == false)RL=-1;
		vb.setLocation(vb.getX()*ca-vb.getY()*sa*RL,
				vb.getX()*sa*RL + vb.getY()*ca );

		return new Point2D.Double(vb.getX()+P[0].getX(),
				vb.getY()+P[0].getY() );
	}
//************************************************************************
	private Point2D.Double [] RectangleFrom2PointsSlanting(Point2D.Double [] P,
			boolean Right) {
		Point2D.Double [] r = new Point2D.Double [4];
		r[0]=new Point2D.Double(P[0].getX(),P[0].getY());
		Point2D.Double p  = TriangleFrom2Points(P, Right);
		r[1]=new Point2D.Double(p.getX(),p.getY());
		r[2]=new Point2D.Double(P[1].getX(),P[1].getY());

		p=P[0];
		P[0]=P[1];
		P[1]=p;

		p  = TriangleFrom2Points(P,Right);
		r[3]=new Point2D.Double(p.getX(),p.getY());
		return r;
	}
//************************************************************************
	private Point2D.Double [] RectangleFrom2PointsStraight(
			Point2D.Double [] P) {
		//tworzy prostokat znajac 2 punkty i szerokosc(width)
		//prostokat jest na lewo od wektora p[0] -> p[1]
		//a1,a2 to wspolczynniki kierunkowe
		if (P[0].getX() == P[1].getX())
			P[0].setLocation(P[0].getX()+0.000001,P[0].getY());
		if (P[0].getY() == P[1].getY())
			P[0].setLocation(P[0].getX(),P[0].getY()+0.000001);
	
		double a1 = (P[1].getY()-P[0].getY())/(P[1].getX()-P[0].getX() );
		double a2=((double)-1)/a1;
		Point2D.Double [] r = new Point2D.Double [4];
		Point2D.Double vp=new Point2D.Double(P[1].getX()-P[0].getX(),
				P[1].getY()-P[0].getY());

		r[0]=new Point2D.Double(P[0].getX(),P[0].getY());
		r[1]=new Point2D.Double(P[1].getX(),P[1].getY());

		double w=width;
		Point2D.Double xy = new Point2D.Double();
		xy.setLocation(Math.sqrt((w*w)/(1+a2*a2) ),0);
		xy.setLocation(xy.getX(),xy.getX()*a2);
		if (Geo.SinAngle(xy,new Point2D.Double(0,0),vp) <0)
			xy.setLocation(-xy.getX(),-xy.getY());
		r[2]=new Point2D.Double(P[1].getX()+xy.getX(),P[1].getY()+xy.getY());
		r[3]=new Point2D.Double(P[0].getX()+xy.getX(),P[0].getY()+xy.getY());
		return r;
	}
//************************************************************************
	private void PrintPoint(Point2D.Double p) {
		System.err.print("(" + p.getX() + ";" + p.getY() + ")" );
	}
	private void PrintP() {
		for(int i=0;i<4;i++)
			PrintPoint(P[i]);
		System.err.println();
	}
//************************************************************************
	public StreetD(Point2D.Double [] _P, Graphics2D _G) {
		P=_P;
		G=_G;
		Sw = Other.Sw; Sh = Other.Sh;width=Other.width;
		shoulderW = width/10;
		if (Geo.CheckIntersect(P[0],P[2],P[1],P[3]))
			Other.swapPoints(P[0],P[1]);
		PrintP();
	}
//************************************************************************
	private void setP(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2,
		Point2D.Double p3,Point2D.Double p4, Point2D.Double p5, boolean b) {
		Point2D.Double [] P2 = new Point2D.Double[6];
		P2[0]=p0; P2[1]=p1; P2[2]=p2;
		P2[3]=p3; P2[4]=p4; P2[5]=p5;
		for(int i=0;i<6;i++) {
			Point2D.Double p = P2[i];	
			P2[i]= new Point2D.Double(p.getX(),p.getY());
		}
		P=P2;
		SorS=b;
	}
//************************************************************************
	//modyfikuje P znajdujac pozostale wspolrzedne. Jezeli rozmiar P = 4 to 
	//nie znalazlo
	private void FindSCoordinates() {
		Point2D.Double [] s;
		Point2D.Double [] p0;
		System.err.println(Geo.SinAngle(P[2],P[0],P[1]) );
		if(Geo.SinAngle(P[2],P[0],P[1])>0) {
			Other.swapPoints(P[0],P[1]);
			Other.swapPoints(P[2],P[3]);
			System.err.println("FindSCoordinates - zamiana");
		}

		for(int i=0;i<2;i++) {
			p0=Other.Af2P(P[0],P[2]);
			s = RectangleFrom2PointsStraight(p0);
			PrintP();
			System.err.println("Katy to: "+ Geo.SinAngle(P[1],P[0],s[3]) );
			System.err.println("Katy to: "+ Geo.SinAngle(s[2],P[2],P[3]) );
			System.err.println("Katy to(c): "+ Geo.CosAngle(P[1],P[0],s[3]) );
			System.err.println("Katy to(c): "+ Geo.CosAngle(s[2],P[2],P[3]) );
			if (Geo.RightAngle(P[1],P[0],s[3]) && 
					Geo.RightAngle(s[2],P[2],P[3]) ) {
				setP(P[1],P[0],s[3],s[2],P[2],P[3],false);
				return;
			}
			Other.swapPoints(P[0],P[3]);
			Other.swapPoints(P[1],P[2]);
		}

		p0=Other.Af2P(P[0],P[3]);
		s = RectangleFrom2PointsSlanting(p0,false);
		//GeoDraw.drawRectangle(s,G,Color.red);
		if (Geo.RightAngle(P[1],P[0],s[1]) && 
				Geo.RightAngle(P[2],P[3],s[3]) ) {
			setP(P[1],P[0],s[1],P[3],s[3],P[2],true);
			return;
		}

		p0 = Other.Af2P(P[2],P[1]);
		s = RectangleFrom2PointsSlanting(p0,true);
		//GeoDraw.drawRectangle(s,G,Color.red);
		if (Geo.RightAngle(s[3],P[1],P[0]) && 
				Geo.RightAngle(s[1],P[2],P[3]) ) {
			setP(P[0],P[1],s[3],P[2],s[1],P[3],true);
			return;
		}
	}
//************************************************************************
	private void DrawArc(Point2D.Double l, Point2D.Double m, Point2D.Double r) {
		double s = Geo.SinAngle(l,m,r);
		System.err.println("DrawArc Sinus = "+s);
		Point2D.Double p = new Point2D.Double(r.getX()-m.getX(),
				r.getY()-m.getY() );
		Arc2D.Double arc = new Arc2D.Double();
		arc.setArcByCenter(m.getX(),
			m.getY(),width,Math.toDegrees(Math.atan(p.getY()/p.getX()) ),
			Math.toDegrees(Math.asin(s)),arc.OPEN );
		G.draw(arc);
	}
//************************************************************************
	public void DrawStreet() {
		try {
			FindSCoordinates();
			Path2D.Double middle = new Path2D.Double();
			middle.moveTo(P[1].getX(),P[1].getY());
			for(int i=2;i<=4;i++)
				middle.lineTo(P[i].getX(),P[i].getY());
			G.setColor(Color.gray);
			G.fill(middle);

			G.setColor(Color.white);
			Point2D.Double [] Np = new Point2D.Double [2];
			Np[0] = Geo.FindSecondPoint(P[1],P[2],shoulderW);
			Np[1] = Geo.FindSecondPoint(P[4],P[3],shoulderW);
			G.draw(new Line2D.Double(Np[0],Np[1]));

			Np[0] = Geo.FindSecondPoint(P[2],P[1],shoulderW);
			Np[1] = Geo.FindSecondPoint(P[3],P[4],shoulderW);
			G.draw(new Line2D.Double(Np[0],Np[1]));

			float dash1 [] = {10.0f}; 
			G.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_MITER,10.0f,dash1,0.0f));
			Np[0] = Geo.FindSecondPoint(P[1],P[2],width/2);
			Np[1] = Geo.FindSecondPoint(P[4],P[3],width/2);
			G.draw(new Line2D.Double(Np[0],Np[1]));

			G.setColor(Color.gray);
			GeoDraw.drawPoint2(P[0],G,Color.green);
			GeoDraw.drawPoint2(P[1],G,Color.green);
			GeoDraw.drawPoint2(P[2],G,Color.green);
			DrawArc(P[0],P[1],P[2]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			for(int i=0;i<4;i++)
				GeoDraw.drawPoint2(P[i],G,Color.orange);
		}
	}
	public void DrawStreet3() {
		PrintP();
		width = P[0].distance(P[1]);
		for(int i=0;i<2;i++)
			GeoDraw.drawPoint2(P[i],G,Color.black);
		for(int i=2;i<4;i++)
			GeoDraw.drawPoint2(P[i],G,Color.gray);
		FindSCoordinates();
		for(int i=2;i<4;i++)
			GeoDraw.drawPoint2(P[i],G,Color.orange);
	}
	public void DrawStreet2() {
		Point2D.Double [] p = {new Point2D.Double(300,50),
			new Point2D.Double(600,50) };

		System.out.println();
		//Other.swapPoints(p[0],p[1]);
		PrintPoint(p[0]);
		PrintPoint(p[1]);
		Point2D.Double [] R = RectangleFrom2PointsSlanting(p,false);
		for(int i=0,j=1;i<4;i++,j=(j+1)%4) {
			GeoDraw.drawPoint2(R[i],G,Color.black);
			G.draw(new Line2D.Double(R[i],R[j]) );
		}
		GeoDraw.drawPoint2(R[1],G,Color.red);
		GeoDraw.drawPoint2(R[3],G,Color.orange);
	}
//************************************************************************
}
