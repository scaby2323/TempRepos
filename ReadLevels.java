import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadLevels {
	
	private String level; 
	
	public ReadLevels(String file_path){
		level = file_path; 

	}
	
	public String[] OpenFile() throws IOException{
	
		FileReader fr = new FileReader(level); 
		BufferedReader br = new BufferedReader(fr); 
		int num_lines = getNumLines(); 
		String[] file = new String[num_lines]; 
		for(int i = 0; i < file.length; i++){
			file[i] = br.readLine(); 
		}
		br.close(); 
		return file; 
		
	}
	
	public int getNumLines() throws IOException{
		FileReader fr = new FileReader(level); 
		BufferedReader br = new BufferedReader(fr); 
		
		String line; 
		int num_lines = 0; 
		while((line = br.readLine()) != null){
			num_lines++; 
		}
		br.close(); 		
		return num_lines; 
	}

}
