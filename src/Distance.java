import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Distance {

    private ArrayList<String> sortingList;
    private String shortestDistanceWord;
    private int shortDistance; //The distance is here only to check if a smaller distance appears, and update the word.

    public void setShortDistance(int shortDistance) {
        this.shortDistance = shortDistance;
    }

    public void setShortestDistanceWord(String shortestDistanceWord) {
        this.shortestDistanceWord = shortestDistanceWord;
    }

    public void sortingInitializer() {
        this.sortingList = new ArrayList<>();
    }

    //---------------FUNCTION TO CHECK WHICH WORD HAS THE SHORTEST EDIT DISTANCE-------------------------------------
    public void checkClosestDistance(HashMap<String,Integer> userFileWords)
    {
        var outputFile = new OutputFile();
        outputFile.openFile();
        setShortDistance(90);
        sortingInitializer(); //Initializes the ArrayList whose point it is to order the words written to the output file.
        for (Map.Entry<String,Integer> stringIntegerEntry : userFileWords.entrySet())
        {
            if ((ParseCorpusText.getCounts().containsKey(stringIntegerEntry.getKey())) || ((stringIntegerEntry.getValue() < 5) && (Character.isUpperCase(stringIntegerEntry.getKey().charAt(0)))))
           {
               outputFile.addRecords(stringIntegerEntry.getKey());
           }//IF THE CORPUS CONTAIN THE SAID WORD, WE SIMPLY WRITE IT TO THE OUTPUT FILE UNCHANGED
            //WE ALSO DO IT IF THE COUNT IS SMALLER THAN 5 (THIS MEANS IT IS A NAME OR SOMETHING TO IGNORE)
           else
           {
              for (String corpusWord : ParseCorpusText.getCounts().keySet())
              {
                  int length1 = stringIntegerEntry.getKey().length();
                  int length2 = corpusWord.length();
                  int editDistance = CheckWordsLevenshtein.editDist(stringIntegerEntry.getKey(),corpusWord,length1,length2);
                  setMinimum(editDistance, corpusWord);
              }
               outputFile.addRecords(this.shortestDistanceWord);
           }
        }//ITERATING OVER THE USER-FILE HASHMAP, TO CHECK IF EACH OF THE WORDS EXISTS IN THE PARSECORPUSTEXT
        outputFile.closeFile();
    }



    //---------------FUNCTION TO FIND THE MINIMUM EDIT DISTANCE, TO KNOW WITH WHICH WORD TO REPLACE-------------------------------------------------
    public void setMinimum(int editDistance, String corpusWord)
    {
        if (editDistance < this.shortDistance)
        {
            setShortDistance(editDistance);
            setShortestDistanceWord(corpusWord);
        }
    }
}
