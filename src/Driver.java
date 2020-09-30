package src;
import java.util.*;


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
                    int NB,NK;
                    System.out.print("Masukkan jumlah baris : "); NB = input.nextInt();
                    System.out.print("Masukkan jumlah kolom : "); NK = input.nextInt();
                    Matriks M1 = new Matriks(NB,NK); M1.InputMatriks();
                    Matriks Mhasil = M1.Gauss1();
                    double Dethasil= Mhasil.Determinan();

                    if (Dethasil != 0.0) {
                        Mhasil.TulisMatriks();
                    }
                    else {
                        Mhasil.TulisMatriks();
                    }
                }
                else if (PilihanSub == 2) { //Metode Gauss
                    int NB,NK;
                    System.out.print("Masukkan jumlah baris : "); NB = input.nextInt();
                    System.out.print("Masukkan jumlah kolom : "); NK = input.nextInt();
                    Matriks M1 = new Matriks(NB,NK); M1.InputMatriks();
                    Matriks Mhasil = M1.GaussJordan();
                    double Dethasil= Mhasil.Determinan();

                    if (Dethasil != 0.0) {
                        Mhasil.TulisMatriks();
                    }
                    else {
                        Mhasil.TulisMatriks();
                    }
                }
                else if (PilihanSub == 3) {
                    System.out.println("Masukkan jumlah persamaan: ");
                    int NB = input.nextInt();
                    System.out.println("Masukkan jumlah variabel: ");
                    int NK = input.nextInt();
                    Matriks M1 = new Matriks(NB, NK);
                    System.out.println("Masukkan matriks variabel: ");
                    M1.InputMatriks();
                    Matriks b = new Matriks(NB,1);
                    System.out.println("Masukkan nilai hasil masing persamaan: ");
                    b.InputMatriks();
                    Matriks What = M1.TukerKolom(b);
                    if (M1.Determinan() != 0) {
                        Matriks Mhasil = M1.Inverse().KaliMatriks(b);
                        Mhasil.TulisSPLUnik();
                    } else {
                        if (M1.TukerKolom(b).Determinan() != 0) {
                            System.out.println("SPL tidak memiliki solusi");
                        } else {
                            int varBebas = 1;
                            Matriks Basis = M1.JadiBasis();
                            while (Basis.Determinan() == 0) {
                                Basis = Basis.JadiBasis();
                                varBebas++;
                            }
                            System.out.println("Terdapat " + varBebas + " variabel bebas.");
                        }
                    }
                }
                else if (PilihanSub == 4) {
                    int NB,NK;
                    System.out.print("Masukkan jumlah baris : ");
                    NB = input.nextInt();
                    System.out.print("Masukkan jumlah kolom : ");
                    NK = input.nextInt();
                    Matriks M1 = new Matriks(NB,NK);
                    M1.InputMatriks();
                    Matriks M2 = new Matriks(NB,1);
                    M2.InputMatriks();
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
                        // M1 = Scan Matriks dari File.txt
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        // System.out.println(M1.Determinan());
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
                        // M1 = Scan Matriks dari File.txt
                        System.out.print("Determinan dari permasalahan tersebut adalah : ");
                        // System.out.println(M1.Determinan2());
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
                    M1.Inverse();
                    M1.TulisMatriks();
                }
                else {
                    // M1 = Scan Matriks dari File.txt
                    System.out.println("Invers dari matriks tersebut adalah : ");
                    // M1.Inverse();
                    // M1.TulisMatriks();
                }
            }
            else if (PilihanMenu == 4) {
                System.out.println("Interpolasi Polinom");
                if (PilihanMet == 1) {
                    
                }
                else {
                    
                }
            }
            else if (PilihanMenu == 5) {
                System.out.println("Regresi linier berganda");
                if (PilihanMet == 1) {
                    
                }
                else {
                    
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