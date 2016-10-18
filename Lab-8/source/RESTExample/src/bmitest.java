import static org.junit.Assert.*;

import org.junit.Test;

public class bmitest {
 first obj=new first();
 double bmi=obj.calculate(1.75,90);
 
 double test=29.39;
	@Test
	public void test() {
		System.out.println("@Test bmi(): "+bmi+"="+test);
		assertEquals(test,bmi,0);
	}

}
