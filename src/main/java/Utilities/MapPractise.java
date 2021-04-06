package Utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapPractise {

	public static void main(String[] args) {

		// Map.put operation
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Sabir");
		map.put("Age", "27");
		map.put("Maritial Status", null);

		//Map.get operation
		System.out.println("Get value: "+map.get("name")); 
		System.out.println("Get value of key not exist in Map : "+map.get("Status")); 
		System.out.println("Get null value associated to key in map : "+map.get("Maritial Status")); 

		//ContainsKey && ContainsValue to find if mapping present for key
		System.out.println("containsKey : "+map.containsKey("Maritial Status"));
		System.out.println("containsKey : "+map.containsKey("Status"));
		
		//Map.entrySet()
		for(Map.Entry mapSet : map.entrySet()) {
			System.out.println("Entry Set -->  Key: "+mapSet.getKey()+", Value: "+mapSet.getValue());
		}
		
		//Map.keySet()
		Set<String> keySet = map.keySet();
		Iterator<String> key =  keySet.iterator();
		while(key.hasNext()) {
			System.out.println("Inside key set: "+key.next());
		}
		System.out.println("Contains key : "+keySet.contains("name"));
		
		//Map.values() to get all values from the Map
		Collection<String> values = map.values();
		System.out.println("Map.values() : "+values.contains("Sabir"));
	}

}
