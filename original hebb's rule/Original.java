/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.Arrays;



public class Original{
    public static void main(String[] args) {
        double []mp1 = {
                -1, 1, 1, 1, 1,
                1, -1, -1, -1, 1,
                1, -1, -1, -1, 1,
                1, -1, -1, -1, 1,
                1, -1, -1, -1, 1,
                -1, 1, 1, 1, -1
        };
        double [] mp2 = {
                -1, 1, 1, -1, -1,
                -1, -1, 1, -1, -1,
                -1, -1, 1, -1, -1,
                -1, -1, 1, -1, -1,
                -1, -1, 1, -1, -1,
                -1, -1, 1, -1, -1
        };
        double [] mp3 = {
                1, 1, 1, -1, -1,
                -1, -1, -1, 1, -1,
                -1, -1, -1, 1, -1,
                -1, 1, 1, -1, -1,
                -1, 1, -1, -1, -1,
                -1, 1, 1, 1, 1
        };
        
        double [] Test = {
                -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1,
                -1, 1, 1, 1, -1,
                -1, 1, -1, -1, -1,
                -1, 1, 1, -1, -1
        };
        
        
        
        
        

        double rfa = 0.2;
        double [][] w = new double [30][30];
        w = sum(sum(m(mp1), m(mp2)), m(mp3));

        //show(w);
       // System.out.println("\n");
       
        
        w=training(w, mp1, mp1, rfa);
        w=training(w, mp2, mp2, rfa);
        w=training(w, mp3, mp3, rfa);
        //show(w);
         
         
        int [] output0 = working(w, mp1, rfa);
        int [] output1  = working(w, mp2, rfa);
        int [] output2 = working(w, mp3, rfa);
        int [] output3 = working(w, Test, rfa);
       
        //show(output0);
       // show(output1);
       // show(output2);
        //show(output3);
        check(output0,output1,output2,output3);
        
        
        //show(w);
    }

    public static double [][] training(double [][] weight, double [] input, double [] output, double rfa){
        for(int i = 0; i < weight.length; i++){
            for(int j = 0; j < weight[i].length; j++){
                weight[i][j] = weight[i][j] + rfa*input[i]*output[j];
            }
        }
        return weight;
    }

//    public static double [] working(double [][] weight, double [] input, double rfa){
//        double []output = new double[input.length];
//        for(int i = 0; i < weight.length; i++){
//            for(int j = 0; j < weight[i].length; j++){
//                output[j] += weight[i][j] * input[i];
//            }
//        }
//        return output;
//    }

    public static int [] working(double [][] weight, double [] input, double rfa){
        double []output = new double[input.length];
        int [] output1 = new int[input.length];
        for(int i = 0; i < weight.length; i++){
            for(int j = 0; j < weight[i].length; j++){
                output[j] += weight[i][j] * input[i];
            }
        }
        for(int i = 0; i < output.length; i++){
            output1[i] = hardlim(output[i]);
        }
        return output1;
    }

    public static int[] hardlim(double[] a){
        int []num = new int[a.length];
        for(int i = 0; i < a.length; i++){
            if(a[i] > 0){
                num[i] = 1;
            }
            else{
                num[i] = -1;
            }
        }
        return num;
    }

    public static int hardlim(double a){
        int num ;
            if(a > 0){
                num = 1;
            }
            else {
                num = -1;
            }
        return num;
    }

    //return a product of a nx1 matrix p mutiply the transpose of p
    public static double[][] m(double [] p){
        double [][] result=new double [p.length][p.length];
        for(int i = 0; i< p.length; i++){
            for(int j = 0; j < p.length; j++){
                result[i][j]=p[i]*p[j];
            }
        }
        return result;
    }

    //return a sum of two matrix which have the same dimensions
    public static double[][] sum(double [][] p, double [][] q){
        double [][] sum =new double [p.length][p[0].length];
        for(int i = 0; i < p.length; i++){
            for(int j=0; j < p[i].length; j++){
                sum[i][j] = (double)p[i][j] + q[i][j];
            }
        }
        return sum;
    }

    //display a two dimension array
    public static void show(double [][] matrix){
      //  System.out.println(matrix.length + " " + matrix[0].length);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void show(int [] matrix){
        for(int i = 0; i < matrix.length; i++){
            if((i+1)%5 ==0 && i > 0){
                System.out.print(" " + matrix[i] + " ");
                System.out.println();
            }
            else{
                System.out.print(" " + matrix[i] + " ");
            }
        }
    }
    
    public static void check(int [] matrix0,int [] matrix1,int [] matrix2,int [] matrix3){
        for(int i = 0; i < 3; i++){
          
        }
        
        if(Arrays.equals(matrix0, matrix3)){
            show(matrix3);
            System.out.print(" classify to 0\n" );
            
        }
        if(Arrays.equals(matrix1, matrix3)){
            show(matrix3);
            System.out.print(" classify to 1\n" );
            
        }
        if(Arrays.equals(matrix2, matrix3)){
            show(matrix3);
            System.out.print(" classify to 2\n" );
           
        }
        else{
            System.out.print(" the input does not belong to the training set\n" );
        }
   
    }
    
    
    
    
}
