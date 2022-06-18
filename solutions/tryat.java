/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author P.Fatirah Shazwina
 */
public class tryat {

    public static void main(String[] args) {
        
        String[] str1, str2;
        Scanner input = new Scanner(System.in);
        WeightedGraph<String, Integer> graph1 = new WeightedGraph<>();
      
        int num=0;
        
        try{
            
            Scanner read = new Scanner(new FileInputStream ("C:\\Users\\P.Fatirah Shazwina\\Documents\\NetBeansProjects\\meow5\\0.txt")); // read text file
            while(read.hasNextLine()) { 
                //if there is Integer, read the line as string and take the integer from string
                if(read.hasNextInt()) {
                    String line2 = read.nextLine();
                    num = Integer.valueOf(line2);
                }
                // if the line is not equals to QUERY, read the next line
                String line = read.nextLine();
                if(!line.equalsIgnoreCase("QUERY")) {
                    for(int i=0; i<num; i++) {

                String sentence = read.nextLine();

                if(!read.hasNextLine()) { // if there is no line anymore, stop the loop
                    break;
                }

                str1 = sentence.split(" => "); // to split the string and assign it as String array
                str2 = sentence.split(" -> ");

                if(str1.length>1) {     
                    graph1.addVertex(str1[0]); // add the substring at index 0 as the vertex
                    graph1.addVertex(str1[1]);
                    graph1.addEdge(str1[0], str1[1], 1); // add the edges between substring index 0 and index 1
                    graph1.addEdge(str1[1], str1[0], 1); // add the edges between substring index 1 and index 1 (bidirectional)
                }

                if(str2.length>1) { // to get the path between source and destination
                    System.out.println(str2[0] + " => " + str2[1]);
                    graph1.DFS(graph1, str2[0], str2[1]); // using Depth First Search to find the path between the vertices
                    System.out.println("\n");
                }
                    }
                 }
        }
            read.close();       
            
        }catch(FileNotFoundException e){ // throw exception if did not find file
            System.out.println("File Error");
        }
    }
    
}
