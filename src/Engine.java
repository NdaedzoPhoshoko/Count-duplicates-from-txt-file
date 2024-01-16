//read the Words.txt file and find allm duplicated words with count for each word excluding special characters
//write the duplicates and the corresponding counts into file Duplicates.txt

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Engine{

    public static void main(String[] args) {
        File mFile = new File("src\\Words.txt");//instance pointing to an our Words.txt file
        String sentence = "";
        try
        {
            Scanner input = new Scanner(mFile); //scanner to read file
            while(input.hasNextLine())
            {
                sentence += input.nextLine();
            }
            input.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("Please create file named Words.txt first, it is not found.");
        }
    
        String[] words = sentence.split("([^a-zA-z0-9])");
        List<String> mWords = new ArrayList<>();

        int s=0;
        while(s<words.length) //copy all words from sentence to arrayList
        {
            mWords.add(words[s]);
            s++;
        }

        Map<String, Integer> duplicates = new HashMap<>(); //map to store duplicated word and count

        while(mWords.size()>0)
        {
            String currentWord = mWords.get(0); //current word to check for duplicates
            Boolean run = true;
            int count =0; //to store the occurances for each word

            while(run)
            {
                if(mWords.contains(currentWord))//assuming list has a word count number of times it occurs
                {
                    count++;
                    mWords.remove(currentWord); //remove this occurance to avoid reading it multiple times
                }else{//assuming the word has no other duplicates then stop loop
                    run = false;
                    if(count>1)//assuming word has multiple occurances, store it in the duplicates with the count value
                    {
                        duplicates.put(currentWord, count);
                    }
                }
            }
        }
        //write the duplicates and occurances into Duplicates.txt file
        try{
            FileWriter write = new FileWriter("src\\Duplicates.txt");
            int a=1;
            for(String eachWord: duplicates.keySet())
            {
                write.write(a+". "+ eachWord +" : "+ duplicates.get(eachWord)+"\n");
                a++;
            }
            write.close();
        }catch(IOException err)
        {
            System.out.println(err.getMessage());
        }finally{
            System.out.println("\tProgram Finished.");
        }
    }
}
