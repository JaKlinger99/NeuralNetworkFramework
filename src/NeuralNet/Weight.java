package NeuralNet;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;

public class Weight {

    public Matrix matrix;
    private Layer predecessor;
    private Layer successor;

    public Weight(Matrix mat, Layer predecessor, Layer successor){
        this.matrix = mat;
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




}
