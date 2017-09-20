/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork.AceptionFunctions;

/**
 *
 * @author Christian Oden
 */
public class AccMin implements AceptionFunction {

    private float min = 0;

    @Override
    public boolean accept(float input) {
        if (input > min) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void setAceptionValue(float value) {
        min = value;
    }

}
