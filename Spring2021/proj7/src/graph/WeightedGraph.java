package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * <P>This class represents a general "directed graph", which could 
 * be used for any purpose.  The graph is viewed as a collection 
 * of vertices, which are sometimes connected by weighted, directed
 * edges.</P> 
 * 
 * <P>This graph will never store duplicate vertices.</P>
 * 
 * <P>The weights will always be non-negative integers.</P>
 * 
 * <P>The WeightedGraph will be capable of performing three algorithms:
 * Depth-First-Search, Breadth-First-Search, and Djikatra's.</P>
 * 
 * <P>The Weighted Graph will maintain a collection of 
 * "GraphAlgorithmObservers", which will be notified during the
 * performance of the graph algorithms to update the observers
 * on how the algorithms are progressing.</P>
 */
public class WeightedGraph<V> {

	/* STUDENTS:  You decide what data structure(s) to use to
	 * implement this class.
	 * 
	 * You may use any data structures you like, and any Java 
	 * collections that we learned about this semester.  Remember 
	 * that you are implementing a weighted, directed graph.
	 */
	//Remember Map is easiest

	Map<V, Map<V, Integer>> weightedGraph;





	/* Collection of observers.  Be sure to initialize this list
	 * in the constructor.  The method "addObserver" will be
	 * called to populate this collection.  Your graph algorithms 
	 * (DFS, BFS, and Dijkstra) will notify these observers to let 
	 * them know how the algorithms are progressing. 
	 */
	private Collection<GraphAlgorithmObserver<V>> observerList;


	/** Initialize the data structures to "empty", including
	 * the collection of GraphAlgorithmObservers (observerList).
	 */
	public WeightedGraph() {
		weightedGraph = new HashMap<>();
		observerList = new ArrayList<GraphAlgorithmObserver<V>>();
	}

	/** Add a GraphAlgorithmObserver to the collection maintained
	 * by this graph (observerList).
	 * 
	 * @param observer
	 */
	public void addObserver(GraphAlgorithmObserver<V> observer) {
		observerList.add(observer);
	}

	/** Add a vertex to the graph.  If the vertex is already in the
	 * graph, throw an IllegalArgumentException.
	 * 
	 * @param vertex vertex to be added to the graph
	 * @throws IllegalArgumentException if the vertex is already in
	 * the graph
	 */
	public void addVertex(V vertex) {
		if(this.containsVertex(vertex)) {
			throw new IllegalArgumentException();
		} else {
			weightedGraph.put(vertex, new HashMap<>());
		}
	}

	/** Searches for a given vertex.
	 * 
	 * @param vertex the vertex we are looking for
	 * @return true if the vertex is in the graph, false otherwise.
	 */
	public boolean containsVertex(V vertex) {
		if(weightedGraph.containsKey(vertex)) {
			return true;
		}
		return false;
	}

	/** 
	 * <P>Add an edge from one vertex of the graph to another, with
	 * the weight specified.</P>
	 * 
	 * <P>The two vertices must already be present in the graph.</P>
	 * 
	 * <P>This method throws an IllegalArgumentExeption in three
	 * cases:</P>
	 * <P>1. The "from" vertex is not already in the graph.</P>
	 * <P>2. The "to" vertex is not already in the graph.</P>
	 * <P>3. The weight is less than 0.</P>
	 * 
	 * @param from the vertex the edge leads from
	 * @param to the vertex the edge leads to
	 * @param weight the (non-negative) weight of this edge
	 * @throws IllegalArgumentException when either vertex
	 * is not in the graph, or the weight is negative.
	 */
	public void addEdge(V from, V to, Integer weight) {
		//throws an exception if the weight is negative
		if((weight < 0) || (!this.containsVertex(from)) || (!this.containsVertex(to))) {
			throw new IllegalArgumentException();
		} else{
			weightedGraph.get(from).put(to, weight);
		}
	}

	/** 
	 * <P>Returns weight of the edge connecting one vertex
	 * to another.  Returns null if the edge does not
	 * exist.</P>
	 * 
	 * <P>Throws an IllegalArgumentException if either
	 * of the vertices specified are not in the graph.</P>
	 * 
	 * @param from vertex where edge begins
	 * @param to vertex where edge terminates
	 * @return weight of the edge, or null if there is
	 * no edge connecting these vertices
	 * @throws IllegalArgumentException if either of
	 * the vertices specified are not in the graph.
	 */
	public Integer getWeight(V from, V to) {
		Integer weight = null;
		if(this.containsVertex(from) && this.containsVertex(to)) {
			weight = weightedGraph.get(from).get(to);
		} else {
			throw new IllegalArgumentException();
		}
		return weight;
	}

	/** 
	 * <P>This method will perform a Breadth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyBFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without processing further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */
	public void DoBFS(V start, V end) {
		//notifies observers that breadth-first traversal has begun
		for(GraphAlgorithmObserver<V> observers: observerList) {
			observers.notifyBFSHasBegun();
		}
		//stores visited vertices
		Set <V> visited = new HashSet<>();
		//stores discovered vertices 
		Queue <V> discovered = new LinkedList<>();

		discovered.add(start);

		while(!discovered.isEmpty()) {
			V x = discovered.element(); //current vertex
			discovered.remove(x);
			if(!visited.contains(x)) {
				//notifies observerList that vertex has been visited
				for(GraphAlgorithmObserver<V> observers: observerList) {
					observers.notifyVisit(x);
				}
				//adds vertex to visitedSet
				visited.add(x);

				//adds all successors of x to discoveredSet
				for(V y: weightedGraph.get(x).keySet()) {
					if(!visited.contains(y)) {
						discovered.add(y);
					}
				}
			}
			//notifies observer that search is complete and ends the loop
			if(x.equals(end)) {
				for(GraphAlgorithmObserver<V> observers: observerList) {
					observers.notifySearchIsOver();
				}
				break;
			}
		}
	}

	/** 
	 * <P>This method will perform a Depth-First-Search on the graph.
	 * The search will begin at the "start" vertex and conclude once
	 * the "end" vertex has been reached.</P>
	 * 
	 * <P>Before the search begins, this method will go through the
	 * collection of Observers, calling notifyDFSHasBegun on each
	 * one.</P>
	 * 
	 * <P>Just after a particular vertex is visited, this method will
	 * go through the collection of observers calling notifyVisit
	 * on each one (passing in the vertex being visited as the
	 * argument.)</P>
	 * 
	 * <P>After the "end" vertex has been visited, this method will
	 * go through the collection of observers calling 
	 * notifySearchIsOver on each one, after which the method 
	 * should terminate immediately, without visiting further 
	 * vertices.</P> 
	 * 
	 * @param start vertex where search begins
	 * @param end the algorithm terminates just after this vertex
	 * is visited
	 */
	
	public void DoDFS(V start, V end) {
		//notifies observers that depth-first traversal has begun
		for(GraphAlgorithmObserver<V> observers: observerList) {
			observers.notifyDFSHasBegun();
		}

		//stores visited vertices
		Set<V> visited = new HashSet<>();
		//stores discovered vertices
		Stack<V> discovered = new Stack<>();

		discovered.push(start);
		while(!discovered.empty()) {
			V x = discovered.pop(); //current vertex
			if(!visited.contains(x)) {
				//notifies observerList that vertex has been visited
				for(GraphAlgorithmObserver<V> observers: observerList) {
					observers.notifyVisit(x);
				}
				//adds vertex to visitedSet
				visited.add(x);

				//adds all successors of x to discoveredSet
				for(V y: weightedGraph.get(x).keySet()) {
					if(!visited.contains(y)) {
						discovered.push(y);
					}
				}
			}
			//notifies observer that search is complete and ends the loop
			if(x.equals(end)) {
				for(GraphAlgorithmObserver<V> observers: observerList) {
					observers.notifySearchIsOver();
				}
				break;
			}
		}
	}

	/** 
	 * <P>Perform Dijkstra's algorithm, beginning at the "start"
	 * vertex.</P>
	 * 
	 * <P>The algorithm DOES NOT terminate when the "end" vertex
	 * is reached.  It will continue until EVERY vertex in the
	 * graph has been added to the finished set.</P>
	 * 
	 * <P>Before the algorithm begins, this method goes through 
	 * the collection of Observers, calling notifyDijkstraHasBegun 
	 * on each Observer.</P>
	 * 
	 * <P>Each time a vertex is added to the "finished set", this 
	 * method goes through the collection of Observers, calling 
	 * notifyDijkstraVertexFinished on each one (passing the vertex
	 * that was just added to the finished set as the first argument,
	 * and the optimal "cost" of the path leading to that vertex as
	 * the second argument.)</P>
	 * 
	 * <P>After all of the vertices have been added to the finished
	 * set, the algorithm will calculate the "least cost" path
	 * of vertices leading from the starting vertex to the ending
	 * vertex.  Next, it will go through the collection 
	 * of observers, calling notifyDijkstraIsOver on each one, 
	 * passing in as the argument the "lowest cost" sequence of 
	 * vertices that leads from start to end (I.e. the first vertex
	 * in the list will be the "start" vertex, and the last vertex
	 * in the list will be the "end" vertex.)</P>
	 * 
	 * @param start vertex where algorithm will start
	 * @param end special vertex used as the end of the path 
	 * reported to observers via the notifyDijkstraIsOver method.
	 */
	public void DoDijsktra(V start, V end) {
		//notifies observers that Dijkstra's algorithm has begun
		for(GraphAlgorithmObserver<V> observers: observerList) {
			observers.notifyDijkstraHasBegun();
		}

		//stores the path for all vertices
		Set<V> finishedSet = new HashSet<>();
		finishedSet.add(start); //algorithm begins by storing starting vertex in finishedSet
		
		//notifies observers that a vertex has been added to the finishedSet 
		for(GraphAlgorithmObserver<V> observers : observerList) {
			observers.notifyDijkstraVertexFinished(start,0);
		}

		//stores the cost for all vertices
		Map<V, Integer> cost = new HashMap<>();
		cost.put(start, 0); //initially only knows the starting vertex

		//stores the current vertex and its predecessor
		Map<V,V> pred = new HashMap<>();
		pred.put(start,null); //predecessor is null b/c starting vertex has no predecessor

		//stores visited vertices
		ArrayList<V> lowestCost = new ArrayList<>();

		//value to represent infinity
		Integer inf = Integer.MAX_VALUE;

		for(V v: weightedGraph.keySet()) { 
			if(!v.equals(start)) {
				//adds "infinity" as the initial cost for vertices
				cost.put(v, inf);
				//adds null as initial predecessors for all vertices
				pred.put(v, null);
			}
		}
		//smallest vertex
		V smallestV = start; //begins at the start b/c it has the lowest cost
		//represent the smallest path from starting vertex to another vertex
		Integer smallestCos = 0;

		while(finishedSet.size() != weightedGraph.size()) {
			/* traverses through the neighbors of the vertex, looking for the next smallest one in 
			 * the weighted graph that isn't already in the finishedSet */
			for(V adjacent: weightedGraph.get(smallestV).keySet()) {
				if(!finishedSet.contains(adjacent)) {
					Integer newPath = smallestCos + getWeight(smallestV, adjacent);
					if(newPath < cost.get(adjacent)) { //checks if the cost of new path is smaller
						cost.put(adjacent, newPath);
						pred.put(adjacent, smallestV);
					}
				}
			}
			
			smallestCos = inf; //set to infinity b/c the cost for other vertices was set to infinity
			
			//checks if the cost to the vertex is the smallest
			for(V vertex: cost.keySet()) {
				if(!finishedSet.contains(vertex)) {
					if(cost.get(vertex) < smallestCos) {
						smallestCos = cost.get(vertex);
						smallestV = vertex;
					}
				}
			}
			finishedSet.add(smallestV);
			for(GraphAlgorithmObserver<V> observers : observerList) {
				observers.notifyDijkstraVertexFinished(smallestV, smallestCos);
			}
		}
		
		V result = end;
		//adds all the shortest paths to the lowestCost list
		while(result != null) {
			lowestCost.add(0, result);
			result = pred.get(lowestCost.get(0));
		}
		
		//notifies observers that Dijsktra's algorithm is complete
		for(GraphAlgorithmObserver<V> observers: observerList) {
			observers.notifyDijkstraIsOver(lowestCost);
		}
	}
}
