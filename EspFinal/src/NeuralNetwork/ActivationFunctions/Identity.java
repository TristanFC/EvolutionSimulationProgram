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
public class Identity implements ActivationFunction {

    @Override
    public float activation(float input) {
        return input;
    }

}
