import NeuralNet.LayerHandler;
import NeuralNet.NeuralNetwork;
import NeuralNet.NetManager;
import NeuralNet.Layer;

import Math.Matrix;

public class Main {

    public static void main(String[] args){
        int[] input = {4,4,4,1};
        NeuralNetwork n = new NeuralNetwork(input);
        NetManager.setNet(n);



    }
}
