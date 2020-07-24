import java.util.HashMap;
import java.util.regex.Pattern;

public interface FileProcessor {

    public HashMap<String,Integer> countWordsInFile(String fileName);
    public Pattern defaultPattern();
}

//Some problems which I noticed:
//-THE PROGRAM TAKES A LOT OF TIME TO RUN LONGER TEXT FILES.
//-IF FILES HAVE SIMILAR NAMES, THE PROGRAM WILL TAKE THE CLOSEST ONE AS IT SIMPLY SEARHCES FOR WHETHER THAT FILENAME EXISTS
//-THE WORDS AREN'T WRITTEN TO THE OUTPUT FILE IN ORDER (I TRIED IMPLEMENTING AN ARRAYLIST TO ORDER THEM, BUT IT DIDN'T WORK)
