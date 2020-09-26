package src;

import java.util.*;

public class Matriks {
    // Atribut
    private int NBrsEff, NKolEFF;
    int[][] Mat = new int[100][100];
    
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

}