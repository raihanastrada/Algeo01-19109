package src;

import java.util.*;
import src.Matriks;

public class Interpolasi {
    // Atribut
    private int NBrs;
    private int NKol;
    Matriks Mat;
    
    public Interpolasi(int N) {
        MakePolinom(N);
    }
    public Interpolasi(Matriks M) {
        this.Mat = M;
        this.NBrs = M.NBrsEff;
        this.NKol = M.NKolEFF;
    }
    
    public void MakePolinom(int N) {
        this.NBrs = N+1;
        this.NKol = N+2;
        Matriks M1 = new Matriks(this.NBrs,this.NKol);
        this.Mat = M1;
    }

    public void InputPolinom() {
        double temp = 0.0;
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < this.NBrs; i++) {
            for (int j = 0; j < this.NKol; j++) {
                if (j == 0) {
                    this.Mat.Mat[i][j] = 1.0;
                    System.out.print("Masukkan input X" + i + ": ");
                    temp = input.nextDouble();
                }
                else if (j==this.NKol-1) {
                    System.out.print("Masukkan input Y" + i + ": ");
                    this.Mat.Mat[i][j] = input.nextDouble();
                }
                else {
                    double HslExp = 1.0;
                    for (int exp=1; exp <= j;exp++) {
                        HslExp *= temp;
                    }
                    this.Mat.Mat[i][j] = HslExp;
                }
            }
        }
    }

    public void toPolinom() {
        this.NKol = this.NBrs+1;
        Matriks M1 = new Matriks(this.NBrs,this.NKol);
        double temp = 0;
        for (int i = 0; i < this.NBrs; i++) {
            for (int j = 0; j < this.NKol; j++) {
                if (j == 0) {
                    M1.Mat[i][j] = 1.0;
                    temp = this.Mat.Mat[i][j];
                }
                else if (j==this.NKol-1) {
                    M1.Mat[i][j] = this.Mat.Mat[i][1];
                }
                else {
                    double HslExp = 1.0;
                    for (int exp=1; exp <= j;exp++) {
                        HslExp *= temp;
                    }
                    M1.Mat[i][j] = HslExp;
                }
            }
        }
        this.Mat = M1;
    }

    public double Fungsi(double X) {
        double Hsl = 0.0;
        double[] FunctionCons = new double[this.NBrs]; 
        
        for (int i=0; i<this.NBrs; i++) {
            FunctionCons[i] = this.Mat.Mat[i][this.NKol-1];
        }

        for (int i=0; i<this.NBrs; i++) {
            double exp = 1.0;
            if (i != 0) {
                for (int k=0; k < i; k++) {
                    exp *= X;
                }
            }
            Hsl += exp*FunctionCons[i];
        }
        return Hsl;
    }
}