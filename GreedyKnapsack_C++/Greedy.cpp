#include<iostream> 
#include<vector> 
#include<fstream> 
#include "greedy.hpp"

int main(){
   float a;
   a = 5 / 2;
   std::cout << a << std::endl;

   Greedy greedy = Greedy("knapsack.txt");
   std::cout << greedy.NbConstraints << " ";
   std::cout << greedy.NbVariable << std::endl;
   std::cout << greedy.constraint[0][0] << std::endl;
  /* greedy.displayCostMatrix(); 
   greedy.calculateUtility();
   greedy.display_utility();
   std::cout << std::endl;
   greedy.order_utility();
   greedy.display_utility(); */
   /* greedy.choose_candidate();

   std::cout << std::endl;

   

   std::cout << "Le candidat choisi est: " << greedy.IndCandidat << std::endl;

   greedy.ChackUpdateSolution(); */ 

   greedy.resolve();







}