import java.util.*;
import java.io.*;


public class Greedy  {

     int NbVariable;
     int NbConstraint;
     int [] PriceVariable; 
     Float [][] ConstraintMatrix;
     Float[] Constraints; 
     Utility[] Futility;
     
     int[] Candidate_choosen;
     int[] Solution;
     int IndCandidat;

     List<Utility> ListUtility;



     Greedy(String name){  
         
        File input = new File(name);
        try{
          
            Scanner reader = new Scanner(input);
            String[] nums = reader.nextLine().split(",");
            this.NbVariable = Integer.parseInt(nums[0]);
            this.NbConstraint= Integer.parseInt(nums[1]);
            this.PriceVariable = new int[this.NbVariable];
            this.Candidate_choosen = new int[this.NbVariable];
            this.Solution = new int[this.NbVariable]; 
            this.ConstraintMatrix = new Float[this.NbConstraint][this.NbVariable];
            this.Constraints = new Float[this.NbConstraint];
            this.ListUtility = new ArrayList<Utility>(this.NbVariable);
     /*      this.Futility = new Utility[this.NbVariable]; */
            nums = reader.nextLine().split(",");
            
            for(int i = 0; i < this.NbVariable; i++){
              Utility k = new Utility(0,0,0);
              this.PriceVariable[i] = Integer.parseInt(nums[i]);
              this.ListUtility.add(k);
              Candidate_choosen[i] = 0;
              Solution[i] = 0;

       
   /*             this.Futility[i].U = 0 ; 
              this.Futility[i].ordre = i;
              this.Futility[i].permu = i; 
              */


            }
            
            for(int i = 0; i < this.NbConstraint; i++){
              nums = reader.nextLine().split(",");

              for(int j = 0; j < this.NbVariable; j++ ){
              this.ConstraintMatrix[i][j] = Float.parseFloat(nums[j]);
            }
          }
          
          nums = reader.nextLine().split(",");
          for(int i = 0; i < this.NbConstraint; i++){
            this.Constraints[i] = Float.parseFloat(nums[i]);
          }

            reader.close();
          
          
          }
          catch (IOException e){
            System.out.println("no file");
           }

        };

      
      
 
public void calculateUtility(){
     int sum = 0; 
    float result;
      for (int i = 0; i < NbVariable; i++){
             for(int j = 0; j < NbConstraint; j++){
                    sum += ConstraintMatrix[j][i];


        }
        result = (float)PriceVariable[i] / sum;
         this.ListUtility.get(i).U = result; 


        sum = 0;

      }
    }


    void order_utility(){
      int k = 0;
      int kp = 0;
      float max; 

      while(k < NbVariable){ 
          max = 0; 
          for(int i = k; i < NbVariable; i++){
              if( this.ListUtility.get(i).U > max){
                  max = this.ListUtility.get(i).U; 
                  kp = i;
                  }
              }
              if (this.ListUtility.get(k).U < max){
            this.ListUtility.get(kp).U = this.ListUtility.get(k).U;
             this.ListUtility.get(kp).permu = this.ListUtility.get(k).permu;
           this.ListUtility.get(k).U = max; 
           this.ListUtility.get(k).permu = kp;
              }
               k++; 
              } 
          }


          void choose_candidate(){

            int k = 0;
    
            for(int i = 0; i < NbVariable; i++){
    
                k = ListUtility.get(i).permu; 
    
            
    
                //On parcours la fonction d'utilité dans l'ordre
    
                if(Candidate_choosen[k] < 1) {
    
                    Candidate_choosen[k] = 1;
                    IndCandidat = k;
                    break; 
    
                    
    
                }
    
            }
    
            System.out.println( "le candidat choisie est: " + k );
              System.out.println( "le candidat choisie est: " + IndCandidat);

    
    
            
        }
    
        void ChackUpdateSolution(){
            float sum; 
            Solution[IndCandidat] = 1;
            int j = 0;
    
            while(j < NbConstraint){
                sum = 0;
                for( int i = 0; i < NbVariable; i++){
    
                    sum += Solution[i] * ConstraintMatrix[j][i];
    
    
                }
                if(sum > Constraints[j]){
    
                    Solution[IndCandidat] = 0;
    
                System.out.println("Le candidat est rejeté");    
                    break;
    
                }
                    
                    j++;
            
    
    
    
    
            }

            System.out.println("Solution mise à jour");
                
    
    
        }

        
   public  void resolve(){
      calculateUtility();
      order_utility();
      for(int i = 0; i < NbVariable; i++){
         displayGreedy();


         choose_candidate();
         ChackUpdateSolution();
      }

      displaySolution();



 }


     

public void displayGreedy(){

        System.out.println("Le nombre de variable est: " + NbVariable);
        System.out.println("Le nombre de contraintes est: " + NbConstraint);
        System.out.println("La liste des prix est: " + "\t");
                for(int i = 0; i < this.NbVariable; i++ ){
                        System.out.println(this.PriceVariable[i] + "\t");

                        

            };
        System.out.println("la liste des contraintes est: ");
        
      
        for (int r = 0; r < NbConstraint; r++) {
          for (int c = 0; c < NbVariable; c++) {
            System.out.print(ConstraintMatrix[r][c] + "\t");
          }
          System.out.println();
        };

        System.out.println("Les contraintes sont: ");
        for(int i = 0; i < NbConstraint; i++)
        {

          System.out.println(Constraints[i] + "\t");
        };

        System.out.println("Les caractéristiques de la fonction d'utilité sont: " );
        for(int i = 0; i < NbVariable ; i++){
          System.out.println("u" + "_i: " + this.ListUtility.get(i).U ); 
         /*    System.out.println("u" + "_i: " +   this.Futility[i].U ); */
          System.out.println("ordre" + "_i: " + this.ListUtility.get(i).ordre ); 
          /*   System.out.println("ordre" + "_i: " +   this.Futility[i].ordre );*/
           System.out.println("permutation" + "_i: " + this.ListUtility.get(i).permu );
          /*  System.out.println("permutation" + "_i: " +   this.Futility[i].permu); */






        }



    //    for(int r = 0; r < AdjacencyList.size(); r++){

      //      	System.out.print(AdjacencyList.get(r)+ "\t");



       // }





    
      
        

    }

    
 public void displaySolution(){
  for(int i = 0; i < NbVariable ; i++){

    System.out.println(this.Solution[i]);
  }
}
}







    

