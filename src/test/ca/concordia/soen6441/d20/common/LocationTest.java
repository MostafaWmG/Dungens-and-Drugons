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
	
	@Before
	public void setUp() {
		location = new Location(1,2);
	}

	@Test
	public void createLocation() {
		assertEquals(1, location.getX());
		assertEquals(2, location.getY());
	}
	
	@Test
	public void twoEqualLocationsShouldBeEquals() {
		Location otherLocation = new Location(1,2);
		
		assertEquals(location, otherLocation);
	}
	
	@Test
	public void twoDifferentLocationsShouldNotBeEqual() {
		Location otherLocation = new Location(2,1);
		
		assertNotEquals(location, otherLocation);
	}
	
	@Test
	public void hashCodeShouldBeEqualToHashCodeOfXAndYConcatenatedASString() {
		int expectedHashCode = String.format("%d:%d", 1,2).hashCode();
		
		int hashCode = location.hashCode();
		
		assertEquals(expectedHashCode, hashCode);
	}

}
