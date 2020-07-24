import java.io.*;
import java.util.Formatter;

public class OutputFile {

    private Formatter formatter;

    public void openFile()
    {
        try
        {
                this.formatter = new Formatter(generateNewFileName() + ".txt");
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
    }


    public void addRecords(String word)
    {
        formatter.format("%s ", word);
    }


    public void closeFile()
    {
        formatter.close();
    }


//
    private String generateNewFileName()
    {
        return FileReader.getFileName() + "_NEW_FILE";
    }


}
