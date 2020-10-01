package src;

import java.util.Scanner;

public class Regresi {
    // Atribut
    Matriks X;
    Matriks Y;
    Matriks Augmented;
    double[] TabTaksiran;
    
    public Regresi(Matriks Mx, Matriks My) {
        MakeRegresi(Mx,My);
    }

    public void MakeRegresi(Matriks X, Matriks Y) {
        this.X = X;
        this.Y = Y;
    }

    private double SigmaX(int j) {
        double Hsl = 0;
        for (int k=0; k < X.NBrsEff; k++) {
            Hsl += X.Mat[k][j];
        }
        return Hsl;
    }
    private double SigmaY() {
        double Hsl = 0;
        for (int i=0; i < Y.NBrsEff; i++) {
            Hsl += Y.Mat[i][0];
        }
        return Hsl;
    }
    private double SigmaXX(int i, int j) {
        double Hsl = 0;
        for (int k=0; k < X.NBrsEff; k++) {
            Hsl += X.Mat[k][i]*X.Mat[k][j];
        }
        return Hsl;
    }
    private double SigmaXY(int i) {
        double Hsl = 0;
        for (int k=0; k < Y.NBrsEff; k++) {
            Hsl += X.Mat[k][i]*Y.Mat[k][0];
        }
        return Hsl;
    }
    public void NormEq() {
        Matriks Augmented = new Matriks(X.NKolEFF,X.NKolEFF+1);
        for (int i=0; i<Augmented.NBrsEff; i++) {
            for (int j=0; j<Augmented.NKolEFF; j++) {
                if (i == 0 && j == 0) {
                    Augmented.Mat[i][j] = X.NBrsEff;
                }
                else if (j == Augmented.NKolEFF-1) {
                    if (i==0) {
                        Augmented.Mat[i][j] = SigmaY();
                    }
                    else {
                        Augmented.Mat[i][j] = SigmaXY(i);
                    }
                }
                else {
                    if (i == 0) {
                        Augmented.Mat[i][j] = SigmaX(j);    
                    }
                    else if (j == 0) {
                        Augmented.Mat[i][j] = SigmaX(i);
                    }
                    else {
                        Augmented.Mat[i][j] = SigmaXX(i,j);
                    }
                }
            }
        }
        this.Augmented = Augmented;
    }

    public double Fungsi() {
        /* I.S. : SPL memiliki solusi unik*/
        double Hsl = 0.0;
        double[] FunctionCons = new double[Augmented.NBrsEff]; 
        
        for (int i=0; i<Augmented.NBrsEff; i++) {
            FunctionCons[i] = Augmented.Mat[i][Augmented.NKolEFF-1];
        }

        for (int i=0; i<Augmented.NBrsEff; i++) {
            if (i == 0) {
                Hsl += FunctionCons[i];
            }
            else {
                Hsl += TabTaksiran[i]*FunctionCons[i];
            }
        }
        return Hsl;
    }

    public void InputTaksiran() {
        Scanner input = new Scanner(System.in);
        double[] TabX = new double[X.NKolEFF];
        TabX[0] = 0;
        for (int i=1; i<X.NKolEFF; i++) {
            System.out.print("Masukkan nilai X"+(i)+" yang akan ditaksir: ");
            TabX[i] = input.nextDouble();
        }
        this.TabTaksiran = TabX;
    }
}