package generator;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;
import street.Street;
import other.Other;

//to bedzie raczej statyczna klasa zawierajaca tylko funkcje generujaca miasto.
public class Generator{
	int seed=0;
	 Random R;
	public static void Generator(int _seed) {
		seed=_seed;
		R = new Random(seed);
	}
}
