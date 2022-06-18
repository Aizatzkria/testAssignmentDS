
package navigation;
//(A)
public class UnweightedGraph<T extends Comparable<T>> extends WeightedGraph<T, Integer> {
    // public class Graph<T extends Comparable<T>> extends WeightedGraph
    public boolean addEdge(T source, T destination){
        // return super.addEdge(source, destination, null);
        return super.addEdge(source, destination, 1);
    }
    public boolean addUndirectedEdge(T vertex1, T vertex2){
//        return super.addUndirectedEdge(vertex1, vertex2, null);
        return super.addUndirectedEdge(vertex1, vertex2, 1);
    }
}