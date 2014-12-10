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
		file = r.OpenFile(); 		
		for (int i = 0; i < file.length-1; i++ ){
			String[] level_info = file[i].split(" ");
			String level_num = level_info[0];
			char num = level_num.charAt(0); 
			//number of level
			key = (int)num-48; 
			//make ArrayList of the information for each row of the board
			// <Level #, block orientation>
			value = new ArrayList<String>(); 
			for(int l = 1; l<level_info.length; l++){
				value.add(level_info[l]); 
			}
			levels.put(i, value);	
		}				
	}
	
	public TreeMap<Integer, ArrayList<String>> getLevels(){
		return levels; 
	}
}
	
	
