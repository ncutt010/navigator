// Mike Northcutt, Kevin Coughlin, Mark Berberoglu
// CSC 380 -- AI Project
// GPS Program using Maze

import java.util.*;

public class GPS {
   
   public static class Location {
// Coordinates as Integer types, x and y
       public int x, y;
       public Location pred;
       public Location(int _x, int _y) {
           x = _x; y = _y;
           pred = null;
       }
       
       void setPred(Location l) { pred = l; }
       Location getPred() { return pred; }
       
// Stops Program from Looping, since 'Object O' resides at Location x,y 
// force program to move from current Location     
       public boolean equals(Object o) {
           if (!(o instanceof Location)) return false;
           if (((Location)o).x == x && 
               ((Location)o).y == y)
               return true;
			   return false;
       }

// Print Location (x , y)       
       public String toString() {
           return " (" + x + "," + y + ")";
       }
// Checking South, as long as not out of bounds, new location south = y+1       
       public Location south(int ylimit) {
           Location ret = null;
           if (y < (ylimit - 1)) {
               ret = new Location(x, y+1);
               ret.pred = this;
           }
           return ret; 
       }
// Checking North, as long as not out of bounds, new location north = y-1
       public Location north() {
           Location ret = null;
           if (y > 0) {
               ret = new Location(x, y-1);
               ret.pred = this;
           }
           return ret; 
       }
// Checking West, as long as not out of bounds, new location west = x-1       
       public Location west() {
           Location ret = null;
           if (x > 0) {
               ret = new Location(x-1, y);
               ret.pred = this;
           }
           return ret; 
       }
// Checking East, as long as not out of bounds, new location east = x+1       
       public Location east(int xlimit) {
           Location ret = null;
           if (x < (xlimit - 1)) {
               ret = new Location(x+1, y);
               ret.pred = this;
           }
           return ret; 
       }

/*	   
// Seconds Calculator to see how long Agent takes to reach Goal
	   public Timer time() {
	   int count = 0; 
	   for(;;) 
	   { 
		   try { 
			   Thread.sleep(1000); 
			   count ++; 
			   System.out.println(count);
		   } catch (InterruptedException e) { 
			   // Catch Block
			   e.printStackTrace(); 
		   } 
	   }
	   }
 
*/
 
// Change a Variable after X-amount of Time
//	   public Changer change() {
		   
	   
	  	   
			   	   
// Create new List of Neighbors, South, North, East, West
// As as not out of bounds, add the neighbor
       public List neighbors(int xlimit, int ylimit) {
           ArrayList ret = new ArrayList(4);
           Location n;
           n = south(ylimit);
           if (n != null) ret.add(n);
           n = north();
           if (n != null) ret.add(n);
           n = east(xlimit);
           if (n != null) ret.add(n);
           n = west();
           if (n != null) ret.add(n);
           return ret;
       }
   }
// ---------Split Code Here?----------------------------------------------
   public static final int[] WALL = {2,3};
   public static final int[] GOAL = {1,3};

   // Maze [2] = Wall, [0] = Possible Path, [1] = Goal
   // Second Array Value is Speed Limit which directly influences the delay with how the agent moves.
   // Possible Speeds Are:
   // 25, 30, 45, 50 
   public static int maze[][][] = 
	{
		{ {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}},
		{ {2,3}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {0,30}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {0,50}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {0,45}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}, {2,3}, {2,3}, {0,25}, {2,3}},
		{ {0,3}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {0,75}, {2,3}},
		{ {0,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}, {2,3}}
	};
   
   static int startX = 0;
   static int startY = 0;

   public static void main(String [] args) {
	   
	   /* This is a second array that simply holds a value in each cell corresponding to how many 
	   times that cell has become blocked. */ 
	   int[][] history = new int[23][27];
	   
       Location start, loc, newloc, finish;
       List neighbors;
       List beentried;
       Iterator iter;
// Location = New Start Location @ {0,0}
       start = new Location(startX, startY);
       List queue = new java.util.LinkedList();
       beentried = new java.util.LinkedList();
       queue.add(start);
       finish = null;
       int count = 0;
       int hisValue;
       
       while ( finish != loc) {
			   // When 1 Second Passes
			   if (count == 1) {
				   // Turn Path into Wall after a Second, Need to Specify an X,Y Coordinate
				   maze[22][21][0] = maze[22][21][2];
				   // This will increment the corresponding location of the history.
				   history[22][21]++;
				   break;
			   }
		   }
/*	   
	// Cases That Changes how long the Agent 'sleeps' depending on the MPH read from the Maze to mimic traffic / lack-of
	//Possible Speed Limits again are:
	//25, 30, 45, 50, 75
		
		switch (MPH) {
		case 25: Thread.sleep(2000); break;
		case 30: Thread.sleep(1600); break;
		case 45: Thread.sleep(1250); break;
		case 50: Thread.sleep(1000); break;
		case 75: Thread.sleep(750); break;
		}
		System.out.println(MPH);
*/
       
// LinkedList Queue, while not empty, check if GOAL is neighbor, if so then print Location
// If not Goal add location to neighbors
       while(!(queue.size() == 0)) {
           loc = (Location)(queue.remove(0));
           if ((maze[loc.y][loc.x]) == GOAL) {
               finish = loc;
               break;
           }
           
           else {
               beentried.add(loc);
           }
//Neighbors LinkedList length of maze, iterator traverses LinkedList analyzing Locations           
           neighbors = loc.neighbors(maze[0].length, maze.length);
           for(iter = neighbors.iterator(); iter.hasNext(); ) {
               newloc = (Location)(iter.next());
//If the new locations x,y are not equal to WALL and it has not been trained and is not in
//the queue then add to queue
               if (!(maze[newloc.y][newloc.x] == WALL) &&
                   !(beentried.contains(newloc)) &&
                   !(queue.contains(newloc)) && 
            	   // added check to history array. Current threshold is 3.
            	   !(history[newloc.y][newloc.x] < 3))
                   {
                       queue.add(newloc);
                   }
           }
       }
// Check if No Solution       
       if (finish == null) {
           System.out.println("There is no way to the Goal!");
       } 
       
       else {
           Stack stack = new Stack();
           for(loc = finish; loc != null; loc = loc.getPred()) {
               stack.push(loc);
           }
// Starting Point
           System.out.print("Agent Starting At: ");
           
           while(!stack.empty()) {
               loc = (Location)(stack.pop());
               System.out.println(loc.toString());
               if (!stack.empty()) 
                   System.out.print("Next Location:");
           }
// Goal Reached
           System.out.println("End Point Reached!");
       }
   }
}