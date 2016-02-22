package primitiveCity;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.*;
import street.StreetD;
import other.Other;
import primitiveCity.PrimitiveCity;
import primitiveCity.Street;
import primitiveCity.Intersection;

public class PCityComponent extends JComponent{

	private double DEFAULT_WIDTH;
	private double DEFAULT_HEIGHT;
	private Graphics2D g;
	private PrimitiveCity p;
	public PCityComponent(PrimitiveCity _p,double width,double height) {
		p = _p;
		DEFAULT_WIDTH = width;
		DEFAULT_HEIGHT = height;
	}	
	public void setPrimitiveCity(PrimitiveCity _p) {
		p = _p;
	}
	public void paintComponent(Graphics g2) {
		g = (Graphics2D)g2;
		p.paintPrimitiveCity(g);
	}
	public Dimension getPreferredSize() {
		return new Dimension((int)DEFAULT_WIDTH, (int)DEFAULT_HEIGHT);
	}
}
