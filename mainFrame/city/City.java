package mainFrame.city;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class City extends JComponent{

	private int DEFAULT_WIDTH = 800;
	private int DEFAULT_HEIGHT = 500;
	private Graphics2D g;
	public City() {
	}	
	//funkcje beda korzystaly z zmiennych znajdujacych sie w Other
	private void paintPrimitiveCity(PrimitiveCity p) {
		p.paintPrimitiveCity(g);
	}
	private void paintCity() {
	}
	public void paintComponent(Graphics g) {
		this.g = (Graphics2D)g;
		paintPrimitiveCity()
		boolean TypeOfCity = false;
		/*if (TypeOfCity)
			paintCity();
		else paintPrimitiveCity();*/
	}
}
