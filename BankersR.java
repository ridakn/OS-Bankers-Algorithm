import java.io.FileNotFoundException;
import java.io.*;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class BankersR {

    public static boolean SafeState(int A[][], int N[][], int R[], int nP, int nR, String fname) {
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fname));
            boolean done[] = new boolean[nP];
            int safeS[] = new int[nP];
        
            int count = 0;
        
            while(count < nP) {
            
                boolean check = false; 
                for(int i = 0; i < nP; i++) {

                    if(done[i] == false) {
                        
                        writer.append("Currently available resources");
                        writer.newLine();
                        for(int p = 0; p < nR; p++) {
                            writer.append(" " + R[p] + " ");
                        }
                        writer.newLine();
                        writer.append("Current client request: ");
                        writer.newLine();
                        for(int k = 0; k < nR; k++) {
                            writer.append(" " + N[i][k] + " ");
                        }   
                        writer.newLine();

                        int j = 0;
                        for(j = 0; j < nR; j++) {
                    
                            if( N[i][j] > R[j]) { 
                                writer.append("Request for client " + i + " rejected."); 
                                writer.newLine();
                                break; 
                            }
                        }
                        
                        if(j == nR) {
                            writer.append("Request for client " + i + " accepted.");
                            writer.newLine();
                            for(int x = 0; x < nR; x++) {
                                R[x] += A[i][x];
                            }
                            safeS[count++] = i;
                            done[i] = true;
                            check = true;
                        }
                    }

                }
       

                if(check == false) { 
                    writer.newLine();
                    writer.append("Unsafe state");
                    System.out.println("Unsafe state");
                    return false;
                }
            
            }
        
            writer.newLine();
            writer.append("Safe State, client order is: " );
            System.out.println("Safe State, client order is: " );
            writer.newLine();
            for(int j = 0; j < nP; j++) {
                System.out.println(" " + safeS[j] + " "); 
                writer.append(" " + safeS[j] + " "); 
            }
        
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                   writer.close();
                }
            } catch (IOException e) {}
        
        }
        return true;
    }
    
    public static void main (String[] args) {
        
        Scanner r = new Scanner(System.in);
        System.out.println("Please enter an output filename");
        String fname = r.nextLine();
            
        System.out.println("1.Input from Main OR 2.User Input");
        int ans = r.nextInt();
        if(ans == 2) {
            System.out.println("Number of clients: ");
            int NP = r.nextInt();
            System.out.println("Number of resources: ");
            int NR = r.nextInt();
            System.out.println("Currently available resources ");
            int R[] = new int[NR];
            for(int i = 0; i < NR; i++) { R[i] = r.nextInt(); }
            System.out.println("Allocated resources: ");
            int A[][] = new int[NP][NR];
            for(int i = 0; i < NP; i++) { 
                System.out.println("Enter resources allocated to Client" + i );
                for(int j = 0; j < NR; j++) {
                    System.out.println("R" + j + " for C" + i );
                    A[i][j] = r.nextInt();
                }
            }
            System.out.println("Client Requests: ");
            int M[][] = new int[NP][NR];
            for(int i = 0; i < NP; i++) { 
                System.out.println("Enter maximum need of Client" + i );
                for(int j = 0; j < NR; j++) {
                    System.out.println("R" + j + " needed by Client" + i );
                    M[i][j] = r.nextInt();
                }
            }
            
            boolean safe = SafeState(A, M, R, NP, NR, fname);
        }
        else if(ans == 1) {
            
            System.out.println("Please pick test input 1, 2, 3, 4");
            int inp = r.nextInt();
            
            switch(inp) {
                
                case 1:
                
                    int NP = 5, NR = 3;
                    int R[] = {3, 3, 2}; 
    
                    int N[][] = {{7, 4, 3}, 
                     {1, 2, 2}, 
                     {6, 0, 0}, 
                     {0, 1, 1}, 
                     {4, 3, 1}}; 
  
                    
                     int A[][] = {{0, 1, 0}, 
                      {2, 0, 0}, 
                      {3, 0, 2}, 
                      {2, 1, 1}, 
                      {0, 0, 2}}; 
            
                      boolean safe = SafeState(A, N, R, NP, NR, fname);
                      
                      break;
                
                case 2:
                
                    NP = 4; NR = 5;
                    int R1[] = {0, 0, 2, 1, 1}; 
    
                    int N1[][] = {{0,1,0,0,1}, 
                     {0,2,1,0,0}, 
                     {1,0,3,0,0}, 
                     {0, 0,1, 1,1}}; 
  
                    
                     int A1[][] = {{1,0,2,1,1}, 
                     {2,0,1,1,0}, 
                     {1,1,0,1,0}, 
                     {1, 1,1, 1,0}}; 
            
                      boolean safe1 = SafeState(A1, N1, R1, NP, NR, fname);
                      
                      break;
                
                case 3:
                
                    NP = 4; NR = 5;
                    int R2[] = {0, 0, 2, 1, 1}; 
    
                    int N2[][] = {{0,1,0,0,2}, 
                     {0,2,1,0,0}, 
                     {1,0,3,0,0}, 
                     {0, 0,1, 1,1}}; 
  
                    
                     int A2[][] = {{1,0,2,1,1}, 
                     {2,0,1,1,0}, 
                     {1,1,0,1,0}, 
                     {1, 1,1, 1,0}}; 
            
                      boolean safe2 = SafeState(A2, N2, R2, NP, NR, fname);
                      
                      break;
                      
                case 4:
                
                    NP = 5; NR = 4;
                    int R3[] = {2, 0, 1, 1}; 
    
                    int N3[][] = {{2,0,1,1}, 
                     {0,6,5,0}, 
                     {1,1,0,2}, 
                     {1,0,2,0},
                     {0,2,4,4}}; 
  
                    
                     int A3[][] = {{4,0,0,1}, 
                     {1,1,0,0}, 
                     {1,2,5,4}, 
                     {0,6,3,3},
                     {1,4,1,2}}; 
            
                      boolean safe3 = SafeState(A3, N3, R3, NP, NR, fname);
                      
                      break;
            }
            
        }
        
    }

}
