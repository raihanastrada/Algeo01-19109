package src;
import java.util.Scanner;
import java.io.FileWriter;

public class Driver2 {
    static void MenuAwal() {
        System.out.println("#========== PROGRAM MATRIKS ==========#");
        System.out.println("# MENU                                #");
        System.out.println("# 1. Sistem Persamaan Linear          #");
        System.out.println("# 2. Determinan                       #");
        System.out.println("# 3. Matriks balikan                  #");
        System.out.println("# 4. Interpolasi Polinom              #");
        System.out.println("# 5. Regresi linier berganda          #");
        System.out.println("# 6. Keluar                           #");
    }

    static void SubMenu() {
        System.out.println("#========== PROGRAM MATRIKS ==========#");
        System.out.println("# Pilihan Metode                      #");
        System.out.println("# 1. Metode eliminasi Gauss           #");
        System.out.println("# 2. Metode eliminasi Gauss-Jordan    #");
        System.out.println("# 3. Metode Matriks balikan           #");
        System.out.println("# 4. Kaidah Cramer                    #");
    }

    static void InputMethod() {
        System.out.println("Pilih Metode Input Data             ");
        System.out.println("1. Masukan dari keyboard            ");
        System.out.println("2. Masukan dari file                ");
    }

    static String[] JoinStrArr(String[] S1, String[] S2) {
        int n = S1.length + S2.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            if (i < S1.length) {
                res[i] = S1[i];
            } else {
                res[i] = S2[i-S1.length];
            }
        }
        return res;
    }

    static void Output(String[] output) {
        Scanner input = new Scanner(System.in);
        System.out.println("Pilihan Metode Output Hasil         ");
        System.out.println("1. Cetak di layar                   ");
        System.out.println("2. Simpan ke file                   ");
        System.out.println("Pilihan Metode:                     ");
        int PilihanOut = input.nextInt();
        while (PilihanOut > 2 || PilihanOut < 1) {
            System.out.println("Input yang Anda masukkan tidak valid.");
            PilihanOut = input.nextInt();
        }
        if (PilihanOut == 1) {
            for (int i = 0; i < output.length; i++) {
                System.out.println(output[i]);
            }
        } else {
            System.out.print("Simpan ke file: ");
            input = new Scanner(System.in);
            String filename = input.nextLine();
            try {
                FileWriter writer = new FileWriter(filename);
                for (int i = 0; i < output.length; i++) {
                    if (output[i] != "") {
                        writer.write(output[i] + "\n");
                    }
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("File tidak ada.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while (!exit) {
            InputMethod();
            int PilihanMet = input.nextInt();
            while (PilihanMet > 2 || PilihanMet < 1) {
                System.out.println("Input yang anda masukkan tidak valid");
                PilihanMet = input.nextInt();
            }
            MenuAwal();
            System.out.print("Masukkan pilihan: ");
            int PilihanMenu = input.nextInt();
            while (PilihanMenu > 6 || PilihanMenu < 1) {
                System.out.println("Input yang anda masukkan tidak valid");
                PilihanMenu = input.nextInt();
            }
            if (PilihanMenu == 1) {
                SubMenu();
                System.out.print("Masukkan pilihan: ");
                int PilihanSub = input.nextInt();
                while (PilihanSub > 4 || PilihanMenu < 1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    PilihanSub = input.nextInt();
                }
                if (PilihanSub == 1) { //Metode Gauss
                    Matriks M1;
                    if (PilihanMet == 1) {
                        int NB,NK;
                        System.out.print("Masukkan jumlah baris : "); NB = input.nextInt();
                        System.out.print("Masukkan jumlah kolom : "); NK = input.nextInt();
                        M1 = new Matriks(NB,NK); M1.InputMatriks();
                    } else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                    }
                    Matriks Mhasil = M1.Gauss1();
                    Matriks Mcek = M1.GaussJordan();

                    String[] output = new String[1];

                    if(Mcek.IsUnik()){
                        String[] part1 = Mhasil.TulisMatriks();
                        String[] part2 = Mhasil.TulisSPLUnik();
                        output = JoinStrArr(part1, part2);
                    }
                    else if (!(Mcek.IsUnik())) {
                        if(Mcek.AdaBarisKosong()){
                            String[] part1 = Mhasil.TulisMatriks();
                            String[] part2 = new String[1];
                            part2[0] = "SOLUSI BANYAK";
                            String[] part3 = Mhasil.TulisSPL();
                            output = JoinStrArr(JoinStrArr(part1, part2), part3);
                        }
                        else {
                            if (Mcek.NoSolution()){
                                String[] part1 = Mhasil.TulisMatriks();
                                String[] part2 = new String[1];
                                part2[0] = "TIDAK ADA SOLUSI";
                                output = JoinStrArr(part1, part2);
                            }
                            else {
                                String[] part1 = Mhasil.TulisMatriks();
                                String[] part2 = Mhasil.TulisSPL();
                                output = JoinStrArr(part1, part2);
                            }
                        }

                    }
                    Output(output);
                }
                else if (PilihanSub == 2) { //Metode GaussJordan
                    Matriks M1;
                    if (PilihanMet == 1) {
                        int NB,NK;
                        System.out.print("Masukkan jumlah baris : "); NB = input.nextInt();
                        System.out.print("Masukkan jumlah kolom : "); NK = input.nextInt();
                        M1 = new Matriks(NB,NK); M1.InputMatriks();
                    } else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                    }
                    Matriks Mhasil = M1.GaussJordan();

                    String[] output = new String[1];

                    if(Mhasil.IsUnik()){
                        String[] part1 = Mhasil.TulisMatriks();
                        String[] part2 = Mhasil.TulisSPLUnik();
                        output = JoinStrArr(part1, part2);
                    }
                    else if (!(Mhasil.IsUnik())) {
                        if(Mhasil.AdaBarisKosong()){
                            String[] part1 = Mhasil.TulisMatriks();
                            String[] part2 = new String[1];
                            part2[0] = "SOLUSI BANYAK";
                            String[] part3 = Mhasil.TulisSPL();
                            output = JoinStrArr(JoinStrArr(part1, part2), part3);
                        }
                        else {
                            if (Mhasil.NoSolution()){
                                String[] part1 = Mhasil.TulisMatriks();
                                String[] part2 = new String[1];
                                part2[0] = "TIDAK ADA SOLUSI";
                                output = JoinStrArr(part1, part2);
                            }
                            else {
                                String[] part1 = Mhasil.TulisMatriks();
                                String[] part2 = Mhasil.TulisSPL();
                                output = JoinStrArr(part1, part2);
                            }
                        }

                    }
                    Output(output);
                }
                else if (PilihanSub == 3) { // metode inverse; x = A^-1 * b
                    Matriks M1, b;
                    if (PilihanMet == 1) {
                        System.out.println("Masukkan jumlah persamaan: ");
                        int NB = input.nextInt();
                        System.out.println("Masukkan jumlah variabel: ");
                        int NK = input.nextInt();
                        M1 = new Matriks(NB, NK);
                        System.out.println("Masukkan matriks variabel: ");
                        M1.InputMatriks();
                        b = new Matriks(NB,1);
                        System.out.println("Masukkan nilai hasil masing persamaan: ");
                        b.InputMatriks();
                    } else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                        b = M1.Deaugment();
                    }
                    M1.SPLInverse(b);
                    Output(M1.SPLInverse(b));
                }
                else if (PilihanSub == 4) {
                    Matriks M1, M2;
                    if (PilihanMet == 1) {
                        int NB,NK;
                        System.out.print("Masukkan jumlah baris : ");
                        NB = input.nextInt();
                        System.out.print("Masukkan jumlah kolom : ");
                        NK = input.nextInt();
                        M1 = new Matriks(NB,NK);
                        M1.InputMatriks();
                        M2 = new Matriks(NB,1);
                        M2.InputMatriks();
                    } else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                        M2 = M1.Deaugment();
                    }
                    String[] output = new String[1];
                    if (M1.Determinan() == 0) {
                        output[0] = "SPL tidak memiliki solusi";
                    } else {
                        output = M1.Crammer(M2);
                    }
                    Output(output);
                }                
            }
            else if (PilihanMenu == 2) {
                System.out.println("# Pilihan Metode                      #");
                System.out.println("# 1. Metode ekspansi kofaktor         #");
                System.out.println("# 2. Metode reduksi baris             #");
                System.out.print("Masukkan pilihan: ");
                int PilihanDet = input.nextInt();
                while (PilihanDet > 2 || PilihanDet < 1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    PilihanDet = input.nextInt();
                }
                if (PilihanDet == 1) {
                    Matriks M1;
                    System.out.println("# Metode ekspansi kofaktor         #");
                    if (PilihanMet == 1) {
                        System.out.print("Masukkan jumlah N (baris & kolom) : ");
                        int N = input.nextInt();
                        M1 = new Matriks(N,N);
                        M1.InputMatriks();
                    }
                    else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                    }
                    String[] output = new String[1];
                    output[0] = "Determinan dari permasalahan tersebut adalah : " + M1.Determinan();
                    Output(output);
                }
                else {
                    System.out.println("# Metode reduksi baris             #");
                    Matriks M1;
                    if (PilihanMet == 1) {
                        System.out.print("Masukkan jumlah N (baris & kolom) : ");
                        int N = input.nextInt();
                        M1 = new Matriks(N,N);
                        M1.InputMatriks();
                    }
                    else {
                        M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                    }
                    String[] output = new String[1];
                    output[0] = "Determinan dari permasalahan tersebut adalah : " + M1.Determinan2();
                    Output(output);
                }
            }
            else if (PilihanMenu == 3) { // Matriks
                Matriks M1;
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah N (baris & kolom) : ");
                    int N = input.nextInt();
                    M1 = new Matriks(N,N);
                    M1.InputMatriks();
                }
                else {
                    M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                }
                if (M1.Determinan() == 0) {
                    String[] output = new String[1];
                    output[0] = "Matriks dengan determinan 0 tidak memiliki invers";
                    Output(output);
                } else {
                    String[] part1 = new String[1];
                    part1[0] = "Invers dari matriks tersebut adalah : " + M1.Determinan();
                    String[] part2 = M1.Inverse().TulisMatriks();
                    Output(JoinStrArr(part1, part2));
                }
            }
            else if (PilihanMenu == 4) {
                System.out.println("Interpolasi Polinom");
                SubMenu();
                System.out.print("Masukkan pilihan: ");
                int PilihanSub = input.nextInt();
                while (PilihanSub > 4 || PilihanMenu < 1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    PilihanSub = input.nextInt();
                }
                Interpolasi Px;
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah N: ");
                    int N = input.nextInt();
                    Px = new Interpolasi(N);
                    Px.InputPolinom();
                }
                else {
                    Matriks M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                    Px = new Interpolasi(M1);
                    Px.toPolinom();
                }
                if (PilihanSub == 1 || PilihanSub == 2) {
                    Px.Mat.GaussJordan();
                    System.out.print("Masukkan nilai X yang akan ditaksir: ");
                    double X = input.nextDouble();
                    String[] output = new String[1];
                    if (Px.Mat.Determinan2() != 0) {
                        output[0] = "Nilai taksiran dari X="+X+" adalah: " + Px.Fungsi(X);
                    }
                    else {
                        output[0] = "Nilai dari X="+X+" tidak dapat ditaksir";
                    }
                    Output(output);
                } else if (PilihanSub == 3) {
                    Matriks b = Px.Mat.Deaugment();
                    Matriks M1 = Px.Mat.Copy();
                    System.out.print("Masukkan nilai X yang akan ditaksir: ");
                    double X = input.nextDouble();
                    String[] output = new String[1];
                    if (M1.Determinan() != 0) {
                        Px.Mat = (M1.Identitas()).Augment((M1.Inverse()).KaliMatriks(b));
                        output[0] = "Nilai taksiran dari X="+X+" adalah: " + Px.Fungsi(X);
                    } else {
                        output[0] = "Nilai dari X="+X+" tidak dapat ditaksir";
                    }
                    Output(output);
                } else {
                    Matriks b = Px.Mat.Deaugment();
                    Matriks M1 = Px.Mat.Copy();
                    System.out.print("Masukkan nilai X yang akan ditaksir: ");
                    double X = input.nextDouble();
                    String[] output = new String[1];
                    if (M1.Determinan() != 0) {
                        Matriks Mtemp;
                        Matriks Arrhsl = new Matriks(M1.NBrsEff, 1);
                        double D = M1.Determinan();
                        for (int j = 0; j < M1.NKolEFF; j++) {
                            Mtemp = M1.TukerKolom(b, j);
                            Arrhsl.Mat[j][0] = Mtemp.Determinan()/D;
                        }
                        Px.Mat = (M1.Identitas()).Augment(Arrhsl);
                        output[0] = "Nilai taksiran dari X="+X+" adalah: " + Px.Fungsi(X);
                    } else {
                        output[0] = "Nilai dari X="+X+" tidak dapat ditaksir";
                    }
                    Output(output);
                }
                
            }
            else if (PilihanMenu == 5) {
                System.out.println("Regresi linier berganda");
                SubMenu();
                System.out.print("Masukkan pilihan: ");
                int PilihanSub = input.nextInt();
                while (PilihanSub > 4 || PilihanMenu < 1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    PilihanSub = input.nextInt();
                }
                Matriks M1;
                int NB, NK;
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah baris : "); NB = input.nextInt();
                    System.out.print("Masukkan jumlah kolom : "); NK = input.nextInt();
                    M1 = new Matriks(NB,NK); M1.InputMatriks();
                } else {
                    M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                    NB = M1.NBrsEff;
                    NK = M1.NKolEFF;
                }
                Matriks X = new Matriks(NB,NK);
                Matriks Y = new Matriks(NB,1);
                for (int i=0; i<NB; i++) {
                    for (int j=0; j<NK; j++) {
                        if (j==0) {
                            X.Mat[i][j] = 1;    
                        }
                        else if (j==NK-1) {
                            Y.Mat[i][0] = M1.Mat[i][j];
                            X.Mat[i][j] = M1.Mat[i][j-1];
                        }
                        else {
                            X.Mat[i][j] = M1.Mat[i][j-1];
                        }
                    }
                }
                Regresi R = new Regresi(X,Y);
                R.NormEq();
                R.InputTaksiran();
                if (PilihanSub == 1 || PilihanSub == 2) {
                    R.Augmented.GaussJordan();
                    String[] output = new String[1];
                    if (R.Augmented.Determinan2() != 0) {
                        output[0] = "Nilai taksiran dari input adalah: " + R.Fungsi();
                    }
                    else {
                        output[0] = "Nilai dari input tidak dapat ditaksir";
                    }
                    Output(output);
                } else if (PilihanSub == 3) {
                    Matriks b = R.Augmented.Deaugment();
                    Matriks M2 = R.Augmented.Copy();
                    String[] output = new String[1];
                    if (M2.Determinan() != 0) {
                        R.Augmented = (M2.Identitas()).Augment((M2.Inverse()).KaliMatriks(b));
                        output[0] = "Nilai taksiran dari input adalah: " + R.Fungsi();
                    } else {
                        output[0] = "Nilai dari input tidak dapat ditaksir";
                    }
                    Output(output);
                } else {
                    Matriks b = R.Augmented.Deaugment();
                    Matriks M2 = R.Augmented.Copy();
                    String[] output = new String[1];
                    if (M2.Determinan() != 0) {
                        Matriks Mtemp;
                        Matriks Arrhsl = new Matriks(M2.NBrsEff, 1);
                        double D = M2.Determinan();
                        for (int j = 0; j < M2.NKolEFF; j++) {
                            Mtemp = M2.TukerKolom(b, j);
                            Arrhsl.Mat[j][0] = Mtemp.Determinan()/D;
                        }
                        R.Augmented = (M2.Identitas()).Augment(Arrhsl);
                        output[0] = "Nilai taksiran dari input adalah: " + R.Fungsi();
                    } else {
                        output[0] = "Nilai dari input tidak dapat ditaksir";
                    }
                    Output(output);
                }
            }
            else if (PilihanMenu == 6) {
                System.out.println("Apakah anda akan keluar dari Program Matriks? (Y/N)");
                char Konfirmasi = input.next().charAt(0);
                while (Konfirmasi != 'Y' &&  Konfirmasi != 'y' && Konfirmasi != 'N' && Konfirmasi != 'n') {
                    System.out.println("Input yang anda masukkan tidak valid");
                    System.out.println("Apakah anda akan keluar dari Program Matriks? (Y/N)");
                    char tempChar = input.next().charAt(0);
                    Konfirmasi = tempChar; 
                }
                if (Konfirmasi == 'Y' ||  Konfirmasi == 'y') {
                    System.out.println("Keluar dari program matriks...");
                    input.close();
                    exit = true;
                }
                else {
                    System.out.println("Anda akan kembali ke Menu Awal...");
                }        
            }
            
        }
    }
}