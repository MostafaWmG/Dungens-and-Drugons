package test.ca.concordia.soen6441.d20.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.concordia.soen6441.d20.common.Location;
/**
 * This test class is representation of Location class working correctly.
 *
 */
public class LocationTest {
	private Location location;
	
	/**
	 * This method creates a location object before each test case runs
	 */
	@Before
	public void setUp() {
		location = new Location(1,2);
	}

	/**
	 * This test case checks that the location object is correctly created location (1,2)
	 */
	@Test
	public void createLocation() {
		assertEquals(1, location.getX());
		assertEquals(2, location.getY());
	}
	/**
	 * This test case checks that two location objects that have the same coordinate
	 * are actually equal
	 */
	@Test
	public void twoEqualLocationsShouldBeEquals() {
		Location otherLocation = new Location(1,2);
		
		assertEquals(location, otherLocation);
	}
	/**
	 * This test case is used to show that no different location objects are equal
	 */
	@Test
	public void twoDifferentLocationsShouldNotBeEqual() {
		Location otherLocation = new Location(2,1);
		
		assertNotEquals(location, otherLocation);
	}
	/**
	 * This test case checks the equality of the expected hash code and the test hash code
	 */
	@Test
	public void hashCodeShouldBeEqualToHashCodeOfXAndYConcatenatedASString() {
		int expectedHashCode = String.format("%d:%d", 1,2).hashCode();
		
		int hashCode = location.hashCode();
		
		assertEquals(expectedHashCode, hashCode);
	}

}
