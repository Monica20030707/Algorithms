/***********************************************************
* Name: Monica Nguyen
* Date: 5 Jan 2023
* Course Code: CS401
* Description: This program used the PathCompression
* algorithm here since the other file is a constructor.
* It will print the result based on 3 sample text files.
***********************************************************/

import java.io.FileNotFoundException;

public class output {
    public static void main(String[] args) throws FileNotFoundException {
        PathCompression readText1 = new PathCompression(5, "text1.txt");
        PathCompression readTest2 = new PathCompression(5, "text2.txt");
        PathCompression readText3 = new PathCompression(10, "text3.txt");
    }
}