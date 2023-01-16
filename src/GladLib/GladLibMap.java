package GladLib;
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> usedList;
	private int numSubs;
	private Random myRandom;
	
	// private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "GladLib/data";
	
	public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		usedList = new ArrayList<String>();
		numSubs = 0;
	}
	

	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	

	private void initializeFromSource(String source) {
        // Create an Array of categories and then iterate over this Array
        ArrayList<String> catArray = new ArrayList<>();
        catArray.add("adjective");
        catArray.add("noun");
        catArray.add("color");
        catArray.add("country");
        catArray.add("name");
        catArray.add("animal");
        catArray.add("timeframe");
        catArray.add("verb");
        catArray.add("fruit");
        // For each category, read in the words from the associated file,
        for (String c : catArray){
            // create an ArrayList of the words (using the method readIt),
            ArrayList<String> currArray = readIt(source+"/"+c+".txt");	
            // and put the category and ArrayList into the HashMap.
            myMap.put(c, currArray);
            // System.out.println(c+" array has been built");
        }          
	}
	

	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	

	private String getSubstitute(String label) {    
        if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
        else {
            ArrayList<String> currList = myMap.get(label);
            return randomFrom(currList);
        }
	}
	

	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		// If '<' or '>' doesn't exist in that string, no change
		if (first == -1 || last == -1){
			return w;
		}
		// Else,
		String prefix = w.substring(0,first);	// before '<'
		String suffix = w.substring(last+1);	// after '>'
		while (true){
			// Get a substitute
			String sub = getSubstitute(w.substring(first+1,last));
			if (! usedList.contains(sub)){
				usedList.add(sub);
				numSubs ++;
				return prefix+sub+suffix;
			}
		}
	}
	

	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	

	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	

	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	

	public void makeStory(){
		usedList.clear();
	    System.out.println("\n");
        // fromTemplate("GladLib/data/madtemplate2.txt");
		String story = fromTemplate("GladLib/data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\nNumber of words replaced: "+ numSubs);
		System.out.println("Size of usedList: "+usedList.size());
		System.out.println(usedList);
	}


    public int totalWordsInMap(){
        int totalOpts = 0;
        for (String w : myMap.keySet()){
            int currOpts = myMap.get(w).size();
            totalOpts += currOpts;
        }
        return totalOpts;
    }


    public int totalWordsConsidered(){
        return usedList.size();
    }



    public static void main (String[] args) {
        GladLibMap glm = new GladLibMap();
        // System.out.println(glm.myMap);
        // String mySub = glm.getSubstitute("country");
        // System.out.println(mySub);
        glm.makeStory();
        System.out.println("Total number of options: "+glm.totalWordsInMap());
        System.out.println("Total number of used: "+glm.totalWordsConsidered());
    }
	

}
