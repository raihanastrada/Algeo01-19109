package src;
import java.util.Scanner;



public class Driver {
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

                    if(Mcek.IsUnik()){
                        Mhasil.TulisMatriks();
                        Mhasil.TulisSPLUnik();
                    }
                    else if (!(Mcek.IsUnik())) {
                        if(Mcek.AdaBarisKosong()){
                            Mhasil.TulisMatriks();
                            System.out.println("SOLUSI BANYAK");
                            Mhasil.TulisSPL();
                        }
                        else {
                            if (Mcek.NoSolution()){
                                Mhasil.TulisMatriks();
                                System.out.println("TIDAK ADA SOLUSI");
                            }
                            else {
                                Mhasil.TulisMatriks();
                                Mhasil.TulisSPL();
                            }
                        }

                    }
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


                    if(Mhasil.IsUnik()){
                        Mhasil.TulisMatriks();
                        Mhasil.TulisSPLUnik();
                    }
                    else if (!(Mhasil.IsUnik())) {
                        if(Mhasil.AdaBarisKosong()){
                            Mhasil.TulisMatriks();
                            System.out.println("SOLUSI BANYAK");
                            Mhasil.TulisSPL();
                        }
                        else {
                            if (Mhasil.NoSolution()){
                                Mhasil.TulisMatriks();
                                System.out.println("TIDAK ADA SOLUSI");
                            }
                            else {
                                Mhasil.TulisMatriks();
                                Mhasil.TulisSPL();
                            }
                        }

                    }
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
                    M1.Crammer(M2);
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
                    System.out.println("# Metode ekspansi kofaktor         #");
                    if (PilihanMet == 1) {
                        System.out.print("Masukkan jumlah N (baris & kolom) : ");
                        int N = input.nextInt();
                        Matriks M1 = new Matriks(N,N);
                        M1.InputMatriks();
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        System.out.println(M1.Determinan());
                    }
                    else {
                        Matriks M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        System.out.println(M1.Determinan());
                    }
                }
                else {
                    System.out.println("# Metode reduksi baris             #");
                    if (PilihanMet == 1) {
                        System.out.print("Masukkan jumlah N (baris & kolom) : ");
                        int N = input.nextInt();
                        Matriks M1 = new Matriks(N,N);
                        M1.InputMatriks();
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        System.out.println(M1.Determinan2());
                    }
                    else {
                        Matriks M1 = new Matriks(0,0);
                        M1.InputMatriksFile();
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        System.out.println(M1.Determinan2());
                    }
                }
            }
            else if (PilihanMenu == 3) { // Matriks
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah N (baris & kolom) : ");
                    int N = input.nextInt();
                    Matriks M1 = new Matriks(N,N);
                    M1.InputMatriks();
                    System.out.println("Invers dari matriks tersebut adalah : ");
                    System.out.println(M1.Determinan());
                    M1.Inverse().TulisMatriks();
                }
                else {
                    Matriks M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                    System.out.println("Invers dari matriks tersebut adalah : ");
                    M1.Inverse().TulisMatriks();
                }
            }
            else if (PilihanMenu == 4) {
                System.out.println("Interpolasi Polinom");
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah N: ");
                    int N = input.nextInt();
                    Interpolasi Px = new Interpolasi(N);
                    Px.InputPolinom();
                    Px.Mat.GaussJordan();
                    System.out.print("Masukkan nilai X yang akan ditaksir: ");
                    double X = input.nextDouble();
                    if (Px.Mat.Determinan2() != 0) {
                        System.out.print("Nilai taksiran dari X="+X+" adalah: ");
                        System.out.println(Px.Fungsi(X));
                    }
                    else {
                        System.out.println("Nilai dari X="+X+" tidak dapat ditaksir");
                    }
                }
                else {
                    Matriks M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                    Interpolasi Px = new Interpolasi(M1);
                    Px.Mat.GaussJordan();
                    System.out.print("Masukkan nilai X yang akan ditaksir: ");
                    double X = input.nextDouble();
                    if (Px.Mat.Determinan2() != 0) {
                        System.out.print("Nilai taksiran dari X="+X+" adalah: ");
                        System.out.println(Px.Fungsi(X));
                    }
                    else {
                        System.out.println("Nilai dari X="+X+" tidak dapat ditaksir");
                    }
                }
            }
            else if (PilihanMenu == 5) {
                System.out.println("Regresi linier berganda");
                if (PilihanMet == 1) {
                    System.out.print("Masukkan jumlah baris : "); int NB = input.nextInt();
                    System.out.print("Masukkan jumlah kolom : "); int NK = input.nextInt();
                    Matriks M1 = new Matriks(NB,NK); M1.InputMatriks();
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
                    R.Augmented.GaussJordan();
                    R.InputTaksiran();
                    if (R.Augmented.Determinan2() != 0) {
                        System.out.print("Nilai taksiran dari input adalah: ");
                        System.out.println(R.Fungsi());
                    }
                    else {
                        System.out.println("Nilai dari input tidak dapat ditaksir");
                    }
                }
                else {
                    Matriks M1 = new Matriks(0,0);
                    M1.InputMatriksFile();
                    int NB = M1.NBrsEff;
                    int NK = M1.NKolEFF;
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
                    R.Augmented.GaussJordan();
                    R.InputTaksiran();
                    if (R.Augmented.Determinan2() != 0) {
                        System.out.print("Nilai taksiran dari input adalah: ");
                        System.out.println(R.Fungsi());
                    }
                    else {
                        System.out.println("Nilai dari input tidak dapat ditaksir");
                    }
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