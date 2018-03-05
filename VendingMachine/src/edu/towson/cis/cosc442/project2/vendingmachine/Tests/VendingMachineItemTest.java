package edu.towson.cis.cosc442.project2.vendingmachine.Tests;
/**

 * 
 */


import static org.junit.Assert.*;


import edu.towson.cis.cosc442.project2.vendingmachine.*;

import org.junit.*;

/**
 * @author Kolbe
 *
 */
public class VendingMachineItemTest {
	
	VendingMachineItem item1, item2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item1 = new VendingMachineItem("name1", 1999.99);
		item2 = new VendingMachineItem("", 0.99);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test constructor with invalid input 
	 */
	@Test(expected = VendingMachineException.class)
	public void constructorErrorTest() {
		@SuppressWarnings("unused")
		VendingMachineItem item3 = new VendingMachineItem("xxx", -1.25);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Test
	public void getNameValidTest() {
		assertEquals("name1", item1.getName());
	}
	
	/**
	 * Gets the name when name is "".
	 *
	 * @return the name
	 */
	@Test
	public void getNameEmptyTest() {
		assertEquals("", item2.getName());
		assertNotNull(item2.getName());
	}
	
	@Test
	public void getPriceValidTest() {
		assertEquals(1999.99, item1.getPrice(), 0.0);
	}

}
