#include<vector> 
#include <iostream>
#include <string>
#include <fstream>

struct utility{
 

    float u;   /*u_i */
    int range; /* order of priority of our utility singleton*/
    int permu;

};


struct Greedy{

    int NbVariable;
    int NbConstraints;
    int IndCandidat;
    /* Price function */
    std::vector<int> * Price;
    /*Utilisty function*/
    std::vector<utility> * Utility;

    /*Matrix of constraint*/
    std::vector<int>*CostMatrix;
    /*Vector of solution*/
    std::vector<int> * Solution;
    std::vector<int> * Utilityorder;
    std::vector<bool> * Candidate_choosen; 
    std::vector<float> * constraint;

 
    Greedy(std::string name){

        int k = 0;
        int number;
        int candidat; 

            std::ifstream fichier(name);
                if(fichier){

                   

                     fichier >> NbVariable;
                     fichier >> NbConstraints; 

                     Price = new std::vector<int>; 
                     Solution = new std::vector<int>;
                     CostMatrix = new  std::vector<int>[NbConstraints];
                     Utility = new std::vector<utility>[NbVariable];
                     Utilityorder = new std::vector<int>[NbVariable];
                     Candidate_choosen = new std::vector<bool>;
                     constraint = new std::vector<float>; 



                     while(k < NbVariable){
                        fichier >> number; 
                        std::cout << number << std::endl;
                        Candidate_choosen[0].push_back(0);
                        Price[0].push_back(number);
                        Solution[0].push_back(0); 
                        Utility->push_back({0,k,k});
                        k++;



                     };



                     for(int i = 0; i < NbConstraints; i++){
                        k = 0;

                        while(k < NbVariable){
                            fichier >> number; 
                            CostMatrix[i].push_back(number);
                            k++;
                        }
                     }

                     for(int i = 0; i < NbConstraints; i++){
                        fichier >> number;
                        constraint[0].push_back(number);
                     }
                     
                };
                



    };

    void displayCostMatrix(){
        for(int i = 0; i < NbConstraints; i++){
            for(int j = 0; j < NbVariable; j++)
            {
                std::cout << CostMatrix[i][j] << " ";

        }

        std::cout << std::endl;
        }
    };


    void calculateUtility(){
        int sum = 0; 
        float result;
        for (int i = 0; i < NbVariable; i++){
             for(int j = 0; j < NbConstraints; j++){
                    sum += CostMatrix[j][i];


        }
        std::cout << std::endl;
        std::cout << "La somme est: " << sum << std::endl;
        result = (float)Price[0][i] / sum;
        std::cout << "le résultat est: " << result << std::endl;
        Utility[0][i].u = result; 


        sum = 0;

        }
       
        }
    
    void order_utility(){
        float Tableau[NbVariable];
        int k = 0;
        int kp = 0;
        float max; 

        while(k < NbVariable){ 
            max = 0; 
            for(int i = k; i < NbVariable; i++){
                if( Utility[0][i].u > max){
                    max = Utility[0][i].u; 
                    kp = i;
                    }
                }
                if (Utility[0][k].u < max){
                Utility[0][kp].u = Utility[0][k].u;
                Utility[0][kp].permu = Utility[0][k].permu;
                Utility[0][k].u = max; 
                Utility[0][k].permu = kp;
                }
                 k++; 
                } 
            }

    void display_utility(){

        std::cout << "Le vecteur d'utilité est: ";
        for(int i = 0; i < NbVariable; i++){

            std::cout << "u_str_" << Utility[0][i].range << "_permu_" << Utility[0][i].permu << ": " <<  Utility[0][i].u  << " ";  

        }

        std::cout << std::endl; 

        std::cout << "La permutation est: "; 

        for(int i = 0; i < NbVariable; i++){

         std::cout <<  Utility[0][i].permu <<  ""; 


        }
    }

    void choose_candidate(){

        int k = 0;

        for(int i = 0; i < NbVariable; i++){

            k = Utility[0][i].permu;

        

            //On parcours la fonction d'utilité dans l'ordre

            if(Candidate_choosen[0][k] < 1) {

                Candidate_choosen[0][k] = 1;
                IndCandidat = k;
                break; 

                

            }

        }

        std::cout << "le candidat choisie est: " << k << std::endl;


        std::cout << "le candidat choisie est: " << IndCandidat << std::endl;
        
    }

    void ChackUpdateSolution(){
        float sum; 
        Solution[0][IndCandidat] = 1;
        int j = 0;

        while(j < NbConstraints){
            sum = 0;
            for( int i = 0; i < NbVariable; i++){

                sum += Solution[0][i] * CostMatrix[j][i];


            }
            if(sum > constraint[0][j]){

                Solution[0][IndCandidat] = 0;

                std::cout << "Le candidat est rejeté" << std::endl;
                break;

            }
                
                j++;
        




        }
            
            std::cout << "Solution mise à jour" << std::endl;


    }


    void resolve(){
         calculateUtility();
         order_utility();
         for(int i = 0; i < NbVariable; i++){
            display_utility();


            choose_candidate();
            ChackUpdateSolution();
         }

         displaySolution();



    }

    void displaySolution(){
        for(int i = 0; i < NbVariable; i++){

            std::cout << Solution[0][i] << " ";
        }
    }

    void displayConstraint(){

    }



    
};
