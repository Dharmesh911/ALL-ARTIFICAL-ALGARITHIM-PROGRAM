
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Bread {
String expandedNode;
public static String inputString= "586073421";
public static String goalString = "123804765";
int generatedNodes = 0;
int searchLimit = 100000;
int cost;
int indexPosition;
int visitedNodes = 0;
boolean finished = false;
LinkedList<String> list = new LinkedList<String>();
Map<String, Integer> binaryTreeCost = new HashMap<String, Integer>();
long endTime,startTime = System.currentTimeMillis();
void expandFrontiers()
{
while(list.size()>0)
{
expandedNode=list.removeFirst();
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
appendToList(generatedNode,previousNode);
}
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
void appendToList(String generatedNode,String previousNode )
{
cost = calculateCost(previousNode);
list.add(generatedNode);
binaryTreeCost.put(generatedNode, cost);
}
void displayState(String expandedNode){
if (finished == true) {
long endTime = System.currentTimeMillis();
System.out.println("///////////////////////////////////////");
System.out.println("Breadth First Search HAS FOUND THE SOLUTION");
System.out.println();
System.out.println("It was found in:"+binaryTreeCost.get(expandedNode) + " STEPS");
System.out.println("Number of nodes visited: " +
visitedNodes);
System.out.println("Number of nodes considered: " +
generatedNodes);
System.out.println("Execution time "+ (endTime -
startTime)+" ms");
System.out.println();
System.out.println("///////////////////////////////////////");
}
else
{
System.out.println("///////////////////////////////////////");
System.out.println("Breadth First Search COULD NOT FINDTHE SOLUTION");
System.out.println("///////////////////////////////////////");
}
}
public static void main(String[] args)
{
Bread bread = new Bread();
bread.appendToList(inputString, null);
bread.expandFrontiers();
}
}