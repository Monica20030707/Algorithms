/***********************************************************
* Name: Monica Nguyen
* Date: 5 Jan 2023
* Course Code: CS401
* Description: This program is an algoritm to reads from the given 
* input file, text1.txt, text2.txt and text3.txt and determine 
* if the soil allow the water to drain or hold the water.
***********************************************************/

import java.io.*;
import java.util.*;
public class PathCompression extends WeightedQuickUnion{

    public static int[][] token;
    public static int size;

    public PathCompression(int length, String filename) throws FileNotFoundException {
        super(length*length);

        size= length;

        Scanner file= new Scanner(new File(filename));
        
        // we just have the biggest size for the array
        token= new int [size][size];

        // store all information into the array
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                token[row][column] = file.nextInt();
            }
        }
        
        System.out.println(testDrain());
    }

    public void findUnion() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {

                //Only cares about case that the value number is 1
                if (token[row][column] == 1) {

                    // if not the last row then the union the current token with the one below
                    // in the same column if that below token also has value 1
                    if (row < size-1 && token[row+1][column] == 1)
                        union(row * size + column, row * size + column + size);

                    // if not the first row then the union the current token with the one above
                    // in the same column if that above token also has value 1
                    if (row > 0 && token[row-1][column] == 1)
                        union(row * size + column, row * size + column - size);

                    // if not the leftmost column then the union the current token with the one from
                    // the right column on the same row if that token on the right also has value 1
                    if (column < size - 1 && token[row][column + 1] == 1)
                        union(row * size + column, row * size + column + 1);

                    // if not the rightmost column then the union the current token with the one from
                    // the left column on the same row if that token on the left also has value 1
                    if (column > 0 && token[row][column - 1] == 1)
                        union(row * size + column, row * size + column - 1);

                }
            }
        }
    }

    //Lets override the root method from QuickUnion class to use in testDrain() later
    @Override 
    protected int root(int p){
        while(id[p] != p) {
            id[p] = id[id[p]];
			p = id[p];
		}
		
		return p;
    }

    // As I understand, if the root index is in the first row, the water will drain
    public String testDrain() {
        findUnion();
        for (int lastRow = size * size - size; lastRow < size * size; lastRow++) {
            if (root(lastRow) < size) {
                return "Allows water to drain";
            }
        }
       return "Don't allow water to drain";
    }
}
