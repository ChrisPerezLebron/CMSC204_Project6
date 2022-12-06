import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {
	
	private Road[] roads; 
	@BeforeEach
	void setUp() throws Exception {
		roads = new Road[4]; 
		roads[0] = new Road(new Town("Town_1"), new Town("Town_2"), 168, "1_2" ); 
		roads[1] = new Road(new Town("Town_2"), new Town("Town_3"), 119, "2_3" ); 
		roads[2] = new Road(new Town("Town_3"), new Town("Town_4"), 14, "3_4" ); 
		roads[3] = new Road(new Town("Town_4"), new Town("Town_5"), 293, "4_5" ); 
		
	}

	@AfterEach
	void tearDown() throws Exception {
		roads = null;
	}

	@Test
	void testHashCode() {
		
		Road newRoad = new Road(new Town("Town_2"), new Town("Town_1"), 168, "2_1" );
		/*
		 * hash codes should be the same when two objects are the same according to equals method
		 * the equals method I wrote says that two roads are the same if they have the same end points 
		 * (regardless of order) and the same weight. Name does not affect equality as I do not consider 
		 * it a crucial part of the road. If the road has the same end points and same weight they are 
		 * essentially the same exact thing minus the name. 
		 */
		assertTrue(roads[0].hashCode() == newRoad.hashCode()); 
		
		/*
		 * hash codes should be different whenever 
		 */
		assertFalse(roads[0].hashCode() == roads[1].hashCode()); 
		
		/*
		 * I am not 100% sure how to check the "dispersion" of hash codes so I will omit testing that
		 */
	}

	@Test
	void testContains() {
		
		assertTrue(roads[3].contains(new Town("Town_4"))); 
		assertTrue(roads[3].contains(new Town("Town_5"))); 
		
		assertTrue(roads[2].contains(new Town("Town_3"))); 
		assertTrue(roads[2].contains(new Town("Town_4")));
	}

	@Test
	void testToString() {
		
		assertTrue(roads[0].toString().equals("1_2"));
		assertTrue(roads[1].toString().equals("2_3"));
	}

	@Test
	void testGetName() {
		/*
		 * essentially the same thing as toString but I will test again
		 */
		
		assertTrue(roads[2].getName().equals("3_4")); 
		assertTrue(roads[3].getName().equals("4_5")); 
	}

	@Test
	void testGetDestination() {
		assertTrue(roads[3].getDestination().getName().equals("Town_5")); 
		assertTrue(roads[2].getDestination().getName().equals("Town_4")); 
	}

	@Test
	void testGetSource() {
		assertTrue(roads[0].getSource().getName().equals("Town_1")); 
		assertTrue(roads[1].getSource().getName().equals("Town_2")); 
	}

	@Test
	void testCompareTo() {
		/*
		 * the project specifications said to compare based on roadName. But because of this, 
		 * the equals method and compareTo do not agree when the objects are equal. I know that 
		 * this is not proper convention and mention it here to emphasize this is a product of the 
		 * specifications not my decision making. 
		 */
		assertTrue(roads[0].compareTo(roads[1]) == -1); 
		
		assertTrue(roads[1].compareTo(roads[0]) == 1);
		
		assertTrue(roads[0].compareTo(roads[0]) == 0); 
		
		/*
		 * equality is only determined by name (BY PROJECT SPECIFICATIONS) 
		 */
		assertTrue(roads[0].compareTo(new Road(new Town("Some random source"), new Town("Some random end"), 19281, "1_2")) == 0);     
		
	}

	@Test
	void testGetWeight() {
		assertTrue(roads[2].getWeight() == 14); 
		assertTrue(roads[3].getWeight() == 293);
	}

	@Test
	void testEqualsObject() {
		assertFalse(roads[1].equals(roads[2])); 
		
		/*
		 * make a new road that is identical to roads[0] except for the order of the end points 
		 * and the name of the road. 
		 * 
		 * this road should be equal to roads[0] 
		 */
		Road newRoad = new Road(new Town("Town_2"), new Town("Town_1"), 168, "2_1" );
		
		assertTrue(roads[0].equals(newRoad)); 
		
	}

}
