import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Pattern;


public class FileReader implements FileProcessor{

    private  HashMap<String,Integer> userFileWords;
    private static String fileName;

    public FileReader()
    {
        this.userFileWords = new HashMap<>();
    }

    public static String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    //---------------FUNCTION TO READ THE FILE WITH A GIVEN NAME IN A PARTICULAR DIRECTORY----------------------------
    @Override
    public HashMap<String,Integer> countWordsInFile(String fileName)
    {
        var userFileWords = new HashMap<String,Integer>();
        this.setFileName(fileName); //Setting the filename, so that we can later retrieve it from the OutputFile class.
            File dir = new File("./src");//Check whether it should search a directory like this.
            for (File f : dir.listFiles()) {
                if (f.getName().contains(fileName)) //The FILENAME
                {
//                    open the file.
                    try
                    {
                        var reader = Files.newBufferedReader(Path.of("./src\\" + f.getName()));
                        var pattern = defaultPattern();
                        String line;
                        var matcher = pattern.matcher("");

                        while ((line = reader.readLine()) != null)
                        {
                            matcher.reset(line);
                            while(matcher.find())
                            {
                                var word = matcher.group();
                                var count = userFileWords.getOrDefault(word, 0);
                                userFileWords.put(word,count+1);
                            }
                        }
                    }
                    catch (IOException ioe)
                    {

                        ioe.printStackTrace();
                    }
                }
            }
            setUserFileWords(userFileWords);
        return userFileWords;
    }


    public void setUserFileWords(HashMap<String, Integer> userFileWords) {
        this.userFileWords = userFileWords;
    }

    public  HashMap<String, Integer> getUserFileWords() {
        return userFileWords;
    }



    //------FUNCTION TO FIND THE REGULAR EXPRESSION PATTERN USED FOR COUNTING WORDS IN THE FILE---------------
    @Override
    public Pattern defaultPattern()
    {
        return Pattern.compile("[A-Za-z']+");
    }
}
////
