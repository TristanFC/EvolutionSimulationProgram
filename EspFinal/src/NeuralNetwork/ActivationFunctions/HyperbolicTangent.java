/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork.ActivationFunctions;

/**
 *
 * @author Christian Oden
 */
public class HyperbolicTangent implements ActivationFunction {

    @Override
    public float activation(float input) {
        float epx = (float) Math.pow(Math.E, input);
        float enx = (float) Math.pow(Math.E, -input);

        return (epx - enx) / (epx + enx);
    }

}
