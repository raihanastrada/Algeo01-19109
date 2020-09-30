//package src;
import java.util.*;

import src.Matriks;

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
                    double Dethasil= Mhasil.Determinan();

                    if (Dethasil != 0.0) {
                        Mhasil.TulisMatriks();
                    }
                    else {
                        Mhasil.TulisMatriks();
                    }
                }
                else if (PilihanSub == 2) { //Metode Gauss
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
                    double Dethasil= Mhasil.Determinan();

                    if (Dethasil != 0.0) {
                        Mhasil.TulisMatriks();
                    }
                    else {
                        Mhasil.TulisMatriks();
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