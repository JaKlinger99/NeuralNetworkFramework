package Math;

public class Matrix {

    private double[][] mat;

    public Matrix(double[][] m){
        mat = m;
    }

    public Matrix(double[] v){
        mat = new double[1][v.length];
        for (int i = 0; i < v.length; i++) {
            mat[0][i] = v[i];
        }
        printMat();
    }

    public Matrix(int rows, int columns){
        mat = new double[rows][columns];
    }

    public void setValueAt(int row, int column, double value){
        mat[row][column] = value;
    }

    public double getValueAt(int row, int column){
        return mat[row][column];
    }

    public int getRows(){
        return mat.length;
    }

    public int getColumns(){
        return mat[0].length;
    }

    public void scaleMat(double scale){
        for (double[] v: mat) {
            for (double d: v) {
                d = d*scale;
            }
        }
    }

    public void randomize(){
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                mat[i][j] = Math.random();
            }
        }

    }

    public void printMat(){
        for (double[] a: mat) {
            for (double d:a) {
                System.out.print(d + "  ");
            }
            System.out.println("");
        }
    }






}
