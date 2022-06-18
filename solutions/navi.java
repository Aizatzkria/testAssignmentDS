
package navigation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class navi {

   public static void main(String[] args) {
        
        WeightedGraph<String, Integer> district = new WeightedGraph<>();
        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\User\\Downloads\\Assignment DS\\DS-Assignment-2022-main (2)\\DS-Assignment-2022-main\\tasks\\navigation\\cases\\1.txt"));
            
            //read first line as the number of connections
            String M = in.nextLine();
            System.out.println(M);
            //need to skip that line so the loop doesnt read the number of connections again
            int N = Integer.parseInt(M);
            //reading the lines
            for(int i=0;i<N; i++){
                //reading the next line which is the connection
                String rails = in.nextLine();
                //splitting the line into 2 elements in an array
                String[] arrOfStr = rails.split(" => ");
                //System.out.println(arrOfStr.length);
                
                //adding vertex to the graph
                if(!district.hasVertex(arrOfStr[0]))
                    district.addVertex(arrOfStr[0]);
                
                if(!district.hasVertex(arrOfStr[1]))
                    district.addVertex(arrOfStr[1]);
                
                //adding connection between 2 station bidirectionally
                district.addEdge(arrOfStr[0], arrOfStr[1], 1);
            }
            //to check dah update vertex ke belum
            for (int i = 0; i <= district.getSize() - 1; i++){
                System.out.println(i + ": " + district.getVertex(i));
            }
            //nak check ada connection ke tak
            //district.printEdges();
            
            //start queries
            String query = in.nextLine();
            System.out.println(query);
            
            if(query.equalsIgnoreCase("QUERIES")){
                //read first line as the number of connections
                String S = in.nextLine();
                System.out.println(S);
                //need to skip that line so the loop doesnt read the number of connections again
                int Q = Integer.parseInt(S);
                
                //reading the lines
                for(int i=0;i<Q; i++){
                    //reading the next line which is the connection
                    String rails = in.nextLine();
                    //splitting the line into 2 elements in an array
                    String[] arrOfQ = rails.split(" -> ");
                    //checking whether ada ke tak vertex ni dalam system
                    if(!district.hasVertex(arrOfQ[0]))
                        System.out.println("This path doesnt start at the starting station!");
                    else if(!district.hasVertex(arrOfQ[1]))
                        System.out.println("This path doesnt end at the destination!");    
                    
                    if(arrOfQ.length>1) { // to get the path between source and destination
                        System.out.println(arrOfQ[0] + " => " + arrOfQ[1]);
                        district.DFS(district, arrOfQ[0], arrOfQ[1]); // using Depth First Search to find the path between the vertices
                        System.out.println("\n");
                    }
                }
            }
            in.close();
        }catch(FileNotFoundException e){
            System.out.println("File was not found");
        }
   }    
   
   
}
