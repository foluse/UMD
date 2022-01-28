
public class ComplexNumber {

	/* STUDENTS:  You may NOT add any further instance or static variables! */
	private final MyDouble real;   // To be initialized in constructors
	private final MyDouble imag;   // To be initialized in constructors


	/* STUDENTS: Put your methods here, as described in the project description. */

	//constructors
	
	public ComplexNumber(MyDouble realIn, MyDouble imagIn) {
		real = realIn;
		imag = imagIn;
	}

	public ComplexNumber(MyDouble realIn) {
		real = realIn;
		imag = new MyDouble (0);
	}

	//copy constructor
	public ComplexNumber(ComplexNumber complex) {
		real = complex.real;
		imag = complex.imag;
	}

	//gets real value of ComplexNumber
	public MyDouble getReal() {
		return real;
	}

	//gets image value of ComplexNumber 
	public MyDouble getImag() {
		return imag;
	}

	//returns ComplexNumber equal to the current obj and parameter
	public ComplexNumber add(ComplexNumber num) {
		return new ComplexNumber(real.add(num.real), imag.add(num.imag));
	}

	//returns ComplexNumber equal to the parameter subtracted from current obj
	public ComplexNumber subtract(ComplexNumber num) {
		return new ComplexNumber(real.subtract(num.real), 
				imag.subtract(num.imag));
	}

	//returns ComplexNumber equal to product of the current obj and parameter
	public ComplexNumber multiply(ComplexNumber num) {
		MyDouble realPart = ((real.multiply(num.real))
				.subtract(imag.multiply(num.imag)));
		MyDouble imagPart = (imag.multiply(num.real))
				.add(real.multiply(num.imag));
		return new ComplexNumber(realPart, imagPart);
	}

	//returns ComplexNumber equal to current obj divided by parameter
	public ComplexNumber divide(ComplexNumber num) {
		MyDouble part1 = (real.multiply(num.real))
				.add(imag.multiply(num.imag));
		MyDouble part2 = (imag.multiply(num.real))
				.subtract(real.multiply(num.imag));
		MyDouble denominator = ((num.real).multiply(num.real))
				.add((num.imag).multiply(num.imag));
		return new ComplexNumber((part1.divide(denominator)),
				(part2.divide(denominator)));
	}

	//checks if ComplexNumber equals parameter
	public boolean equals(ComplexNumber num) {
		if(real.equals(num.real) && imag.equals(num.imag)) {
			return true;
		} else {
			return false;
		}
	} 

	/*compares the norm of the current obj with the norm of the parameter. If 
	 the norms are equal, this method will return 0; if the norm of the current 
	 obj is less than the norm of the parameter, this method returns -1; if the
	 current obj is greater than the norm of the parameter, this method returns
	 1. */
	public int compareTo(ComplexNumber num) {
		int compare = norm(this).compareTo(norm(num));
		if (compare < 0) {
			return -1;
		} else if (compare > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	//converts ComplexNumber into a simplified string free of redundancies.
	public String toString() {
		String toString = null;
		if (imag.compareTo(new MyDouble(0)) < 0) {
			toString = real.toString() + imag.toString() + "i";
		} else {//prevents "-+"
			toString = real.toString() + "+" + imag.toString() + "i"; 
		}
		return toString;
	}

	//public static methods
	public static MyDouble norm(ComplexNumber num) {
		MyDouble norm = (num.real.multiply(num.real))
				.add(num.imag.multiply(num.imag));

		return norm.sqrt();
	}
	//converts string to ComplexNumber
	public static ComplexNumber parseComplexNumber(String other) {
		String clear = other.replaceAll(" ", ""); //removes white space
		int findAddition = clear.indexOf("+"); //finds +
		int findSub = clear.lastIndexOf("-"); //finds -
		int findImag = clear.indexOf("i"); //finds "i"
		double doubleReal, doubleImag;

		//stores ComplexNumber
		String stringReal, stringImag;

		if (findAddition != -1) {
			stringReal = clear.substring(0, findAddition);
			stringImag = clear.substring(findAddition + 1, findImag);

		} else {
			stringReal = clear.substring(0, findSub);
			stringImag = clear.substring(findSub, findImag);
		}

		doubleReal = Double.parseDouble(stringReal);
		doubleImag = Double.parseDouble(stringImag);

		//converts ComplexNumber
		MyDouble finalReal = new MyDouble(doubleReal);
		MyDouble finalImag = new MyDouble (doubleImag);

		return new ComplexNumber(finalReal, finalImag);
	}
}
