import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LevelScanner {

	private Reader r ; 
	private static int l;
	
	public LevelScanner(Reader r) throws IOException {
		if(r == null){
			throw new NoSuchElementException();
		}
		BufferedReader read = new BufferedReader(r); 
		String line;
		
		l = r.read(); 
	}
}
	
	
