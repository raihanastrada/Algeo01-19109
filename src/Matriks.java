package src;

import java.util.*;

import java.io.*;

public class Matriks {
    // Atribut
    private int NBrsEff, NKolEFF;
    double[][] Mat = new double[100][100];

    public Matriks(int NB, int NK) {
        MakeMatriks(NB, NK);
    }

    // Method
    public void MakeMatriks(int NB, int NK) {
        this.NBrsEff = NB;
        this.NKolEFF = NK;
    }

    public void InputMatriks() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                System.out.print("Masukkan input matriks [" + i + "][" + j + "]: ");
                Mat[i][j] = input.nextDouble();
            }
        }
    }

    public void InputMatriksFile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Import dari file: ");
        String filename = input.nextLine();
        try {
            Scanner s = new Scanner (new File(filename));
            int NB = 0;
            int NK = 0;
            while(s.hasNextLine()) {
                Scanner colReader = new Scanner(s.nextLine());
                while(colReader.hasNextDouble()) {
                    ++NK;
                    colReader.nextDouble();
                }
                ++NB;
            }
            NK /= NB;
            this.MakeMatriks(NB, NK);
            try {
                s = new Scanner(new File(filename));
                for(int i = 0; i < this.NBrsEff; i++) {
                    for(int j = 0; j < this.NKolEFF; j++) {
                        if(s.hasNextDouble()) {
                            Mat[i][j] = s.nextDouble();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("File not found");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public Matriks Deaugment() {
        Matriks b = new Matriks(this.NBrsEff, 1);
        for (int i = 0; i < this.NBrsEff; i++) {
            b.Mat[i][0] = Mat[i][this.NKolEFF-1];
        }
        Matriks M1 = this.Copy();
        this.MakeMatriks(this.NBrsEff, this.NKolEFF-1);
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                Mat[i][j] = M1.Mat[i][j];
            }
        }
        return b;
    }

    public void TulisMatriks() {
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                if (j != this.NKolEFF - 1) {
                    System.out.print(Mat[i][j] + " ");
                } else {
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
                    M1.Mat[i][j] += Mat[i][k] * M2.Mat[k][j];
                }
            }
        }
        return M1;
    }

    public double Determinan() {
        /* Metode Ekspansi Cofactor */
        double d = 0;
        if ((this.NBrsEff == 1) && (this.NKolEFF == 1)) {
            return (double) Mat[0][0];
        } else {
            int j, i1, j1, j2;
            int s = 1;
            Matriks Cofact;
            for (j = 0; j < this.NKolEFF; j++) {
                Cofact = new Matriks(this.NBrsEff - 1, this.NKolEFF - 1);
                for (i1 = 1; i1 < this.NBrsEff; i1++) {
                    j2 = 0;
                    for (j1 = 0; j1 < this.NKolEFF; j1++) {
                        if (j1 != j) {
                            Cofact.Mat[i1 - 1][j2] = Mat[i1][j1];
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
        } else {
            Matriks tempMat = new Matriks(this.NBrsEff, this.NKolEFF);
            for (int j = 0; j < this.NKolEFF; j++) {
                for (int i = 0; i < this.NBrsEff; i++) {
                    tempMat.Mat[i][j] = Mat[i][j];
                }
            }
            double det = 1; // Assign variabel awal determinan
            double temp = 0; // Untuk menyimpan variabel pada swap
            int swap = 0; // Jumlah terjadinya swap
            int i = 0;
            while (i < this.NBrsEff - 1) {
                if (tempMat.Mat[i][i] == 0) {
                    /* Swap jika elemen baris awal & kolom awal = 0 */
                    int tempIdx = i + 1;
                    for (int j = 0; j < this.NKolEFF; j++) {
                        temp = tempMat.Mat[i][j];
                        tempMat.Mat[i][j] = tempMat.Mat[tempIdx][j];
                        tempMat.Mat[tempIdx][j] = temp;
                    }
                    swap = swap + 1;
                }
                // Baris i dijadikan 1
                det *= tempMat.Mat[i][i];
                for (int j = this.NKolEFF - 1; j >= 0; j--) {
                    tempMat.Mat[i][j] /= tempMat.Mat[i][i];
                }
                // Membuat matriks bawah menjadi 0
                for (int tempIdx1 = i + 1; tempIdx1 < this.NBrsEff; tempIdx1++) {
                    for (int j = this.NKolEFF - 1; j >= 0; j--) {
                        tempMat.Mat[tempIdx1][j] -= tempMat.Mat[tempIdx1][i] * tempMat.Mat[i][j];
                    }
                }
                i += 1;
            }
            for (i = 0; i < this.NBrsEff; i++) {
                det *= tempMat.Mat[i][i];
            }
            if (swap % 2 == 0) {
                det *= 1;
            } else {
                det *= (-1);
            }
            return det;
        }

    }

    public Matriks Inverse() {
        Matriks Inv = new Matriks(this.NBrsEff, this.NKolEFF);
        if ((this.NBrsEff == 1) && (this.NKolEFF == 1)) {
            Inv.Mat[0][0] = 1 / this.Determinan();
        } else {
            int i, j, i1, j1, i2, j2;
            int s = 1;
            double det = this.Determinan();
            Matriks Cofact = new Matriks(this.NBrsEff - 1, this.NKolEFF - 1);
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
                    if ((i + j) % 2 == 1) {
                        s *= -1;
                    }
                    Inv.Mat[j][i] = s*Cofact.Determinan()/det;
                    s = 1;
                }
            }
        }
        return Inv;
    }

    public void Crammer(Matriks MHsl) {
        double D = this.Determinan2();
        double[] arrHsl = new double[this.NKolEFF];
        Matriks tempMat = new Matriks(this.NBrsEff,this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) {
            for (int i = 0; i < this.NBrsEff; i++) {
                tempMat.Mat[i][j] = Mat[i][j];
                Mat[i][j] = MHsl.Mat[i][0];
            }
            TulisMatriks();
            arrHsl[j] = (this.Determinan()) / D; // Menghitung D/D[n]
            for (int k = 0; k < this.NBrsEff; k++) {
                Mat[k][j] = tempMat.Mat[k][j]; // Mengembalikan kolom
            }
        }
        for (int i = 0; i < this.NKolEFF; i++) {
            System.out.print("solusi dari X" + (i + 1) + " adalah = ");
            System.out.print(arrHsl[i]);
            if (i != this.NKolEFF - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    public Matriks Gauss(Matriks Mkonstan) {
        Matriks MAugmented = new Matriks(this.NBrsEff, this.NKolEFF + 1);
        int N = this.NBrsEff;
        for (int scan = 0; scan < N; scan++) {
            /* Finding pivot row */
            int maxrow = scan;
            for (int i = scan + 1; i < N; i++) {
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
            for (int i = scan + 1; i < N; i++) {
                double factor = Mat[i][scan] / Mat[scan][scan];
                Mkonstan.Mat[i][0] -= factor * Mkonstan.Mat[scan][0];
                for (int j = scan; j < N; j++) {
                    Mat[i][j] -= factor * Mat[scan][j];
                }
            }
        }
        /* Assign Maugmented */
        for (int i = 0; i < MAugmented.NBrsEff; i++) {
            for (int j = 0; j < MAugmented.NKolEFF - 1; j++) {
                MAugmented.Mat[i][j] = Mat[i][j];
            }
        }
        for (int i = 0; i < MAugmented.NBrsEff; i++) {

            for (int j = MAugmented.NKolEFF - 1; j < MAugmented.NKolEFF; j++) {
                MAugmented.Mat[i][j] = Mkonstan.Mat[i][0];
            }
        }
        return MAugmented;
    }

    public Matriks Gauss1() {
        Matriks MAugmented = new Matriks(this.NBrsEff, this.NKolEFF);
        int N = this.NBrsEff;
        for (int scan = 0; scan < N; scan++) {
            /* Finding pivot row */
            int maxrow = scan;
            for (int i = scan + 1; i < N; i++) {
                if (Mat[i][scan] > Mat[maxrow][scan]) {
                    maxrow = i;
                }
            }
            /* Swap row with maximum element */
            double[] temp = Mat[scan];
            Mat[scan] = Mat[maxrow];
            Mat[maxrow] = temp;

            /* Simplifying row using factor */
            for (int i = scan + 1; i < N; i++) {
                double factor = Mat[i][scan] / Mat[scan][scan];
                for (int j = scan; j < this.NKolEFF; j++) {
                    Mat[i][j] -= factor * Mat[scan][j];
                }
            }
        }
        /* Scanning the diagonal elements and make it to 1 (divide the i-th row by [scan][scan]-th elements) */
        for (int i = 0; i < this.NBrsEff ; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                double divisor;
                if (i == j) {
                    divisor = Mat[i][j];
                    for (int k = j; (k < this.NKolEFF); k++){
                        if (divisor != 0) {
                            Mat[i][k] /= divisor;
                        }
                    }
                }
            }
        }
        /* Swapping row with all 0's as the elements */
        int i,j,k; boolean zero;
        k = 1;
        for (i = 0; i < this.NBrsEff; i++){
            for (j = 0; j < this.NKolEFF; j++){
                if(Mat[i][j] != 0) break;
                if(j == this.NKolEFF - 1) {
                    double [] temp = Mat[i];
                    Mat[i] = Mat[this.NBrsEff - k];
                    Mat[this.NBrsEff - k] = temp;
                }
            }
        }
        /* Assigning value to MAugmented */
        for ( i = 0; i < MAugmented.NBrsEff; i++) {
            for ( j = 0; j < MAugmented.NKolEFF; j++) {
                MAugmented.Mat[i][j] = Mat[i][j];
            }
        }
        return MAugmented;
    }

    public Matriks GaussJordan() {
        Matriks MAugmented = new Matriks(this.NBrsEff, this.NKolEFF);
        int N = this.NBrsEff;
        for (int scan = 0; scan < N; scan++) {
            /* Finding pivot row */
            int minrow = scan;
            for (int i = scan + 1; i < N; i++) {
                if (Mat[i][scan] < Mat[minrow][scan]) {
                    minrow = i;
                }
            }

            /* Swap row with minimum element */
            double[] temp = Mat[scan];
            Mat[scan] = Mat[minrow];
            Mat[minrow] = temp;

            /* Simplifying row using factor */
            for (int i = 0 ; i < N; i++) {
                double factor;
                if (Mat[scan][scan] != 0) {
                    factor = Mat[i][scan] / Mat[scan][scan];
                }
                else {
                    factor = 0;
                }
                if ( i != scan) {
                    for (int j = scan; j < this.NKolEFF; j++) {
                        Mat[i][j] -= factor * Mat[scan][j];
                    }
                }
            }
        }
        /* Scanning the diagonal elements and make it to 1 (divide the i-th row by [scan][scan]-th elements) */
        for (int i = 0; i < this.NBrsEff ; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                double divisor;
                if (i == j) {
                    divisor = Mat[i][j];
                    for (int k = j; (k < this.NKolEFF); k++){
                        if (divisor != 0) {
                            Mat[i][k] /= divisor;
                        }
                    }
                }
            }
        }
        /* Swapping row with all 0's element */
        int i,j,k; boolean zero;
        k = 1;
        for (i = 0; i < this.NBrsEff; i++){
            for (j = 0; j < this.NKolEFF; j++){
                if(Mat[i][j] != 0) break;
                if(j == this.NKolEFF - 1) {
                    double [] temp = Mat[i];
                    Mat[i] = Mat[this.NBrsEff - k];
                    Mat[this.NBrsEff - k] = temp;
                }
            }
        }

        for (i = 0; i < MAugmented.NBrsEff ; i++) {
            for (j = 0; j < MAugmented.NKolEFF; j++) {
                MAugmented.Mat[i][j] = Mat[i][j];
            }
        }
        return MAugmented;
    }

    public void TulisSPLUnik() {
        for (int i = 0; i < this.NBrsEff; i++) {
            System.out.print("Solusi dari X" + (i + 1) + " adalah = ");
            System.out.println(Mat[i][0]);
        }
    }

    public void TulisSPLParametrik(Matriks M1, Matriks b1) {
        double epsilon = 1e-10;
        Matriks A = this.Copy();
        Matriks bvar = b1.VarMask(M1);
        Matriks Mkonst = A.KaliMatriks(b1.UnMask(bvar));
        String outputln = "";
        for (int i = 0; i < A.NBrsEff; i++) {
            outputln = "";
            if ((Mkonst.Mat[i][0] > epsilon )|| (Mkonst.Mat[i][0] < -epsilon)) {
                outputln += (Mkonst.Mat[i][0]);
            }
            for (int j = 0; j < A.NKolEFF; j++) {
                if (bvar.Mat[j][0] != 0) {
                    if (A.Mat[i][j] > epsilon) {
                        if ((Mkonst.Mat[i][0] > epsilon )|| (Mkonst.Mat[i][0] < -epsilon)) {
                            outputln += " + ";
                        }
                        if (A.Mat[i][j] != 1) {
                            outputln += A.Mat[i][j];
                        }
                        outputln += "k" + ((int) bvar.Mat[j][0]);
                    } else if (A.Mat[i][j] < -epsilon) {
                        outputln += " - ";
                        if (A.Mat[i][j] != -1) {
                            outputln += (-1*A.Mat[i][j]);
                        }
                        outputln += "k" + ((int) bvar.Mat[j][0]);
                    }
                }
            }
            if (outputln == "") {
                outputln = "0.0";
            }
            if (!(M1.AmbilKolom(i).IsEmpty())) {
                outputln = "Solusi dari X" + (i + 1) + " adalah = " + outputln;
                System.out.println(outputln);
            }
        }
    }

    public Matriks TukerKolom(Matriks k, int j) {
        Matriks M1 = this.Copy();
        for (int i = 0; i < this.NBrsEff; i++) {
            M1.Mat[i][j] = k.Mat[i][0];
        }
        return M1;
    }

    public Matriks TukerBaris(Matriks b, int i) {
        Matriks M1 = this.Copy();
        for (int j = 0; j < this.NKolEFF; j++) {
            M1.Mat[i][j] = b.Mat[0][j];
        }
        return M1;
    }

    public boolean AdaBasis(int Loc) {
        boolean ada = false;
        boolean adaBrs;
        for (int i = 0; i < this.NBrsEff; i++) {
            adaBrs = true;
            for (int j = 0; j < this.NKolEFF; j++) {
                adaBrs = adaBrs && (((j == Loc) && (Mat[i][j] == 1)) || ((j != Loc) && (Mat[i][j] == 0)));
            }
            ada = ada || adaBrs;
        }
        return ada;
    }

    public Matriks AmbilBaris(int i) {
        Matriks Hasil = new Matriks(1, this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) {
            Hasil.Mat[0][j] = Mat[i][j];
        }
        return Hasil;
    }

    public Matriks AmbilKolom(int j) {
        Matriks Hasil = new Matriks(this.NBrsEff, 1);
        for (int i = 0; i < this.NBrsEff; i++) {
            Hasil.Mat[i][0] = Mat[i][j];
        }
        return Hasil;
    }

    public Matriks Square() {
        if (this.NBrsEff != this.NKolEFF) {
            int N = this.NKolEFF;
            if (this.NBrsEff > this.NKolEFF) {
                N = this.NBrsEff;
            }
            Matriks M1 = new Matriks(N, N);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((j < this.NKolEFF) && (i < this.NBrsEff)) {
                        M1.Mat[i][j] = this.Mat[i][j];
                    } else {
                        M1.Mat[i][j] = 0;
                    }
                }
            }
            return M1;
        }
        return this.Copy();
    }

    public boolean IsEmpty() {
        double epsilon = 1e-10;
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                if ((Mat[i][j] > epsilon) || (Mat[i][j] < -epsilon)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean AdaBarisKosong() {
        for (int i = 0; i < this.NBrsEff; i++) {
            if (this.AmbilBaris(i).IsEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean AdaKolomKosong() {
        for (int j = 0; j < this.NKolEFF; j++) {
            if (this.AmbilKolom(j).IsEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Matriks Copy(){
        Matriks Hasil = new Matriks(this.NBrsEff, this.NKolEFF);
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                Hasil.Mat[i][j] = Mat[i][j];
            }
        }
        return Hasil;
    }

    public Matriks JadiBasis() {
        Matriks M1 = this.Copy();
        int fill;
        if (M1.AdaKolomKosong()) {
            fill = 0;
            while ((!this.AmbilKolom(fill).IsEmpty()) && (fill < this.NKolEFF)) {
                fill++;
            }
        } else {
            fill = this.NBrsEff-1;
            while (this.AdaBasis(fill)) {
                fill--;
            }
        }
        int i = NBrsEff-1;
        Matriks Mtemp;
        Matriks BarisIden = new Matriks(1, this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) {
            if (j == fill) {
                BarisIden.Mat[0][j] = 1;
            } else {
                BarisIden.Mat[0][j] = 0;
            }
        }
        while (i >= 0) {
            Mtemp = M1.TukerBaris(BarisIden, i);
            if (M1.AmbilBaris(i).IsEmpty()) {
                return Mtemp;
            } else if ((Mtemp.Determinan() != 0) || (i == 0)) {
                return Mtemp;
            }
            i--;
        }
        return M1;
    }

    public Matriks ExtSolusi(int NBrs, double fill) {
        Matriks b1 = new Matriks(NBrs, 1);
        for (int i = 0; i < NBrs; i++) {
            if (i < this.NBrsEff) {
                b1.Mat[i][0] = Mat[i][0];
            } else {
                b1.Mat[i][0] = fill;
            }
        }
        return b1;
    }

    public Matriks VarMask(Matriks M) { // mengubah konstanta dalam b menjadi 0 dan variabel menjadi k
        Matriks b = this.Copy();
        int k = 1;
        for (int i = 0; i < this.NBrsEff; i++) {
            if (M.AmbilBaris(i).IsEmpty()) {
                b.Mat[i][0] = k;
                k++;
            } else {
                b.Mat[i][0] = 0.0;
            }
        }
        return b;
    }

    public Matriks UnMask(Matriks bvar) {
        Matriks b = this.Copy();
        for (int i = 0; i < this.NBrsEff; i++) {
            if (bvar.Mat[i][0] != 0) {
                b.Mat[i][0] = 0;
            }
        }
        return b;
    }

    public Matriks ConjMatriks(Matriks M) {
        Matriks M1 = this.Copy();
        boolean beda;
        for (int i = 0; i < this.NBrsEff; i++) {
            beda = false;
            for (int j = 0; j < this.NKolEFF; j++) {
                if (M1.Mat[i][j] != M.Mat[i][j]) {
                    beda = true;
                }
            } if (beda) {
                for (int j = 0; j < this.NKolEFF; j++) {
                    M1.Mat[i][j] = 0;
                }
            }
        }
        return M1;
    }

    public void SPLInverse(Matriks b) {
        Matriks M1 = this.Square();
        Matriks b1 = b.ExtSolusi(M1.NBrsEff, 0.0);
        if (M1.Determinan() != 0) {
            Matriks Mhasil = M1.Inverse().KaliMatriks(b);
            Mhasil.TulisSPLUnik();
        } else {
            boolean valid = true;
            if (M1.AdaBarisKosong()) {
                for (int i = 0; i < M1.NBrsEff; i++) {
                    if (M1.AmbilBaris(i).IsEmpty()) {
                        valid = valid && (b1.Mat[i][0] == 0);
                    }
                }
            }
            boolean nonZero = false;
            for (int j = 0; j < M1.NKolEFF; j++) {
                if (M1.TukerKolom(b1, j).Determinan() != 0) {
                    nonZero = true;
                }
            }
            if (nonZero || !valid) {
                System.out.println("SPL tidak memiliki solusi");
            } else {
                int varBebas = 1;
                Matriks Basis = M1.JadiBasis();
                while ((Basis.Determinan() == 0) && varBebas <= M1.NBrsEff) {
                    Basis = Basis.JadiBasis();
                    varBebas++;
                }
                Matriks A = Basis.Inverse();
                Matriks MTrue = Basis.ConjMatriks(M1);
                A.TulisSPLParametrik(MTrue, b1);
                System.out.println("Dengan " + varBebas + " variabel bebas.");
            }
        }
    }
}
