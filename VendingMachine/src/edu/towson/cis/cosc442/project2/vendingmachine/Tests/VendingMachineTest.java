package edu.towson.cis.cosc442.project2.vendingmachine.Tests;

import edu.towson.cis.cosc442.project2.vendingmachine.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.*;

public class VendingMachineTest {
	VendingMachine vm;

	@Before
	public void setUp() throws Exception {
		//start w only slot D full
		vm = new VendingMachine();
		vm.addItem(new VendingMachineItem("cookie", 0.99), "D");
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * This will throw an exception, because we can't add an item to a full slot.
	 */
	@Test(expected = VendingMachineException.class)
    public void addItemExceptionTest() {
		vm.addItem(new VendingMachineItem("item1", 1.23), "D");
    }
	
	/*
	 * Try to use the exception constructor.
	 */
	@Test(expected = VendingMachineException.class)
	public void exceptionCreationTest() {
		throw new VendingMachineException();
	}
	
	/*
	 * Nothing bad should happen here; adding to an empty slot.
	 */
	@Test
    public void addItemValidTest() {
		vm.addItem(new VendingMachineItem("item2", 1.23), "A");
    }
	
	@Test
    public void addItemBValidTest() {
		vm.addItem(new VendingMachineItem("item2", 1.23), "B");
    }
	
	@Test
    public void addItemCValidTest() {
		vm.addItem(new VendingMachineItem("item2", 1.23), "C");
    }
	
	@Test(expected = VendingMachineException.class)
    public void addItemInvalidTest() {
		vm.addItem(new VendingMachineItem("item2", 1.23), "K");
    }
	
	/*
	 * This will throw an exception if the item was successfully removed.
	 */
	@Test(expected = VendingMachineException.class)
	public void removeItemTwiceTest( ) {
		vm.removeItem("D");
		vm.removeItem("D");
	}
	
	/*
	 * Nothing bad should happen here; removing an item from a full slot.
	 */
	@Test
    public void removeItemValidTest() {
		vm.addItem(new VendingMachineItem("item2", 1.23), "A");
		vm.removeItem("A");
    }
	
	/*
	 * Add some money
	 */
	@Test
	public void insertMoneyTest() {
		vm.insertMoney(1.23);
	}
	
	/*
	 * Trying to add negative moneys
	 */
	@Test(expected = VendingMachineException.class)
	public void insertMoneyExceptionTest( ) {
		vm.insertMoney(-99.99);
	}
	
	/*
	 * Check balance when balance is zero
	 */
	@Test
	public void getBalanceZeroTest() {
		assertEquals(0.0, vm.getBalance(), 0.0);
	}
	
	/*
	 * Check balance when balance is above zero (after adding money)
	 */
	@Test
	public void getBalancePositiveTest() {
		vm.insertMoney(1.23);
		assertEquals(1.23, vm.getBalance(), 0.0);
	}
	
	/*
	 * Purchase an item; slot should then be empty, balance should be 0.
	 */
	@Test
	public void makePurchaseBalanceTest() {
		vm.insertMoney(0.99);
		vm.makePurchase("D");
		assertEquals(0.0, vm.getBalance(), 0.0);
	}
	
	/*
	 * Purchase an item from an empty slot
	 */
	@Test
	public void makePurchaseEmptyExceptionTest() {
		vm.insertMoney(2.00);
		assertEquals(false, vm.makePurchase("A"));
	}
	
	/*
	 * Check balance after buying something (balance still positive)
	 */
	@Test
	public void getBalanceAfterRemovalTest() {
		vm.insertMoney(1.23);
		vm.makePurchase("D");
		assertEquals(0.24, vm.getBalance(), 0.0);
	}
	
	/*
	 * Try to get your change after buying something and still having a positive balance
	 */
	@Test
	public void returnChangePositiveTest() {
		vm.insertMoney(1.99);
		vm.makePurchase("D");
		assertEquals(1.00, vm.returnChange(), 0.0);
	}
	
	/*
	 * Try to get your change when you spent all your money in the machine
	 */
	@Test
	public void returnChangeZeroBalanceTest() {
		vm.insertMoney(0.99);
		vm.makePurchase("D");
		assertEquals(0.0, vm.returnChange(), 0.0);
	}
	
	/*
	 * Try to get your change when you never bought anything or inserted any money
	 */
	@Test
	public void returnChangeEmptyTest() {
		assertEquals(0.0, vm.returnChange(), 0.0);
	}

}
