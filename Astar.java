
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
public class Astar {
Heu heuristic = new Heu();
PriorityQueue<Nodes> priority_queue = new PriorityQueue<Nodes>();
Map<String, Integer> binaryTreeCost = new HashMap<String, Integer>();
String expandedNode;
public static String inputString= "583067421";
public static String goalString = "123804765";
int indexPosition;
int searchLimit = 100000;
int cost;
String heuristicsName = "";
int nodeHeuristic;
int heuristicsChoice;
int visitedNodes = 0;
int generatedNodes = 0;
boolean finished = false;
long endTime,startTime = System.currentTimeMillis();
Astar(int heuristicsChoice)
{
this.heuristicsChoice = heuristicsChoice;
}
void expandFrontiers()
{
while(priority_queue.size() > 0 )
{
expandedNode= priority_queue.remove().toString();
if(goalString.equals(expandedNode))
{
finished = true;
displayState(expandedNode);
break;
}
else
{
moveTiles();
}
}
}
void moveTiles()
{
if(searchLimit > binaryTreeCost.get(expandedNode) )
{
indexPosition = expandedNode.indexOf("0");
while (indexPosition != 0 && indexPosition != 3 &&
indexPosition != 6)
{
left();
break;
}
while (indexPosition > 2)
{
up();
break;
}
while (indexPosition != 2 && indexPosition != 5 &&
indexPosition != 8)
{
right();
break;
}
while (indexPosition < 6)
{
down();
break;
}
}
}
void up ()
{
String generatedNode =
expandedNode.substring(0,indexPosition-3)+"0"+
expandedNode.substring(indexPosition-
2,indexPosition)+
expandedNode.charAt(indexPosition-3)+
expandedNode.substring(indexPosition+1);
checkRepeatedNode(generatedNode,expandedNode);
}
void down ()
{
String generatedNode =
expandedNode.substring(0,indexPosition)+
expandedNode.substring(indexPosition+3,indexPosition+4)+
expandedNode.substring(indexPosition+1,indexPosition+3)+"0"+
expandedNode.substring(indexPosition+4);
checkRepeatedNode(generatedNode,expandedNode);
}
void left()
{
String generatedNode =
expandedNode.substring(0,indexPosition-1)+"0"+
expandedNode.charAt(indexPosition-1)+
expandedNode.substring(indexPosition+1);
checkRepeatedNode(generatedNode,expandedNode);
}
void right()
{
String generatedNode =
expandedNode.substring(0,indexPosition)+
expandedNode.charAt(indexPosition+1)+"0"+
expandedNode.substring(indexPosition+2);
checkRepeatedNode(generatedNode,expandedNode);
}
void checkRepeatedNode(String generatedNode, String previousNode)
{
generatedNodes++;
if (!(binaryTreeCost.containsKey(generatedNode)))
{
stackToPriorityQueue(generatedNode,previousNode);
}
}
void stackToPriorityQueue(String generatedNode,String previousNode) {
cost = calculateCost(previousNode);
nodeHeuristic=decideHeuristics(heuristicsChoice,generatedNode,previousNode)+cost;
priority_queue.add(new Nodes(nodeHeuristic, generatedNode));
binaryTreeCost.put(generatedNode, cost);
}
int calculateCost(String previousNode)
{
if(previousNode == null)
{
cost = 0;
}
else
{
cost = 1 + binaryTreeCost.get(previousNode);
visitedNodes++;
}
return cost;
}
int decideHeuristics(int heuristicsChoice,String generatedNode,String
previousNode)
{
switch (heuristicsChoice) {
case 1:
nodeHeuristic = heuristic.missplacedTiles(generatedNode,
goalString) ;
heuristicsName = "Missplaced tiles";
break;
case 2:
nodeHeuristic = heuristic.nielsen(generatedNode,
goalString) ;
heuristicsName = "Nielssen Sequence Score";
break;
case 3:
nodeHeuristic = heuristic.manhhatan(generatedNode,
goalString) ;
heuristicsName = "Manhattan distance";
break;
case 4:
nodeHeuristic = heuristic.rowColumn(generatedNode,
goalString) ;
heuristicsName = "Tiles out of row/ column";
break;
case 5:
nodeHeuristic = heuristic.maxHeu(generatedNode,
goalString) ;
heuristicsName = "Maximmum heuristic";
break;
case 6:
nodeHeuristic = heuristic.averageHeu(generatedNode,
goalString) ;
heuristicsName = "Average heuristic ";
break;
case 7:
nodeHeuristic = heuristic.modified_nielsen(generatedNode,
goalString);
heuristicsName = "Modified Nielssen Sequence Score";
break;
default:
break;
}
return nodeHeuristic;
}
void displayState(String expandedNode){
if (finished == true) {
long endTime = System.currentTimeMillis();
System.out.println("///////////////////////////////////////");
System.out.println("A*(star) HAS FOUND THE SOLUTION");
System.out.println("Heuristics used: "+heuristicsName);
System.out.println();
System.out.println("It was found in:"+binaryTreeCost.get(expandedNode) + " STEPS");
System.out.println("Number of nodes visited: " +
visitedNodes);
System.out.println("Number of nodes generatedconsidered: " + generatedNodes);
System.out.println("Execution time "+ (endTime -
startTime)+" ms");
System.out.println("///////////////////////////////////////");
}
else
{
System.out.println("///////////////////////////////////////");
System.out.println("A*(star) COULD NOT FIND THESOLUTION");
System.out.println("///////////////////////////////////////");
}
}
class Nodes implements Comparable<Nodes> {
private final String node;
private final int heuristics;
public Nodes(int heuristics,String node)
{
this.heuristics = heuristics;
this.node = node;
}
public int compareTo(Nodes nextNode)
{
return
Integer.valueOf(heuristics).compareTo(nextNode.heuristics);
}
public String toString()
{
return node;
}
}
public static void main(String[] args) throws IOException {
BufferedReader userInput = new BufferedReader(new
InputStreamReader(System.in));
int x=10,z=11;
int heuristicsChoice;
System.out.println("///////////////////////////////////////");
System.out.println("THIS IS A*(star) SEARCH ALGORITHM");
System.out.println();
System.out.println("Initital state: "+inputString);
System.out.println("Goal state: "+goalString);
System.out.println("Please choose one of the following heuristics by entering the coresponding value");
System.out.println();
System.out.println(" 1 - Missplaced tiles ");
System.out.println(" 2 - Nielssen Sequence Score ");
System.out.println(" 3 - Manhattan distance");
System.out.println(" 4 - Row Column displacement");
System.out.println(" 5 - Maximmum");
System.out.println(" 6 - Average ");
System.out.println(" 7 - Modified Nielssen Sequence Score");
System.out.println();
System.out.println(" and press enter");
System.out.println();
heuristicsChoice =Integer.parseInt(userInput.readLine());
Astar astar = new Astar(heuristicsChoice);
astar.stackToPriorityQueue(inputString, null);
astar.expandFrontiers();
}
}
//V. Heuristics
