/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package navigation;

import java.util.ArrayList;

class WeightedGraph<T extends Comparable<T>, N extends Comparable<N>> {
    Vertex<T,N> head;
    int size;    
    
    public WeightedGraph(){
        head = null;
        size = 0;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public boolean hasVertex(T v){
        if(head == null)
            return false;
        Vertex<T,N> temp = head;
        while(temp != null){
            if(temp.vertexInfo.compareTo(v) == 0){
                return true;
            }
            temp = temp.nextVertex;
        }
        return false;
    }
    
    public int getIndeg(T v){
        if(hasVertex(v) == true){
            Vertex<T,N> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v) == 0){
                    return temp.indeg;
                }
                temp = temp.nextVertex;
            }
        }
        return -1;
    }
    //tak sure
    public int getOutdeg(T v){
        if(hasVertex(v) == true){
            Vertex<T,N> temp = head;
            while(temp!=null){
                if(temp.vertexInfo.compareTo(v) == 0){
                    return temp.outdeg;
                }
                temp = temp.nextVertex;
            }
        }
        return -1;
    }
    
    public boolean addVertex(T v){
        if(hasVertex(v) == false){
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v,null);
            if(head == null){
                head=newVertex;
            }
            else{
                Vertex<T,N> previous = head;
                while(temp!=null){
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex;
            }
            size++;
            return true;
        }
        else
            return false;
    }
    
    public int getIndex(T v){
        Vertex<T,N> temp = head;
        int pos=0;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v) == 0){
                return pos;
            }
            temp = temp.nextVertex;
            pos+=1;
        }
        return -1;
    }
    
    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while(temp!=null){
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }
    
    public T getVertex(int pos){
        if(pos>size-1 || pos<0){
            return null;
        }
        Vertex<T,N> temp = head;
        for(int i=0; i<pos; i++){
            temp = temp.nextVertex;
        }
        return temp.vertexInfo;
    }
    
    public boolean hasEdge(T source, T destination){
        if(head == null){
            return false;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return false;
        }
        Vertex<T,N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source) == 0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge != null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                        return true;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
            
        }
        return false;
    }
    //directed edge
    public boolean addEdge(T source, T destination, N w){
        if(head == null){
            return false;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return false;
        }
        Vertex<T,N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source) == 0){
                Vertex<T,N> destinationVertex = head;
                while(destinationVertex != null){
                    if(destinationVertex.vertexInfo.compareTo(destination) == 0){
                        Edge<T,N> currentEdge = sourceVertex.firstEdge;
                        Edge<T,N> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }
    
    public N getEdgeWeight(T source, T destination){
        N notFound = null;
        if(head == null){
            return notFound;
        }
        if(!hasVertex(source) || !hasVertex(destination)){
            return notFound;
        }
        Vertex<T,N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source) == 0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge != null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination) == 0){
                        // destination vertex found
                        return currentEdge.weight;
                    }
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return notFound;
    }
    
    public ArrayList<T> getNeighbours(T v){
        if(!hasVertex(v))
            return null;
        ArrayList<T> list = new ArrayList<T>();
        Vertex<T,N> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v) == 0){
                //Reached vertex, look for destination now
                Edge<T,N> currentEdge = temp.firstEdge;
                while(currentEdge != null){
                    list.add(currentEdge.toVertex.vertexInfo);
                    currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }
    
    public void printEdges(){
        Vertex<T,N> temp = head;
        while(temp!=null){
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T,N> currentEdge = temp.firstEdge;
            while(currentEdge!=null){
                System.out.print("[" + temp.vertexInfo + ", " + currentEdge.toVertex.vertexInfo + "] ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }
    
    //add undirected edges that receives two vertices and a weight and retuen a boolean
    public boolean addUndirectedEdge(T v1, T v2, N weight){
        if(head == null){
            return false;
        }
        if(!hasVertex(v1) || !hasVertex(v2)){
            return false;
        }
        Vertex<T,N> temp = head;
        while(temp != null){
            if(temp.vertexInfo.compareTo(v1) == 0){
                Vertex<T,N> temp2 = head;
                while(temp2 != null){
                    if(temp2.vertexInfo.compareTo(v2) == 0){
                        Edge<T,N> currentEdge = new Edge<>(temp2, weight, temp.firstEdge);
                        Edge<T,N> currentEdge2 = new Edge<>(temp, weight, temp2.firstEdge);
                        temp.firstEdge = currentEdge;
                        temp2.firstEdge = currentEdge2;
                        temp.indeg ++;
                        temp.outdeg ++;
                        temp2.indeg ++;
                        temp2.outdeg ++;
                        return true;
                    }
                    temp2 = temp2.nextVertex;
                }
                return false;
            }
            temp = temp.nextVertex;
        }
        return false;
    }
    
    public int dfsAddition(WeightedGraph graph, String source, String destination, boolean visited[]){
        visited[graph.getIndex(source)] = true;
        
        System.out.print(source + " -> ");
        for(int i =0;i < graph.getNeighbours(source).size();i++){
            String str = (String) graph.getNeighbours(source).get(i);
            
            if(str.equals(destination)){
                System.out.print(destination);
                return 1;
            }

            if(!visited[graph.getIndex(str)]){
                if(dfsAddition(graph, str, destination, visited) == 1){
                    return 1;
                }else continue;
            }
        }
        return 0;
    }
    
    public int DFS(WeightedGraph graph, String source, String destination){
        boolean visited[] = new boolean[graph.getSize()];
        if(dfsAddition(graph, source, destination, visited) == 1) {
            return 1;
        }
        return 0;
    }
    
    
  
}

