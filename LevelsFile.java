import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
public class LevelsFile {


	private static TreeMap< Integer, ArrayList<String>> levels = new TreeMap< Integer, ArrayList<String>>(); 
	private static int key; 
	private static ArrayList<String> value = new ArrayList<String>(); 
	private static String[] file_read; 
	public static void main(String[] args) throws IOException{
		String levels_file = "C:/Levels.txt"; 
		
		try{
			ReadLevels file = new ReadLevels(levels_file); 
			file_read = file.OpenFile();
			
			for(int i = 0; i < file_read.length; i++){
				String line = file_read[i]; 
				String[] level_info = line.split(" "); 
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
		catch(IOException e){
			System.out.println("empty file");
		}
	}
	
	public ArrayList<String> getLine(int k){
		if(levels.containsKey(k)){
			return levels.get(k); 
		}
		else{
			return (ArrayList<String>) Collections.EMPTY_LIST; 
		}
	}
}
