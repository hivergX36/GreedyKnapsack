from Greedy import Greedy
import os

fileDirectory = os. getcwd()
fileName = "C:/Users/viche/Documents/Programmation/Metaheuristics/GreedyKnapsack/GreedyKnapsack_Python/knapsack.txt"
print(fileName)
greedy = Greedy()
greedy.parseKnapsack(fileName)
greedy.resolve()