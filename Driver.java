import java.io.*;
import java.util.*;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int [] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,-9};
		int [] e2 = { 1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p2.add(p1);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		double[] c3 = {1,2,3};
		int[] e3 = {1,3,4};
		Polynomial p3 = new Polynomial(c3, e3);
		double[] c4 = {2,4};
		int[] e4 = {0,1};
		Polynomial p4 = new Polynomial(c4, e4);
		Polynomial answer = p3.multiply(p4);
		for (int i = 0; i < answer.coefficients.length; i++){
			System.out.println("mult co: " + answer.coefficients[i] + " ex: " + answer.exponents[i]);
		}

		File f = new File("C:\\Users\\shaul\\b07lab1\\testpoly.txt");
		Polynomial p5 = new Polynomial(f);
		for (int i = 0; i < p5.coefficients.length; i++){
			System.out.println("co: " + p5.coefficients[i] + " ex: " + p5.exponents[i]);
		}

		p5.saveToFile("C:\\Users\\shaul\\b07lab1\\testpoly.txt");
	}
}