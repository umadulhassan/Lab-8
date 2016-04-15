/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coinchange;
import java.util.*;
import java.util.Random;


/**
 *
 * @author Umad Rai
 */
public class CoinChange {

    static int greedy(int amount, int[] coins){
     
      int count= 0;
        int sum =0;
        int x = 0;
        while(sum != amount){
            for(int i = coins.length -1 ;i >= 0 ; i --){
                x = coins[i];
                if((x + sum) <= amount){
                    sum = sum + x;
                    count++;
                    break; 
                }
            }
        }
        return count;
    }
     static int dynamic(int amount, int v[])
    {
       int[] M;
       int[] sol, mySol;
       int j, k, min;

       M = new int[amount+1];              // Store results

       sol = new int[v.length];
       mySol = new int[v.length];
       //Base case
         
       M[0] = 0;        // 0 coins needed to make change for $0

//          The other cases (starting from 1 to M.length - 1)

       for ( j = 1; j <= amount; j++ )
       {
//	     Find min # coin to make change for $j

          for ( k = 0; k < v.length; k++ )
             mySol[k] = -1;                   // Initialize mySol[]

          //          Try every denomination k = 1,2,..,C for the last coin
          for ( k = 0; k < v.length; k++ )
          {
//                Check if we can use the k-th denomination
             if ( v[k] <= j )
             {
 //                  Divide step
                sol[k] = M[j - v[k]];     // Use coin v[k] as last coin
                mySol[k] = sol[k] + 1;    // Solution to make change for $j      
             }
          }

//             Find the minimum of all mySol[...]
          M[j] = -1;

          for ( k = 0; k < v.length; k++ )
          {
             if ( mySol[k] > 0 )
             {
                if ( M[j] == -1 || mySol[k] < M[j] )
                   M[j] = mySol[k];
             }
          }
       }

       return( M[amount] );    // Min # coins to change $Am
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int amount = 0;
        int dif = 0;
        int greed, dynam = 0;
        
	int[] coins = { 1, 5, 10, 25 };
      while(dif != 10){
          greed =  greedy(amount,coins);
          dynam = dynamic(amount,coins);
        System.out.println("By Greedy Algorithm " +  greed);
        System.out.println("By Dynamic Algorithm " +  dynam);
            
        if(dynam < greed){        
        dif++;
        }
        amount++;
      }
    }
}
