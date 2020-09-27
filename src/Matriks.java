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
        Matriks M1 = new Matriks(this.NBrsEff, M2.NKolEFF);
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
        /* Metode Eekspansi Cofactor */
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

    public double Determinan2() {
        /* Metode Reduksi Baris */
        if ((this.NBrsEff == 1) && (this.NKolEFF == 1)) {
            return (double) Mat[0][0];
        }
        else {
            double det = 1; // Assign variabel awal determinan
            double temp = 0; // Untuk menyimpan variabel pada swap
            int swap = 0; // Jumlah terjadinya swap
            int i = 0;
            while (i < this.NBrsEff-1) {
                if (Mat[i][i] == 0) {
                    /* Swap jika elemen baris awal & kolom awal = 0 */
                    int tempIdx = i+1;
                    for (int j=0;j<this.NKolEFF;j++) {
                        temp = Mat[i][j]; 
                        Mat[i][j] = Mat[tempIdx][j];
                        Mat[tempIdx][j] = temp;
                    }
                    swap = swap + 1;
                }
                // Baris i dijadikan 1
                det *= Mat[i][i];
                for (int j=this.NKolEFF-1;j>=0;j--) { 
                    Mat[i][j] /= Mat[i][i];
                }
                // Membuat matriks bawah menjadi 0
                for (int tempIdx1 = i+1;tempIdx1<this.NBrsEff;tempIdx1++) { 
                    for (int j=this.NKolEFF-1;j>=0;j--) {
                        Mat[tempIdx1][j] -= Mat[tempIdx1][i]*Mat[i][j];
                    }
                }
                i += 1;
            }
            for (i=0;i<this.NBrsEff;i++){
                det *= Mat[i][i];
            }
            if (swap%2 == 0) {
                det *= 1;  
            }
            else {
                det *= (-1);
            }
            return det;
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
    public void Crammer(Matriks MHsl) {
        // Copy Matriks
        Matriks tempMat = new Matriks(this.NBrsEff, this.NKolEFF);
        for (int j=0;j<this.NKolEFF;j++) {
            for (int i=0;i<this.NBrsEff;i++) {
                tempMat.Mat[i][j] = Mat[i][j];
            }
        }

        double D = tempMat.Determinan2();
        double[] arrHsl = new double[this.NKolEFF];
        for (int j=0;j<this.NKolEFF;j++) {
            for (int i=0;i<this.NBrsEff;i++) {
                tempMat.Mat[i][j] = Mat[i][j];
                Mat[i][j] = MHsl.Mat[i][0];
            }
            TulisMatriks();
            arrHsl[j] = (this.Determinan())/D; // Menghitung D/D[n]
            for (int k=0;k<this.NBrsEff;k++){
                Mat[k][j] = tempMat.Mat[k][j]; // Mengembalikan kolom
            }
        }
        for (int i=0;i<this.NKolEFF;i++){
            System.out.print("solusi dari X"+(i+1)+" adalah = ");
            System.out.print(arrHsl[i]);
            if (i != this.NKolEFF-1){
                System.out.print(", ");
            }
        }
    }
    public Matriks Gauss(Matriks Mkonstan) {
        Matriks MAugmented = new Matriks(this.NBrsEff , this.NKolEFF + 1);
        int N = this.NBrsEff;
        for (int scan = 0; scan < N; scan++) {
            /* Finding pivot row */
            int maxrow = scan;
            for (int i = scan+1; i < N; i++) {
                if (Mat[i][scan] > Mat[maxrow][scan]) {
                    maxrow = i;
                }
            }
            /* Swap row with maximum element */
            double[] temp = Mat[scan];
            Mat[scan] = Mat[maxrow];
            Mat[maxrow] = temp;

            /* Swapping constant matrix */
            double[] t = Mkonstan.Mat[scan];
            Mkonstan.Mat[scan] = Mkonstan.Mat[maxrow];
            Mkonstan.Mat[maxrow] = t;

            /* Simplifying row using factor */
            for (int i = scan+1; i < N; i++){
                double factor = Mat[i][scan] / Mat[scan][scan];
                Mkonstan.Mat[i][0] -= factor * Mkonstan.Mat[scan][0];
                for (int j = scan; j<N; j++){
                    Mat[i][j] -= factor * Mat[scan][j];
                }
            }
        }
        /* Assign Maugmented */
        for (int i = 0; i < MAugmented.NBrsEff ; i++) {
            for (int j = 0; j < MAugmented.NKolEFF - 1; j++) {
                MAugmented.Mat[i][j] = Mat[i][j];
            }
        }
        for (int i = 0; i < MAugmented.NBrsEff ; i++) {

            for (int j = MAugmented.NKolEFF - 1; j <MAugmented.NKolEFF ; j++) {
                MAugmented.Mat[i][j] = Mkonstan.Mat[i][0];
            }
        }
        return MAugmented;
    }

    public Matriks Gauss1(){
        Matriks MAugmented = new Matriks(this.NBrsEff , this.NKolEFF );
        int N = this.NBrsEff;
        for (int scan = 0; scan < N; scan++) {
            /* Finding pivot row */
            int maxrow = scan;
            for (int i = scan+1; i < N; i++) {
                if (Mat[i][scan] > Mat[maxrow][scan]) {
                    maxrow = i;
                }
            }
            /* Swap row with maximum element */
            double[] temp = Mat[scan];
            Mat[scan] = Mat[maxrow];
            Mat[maxrow] = temp;

            /* Simplifying row using factor */
            for (int i = scan+1; i < N; i++){
                double factor = Mat[i][scan] / Mat[scan][scan];
                for (int j = scan; j < this.NKolEFF; j++){
                    Mat[i][j] -= factor * Mat[scan][j];
                }
            }
        }
        for (int i = 0; i < MAugmented.NBrsEff ; i++) {
            for (int j = 0; j < MAugmented.NKolEFF ; j++) {
                MAugmented.Mat[i][j] = Mat[i][j];
            }
        }
        return MAugmented;

    }
}