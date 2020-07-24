import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FileWordCounter implements FileProcessor {
    //Like the FILEWORDCOUNTER class in the demo.


    private static HashMap<String,Integer> result;

    @Override
    public HashMap<String,Integer> countWordsInFile(String filename)
    {
        var result = new HashMap<String, Integer>();

        try
        {
            var reader = Files.newBufferedReader(Path.of(filename));
            var pattern = defaultPattern();
            String line;
            var matcher = pattern.matcher("");


            while ((line = reader.readLine()) != null)
            {
                matcher.reset(line);
                while (matcher.find())
                {
                    var word = matcher.group();
                    var count = result.getOrDefault(word, 0);
                    result.put(word,count+1);
                }
            }
        }
        catch (IOException ioe)
        {
            System.out.println("File not found!");
        }
        return result;
    }




    public  HashMap<String,Integer> countWordsInFiles(List<String> filenames)
    {
        var result = new HashMap<String,Integer>();
        for (String filename : filenames)
        {
            var counts = countWordsInFile(filename);
            result = mergeCount(result,counts);
        }
        return result;
    }




    public static HashMap<String,Integer> mergeCount(HashMap<String,Integer> result, HashMap<String,Integer> counts)
    {
        for (Map.Entry<String,Integer> stringIntegerEntry : counts.entrySet())
        {
            result.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue() + result.getOrDefault(stringIntegerEntry.getKey(), 0));
        }
        return result;
    }



    //ALSO IN FILEREADER
    @Override
    public Pattern defaultPattern()
    {
        return Pattern.compile("[A-Za-z']+");
    }
}
