package mainFrame;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import street.StreetD;
import other.Other;
import mainFrame.city.City;

public class MainFrame extends JFrame{

	public MainFrame() {
		add(new City() );
		pack();
	}	
}
