import NeuralNet.NeuralNetwork;

public class Main {

    public static void main(String[] args){

        NeuralNetwork n = new NeuralNetwork();
        int[] input = {4,4,4,1};
        n.createNet(input);

    }
}
