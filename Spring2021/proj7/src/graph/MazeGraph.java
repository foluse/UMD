package graph;
import graph.WeightedGraph;
import maze.Juncture;
import maze.Maze;

/** 
 * <P>The MazeGraph is an extension of WeightedGraph.  
 * The constructor converts a Maze into a graph.</P>
 */
public class MazeGraph extends WeightedGraph<Juncture> {

	/* STUDENTS:  SEE THE PROJECT DESCRIPTION FOR A MUCH
	 * MORE DETAILED EXPLANATION ABOUT HOW TO WRITE
	 * THIS CONSTRUCTOR
	 */
	
	/** 
	 * <P>Construct the MazeGraph using the "maze" contained
	 * in the parameter to specify the vertices (Junctures)
	 * and weighted edges.</P>
	 * 
	 * <P>The Maze is a rectangular grid of "junctures", each
	 * defined by its X and Y coordinates, using the usual
	 * convention of (0, 0) being the upper left corner.</P>
	 * 
	 * <P>Each juncture in the maze should be added as a
	 * vertex to this graph.</P>
	 * 
	 * <P>For every pair of adjacent junctures (A and B) which
	 * are not blocked by a wall, two edges should be added:  
	 * One from A to B, and another from B to A.  The weight
	 * to be used for these edges is provided by the Maze. 
	 * (The Maze methods getMazeWidth and getMazeHeight can
	 * be used to determine the number of Junctures in the
	 * maze. The Maze methods called "isWallAbove", "isWallToRight",
	 * etc. can be used to detect whether or not there
	 * is a wall between any two adjacent junctures.  The 
	 * Maze methods called "getWeightAbove", "getWeightToRight",
	 * etc. should be used to obtain the weights.)</P>
	 * 
	 * @param maze to be used as the source of information for
	 * adding vertices and edges to this MazeGraph.
	 */
	public MazeGraph(Maze maze) {
		super();
		
		for(int x = 0; x < maze.getMazeWidth(); x++) {
			for(int y = 0; y < maze.getMazeHeight(); y++) {
				this.addVertex(new Juncture(x,y));
			}
		}
		
		for(int x = 0; x < maze.getMazeWidth(); x++) {
			for(int y = 0; y < maze.getMazeHeight(); y++) {
				Juncture juncture = new Juncture(x,y);
				Juncture above = new Juncture(x,y-1);
				Juncture below = new Juncture(x,y+1);
				Juncture right = new Juncture(x+1, y);
				Juncture left = new Juncture(x-1, y);
				
				if(!maze.isWallToLeft(juncture) && this.containsVertex(left)) {
					this.addEdge(juncture, left, maze.getWeightToLeft(juncture));
				}
				
				if(!maze.isWallToRight(juncture) && this.containsVertex(right)) {
					this.addEdge(juncture, right, maze.getWeightToRight(juncture));
				}
				
				if(!maze.isWallAbove(juncture) && this.containsVertex(above)) {
					this.addEdge(juncture, above, maze.getWeightAbove(juncture));
				}
				
				if(!maze.isWallBelow(juncture) && this.containsVertex(below)) {
					this.addEdge(juncture, below, maze.getWeightBelow(juncture));
				}
			}
		}
	}
}
