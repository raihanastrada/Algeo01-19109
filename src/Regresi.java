package src;

public class Regresi {
    // Atribut
    Matriks X;
    Matriks Y;
    Matriks Augmented;
    
    public Regresi(Matriks Mx, Matriks My) {
        MakeRegresi(Mx,My);
    }

    public void MakeRegresi(Matriks X, Matriks Y) {
        this.X = X;
        this.Y = Y;
    }

    private double SigmaX(int j) {
        double Hsl = 0;
        for (int k=0; k < X.NBrsEff; k++) {
            Hsl += X.Mat[k][j];
        }
        return Hsl;
    }
    private double SigmaY() {
        double Hsl = 0;
        for (int i=0; i < Y.NBrsEff; i++) {
            Hsl += Y.Mat[i][0];
        }
        return Hsl;
    }
    public Matriks NormEq() {
        Matriks Augmented = new Matriks(X.NBrsEff+1,X.NKolEFF+1);
        for (int i=0; i<Augmented.NBrsEff; i++) {
            for (int j=0; j<Augmented.NKolEFF; j++) {
                if (i == 0 && j == 0) {
                    Augmented.Mat[i][j] = Augmented.NBrsEff-1;
                }
                else if (j == Augmented.NKolEFF-1) {
                    if (i==0) {
                        Augmented.Mat[i][j] = SigmaY();
                    }
                    else {
                        Augmented.Mat[i][j] = SigmaY()*SigmaX(i);
                    }
                }
                else {
                    if (i == 0) {
                        Augmented.Mat[i][j] = SigmaX(j);    
                    }
                    else if (j == 0) {
                        Augmented.Mat[i][j] = SigmaX(i);
                    }
                    else {
                        Augmented.Mat[i][j] = SigmaX(i)*SigmaX(j);
                    }
                }
            }
        }
        this.Augmented = Augmented;
        return Augmented;
    }

    public double Fungsi(double X) {
        /* I.S. : SPL memiliki solusi unik*/
        double Hsl = 0.0;
        double[] FunctionCons = new double[Augmented.NBrsEff]; 
        
        for (int i=0; i<Augmented.NBrsEff; i++) {
            FunctionCons[i] = Augmented.Mat[i][Augmented.NKolEFF-1];
        }

        for (int i=0; i<Augmented.NBrsEff; i++) {
            if (i == 0) {
                Hsl += FunctionCons[i];
            }
            else {
                Hsl += X*FunctionCons[i];
            }
        }
        return Hsl;
    }
}