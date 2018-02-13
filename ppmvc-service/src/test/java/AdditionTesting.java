import static org.junit.Assert.*;

import org.junit.Test;

import com.artek.test.Addition;

public class AdditionTesting {

	@Test
	public void testGetSum() {
		//fail("Not yet implemented");
		Addition addObj = new Addition();
		assertEquals(4, addObj.getSum(2, 2));
		assertEquals(-2, addObj.getSum(2,-4));
		assertEquals(-4, addObj.getSum(-2, -2));
		assertEquals(-2, addObj.getSum(-4, 2));
	}

}