package Math;
import Logging.EventType;
import Logging.Logger;



public abstract class Operations {


    // Matrix skalieren
    public static Matrix scaleMat(double scale, Matrix matrix){
        matrix.scaleMat(scale);
        return matrix;
    }


    // Matrizen Multiplikation
    public static Matrix multiplyMat(Matrix mat1, Matrix mat2) throws Exception {

        //Überprüfen ob Multiplikation möglich
        if(mat1.getColumns() != mat2.getRows()){
            Logger.getNotification(EventType.CRITTICAL_ERROR, "Matrizen-Multiplikation mit unkompatiblen Matrizen");
            throw new Exception("Fehler @Operations.multiplyMat()");

        }else{
            Matrix result = new Matrix(mat1.getRows(),mat2.getColumns());
            for (int i = 0; i < mat1.getRows(); i++) {
                for (int j = 0; j < mat2.getColumns(); j++) {
                    result.setValueAt(i,j,0);

                    for (int k = 0; k < mat1.getColumns(); k++) {
                        result.setValueAt(i,j, result.getValueAt(i,j)+ mat1.getValueAt(i,k)* mat2.getValueAt(k,j));
                    }

                }
            }
            return result;
        }

    }


}
