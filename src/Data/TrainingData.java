package Data;

public class TrainingData {

    private double[] inputs;
    private double[] expectedResults;

    public TrainingData(double[] inp, double[] expRes){
        inputs = inp;
        expectedResults = expRes;
    }

    public double[] getInputs() {
        return inputs;
    }

    public double[] getExpectedResults() {
        return expectedResults;
    }
}
