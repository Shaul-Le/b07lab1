import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Polynomial{
	double[] coefficients;
	int[] exponents;

	public Polynomial(){
		coefficients = new double[]{0};	
		exponents = new int[]{1};
	}

	public Polynomial(double[] coArr, int[] expArr){
		coefficients = coArr;
		exponents = expArr;
	}

	public Polynomial(File f) throws FileNotFoundException{
		Scanner input = new Scanner(f);
		String line = input.nextLine();
		String[] expression = line.split("");
		int counter = 1;
		for (int i = 0; i < expression.length; i++){
			if(expression[i].equals("-") || expression[i].equals("+")){
				if(i != 0){
					counter++;
				}
			}
		}
		coefficients = new double[counter];
		exponents = new int[counter];
		int j = 0;
		for(int i = 0; i < expression.length; i++){
			String temp = "";
			if (expression[i].equals("+") || expression[i].equals("-")){
				i++;
			}
			while(!(expression[i].equals("+") || expression[i].equals("-")) && i < exponents.length){
				temp += exponents[i];
				i++;
			}
			if(temp.contains("x")){
				double coeff = Double.parseDouble(temp.substring(0, temp.indexOf("x")));
				int exp = 1;
				if((temp.indexOf("x") + 1) < temp.length()){
					exp = Integer.parseInt(temp.substring(temp.indexOf("x") + 1));
				}
				coefficients[j] = coeff;
				exponents[j] = exp; 
				j++;
			} else{
				double coeff = Double.parseDouble(temp);
				int exp = 0;
				coefficients[j] = coeff;
				exponents[j] = exp;
				j++;
			}
			i--;
		}

	}
	
	public void saveToFile(String fileName) throws FileNotFoundException{
		PrintStream output = new PrintStream(fileName);
		String p = "";
		for(int i =  0; i < coefficients.length; i++){
			p += coefficients[i];
			if (exponents[i] > 0){
				p += "x";
				if (exponents[i] > 1){
					p += exponents[i];
				}
			}
			if (coefficients[i+1] >= 0 && i+1 < coefficients.length){
				p += "+";
			} 
		}
		output.println(p);
		output.close();	
	}
	
	public Polynomial add(Polynomial polynomial){
		int length = 0;
		int index = 0;
		boolean check = false;
		int size = polynomial.coefficients.length + coefficients.length;
		Polynomial result = new Polynomial(new double[size], new int[size]);
		for (int i = 0; i < polynomial.exponents.length; i++) {
			for (int j = 0; j < exponents.length; j++) {
				if(polynomial.exponents[i] == exponents[j]) {
					result.coefficients[index] = polynomial.coefficients[i] + coefficients[j];
					result.exponents[index] = polynomial.exponents[i];
					index++;
					check = true;
					if (coefficients[j] + polynomial.coefficients[i] != 0) {
						length++;
					}
					break;
				}
			}
			if (!check) {
				result.coefficients[index] = polynomial.coefficients[i];
				result.exponents[index] = polynomial.exponents[i];
				index++;
				length++;
			}
			check = false;
		}
		check = false;
		for (int i = 0; i < exponents.length; i++) {
			for (int j = 0; j < result.exponents.length; j++) {
				if (result.exponents[j] == exponents[i]) {
					check = true;
					break;
				}
			}
			if (!check) {
				result.coefficients[index] = coefficients[i];
				result.exponents[index] = exponents[i];
				index++;
				length++;
			}
			check = false;
		}
		int counter = 0;
		double[] resultCo = new double[length];
		int[] resultExp = new int[length];
		for (int i = 0; i < result.coefficients.length; i++) {
			if (result.coefficients[i] != 0) {
				resultCo[counter] = result.coefficients[i];
				resultExp[counter] = result.exponents[i];
				counter++;
			}
		}
		return new Polynomial(resultCo, resultExp);
	}
	
	public double evaluate(double x){
		double total =  0;
		for(int i = 0; i < coefficients.length; i++){
			total += coefficients[i]*Math.pow(x, exponents[i]);
		}
		return total;
	}

	public boolean hasRoot(double x){
		return evaluate(x) == 0;
	}

	public Polynomial multiply(Polynomial poly2) {
		int count = 0;
		int length = poly2.coefficients.length * coefficients.length;
		Polynomial[] result = new Polynomial[length];
		for (int i = 0; i < poly2.coefficients.length; i ++) {
			for (int j = 0; j < coefficients.length; j++) {
				double[] co = new double[] {coefficients[j] * poly2.coefficients[i]};
				int[] exp = new int[] {exponents[j] + poly2.exponents[i]};
				Polynomial tempPoly = new Polynomial (co, exp);
				result[count] = tempPoly;
				count++;
			}
		}
		for (int i = 1; i < result.length; i++) {
			result[0] = result[0].add(result[i]);
		}
		return result[0];
	}
}
