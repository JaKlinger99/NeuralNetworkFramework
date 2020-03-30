package NeuralNet;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;

public class Weight {

    private Matrix matrix;
    private Layer predecessor;
    private Layer successor;

    public Weight(Layer predecessor, Layer successor){
        this.matrix = new Matrix(predecessor.getNumberOfNodes(), successor.getNumberOfNodes());
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
}
