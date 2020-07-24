

public class CheckWordsLevenshtein {
    //THE CLASS WHICH USES THE LEVENSHTEIN ALGORITHM TO DETERMINE,
    //FOR EACH WORD IN THE USER'S FILE, HOW FAR IT IS FROM EACH
    //WORD IN THE CORPUS. IT THEN SETS THE WORD WHICH TURNED OUT
    //TO BE THE CLOSEST TO THE VARIABLE 'CLOSEST_WORD', WHICH IS
    //THEN WRITTEN TO THE OUTPUT FILE.


    //---------------FUNCTION TO RECURSIVELY COMPUTE THE EDIT DISTANCE BETWEEN TWO STRINGS----------------------------
    public static int editDist(String str1, String str2, int m, int n)
    {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0)
            return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0)
            return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);

        // If last characters are not same, consider all three
        // operations on last character of first string, recursively
        // compute minimum cost for all three operations and take
        // minimum of three values.
        return 1 + min(editDist(str1, str2, m, n - 1), // Insert
                editDist(str1, str2, m - 1, n), // Remove
                editDist(str1, str2, m - 1, n - 1) // Replace
        );
    }


    //---------------FUNCTION TO FIND THE MINIMUM BETWEEN 3 INTEGERS---------------------------------------------
    public static int min(int x, int y, int z)
    {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }



}
