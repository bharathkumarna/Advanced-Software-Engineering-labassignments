import static org.junit.Assert.*;

import org.junit.Test;

public class idealtest {
	ideal obj=new ideal();
	 double ideal=obj.calculateideal(29.39,1.75);
	 
	 double test=80.22;
		@Test
		public void test() {
			System.out.println("@Test ideal(): "+ideal+"="+test);
			assertEquals(test,ideal,0);
		}

}
