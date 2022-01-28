import static org.junit.Assert.*;

import org.junit.Test;


public class PublicTests {

	@Test
	public void testBasicConstructorsAndGetters() {
		
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		MyDouble d = new MyDouble(555.729);
		
		ComplexNumber x = new ComplexNumber(a, b);
		assertTrue(x.getReal().compareTo(a) == 0 && x.getImag().compareTo(b) == 0);
		
		ComplexNumber z = new ComplexNumber(d);
		assertTrue(z.getReal().compareTo(d) == 0 && z.getImag().compareTo(new MyDouble(0)) == 0);
	}
	
	@Test
	public void testCopyConstructor() {
		
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		
		ComplexNumber x = new ComplexNumber(a, b);
		ComplexNumber y = new ComplexNumber(x);
		assertTrue(x != y);     // Check to be sure they are not aliased!
		assertTrue(y.getReal().compareTo(a) == 0 && y.getImag().compareTo(b) == 0);
	}
	
	@Test
	public void testAdd() {
		MyDouble a = new MyDouble(1.8), b = new MyDouble (4.0),
				c = new MyDouble(-7.3), d = new MyDouble(-4.2);
		
		ComplexNumber check1 = new ComplexNumber(a,b); // 1.8+4.0i
		ComplexNumber check2 = new ComplexNumber(c,d); // -7.3-4.2i
		ComplexNumber check3 = new ComplexNumber (b,d); // 4.0-4.2i 
		
		//adding ComplexNumbers
		ComplexNumber com1 = check1.add(check2); //should be: -5.5-0.2i
		ComplexNumber com2 = check2.add(check3); //should be: -3.3-8.4i
		ComplexNumber com3 = check3.add(check1); //should be: 5.8-0.2i
		
		ComplexNumber checkcom1 = new ComplexNumber(new MyDouble(-5.5), 
				new MyDouble(0.2));
		assertTrue(com1.compareTo(checkcom1) == 0);
		
		ComplexNumber checkcom2 = new ComplexNumber (new MyDouble(-3.3),
				new MyDouble(-8.4));
		assertTrue(com2.compareTo(checkcom2) == 0);
		
		ComplexNumber checkcom3 = new ComplexNumber (new MyDouble (5.8), 
				new MyDouble (04.2));
		assertTrue(com3.compareTo(checkcom3) == 0);
	}
	
	@Test
	public void testSubtract() {
		MyDouble a = new MyDouble(1.8), b = new MyDouble (4.0),
				c = new MyDouble(-7.3), d = new MyDouble(-4.2);
		
		ComplexNumber check1 = new ComplexNumber(a,b); // 1.8+4.0i
		ComplexNumber check2 = new ComplexNumber(c,d); // -7.3-4.2i
		ComplexNumber check3 = new ComplexNumber (b,d); // 4.0-4.2i 
		
		ComplexNumber com1 = check1.subtract(check2); //should be: 9.1+8.2i
		ComplexNumber com2 = check2.subtract(check3); //should be: -11.3+0i
		ComplexNumber com3 = check3.subtract(check1); //should be: 2.2-8.2i
		
		ComplexNumber checkcom1 = new ComplexNumber(new MyDouble(9.1), 
				new MyDouble(8.2));
		assertTrue(com1.compareTo(checkcom1) == 0);
		
		ComplexNumber checkcom2 = new ComplexNumber (new MyDouble(-11.3),
				new MyDouble(0));
		assertTrue(com2.compareTo(checkcom2) == 0);
		
		ComplexNumber checkcom3 = new ComplexNumber (new MyDouble (2.2), 
				new MyDouble (-8.2));
		assertTrue(com3.compareTo(checkcom3) == 0);
	}
	
	@Test
	public void testMult() {
		MyDouble a = new MyDouble(1.8), b = new MyDouble (4.0),
				c = new MyDouble(-7.3), d = new MyDouble(-4.2);
		
		ComplexNumber check1 = new ComplexNumber(a,b); // 1.8+4.0i
		ComplexNumber check2 = new ComplexNumber(c,d); // -7.3-4.2i
		ComplexNumber check3 = new ComplexNumber (b,d); // 4.0-4.2i 
		
		ComplexNumber com1 = check1.multiply(check2); //should be: 3.66-36.76i 
		ComplexNumber com2 = check2.multiply(check3); //should be: -46.84+13.86i
		ComplexNumber com3 = check3.multiply(check1); //should be: 24+8.44i
		
		
		ComplexNumber checkcom1 = new ComplexNumber(new MyDouble(3.66), 
				new MyDouble(-36.76));
		assertTrue(com1.compareTo(checkcom1) == 0);
		
		ComplexNumber checkcom2 = new ComplexNumber (new MyDouble(-46.84),
				new MyDouble(13.86));
		assertTrue(com2.compareTo(checkcom2) == 0);
		
		ComplexNumber checkcom3 = new ComplexNumber (new MyDouble (24), 
				new MyDouble (8.44));
		assertTrue(com3.compareTo(checkcom3) == 0);
	}
	
	@Test
	public void testDiv() {
		MyDouble a = new MyDouble(3.0), b = new MyDouble (4.0),
				c = new MyDouble(6.0), d = new MyDouble(2.0), 
				e = new MyDouble(.65), f = new MyDouble (0.45);
		
		ComplexNumber check1 = new ComplexNumber(a,b); // 3.0+4.0i
		ComplexNumber check2 = new ComplexNumber(c,d); // 6.0+2.0i
		ComplexNumber quotient = new ComplexNumber(e,f); // 0.65+0.45i
		
		check1.divide(check2); 
		
		assertTrue(check1.compareTo(quotient) == 0);
	}
	
	@Test
	public void testEqComp() {
		MyDouble a = new MyDouble(3.0), b = new MyDouble (4.0),
				c = new MyDouble(6.0), d = new MyDouble(2.0);
		
		ComplexNumber check1 = new ComplexNumber(a,b);
		ComplexNumber check2 = new ComplexNumber(a,b);
		ComplexNumber check3 = new ComplexNumber(b,c);
		ComplexNumber check4 = new ComplexNumber(b,c);
		ComplexNumber check5 = new ComplexNumber(c,d);
		ComplexNumber check6 = new ComplexNumber(c,d);
		
		assertTrue(check1.compareTo(check2) == 0);
		assertTrue(check3.compareTo(check4) == 0);
		assertTrue(check5.compareTo(check6) == 0);
	}
	
	@Test
	public void testNorm() {
		
	}
	
	@Test
	public void testParse() {
		
	}
}

