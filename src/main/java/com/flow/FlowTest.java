package com.flow;

import java.util.ArrayList;
import java.util.List;

public class FlowTest {

	public static void main(final String[] args) {
		String aString = "A";
		String bString = "B";
		String cString = "C";
		
		List<String> allDoList = new ArrayList<String>();
		allDoList.add(aString);
		allDoList.add(bString);
		allDoList.add(cString);
		
		List<String> resulList = new ArrayList<String>();
		
		for (int i = 0 ;i<3; i++) {
			resulList = execute(allDoList);
		}
		
		for (int i = 0 ;i<3; i++) {
			resulList = executeKeep(allDoList);
		}

	}
	
	public static List<String> execute(List<String> allDoList) {
		List<String> willDoList = new ArrayList<String>();		
		List<String> finishDoList = new ArrayList<String>();
		
		try {
			// execute
			for (String string : allDoList) {
				finishDoList.add(string);	
				System.out.println("finishDoList :" + string);
				
				if ("C".equals(string)) {
					errorString();
				}
				
			}
		} catch (Exception e) {
			// rollback
			System.out.println("rollback");
			for (String string : finishDoList) {
				willDoList.add(string);
			}
			System.out.println("willDoList :" + willDoList.size() + "\n");
		}
		return willDoList;
	}
	
	
	public static List<String> executeKeep(List<String> allDoList) {
		List<String> willDoList = allDoList;		
		List<String> finishDoList = new ArrayList<String>();
		
		try {
			// execute
			for (int i=0; i<allDoList.size();i++) {
				System.out.println("executeKeep willDoList :" + allDoList.get(i));
				if (i == 1) {
					errorString();
				}
				finishDoList.add(allDoList.get(i));				
				System.out.println("executeKeep finishDoList :" + allDoList.get(i));				
			}
		} catch (Exception e) {
			// rollback
			System.out.println("executeKeep rollback");
			for (String string : finishDoList) {
				willDoList.remove(string);
			}
			
			System.out.println("executeKeepwillDoList :" + willDoList.size() + "\n");
		}
		return willDoList;
	}
	
	public static void errorString() throws Exception{
		System.out.println("Over" + "\n");
		throw new Exception("Over");
	}

}
