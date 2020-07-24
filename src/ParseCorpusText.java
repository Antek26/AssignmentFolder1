import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ParseCorpusText {

    //Like the DICTIONARY class in the demo.
    private static Map<String, Integer> counts;
    private final Map<String, Integer> originalCounts;


    public ParseCorpusText(HashMap<String,Integer> counts)
    {
        this.counts = counts;
        this.originalCounts = new HashMap<>(counts);
    }
    //An object of this class is initialized with the HashMap which contains all the words from the text file.
    //It can be reset, or filtered.


    public int getCountForWord(String word)
    {
        return this.counts.getOrDefault(word,0);
    }


    public void reset()
    {
        this.counts = new HashMap<>(this.originalCounts);
    }

    public void filterByCounts(int limit)
    {
        this.counts.entrySet().removeIf(kv -> kv.getValue() < limit);
    }

    public int size()
    {
        return this.counts.size();
    }


    //---------------FUNCTION TO PRESERVE THE UPPER/LOWER CASE---------------------------------------------
    public HashMap<String,Integer> mergeCapsLowerCase()
    {
        var newCounts = new HashMap<String,Integer>();

        for (Map.Entry<String,Integer> stringIntegerEntry : this.counts.entrySet())
        {
            var lcKey = stringIntegerEntry.getKey().toLowerCase();
            newCounts.put(lcKey,newCounts.getOrDefault(stringIntegerEntry.getValue(),0) + stringIntegerEntry.getValue());
        }
        return newCounts;
    }


//---------------FUNCTION TO PRESERVE THE CAPITALIZATION OF THE MORE COMMON WORDS-------------------------------
    public void mergeCapsPreserving(double ratio) {
        var allWord = new HashSet<String>(); //Create a new HashSet (only for keys).
        for (var word : counts.keySet()) {
            allWord.add(word.toLowerCase()); //Add all keys in the original HashMap to this HashSet.
        }

        var newCounts = new HashMap<String, Integer>();
        for (String lcWord : allWord) {
            var ucWord = Character.toUpperCase(lcWord.charAt(0)) + lcWord.substring(1);
            var ucCount = this.getCountForWord(ucWord);
            var lcCount = this.getCountForWord(lcWord);

            if (ucCount > ratio * lcCount)
            {
                newCounts.put(ucWord, ucCount + lcCount);
                if (ucCount > 100*lcCount)
                {
//                    System.out.println(lcWord + " : " + ucWord);
                }
            }
            else if (lcCount > ratio * ucCount)
            {
                newCounts.put(lcWord, ucCount + lcCount);
                if (lcCount > 100*ucCount)
                {
//                    System.out.println(ucWord + " : " + lcWord);
                }
            }
            else
            {
                if (lcCount != 0)
                {
                    newCounts.put(lcWord,lcCount);
                }
                if (ucCount != 0)
                {
                    newCounts.put(ucWord,ucCount);
                }
            }
        }
        this.counts = newCounts;
    }


    public void printRemovedWords(int limit, int wordsToPrint) {
        this.counts.entrySet()
                .stream()
                .filter(kv -> kv.getValue() < limit)
                .limit(wordsToPrint)
                .forEach(kv -> System.out.println(kv.getKey() + " : " + kv.getValue()));

    }




    public static Map<String, Integer> getCounts() {
        return counts;
    }

    public void setCounts(Map<String, Integer> counts) {
        this.counts = counts;
    }

    public Map<String, Integer> getOriginalCounts() {
        return originalCounts;
    }


}
