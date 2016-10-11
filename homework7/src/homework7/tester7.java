package homework7;

import java.util.*;


public class tester7 {
	public static void main(String[] args){
			Date start = new Date();
			
			ListInterface array1 = new ArrayBasedList();
			ListInterface link1 = new LinkedlistBasedList();
			Random random  = new Random();
			
			
			for(int i = 0; i < 100000; i++){
				link1.add("testing");
			}
			
			
			for(int i = 0; i < 50000; i++){
				link1.replace(link1.randomIndex(link1), "replaced with this");
			}
			
//			link1.display();
//			for(int i = 0; i < 1000000; i++){
//				array1.add("Adding a string");
//			}
//			
//			for(int i = 0; i < 1000000; i++){
//				try{
//					array1.add(array1.randomIndex(array1), "testingrandom");
//				}catch(Exception e){
//					System.out.println("ArrayIndexOutOfBoundException caught here");
//				}
//			}
//			
//			for(int i = 0; i < 10000; i++){
//				try{
//					array1.replace(array1.randomIndex(array1), "testingrandom had been replaced");
//				}catch(Exception e){
//					System.out.println("ArrayIndexOutOfBoundException caught here");
//				}
//			}

			
			Date end = new Date();
			long elapsedTime = end.getTime() - start.getTime();
			System.out.println("Time: " + elapsedTime);
		
	}
}
