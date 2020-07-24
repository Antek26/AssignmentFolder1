import java.util.*;

public class User {

    public void run()
    {
        var fwc = new FileWordCounter();
        //PARSING THE CORPUS FILES TO CREATE A CORPUS
        var counts = fwc.countWordsInFiles(List.of("./English_corpus\\eng_wikipedia_2016_300K-sentences.txt", "./English_corpus\\eng_news_2016_300K-sentences.txt"));
        var parsedCorpus = new ParseCorpusText(counts);
        parsedCorpus.mergeCapsPreserving(10.0);
        parsedCorpus.filterByCounts(5);
        parsedCorpus.mergeCapsLowerCase();


        System.out.println("Please enter the name of the file.");
        Scanner enterFileName = new Scanner(System.in);
        var filename = enterFileName.nextLine();

        var fileReader = new FileReader();
        var userFileWords = fileReader.countWordsInFile(filename);
        if (userFileWords.size() == 0)
        {
            System.out.println("Sorry, this file is empty!");
        }
        else
        {
            var distance = new Distance();
            distance.checkClosestDistance(userFileWords);
            System.out.println(fileReader.getFileName());
        }


        //The userFileWords HashMap needs to be put through the correction, according to
        //the parseCorpusText corpus.
    }
}
