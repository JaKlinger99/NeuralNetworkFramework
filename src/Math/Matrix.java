package Math;

public class Matrix {

    private double[][] mat;

    public Matrix(double[][] m){
        mat = m;
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




}
