class Greedy :
    
    def __init__(self):
        """Constructeur de notre classe"""
        
        self.NbVariable = 0
        self.Nbconstraint = 0 
        self.PriceVariable = []
        self.ConstraintMatrix = []
        self.Constraint = []
        self.Utility = []
        self.candidate_choosen = []
        self.solution = []
        self.IndCandidat = 0
        
    def parseKnapsack(self,text):
        with open(text, "r") as fichier:
           content = ''.join(fichier.readline().splitlines())
           content.split() 
           self.NbVariable = int(content[0])
           content = ''.join(fichier.readline().splitlines())
           content.split() 
           self.NbConstraint =  int(content[0])
           print(self.NbVariable)
           print(self.NbConstraint)
           k = self.NbVariable
           content = [line.strip() for line in fichier.readlines()]
           for i in range(self.NbVariable):
             self.PriceVariable.append(int(content[i]))
             
             
           for i in range(self.NbConstraint):
             l = [int(content[i]) for i in range(k, k + self.NbVariable) ]
             self.ConstraintMatrix.append(l)
             k += self.NbVariable
             
        self.Constraint = [int(content[i]) for i in range(k, k + self.NbConstraint)]
        self.Utility = [[0,i] for i in range(self.NbVariable)]
        self.solution = [0 for i in range(self.NbVariable)]
        self.candidate_choosen = [0 for i in range(self.NbVariable)]
        
        
        
        
    def CalculateUtility(self):
      listsum = [sum([self.ConstraintMatrix[i][j] for i in range(self.NbConstraint)]) for j in range(self.NbVariable)]
      print(listsum)
      U = [self.PriceVariable[i]/listsum[i] for i in range(self.NbVariable)]
      for i in range(self.NbVariable):
        self.Utility[i][0] = U[i]
      print(self.Utility)
      
      
    def order_utility(self):
      l = sorted(self.Utility, key=lambda x:x[0], reverse=True)
      self.Utility = l
      
      
    def choose_candidate(self):
      k = 0 
      for i in range(self.NbVariable):
        k = self.Utility[i][1]
        if self.candidate_choosen[k] < 1:
          self.candidate_choosen[k] = 1
          self.IndCandidat = k
          break
    print("le candidat choisi est: {self.IndCandidat}")
      
      
      
    def CheckUpdateSolution(self):
      self.solution[self.IndCandidat] = 1
      sum_constraint = [sum([self.solution[i]  * self.ConstraintMatrix[j][i] for i in range(self.NbVariable)]) for j in range(self.NbConstraint)]
      for i in range(self.NbConstraint):
        if sum_constraint[i] > self.Constraint[i]:
                self.solution[self.IndCandidat] = 0
                print("candidat refusé")
                break 
              
   
    def resolve(self):
       self.CalculateUtility()
       self.order_utility()
       for i in range(self.NbVariable):
         self.choose_candidate()
         self.CheckUpdateSolution()
       self.DisplaySolution()

         
       
       
      
              

        
        
    
    
    
      
        
        
        
    def DisplaySolution(self):
      print(self.solution)
        

      
             
        
             
             
            
             
            



             
            
            

            
             
          
            

        
        
        




     






    
      
        

