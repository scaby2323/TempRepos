import java.util.TreeMap;


public class HighScoreWriter {
	
	private TreeMap<Integer, TreeMap<String,Integer>> scores = new TreeMap<Integer,TreeMap<String, Integer>>(); 

	public void addScores(String name, int score){
		TreeMap<String,Integer> temp = new TreeMap<String,Integer>(); 
		temp.put(name, score);
		for(int i = 0; i<scores.size(); i++){
			if(scores.isEmpty()){
				scores.put(i, temp); 
			}
			else{
				TreeMap<String,Integer> check = new TreeMap<String, Integer>();
				temp.get(name) ;
				if(temp.get(name) != null){
					if(temp.get(name) > score){
						
					}
				}
			}
		}
			
		
	}
}
