package src;


import java.util.Scanner;

public class Driver {
    static void MenuAwal() {
        System.out.println("|========== PROGRAM MATRIKS ==========#");
        System.out.println("# MENU                                #");
        System.out.println("# 1. Sistem Persamaan Linear          #");
        System.out.println("# 2. Determinan                       #");
        System.out.println("# 3. Matriks balikan                  #");
        System.out.println("# 4. Interpolasi Polinom              #");
        System.out.println("# 5. Regresi linier berganda          #");
        System.out.println("# 6. Keluar                           #");
    }

    static void SubMenu() {
        System.out.println("|========== PROGRAM MATRIKS ==========#");
        System.out.println("# Pilihan Metode                      #");
        System.out.println("# 1. Metode eliminasi Gauss           #");
        System.out.println("# 2. Metode eliminasi Gauss-Jordan    #");
        System.out.println("# 3. Metode Matriks balikan           #");
        System.out.println("# 4. Kaidah Cramer                    #");
    }

    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while (!exit) {
            MenuAwal();
            System.out.print("Masukkan pilihan: ");
            int Pilihan = input.nextInt();
            while (Pilihan > 6 || Pilihan < 1) {
                System.out.println("Input yang anda masukkan tidak valid");
                Pilihan = input.nextInt();
            }
            if (Pilihan == 1 || Pilihan == 2 || Pilihan == 3) {
                SubMenu();
                System.out.print("Masukkan jumlah baris: ");
                int NB = input.nextInt();
                System.out.print("Masukkan jumlah kolom: ");
                int NK = input.nextInt();
                Matriks M1 = new Matriks(NB, NK);
                M1.MakeMatriks(NB, NK);
                M1.InputMatriks();
                M1.TulisMatriks();
            }
            else if (Pilihan == 4) {
                System.out.print("Masukkan jumlah baris: ");
                int NB = input.nextInt();
                System.out.print("Masukkan jumlah kolom: ");
                int NK = input.nextInt();
                Matriks M1 = new Matriks(NB, NK);
                M1.MakeMatriks(NB, NK);
                M1.InputMatriks();

                M1.Gauss1();
                System.out.println(" ");
            }
            else if (Pilihan == 5) {
                System.out.println("Regresi linier berganda");
            }
            else if (Pilihan == 6) {
                System.out.println("Apakah anda akan keluar dari Program Matriks? (Y/N)");
                int Konfirmasi = input.nextInt();
                while (Konfirmasi != 0 || Konfirmasi != 0 || Konfirmasi != -1 || Konfirmasi != -1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    System.out.println("Apakah anda akan keluar dari Program Matriks? (Y/N)");
                    Konfirmasi = input.next().charAt(0);
                }
                if (Konfirmasi == 'Y' || Konfirmasi == 'y') {
                    System.out.println("Keluar dari program matriks...");
                    System.out.println("#========== PROGRAM MATRIKS ==========#");
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