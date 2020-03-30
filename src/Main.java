import NeuralNet.LayerHandler;
import NeuralNet.NeuralNetwork;
import NeuralNet.Layer;

public class Main {

    public static void main(String[] args){

        NeuralNetwork n = new NeuralNetwork();
        int[] input = {4,4,4,1};
        n.createNet(input);



        Layer l = null;
        try {
            l = LayerHandler.getLayerAtIndex(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < l.getMatrix().getColumns(); i++) {
            l.getMatrix().setValueAt(0,i, i);
        }
        LayerHandler.calculatePrediction();

    }
}
