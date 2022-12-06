import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManager_STUDENT_Test {
	
	private TownGraphManager graphMan = new TownGraphManager(); 
	@BeforeEach
	void setUp() throws Exception {
		graphMan.addTown("Chicago");
		graphMan.addTown("Baltimore");
		graphMan.addTown("Miami");
		graphMan.addTown("Texas");
		graphMan.addTown("Dominican Republic");
		
		graphMan.addTown("Austin"); //THIS TOWN IS NOT CONNECTED WITH REST OF GRAPH
		
		graphMan.addRoad("Chicago", "Miami", 50, "CM"); 
		graphMan.addRoad("Chicago", "Baltimore", 56, "CB");
		
		graphMan.addRoad("Baltimore", "Texas", 32, "BT"); 
		graphMan.addRoad("Baltimore", "Miami", 69, "BM"); 
		
		graphMan.addRoad("Miami", "Dominican Republic", 84, "MD"); 
		
	}

	@AfterEach
	void tearDown() throws Exception {
		graphMan = null; 
	}

	@Test
	void testAddRoad() {
		/*
		 * first check some of the ones we already added
		 */
		assertTrue(graphMan.getRoad("Miami", "Dominican Republic").equals("MD")); 
		
		assertTrue(graphMan.getRoad("Austin", "Texas") == null); 
		
		assertTrue(graphMan.getRoad("Baltimore", "Chicago").equals("CB")); 
		
		/*
		 * then add another road and check if it was added properly 
		 */
		graphMan.addRoad("Dominican Republic", "Chicago", 212, "DC"); 
		
		assertTrue(graphMan.getRoad("Chicago", "Dominican Republic").equals("DC")); 
		assertTrue(graphMan.getRoad("Dominican Republic", "Chicago").equals("DC")); 
	}

	@Test
	void testGetRoad() {
		/*
		 * already indirectly tested in previous test but ill do a couple more here
		 */
		assertTrue(graphMan.getRoad("Baltimore", "Miami").equals("BM")); 
		assertTrue(graphMan.getRoad("Miami", "Chicago").equals("CM"));
	}

	@Test
	void testAddTown() {
		/*
		 * already indirectly tested but I will test that the previous towns were added
		 */
		ArrayList<String> resultList = graphMan.allTowns();
		assertTrue(resultList.size() == 6); 
		assertTrue(resultList.get(0).equals("Austin"));
		assertTrue(resultList.get(1).equals("Baltimore"));
		assertTrue(resultList.get(2).equals("Chicago"));
		assertTrue(resultList.get(3).equals("Dominican Republic"));
		assertTrue(resultList.get(4).equals("Miami"));
		assertTrue(resultList.get(5).equals("Texas"));
		
		graphMan.addTown("ZeldaTown"); 
		resultList = graphMan.allTowns(); 
		assertTrue(resultList.size() == 7); 
		assertTrue(resultList.get(6).equals("ZeldaTown"));
		
	}

	@Test
	void testGetTown() {
		/*
		 * this kind of also tests the equals method
		 */
		assertTrue(graphMan.getTown("Miami").equals(new Town("Miami"))); 
		
		/*
		 * get the town object associated with dominican republic, then obtain the road from that town to 
		 * Miami then get the road name and confirm it is correct
		 */
		assertTrue(graphMan.getTown("Dominican Republic").getRoadTo(new Town("Miami")).getName().equals("MD"));
		
	}

	@Test
	void testContainsTown() {
		assertTrue(graphMan.containsTown("Texas")); 
		assertTrue(graphMan.containsTown("Baltimore")); 
		
		assertFalse(graphMan.containsTown("Chew Back AH Town")); 
		assertFalse(graphMan.containsTown("texas")); //ASSUMPTION: Town names should be case sensitive
	}

	@Test
	void testContainsRoadConnection() {
		
		assertFalse(graphMan.containsRoadConnection("Chicago", "Texas")); 
		assertFalse(graphMan.containsRoadConnection("Texas", "Chicago")); 
		
		assertTrue(graphMan.containsRoadConnection("Chicago", "Miami")); 
		assertTrue(graphMan.containsRoadConnection("Miami", "Chicago")); 
	}

	@Test
	void testAllRoads() {
		assertTrue(graphMan.allRoads().toString().equals("[BM, BT, CB, CM, MD]")); 
		assertTrue(graphMan.allRoads().size() == 5); 
	}

	@Test
	void testDeleteRoadConnection() {
		/*
		 * ASSUMPTION: there can only 
		 */
		graphMan.deleteRoadConnection("Dominican Republic", "Miami", "MD");
		assertFalse(graphMan.containsRoadConnection("Dominican Republic", "Miami")); 
		assertFalse(graphMan.containsRoadConnection("Miami", "Dominican Republic"));
		
		
	}

	@Test
	void testDeleteTown() {
		graphMan.deleteTown("Baltimore");
		assertTrue(graphMan.allTowns().toString().equals("[Austin, Chicago, Dominican Republic, Miami, Texas]"));
		assertTrue(graphMan.allRoads().toString().equals("[CM, MD]")); 
		
		assertFalse(graphMan.containsTown("Baltimore")); 
	}

	@Test
	void testAllTowns() {
		assertTrue(graphMan.allTowns().toString().equals("[Austin, Baltimore, Chicago, Dominican Republic, Miami, Texas]")); 
		assertTrue(graphMan.allTowns().size() == 6); 
	}

	@Test
	void testGetPath() {
		ArrayList<String> resultList = graphMan.getPath("Baltimore", "Dominican Republic");
		assertTrue(resultList.size() == 2); 
		assertTrue(resultList.get(0).equals("Baltimore via BM to Miami 69 mi"));
		assertTrue(resultList.get(1).equals("Miami via MD to Dominican Republic 84 mi"));
		assertTrue(graphMan.getTown("Dominican Republic").getPathCost() == 153); 
		
		
	}

	@Test
	void testPopulateTownGraph() {
		File file = new File("TestData1.txt");
		TownGraphManager newGraphMan = new TownGraphManager(); 
		
		try {
			newGraphMan.populateTownGraph(file);
		} catch (Exception e) {
			fail("Exception should not be thrown"); 
		}
		
		assertTrue(newGraphMan.allTowns().toString().equals("[Baltimore, Chicago, Dominican Republic, Miami, Texas]")); 
		assertTrue(newGraphMan.allTowns().size() == 5); 
		
		assertTrue(newGraphMan.allRoads().toString().equals("[BM, BT, CB, CM, MD]")); 
		assertTrue(newGraphMan.allRoads().size() == 5); 
		
	}

}
