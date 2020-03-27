package NeuralNet;
import Logging.EventType;
import Logging.Logger;
import Math.Matrix;

public class Weight {

    public Matrix matrix;
    private int predecessorIndex;
    private int successorIndex;

    public Weight(Matrix mat, int predecessor, int successor){
        this.matrix = mat;
        this.predecessorIndex = predecessor;
        this.successorIndex = successor;
        Logger.getNotification(EventType.INFO, "Gewichte zwischen Layer: " + predecessorIndex + "und: " + successorIndex + "erzeugt");
    }

    public int getPredecessorIndex() {
        return predecessorIndex;
    }

    public void setPredecessorIndex(int predecessorIndex) {
        this.predecessorIndex = predecessorIndex;
    }

    public int getSuccessorIndex() {
        return successorIndex;
    }

    public void setSuccessorIndex(int successorIndex) {
        this.successorIndex = successorIndex;
    }


}
