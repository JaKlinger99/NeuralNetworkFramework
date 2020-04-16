package NeuralNet.Weights;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;
import Math.Operations;
import NeuralNet.Layer.Layer;

/**
 * Klasse, die alle Gewichte zwischen zwei Layers in Form einer Matrix repräsentiert
 * @author Jakob 
 */
public class Weight {

    private Matrix matrix;
    private Matrix gradientMatrix;
    private Layer predecessor;
    private Layer successor;

    public Weight(Layer predecessor, Layer successor){
        this.matrix = new Matrix(predecessor.getNumberOfNodes(), successor.getNumberOfNodes());
        this.gradientMatrix = new Matrix(predecessor.getNumberOfNodes(), successor.getNumberOfNodes());
        matrix.randomize();

        this.predecessor = predecessor;
        this.successor = successor;

        Logger.getNotification(EventType.INFO, "Gewichte zwischen Layer: " + predecessor.getIndex() + "und: " + successor.getIndex() + "erzeugt");
    }


    public Layer getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Layer predecessor) {
        this.predecessor = predecessor;
    }

    public Layer getSuccessor() {
        return successor;
    }

    public void setSuccessor(Layer successor) {
        this.successor = successor;
    }

    public Matrix getMatrix() {
        return matrix;
    }


    public void addValueToGradientMatrix(int row, int column, double value){
        gradientMatrix.setValueAt(row, column, value);
    }

    /**
     * Funkktion, die nach vollständiger Berechnung der Gradienten Matrix fuer diese Ebene, die Gewichtsmatrix durch addition mit der Gradienten Matrix aktualisiert
     * und dann die Gradienten Matrix zuruecksetzt 
     */
    public void applyGradientMatrix(){
        try {
            matrix = Operations.addMat(matrix, gradientMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gradientMatrix = new Matrix(predecessor.getNumberOfNodes(), successor.getNumberOfNodes());
    }

    public Matrix getGradientMatrix() {
        return gradientMatrix;
    }
}
