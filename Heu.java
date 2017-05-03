import java.util.HashMap;
public class Heu {
int averageHeu(String inputString, String goalString) {
int h1 = 0;
int h2 = 0;
int h3 = 0;
int h4 = 0;
h1 = rowColumn(inputString, goalString);
h2 = modified_nielsen(inputString, goalString);
h3 = manhhatan(inputString, goalString);
h4 = missplacedTiles(inputString, goalString);
int h = (h1+h2+h3+h4) / 4;
return h;
}
int maxHeu(String inputString, String goalString) {
int h = 0;
int h1 = 0;
int h2 = 0;
int h3 = 0;
int h4 = 0;
h1 = rowColumn(inputString, goalString);
h2 = modified_nielsen(inputString, goalString);
h3 = manhhatan(inputString, goalString);
h4 = missplacedTiles(inputString, goalString);
int max = Math.max(h1,h2);
int max1 = Math.max(max,h3);
h = Math.max(max1,h4);
return h;
}
int rowColumn(String inputString, String goalString) {
int h = 0;
for (int i = 1; i < 9; i++) // skip blank tile
{
h = h +
rowColumnLookup(inputString.indexOf(Integer.toString(i)),goalString.indexOf(Integer.toString(i)));
}
return h;
}
int rowColumnLookup(int inputPosition, int goalPosition)
{
int row = 0;
int column = 0;
int x1, y1, x2, y2;
HashMap<String, String> map = new HashMap<String,
String>();
map.put("0", "0,0");
map.put("1", "1,0");
map.put("2", "2,0");
map.put("3", "0,1");
map.put("4", "1,1");
map.put("5", "2,1");
map.put("6", "0,2");
map.put("7", "1,2");
map.put("8", "2,2");
String state =
map.get(String.valueOf(inputPosition));
String goal =
map.get(String.valueOf(goalPosition));
x1 = Character.getNumericValue(state.charAt(0));
y1 = Character.getNumericValue(state.charAt(2));
x2 = Character.getNumericValue(goal.charAt(0));
y2 = Character.getNumericValue(goal.charAt(2));
if (x1 != x2) {
column++;
}
if (y1 != y2) {
row++;
}
return column + row;
}
int missplacedTiles(String inputString, String
goalString) {
int heu_value = 0;
for (int i = 1; i < 9; i++) {
if (inputString.indexOf(String.valueOf(i)) !=
goalString
.indexOf(String.valueOf(i))) {
heu_value++;
}
}
return heu_value;
}
int nielsen(String inputString, String goalString) {
int niel = 0;
for (int i = 1; i < 9; i++) {
if (inputString.indexOf(Integer.toString(i))
!= goalString
.indexOf(Integer.toString(i))) {
niel = niel + 2;
}
}
if ((inputString.indexOf(Integer.toString(0)) !=
goalString
.indexOf(Integer.toString(0)))) {
niel = niel + 1;
}
int man = manhhatan(inputString, goalString);
int h = man + (3 * niel);
return h;
}
int modified_nielsen(String inputString, String
goalString) {
int niel = 0;
for (int i = 1; i < 9; i++)
{
if (inputString.indexOf(Integer.toString(i))
!= goalString.indexOf(Integer.toString(i)))
{
niel = niel + 2;
}
}
if ((inputString.indexOf(Integer.toString(0)) !=
goalString.indexOf(Integer.toString(0)))) {
niel = niel + 1;
}
int fn = niel ;
return fn;
}
int manhhatan(String inputString, String goalString) {
int man_heuristic = 0;
for (int i = 1; i < 9; i++)// skip blank
{
man_heuristic = man_heuristic
+
manhattanLookup(inputString.indexOf(Integer.toString(i)),
goalString.indexOf(Integer.toString(i)));
}
return man_heuristic;
}
int manhattanLookup(int inputPosition, int goalPosition)
{
int x1, y1, x2, y2;
HashMap<String, String> map = new HashMap<String,
String>();
map.put("0", "0,0");
map.put("1", "1,0");
map.put("2", "2,0");
map.put("3", "0,1");
map.put("4", "1,1");
map.put("5", "2,1");
map.put("6", "0,2");
map.put("7", "1,2");
map.put("8", "2,2");
String state =
map.get(String.valueOf(inputPosition));
String goal =
map.get(String.valueOf(goalPosition));
x1 = Character.getNumericValue(state.charAt(0));
y1 = Character.getNumericValue(state.charAt(2));
x2 = Character.getNumericValue(goal.charAt(0));
y2 = Character.getNumericValue(goal.charAt(2));
int h = Math.abs(x1 - x2) + Math.abs(y1 - y2);
return h;
}
}