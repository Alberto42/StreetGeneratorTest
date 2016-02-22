//Klasa zawiara funkcje rysujące pewne kształty na płaszczyźnie
package geometry;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
public class GeoDraw {
	//***********************************************************************
	public static void drawPoint2(Point2D.Double p, Graphics2D g2, Color c) {
		Ellipse2D.Double e = new Ellipse2D.Double() ;
		double r=10;
		g2.setPaint(c);
		while(r > 0 ) {
			e.setFrame(p.getX()-r/2,p.getY()-r/2,r,r);
			r-=1;
			g2.draw(e);
		}
		g2.setPaint(Color.black);
	}

	//***********************************************************************
	public static void drawRectangle(Point2D.Double [] p,
			Graphics2D g2,Color c) {
		g2.setPaint(c);
		for(int i=0,j=1;i<4;i++,j=(j+1)%4) {
			g2.draw(new Line2D.Double(p[i],p[j]) );	
		}
		g2.setPaint(Color.black);
	}

	//***********************************************************************
}
