// Mike, Kevin, Mark
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
   public static final int WALL = 2;
   public static final int GOAL = 1;

   // Maze [2] = Wall, [0] = Possible Path, [1] = Goal
   public static int maze[][] = {
       { 0, 2, 2, 2, 2, 2, 2 },
       { 0, 0, 2, 0, 2, 0, 1 },
       { 2, 0, 2, 0, 2, 0, 2 },
       { 0, 0, 0, 0, 2, 0, 0 },
       { 0, 2, 2, 2, 0, 2, 0 },
       { 0, 0, 0, 2, 0, 2, 0 }, 
       { 0, 2, 0, 0, 0, 0, 0 }
   };
   
   static int startX = 0;
   static int startY = 0;

   public static void main(String [] args) {
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
// LinkedList Queue, while not empty, check if GOAL is neighbor, if so then print Location
// If not Goal add location to neighbors
       while(!(queue.size() == 0)) {
           loc = (Location)(queue.remove(0));
           if (maze[loc.y][loc.x] == GOAL) {
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
                   !(queue.contains(newloc))) 
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