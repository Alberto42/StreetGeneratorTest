package primitiveCityTest;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import other.Other;
import mainFrame.MainFrame;
import primitiveCity.PCityComponent;
import primitiveCity.PrimitiveCity;
/*
   Ta funkcja ma za zadanie:
   1. stworzyc obiekt primitiveCity
   2. wykonac na nim pewne operacje
   3. przeslac ten obiekt do mainFrame/City

   Pozniej mainFrame/City go narysuje
   */
public class PrimitiveCityTest{
	public static double DEFAULT_WIDTH = 300;
	public static double DEFAULT_HEIGHT = 300;
	static MyFrame frame;
	public static void main(String [] args) {
		PrimitiveCity p = new PrimitiveCity(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		frame = new MyFrame(p);
		frame.setTitle("PrimitiveCityTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Loop();
	}
	private static void EasyTest1 (PrimitiveCity c) {
		int ile=5;
		for(int i=0;i<ile;i++) {
			c.addRandomDoubleBlindStreet();
		}
	}
	private static void EasyTest2 (PrimitiveCity c) {
		int ileS=1,ileI=1;
		for(int i=0;i<ileS;i++) {
			c.addRandomDoubleBlindStreet();
		}
		for(int i=0;i<ileI;i++) {
			c.addRandomIntersection();
		}
	}
	private static void MediumTest1 (PrimitiveCity c) {
		int ileS=1,ileI=1,ileB=1;
		for(int i=0;i<ileS;i++) {
			c.addRandomDoubleBlindStreet();
		}
		for(int i=0;i<ileI;i++) {
			c.addRandomIntersection();
		}
		for(int i=0;i<ileB;i++) {
			c.addRandomBlindStreet();
		}
	}
	private static void Loop() {
		int seed = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			String s = sc.next();
			if (s.equals("n")) {
				seed++;
			}
			else
			if (s.equals("p")) {
				seed--;
			}
			else {
				seed = Integer.parseInt(s);
			}
			Other.R.setSeed(seed);
			PrimitiveCity p = new PrimitiveCity(DEFAULT_WIDTH,DEFAULT_HEIGHT);
			//cos robie z tym PrimitiveCity
			EasyTest1(p);
			
			frame.c.setPrimitiveCity(p);
			
			frame.repaint();
		}
	}
}
class MyFrame extends JFrame {
	public PCityComponent c;
	public MyFrame(PrimitiveCity p) {
		c = new PCityComponent(p, p.getWidth(),p.getHeight());
		add(c);
		pack();
	}
	
}

