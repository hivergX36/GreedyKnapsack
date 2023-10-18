
public class Main{
 
public static void main(String[] args){
    Greedy greedy = new Greedy("Knapsack.txt");

    greedy.displayGreedy();
    greedy.calculateUtility();
    greedy.displayGreedy();
    greedy.resolve();


}
}