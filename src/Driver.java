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

    public static void main(String[] args) {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while (!exit) {
            MenuAwal();
            System.out.print("Masukkan pilihan: ");
            int PilihanMenu = input.nextInt();
            while (PilihanMenu > 6 || PilihanMenu < 1) {
                System.out.println("Input yang anda masukkan tidak valid");
                PilihanMenu = input.nextInt();
            }
            if (PilihanMenu == 1 || PilihanMenu == 2 || PilihanMenu == 3) {
                SubMenu();
                int PilihanSub = input.nextInt();
                while (PilihanSub > 4 || PilihanMenu < 1) {
                    System.out.println("Input yang anda masukkan tidak valid");
                    PilihanSub = input.nextInt();
                }
                if (PilihanSub == 1) {
                    // M1.Gauss(M2);
                }
                else if (PilihanSub == 2) {
                    System.out.println("Gauss-Jordan");
                }
                else if (PilihanSub == 3) {
                    // M1.Inverse();
                }
                else if (PilihanSub == 4) {
                    // M1.Crammer(M2);
                }                
            }
            else if (PilihanMenu == 4) {
                System.out.println("Interpolasi Polinom");
            }
            else if (PilihanMenu == 5) {
                System.out.println("Regresi linier berganda");
            }
            else if (PilihanMenu == 6) {
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