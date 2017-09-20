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
public class Boolean implements ActivationFunction {

    @Override
    public float activation(float input) {
        if (input < 0) {
            return 0;
        } else {
            return 1;
        }
    }

}
