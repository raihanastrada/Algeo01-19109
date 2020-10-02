package src;

import java.util.*;

import java.io.*;

public class Matriks {
    // Atribut
    public int NBrsEff, NKolEFF;
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
                System.out.println("File tidak ditemukan.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("File tidak ditemukan.");
            e.printStackTrace();
        }
    }

    public Matriks Augment(Matriks b) {
        Matriks M1 = new Matriks(this.NBrsEff, this.NKolEFF+1);
        for (int i = 0; i < M1.NBrsEff; i++) {
            for (int j = 0; j < M1.NKolEFF; j++) {
                if (j < this.NKolEFF) {
                    M1.Mat[i][j] = this.Mat[i][j];
                } else {
                    M1.Mat[i][j] = b.Mat[i][0];
                }
            }
        }
        return M1;
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

    public String[] TulisMatriks() {
        String[] output = new String[this.NBrsEff];
        String outputln;
        for (int i = 0; i < this.NBrsEff; i++) {
            outputln = "";
            for (int j = 0; j < this.NKolEFF; j++) {
                if (j != this.NKolEFF - 1) {
                    outputln += Mat[i][j] + " ";
                } else {
                    outputln += Mat[i][j];
                }
            }
            output[i] = outputln;
        }
        return output;
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

    public String[] Crammer(Matriks MHsl) {
        double D = this.Determinan2();
        double[] arrHsl = new double[this.NKolEFF];
        Matriks tempMat = new Matriks(this.NBrsEff,this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) {
            for (int i = 0; i < this.NBrsEff; i++) {
                tempMat.Mat[i][j] = Mat[i][j];
                Mat[i][j] = MHsl.Mat[i][0];
            }
            // TulisMatriks();
            arrHsl[j] = (this.Determinan()) / D; // Menghitung D/D[n]
            for (int k = 0; k < this.NBrsEff; k++) {
                Mat[k][j] = tempMat.Mat[k][j]; // Mengembalikan kolom
            }
        }
        String[] output = new String[this.NKolEFF];
        String outputln;
        for (int i = 0; i < this.NKolEFF; i++) {
            outputln = "Solusi dari X" + (i + 1) + " adalah = " + arrHsl[i];
            output[i] = outputln;
        }
        return output;
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
            for (int i = scan+1 ; i < N; i++) {
                double factor;
                if (Mat[scan][scan] != 0) {
                    factor = Mat[i][scan] / Mat[scan][scan];
                }
                else {
                    factor = 0;
                }

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
        /* Swapping row with all 0's element */
        int i,j,k; boolean zero;
        k = 1;
        for (i = 0; i <= this.NBrsEff-k; i++){
            for (j = 0; j < this.NKolEFF; j++){
                if(Mat[i][j] != 0) break;
                if(j == this.NKolEFF - 1) {
                    double [] temp = Mat[i];
                    Mat[i] = Mat[this.NBrsEff - k];
                    Mat[this.NBrsEff - k] = temp;
                    k++;
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
        for (i = 0; i <= this.NBrsEff-k; i++){
            for (j = 0; j < this.NKolEFF; j++){
                if(Mat[i][j] != 0) break;
                if(j == this.NKolEFF - 1) {
                    double [] temp = Mat[i];
                    Mat[i] = Mat[this.NBrsEff - k];
                    Mat[this.NBrsEff - k] = temp;
                    k++;
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

    public String[] TulisSPLUnik() {
        String[] output = new String[this.NBrsEff];
        String outputln = "";
        for (int i = 0; i < this.NBrsEff; i++) {
            outputln = "Solusi dari X" + (i + 1) + " adalah = " + Mat[i][this.NKolEFF-1];
            output[i] = outputln;
        }
        return output;
    }

    public String[] TulisSPLInvParametrik(Matriks M1, Matriks b1, int nvar) { // memroses SPL yang butuh solusi parametrik
        double epsilon = 1e-10; // define epsilon untuk toleransi angka kecil
        Matriks A = this.Copy(); // biar tidak ganggu matriks this
        Matriks bvar = b1.VarMask(M1); // mencari tahu kolom mana saja berbentuk variabel/parameter
        Matriks Mkonst = A.KaliMatriks(b1.UnMask(bvar)); // x = A^-1 * b1 untuk mencari konstanta yang berada di masing solusi
        String[] output = new String[nvar];
        String outputln = "";
        for (int i = 0; i < nvar; i++) { 
            outputln = "";
            if ((Mkonst.Mat[i][0] > epsilon )|| (Mkonst.Mat[i][0] < -epsilon)) { // kalau konstanta bukan 0
                outputln += (Mkonst.Mat[i][0]); // cetak konstanta
            }
            for (int j = 0; j < A.NKolEFF; j++) {
                if (bvar.Mat[j][0] != 0) { // kalau mask variabel bukan 0
                    if (A.Mat[i][j] > epsilon) { // kalau koefisien variabel positif
                        if ((Mkonst.Mat[i][0] > epsilon )|| (Mkonst.Mat[i][0] < -epsilon)) { // kalau tadi mencetak konstanta
                            outputln += " + "; // cetak tanda "+ <variabel>"
                        }
                        if (A.Mat[i][j] != 1) { // kalau koefisien bukan 1, cetak koefnya
                            outputln += A.Mat[i][j];
                        }
                        outputln += "k" + ((int) bvar.Mat[j][0]); // cetak variabel k keberapa
                    } else if (A.Mat[i][j] < -epsilon) { // kalau koefisien variabel negatif
                        outputln += " - "; // cetak tanda "- <variabel>"
                        if (A.Mat[i][j] != -1) { // kalau koefisien bukan 1, cetak koefnya
                            outputln += (-1*A.Mat[i][j]);
                        }
                        outputln += "k" + ((int) bvar.Mat[j][0]); // cetak variabel k keberapa
                    }
                }
            }
            if (outputln == "") {
                outputln = "0.0"; // kalau masih kosong berarti hanya konstanta 0 saja
            }
            outputln = "Solusi dari X" + (i + 1) + " adalah = " + outputln; // rangkai string output line ke-i
            output[i] = outputln;
        }
        return output;
    }

    public Matriks TukerKolom(Matriks k, int j) { // menukar kolom ke-j matriks dengan matriks kolom k
        Matriks M1 = this.Copy();
        for (int i = 0; i < this.NBrsEff; i++) {
            M1.Mat[i][j] = k.Mat[i][0];
        }
        return M1;
    }

    public Matriks TukerBaris(Matriks b, int i) { // menukar baris ke-i matriks dengan matriks baris b
        Matriks M1 = this.Copy();
        for (int j = 0; j < this.NKolEFF; j++) {
            M1.Mat[i][j] = b.Mat[0][j];
        }
        return M1;
    }

    public boolean AdaBasis(int Loc) { // periksa apakah ada baris basis dengan elemen 1 di kolom ke-Loc
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

    public Matriks AmbilBaris(int i) { // salin baris ke-i dalam matriks
        Matriks Hasil = new Matriks(1, this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) {
            Hasil.Mat[0][j] = Mat[i][j];
        }
        return Hasil;
    }

    public Matriks AmbilKolom(int j) { // salin kolom ke-j dalam matriks
        Matriks Hasil = new Matriks(this.NBrsEff, 1);
        for (int i = 0; i < this.NBrsEff; i++) {
            Hasil.Mat[i][0] = Mat[i][j];
        }
        return Hasil;
    }

    public Matriks Square() { // extend matriks dengan baris/kolom berisi 0 semua
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

    public boolean IsEmpty() { // periksa apakah suatu matriks berisi 0 semua
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

    public boolean AdaBarisKosong() { // periksa apakah salah satu baris berisi 0 semua
        for (int i = 0; i < this.NBrsEff; i++) {
            if (this.AmbilBaris(i).IsEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean AdaKolomKosong() { // periksa apakah salah satu kolom berisi 0 semua
        for (int j = 0; j < this.NKolEFF; j++) {
            if (this.AmbilKolom(j).IsEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Matriks Copy(){ // salin matriks
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
        if (M1.AdaKolomKosong()) { // kalau ada kolom kosong, prioritasin ngisi basis kolom tsb
            fill = 0;
            while ((!this.AmbilKolom(fill).IsEmpty()) && (fill < this.NKolEFF)) { // cari kolom yang kosong
                fill++;
            }
        } else { // kalau tidak ada kolom kosong, isi dengan basis yang belum muncul
            fill = this.NBrsEff-1;
            while (this.AdaBasis(fill)) { // cari sampai basis ke-fill yang belum ada
                fill--;
            }
        }
        int i = NBrsEff-1;
        Matriks Mtemp;
        Matriks BarisIden = new Matriks(1, this.NKolEFF);
        for (int j = 0; j < this.NKolEFF; j++) { // bikin baris basis seperti [[0,0,0,0,1,0]]
            if (j == fill) {
                BarisIden.Mat[0][j] = 1;
            } else {
                BarisIden.Mat[0][j] = 0;
            }
        }
        while (i >= 0) { // selama i belum mentok
            Mtemp = M1.TukerBaris(BarisIden, i); // coba tuker baris
            if (M1.AmbilBaris(i).IsEmpty()) { // kalau baris kosong, langsung isi
                return Mtemp;
            } else if ((Mtemp.Determinan() != 0) || (i == 0)) { // kalau determinan berubah menjadi tidak 0, atau sudah mentok, langsung isi
                return Mtemp;
            }
            i--;
        }
        return M1;
    }

    public Matriks Identitas() {
        Matriks Iden = new Matriks(this.NBrsEff, this.NKolEFF);
        for (int i = 0; i < this.NBrsEff; i++) {
            for (int j = 0; j < this.NKolEFF; j++) {
                if (i == j) {
                    Iden.Mat[i][j] = 1;
                } else {
                    Iden.Mat[i][j] = 0;
                }
            }
        }
        return Iden;
    }

    public Matriks ExtSolusi(int NBrs) { // agar dimensi matriks hasil dan matriks persamaan bisa dikalikan
        Matriks b1 = new Matriks(NBrs, 1);
        for (int i = 0; i < NBrs; i++) {
            if (i < this.NBrsEff) { // selama masih terdefine, isi sesuai matriks hasil
                b1.Mat[i][0] = Mat[i][0];
            } else { // kalau tidak, isi dengan 0
                b1.Mat[i][0] = 0.0;
            }
        }
        return b1;
    }

    public Matriks VarMask(Matriks M) { // mengubah konstanta dalam b menjadi 0 dan variabel menjadi k untuk mengetahui variabel keberapa
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

    public Matriks UnMask(Matriks bvar) { // mengubah matriks hasil yang tadinya di VarMask dalam 0 dan k menjadi matriks hasil asli dan 0
        Matriks b = this.Copy();
        for (int i = 0; i < this.NBrsEff; i++) {
            if (bvar.Mat[i][0] != 0) {
                b.Mat[i][0] = 0;
            }
        }
        return b;
    }

    public Matriks ConjMatriks(Matriks M) { // kalau barisnya sama, tidak diubah, kalau beda, jadikan baris berelemen 0 semua
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

    public String[] SPLInverse(Matriks b) {
        Matriks M1 = this.Square(); // agar kalkulasi rapih
        if (M1.Determinan() != 0) { // kalau aman dan determinan tidak 0
            Matriks Mhasil = M1.Inverse().KaliMatriks(b);
            String[] output = Mhasil.TulisSPLUnik();
            return output;
        } else {
            Matriks b1 = b.ExtSolusi(M1.NBrsEff); // agar bisa mengali A^-1 * b
            boolean valid = true;
            if (M1.AdaBarisKosong()) { // kalau ada baris kosong
                for (int i = 0; i < M1.NBrsEff; i++) {
                    if (M1.AmbilBaris(i).IsEmpty()) {
                        valid = valid && (b1.Mat[i][0] == 0); // periksa apakah hasil persamaan baris tersebut 0
                    }
                }
            }
            boolean nonZero = false;
            for (int j = 0; j < M1.NKolEFF; j++) { // karena determinan 0
                if (M1.TukerKolom(b1, j).Determinan() != 0) { // periksa apakah ada kolom yang bisa ditukar dengan hasil shg determinan persamaan tidak 0, a.k.a. tidak linear
                    nonZero = true;
                }
            }
            if (nonZero || !valid) { // jika salah satu terpenuhi
                String[] output = new String[1];
                output[0] = "SPL tidak memiliki solusi"; // matriks tidak ada solusi
                return output;
            } else {
                int varBebas = 0; // jika determinan rata 0 (dalam kata lain solusi berbentuk parametrik)
                Matriks Basis = M1.JadiBasis(); // tuker satu baris dengan baris basis
                while ((Basis.Determinan() == 0) && varBebas <= M1.NBrsEff) { // hingga determinan != 0
                    Basis = Basis.JadiBasis();
                    varBebas++;
                }
                Matriks A = Basis.Inverse();
                Matriks MTrue = Basis.ConjMatriks(M1); // matriks yang jadi basis di-"AND" dengan matriks square
                String[] output = A.TulisSPLInvParametrik(MTrue, b1, this.NKolEFF);
                return output;
            }
        }
    }

    public boolean IsUnik(){
        Matriks Munik = new Matriks(this.NBrsEff, this.NBrsEff);
        for (int i = 0; i < this.NKolEFF-1; i++) {
            for (int j = 0 ; j < this.NKolEFF-1; j++) {
                Munik.Mat[i][j] = Mat[i][j];
            }
        }
        if (Munik.Determinan() == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean NoSolution() {
        for (int j = 0; j < NKolEFF-1; j++){
            if (Mat[NBrsEff-1][NKolEFF-1] != 0){
                if(Mat[NBrsEff-1][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public String[] TulisSPL() {
        String[] output = new String[this.NBrsEff];
        String outputln;
        for (int i = 0; i < this.NBrsEff; i++) {
            outputln = "";
            for (int j = 0; j < NKolEFF; j++){
                if (j != NKolEFF -1) {
                    if(Mat[i][j] != 0.0){
                        if (Mat[i][j] < 0) {
                            outputln += " "+Mat[i][j]+"X"+(j+1);
                        }
                        else{
                            if(j == 0) {
                                outputln += Mat[i][j] + "X" + (j + 1);
                            }
                            else {
                                outputln += " + " + Mat[i][j] + "X" + (j + 1);
                            }
                        }
                    }
                }
                else {
                    outputln += " = "+Mat[i][j];
                }
            }
            output[i] = outputln;
        }
        return output;
    }
}