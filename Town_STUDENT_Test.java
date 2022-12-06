import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	private Town[] towns; 
	@BeforeEach
	void setUp() throws Exception {
		towns = new Town[4]; 
		for(int count = 0; count < 4; count++)
			towns[count] = new Town("Town_" + count); 
		
	}

	@AfterEach
	void tearDown() throws Exception {
		towns = null;
	}

	@Test
	void testHashCode() {
		/*
		 * hashcode is based on the towns name so as long as town names are 
		 * different the hashcodes should be different
		 */
		assertTrue(towns[0].hashCode() != towns[1].hashCode()); 
		assertTrue(towns[0].hashCode() != towns[2].hashCode()); 
		assertTrue(towns[0].hashCode() != towns[3].hashCode()); 
		
		/*
		 * but HashCode of an object should be the same when hashed a separate time
		 */
		assertTrue(towns[0].hashCode() == towns[0].hashCode()); 
		
		/*
		 * but town[0]'s hashcode should also be the same as the hashcode for another town 
		 * object with the same name
		 */
		
		Town newTown = new Town("Town_0"); 
		assertTrue(towns[0].hashCode() == newTown.hashCode()); 
	}

	@Test
	void testGetName() {
		/*
		 * virtually the same test as toString
		 */
		assertTrue(towns[0].getName().equals("Town_0"));
		assertTrue(towns[1].getName().equals("Town_1"));
		assertTrue(towns[2].getName().equals("Town_2"));
		assertTrue(towns[3].getName().equals("Town_3"));
	}

	@Test
	void testCompareTo() {
		/*
		 * compared based on names 
		 */
		assertTrue(towns[0].compareTo(towns[1]) == -1);
		assertTrue(towns[0].compareTo(towns[2]) == -2);
		assertTrue(towns[0].compareTo(towns[3]) == -3);
		
		/*
		 * an object to should return 0 when compared with itself or with an object it is 
		 * essentially equal to [equality is determined by name] 
		 */
		
		assertTrue(towns[1].compareTo(towns[1]) == 0); 
		
		Town newTown = new Town("Town_1"); 
		assertTrue(towns[1].compareTo(newTown) == 0);
	}

	@Test
	void testToString() {
		assertTrue(towns[0].toString().equals("Town_0"));
		assertTrue(towns[1].toString().equals("Town_1"));
		assertTrue(towns[2].toString().equals("Town_2"));
		assertTrue(towns[3].toString().equals("Town_3"));
	}

	@Test
	void testEqualsObject() {
		/*
		 * a town should be equal to itself and a town that has the same name 
		 */
		assertTrue(towns[3].equals(towns[3])); 
		Town newTown = new Town("Town_3");
		assertTrue(towns[3].equals(newTown)); 
	}

	@Test
	void testGetRoadIterator() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testContainsRoad() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testAddRoad() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testRemoveRoad() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testRemoveAllRoadsTo() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testVisit() {
		assertFalse(towns[0].isVisited()); 
		towns[0].visit();
		assertTrue(towns[0].isVisited()); 
	}

	@Test
	void testUnVisit() {
		assertFalse(towns[1].isVisited()); 
		towns[1].visit();
		assertTrue(towns[1].isVisited()); 
		towns[1].unVisit();
		assertFalse(towns[1].isVisited()); 
	}

	@Test
	void testIsVisited() {
		/*
		 * tested extensively in the past two test but I guess I can do the same thing a third time? 
		 * I do not see a different way I could test it
		 */
		assertFalse(towns[1].isVisited()); 
		towns[1].visit();
		assertTrue(towns[1].isVisited()); 
		towns[1].unVisit();
		assertFalse(towns[1].isVisited()); 
		
	}

	@Test
	void testGetPathCost() {
		/*
		 * a Town's cost to a certain source should be essentially infinite upon construction
		 */
		assertTrue(towns[3].getPathCost() == Integer.MAX_VALUE);
		towns[3].setPathCost(100);
		assertTrue(towns[3].getPathCost() == 100);
	}

	@Test
	void testSetPathCost() {
		/*
		 * tested in previous test but I will test it again here
		 */
		
		towns[1].setPathCost(960);
		assertTrue(towns[1].getPathCost() == 960);
		
		towns[2].setPathCost(295);
		assertTrue(towns[2].getPathCost() == 295);
	}

	@Test
	void testSetPreviousTown() {
		
		/*
		 * set then get
		 */
		towns[1].setPreviousTown(new Town("Sweet Home Alabama"));
		assertTrue(towns[1].getPreviousTown().equals(new Town("Sweet Home Alabama"))); 
	}

	@Test
	void testGetPreviousTown() {
		/*
		 * previous town should be null upon instantiation
		 */
		assertTrue(towns[0].getPreviousTown() == null); 
		
		/*
		 * set then get
		 */
		towns[3].setPreviousTown(new Town("CaliMama"));
		assertTrue(towns[3].getPreviousTown().equals(new Town("CaliMama"))); 
	}

	@Test
	void testGetRoadTo() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

	@Test
	void testDeleteRoadTo() {
		/*
		 * hard to test this here as adding "roads" at this low level is very difficult. [I would need 
		 * to essentially recreate the logic I wrote in my graph class to be able to test this here. But, 
		 * that logic is already tested else where so no point in redoing it here] 
		 * this is used extensively in the higher level classes and tested in their JUnit tests 
		 * hence I will omit this test 
		 */
	}

}
