package src;

import java.util.*;

public class Matriks {
    // Atribut
    private int NBrsEff, NKolEFF;
    double[][] Mat = new double[100][100];
    
    public Matriks(int NB, int NK){
        MakeMatriks(NB,NK);
    } 
    // Method
    public void MakeMatriks(int NB, int NK) {
        this.NBrsEff = NB;
        this.NKolEFF = NK;
    }
    public void InputMatriks() {
        Scanner input = new Scanner(System.in);
        
        for (int i=0;i<this.NBrsEff;i++) {
            for (int j=0;j<this.NKolEFF;j++) {
                System.out.print("Masukkan input matriks ["+i+"]["+j+"]: ");
                Mat[i][j] = input.nextInt();
            }
        }
        input.close();
    }

    public void TulisMatriks() {
        for (int i=0;i<this.NBrsEff;i++) {
            for (int j=0;j<this.NKolEFF;j++) {
                if (j != this.NKolEFF-1) {
                    System.out.print(Mat[i][j]+" ");
                }
                else {
                    System.out.println(Mat[i][j]);
                }
            }
        }
    }

    public Matriks KaliMatriks(Matriks M2) {
        Matriks M1 = new Matriks(M2.NBrsEff, this.NKolEFF);
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                M1.Mat[i][j] = 0;
                for (int k = 0; k < this.NBrsEff; k++) {
                    M1.Mat[i][j] += Mat[i][k]*M2.Mat[k][j];
                }
            }
        }
        return M1;
    }

    public double Determinan() {
        double d = 0;
        if ((this.NBrsEff == 1) && (this.NKolEFF == 1)) {
            return (double) Mat[0][0];
        } else {
            int j, i1, j1, j2;
            int s = 1;
            Matriks Cofact;
            for (j = 0; j < this.NKolEFF; j++) {
                Cofact = new Matriks(this.NBrsEff-1, this.NKolEFF-1);
                for (i1 = 1; i1 < this.NBrsEff; i1++) {
                    j2 = 0;
                    for (j1 = 0; j1 < this.NKolEFF; j1++) {
                        if (j1 != j) {
                            Cofact.Mat[i1-1][j2] = Mat[i1][j1];
                            j2++;
                        }
                    }
                }

                d += Mat[0][j] * s * Cofact.Determinan();
                s *= -1;
            }
            return d;
        }
    }

    public Matriks Inverse() {
        Matriks Inv = new Matriks(this.NBrsEff, this.NKolEFF);
        if ((this.NBrsEff == 1) && (this.NKolEFF == 1)) {
            Inv.Mat[0][0] = 1/this.Determinan();
        } else {
            int i, j, i1, j1, i2, j2;
            int s = 1;
            Matriks Cofact = new Matriks(this.NBrsEff-1, this.NKolEFF-1);
            for (i = 0; i < this.NBrsEff; i++) {
                for (j = 0; j < this.NKolEFF; j++) {
                    i2 = 0;
                    for (i1 = 0; i1 < this.NBrsEff; i1++) {
                        if (i1 != i) {
                            j2 = 0;
                            for (j1 = 0; j1 < this.NKolEFF; j1++) {
                                if (j1 != j) {
                                    Cofact.Mat[i2][j2] = Mat[i1][j1];
                                    j2++;
                                }
                            }
                            i2++;
                        }
                    }
                    if ((i+j)%2 == 1) {
                        s *= -1;
                    }
                    Inv.Mat[j][i] = s*Cofact.Determinan();
                    s = 1;
                }
            }
        }
    return Inv;
    }
}