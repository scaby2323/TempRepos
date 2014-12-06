import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;


public class LevelScanner {
	
	TreeMap< Integer, ArrayList<String>> levels = new TreeMap< Integer, ArrayList<String>>(); 
	int key; 
	ArrayList<String> value = new ArrayList<String>(); 
	String[] file; 
	public LevelScanner(String file_name) throws IOException{

		ReadLevels r = new ReadLevels(file_name); 
		System.out.println("HI");
		file = r.OpenFile(); 		
		
		for (int i = 0; i < file.length; i++ ){
			String[] level_info = file[i].split(" "); 
			String[] level_num = level_info[0].split(":");
			String num = level_num[0]; 
			key = 0; 
			 for(int j = 0; j < num.length(); j++){
				 key += num.charAt(0) + 10^i; 
			 }
			 
			 for (int j = 1; j < level_info.length; j++){
				 value.add(level_info[i]); 
			 }	
			if(!levels.containsKey(key)){
				levels.put(key, value);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> getLevelInfo(int k){
		if(levels.containsKey(key)){
			return levels.get(k); 
		}
		else{
			return (ArrayList<String>) Collections.EMPTY_LIST; 
		}
	}
}
	
	
