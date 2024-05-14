public class Polynomial{
	double[] coefficients; 

	public Polynomial(){
		coefficients = new double[]{0};	
	}

	public Polynomial(double[] array){
		coefficients = array;
	}

	public Polynomial add(Polynomial polynomial){
		Polynomial poly1;
		Polynomial poly2;
		if (polynomial.coefficients.length > this.coefficients.length){
			poly1 = polynomial;
			poly2 = this;
		}
		else {
			poly1 = this;
			poly2 = polynomial;
		}
		for (int i = 0; i < poly2.coefficients.length; i++){
			poly1.coefficients[i] += poly2.coefficients[i];
		}
		return poly1;
	}
	
	public double evaluate(double x){
		double total =  0;
		for(int i = 0; i < coefficients.length; i++){
			total += coefficients[i]*Math.pow(x, i);
		}
		return total;
	}

	public boolean hasRoot(double x){
		return evaluate(x) == 0;
	}	
}