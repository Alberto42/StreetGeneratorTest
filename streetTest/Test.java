package streetTest;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;
import street.StreetD;
import other.Other;
import geometry.Geo;

public class Test extends JPanel{

	static Graphics2D g2;
	static JFrame frame;
	static int Sw,Sh;

  	public Test(){
	   super();
	}
	static int seed=0;
	private Point2D.Double [] GenerateP() {
		Random Gen = new Random(seed);
		System.out.println();
		System.out.println("Generuje Testy nr "+ seed);
		Point2D.Double [] P = new Point2D.Double[4];
		boolean b = true;
		while(b) {
			do {
				for(int i=0;i<4;i++)
					P[i]=new Point2D.Double(Gen.nextDouble()*Sw,
							Gen.nextDouble()*Sh);
			}
			while(P[0].distance(P[2])<Sh/2);
			double width = Other.width;
			P[1] = Geo.FindSecondPoint(P[0],P[1],width);
			P[3] = Geo.FindSecondPoint(P[2],P[3],width);
			b=false;
			for(int i=0;i<4;i++)	
				if (!Geo.PointExists(P[i],Sw,Sh))
					b=true;
		}
		if (Geo.CheckIntersect(P[0],P[2],P[1],P[3]) )
			Other.swapPoints(P[0],P[1]);
		return P;
	}
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D)g;
		Point2D.Double [] P = GenerateP();
		StreetD s = new StreetD(P,g2);
		s.DrawStreet();
	}
	private static void TestStreet() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			frame.repaint();
			String n = sc.next();
			System.out.println(n);
			if (n.equals("n"))
				seed++;
			else if (n.equals("p"))
				seed--;
			else break;
		}
	}
	private static void TestStreet2() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			seed = sc.nextInt();
			frame.repaint();
		}
	}
  public static void main(String arg[]){
    frame = new JFrame("BasicJPanel");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Other.Sw,Other.Sh);
    Sw = Other.Sw; Sh = Other.Sh;

    Test panel = new Test();

    frame.setContentPane(panel);
    frame.setVisible(true);
   
	TestStreet();
  }
}
