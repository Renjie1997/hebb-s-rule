/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Normalized {

    public double[][] Inputdata(String fname) {

        int row = 10, col = 35;
        double[][] data = new double[row][col];
        String line;
        int i = 0;
        try {

            BufferedReader bf = new BufferedReader(new FileReader(fname));
            while ((line = bf.readLine()) != null) {

                for (int j = 0; j < col; j++) {

                    if (line.charAt(j) == '1') {

                        data[i][j] = 1;
                    } else {
                        data[i][j] = 0;
                    }
                }
                i++;
            }
            bf.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return data;
    }

    public double[] Inputdata2(String fname) {

        int row = 1, col = 35;
        double[] data = new double[col];
        String line;
        int i = 0;
        try {

            BufferedReader bf = new BufferedReader(new FileReader(fname));
            while ((line = bf.readLine()) != null) {

                for (int j = 0; j < col; j++) {

                    if (line.charAt(j) == '1') {

                        data[j] = 1;
                    } else {
                        data[j] = 0;
                    }
                }
                i++;
            }
            bf.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return data;
    }

    public static double[][] training(double[][] data, double[][] w, double[][] last, double c) {

        double[] square = new double[10];
        double[] norm = new double[10];
        double[] s = new double[10];
        double[] x = new double[10];
        double[] max_w = new double[10];

        for (int j = 0; j < 10; j++) {

            int conti = 1;

            while (conti == 1) {

                square[j] = 0;
                for (int i = 0; i < 35; i++) {

                    //System.out.print(w[i][j]);
                    square[j] = w[i][j] + square[j];

                }
                norm[j] = Math.sqrt(square[j]);

                double r = 0.1;

                double th = 1;
                max_w[j] = 0;
                for (int i = 0; i < 35; i++) {

                    w[i][j] = w[i][j] / norm[j];
                    //System.out.print(w[i][j]);

                    if (Math.abs(w[i][j] - last[i][j]) > max_w[j]) {
                        max_w[j] = Math.abs(w[i][j] - last[i][j]);

                    }

                }

                if (max_w[j] <= r) {
                    conti = 0;
                }

                for (int i = 0; i < 35; i++) {
                    last[i][j] = w[i][j];
                    s[j] = s[j] + w[i][j] * data[j][i];

                }

                if (s[j] >= 1) {
                    x[j] = 1;

                } else {
                    x[j] = 0;
                }

                for (int i = 0; i < 35; i++) {
                    w[i][j] = w[i][j] + c * x[j] * data[j][i];
                }

            }

        }
        return w;
    }

    public static int classify(double[][] w, double[] Errmap, double c) {
        int out = 0;

        double[] product = new double[10];

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 35; i++) {

                product[j] = product[j] + Errmap[i] * w[i][j];
            }

        }

        for (int j = 0; j < 10; j++) {

            System.out.print("product of " + j + " : " + product[j]);
            System.out.print("  ");
            System.out.print("\n");

        }

        double max = product[0];
        for (int j = 1; j < 10; j++) {
            if (product[j] > max) {
                max = product[j];
                out = j;
            }
        }

        return out;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub

        Normalized norm = new Normalized();
        double c = 1;

        double[][] data = norm.Inputdata("input.txt");
        double[][] w = new double[35][10];
        double[][] last = new double[35][10];

        for (int i = 0; i < 35; i++) {
            for (int j = 0; j < 10; j++) {
                w[i][j] = 1;
                last[i][j] = 1;

            }
        }

        /* for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 35; j++) {
                System.out.print(data[i][j]);
                 System.out.print("  ");
                 
                
            }
            System.out.print("\n");
        }
         */
        //System.out.print(w[5][5]);
        double[] ErrMap = norm.Inputdata2("TestMap.txt");
        //System.out.print(ErrMap[0]);

        //System.out.print("\n");
        w = training(data, w, last, c);

        int out = 2;

        out = classify(w, ErrMap, c);
        System.out.print("product " + out + "  is max\n");
        System.out.print("the number classify to " + out + "\n");
    }

}
