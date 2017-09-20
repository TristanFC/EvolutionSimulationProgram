/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

/**
 *
 * @author Christian Oden
 */
public class InputNeuron extends Neuron {

    private float value;

    @Override
    public float getValue() {
        return value;

    }

    public void setValue(float value) {
        this.value = value;
    }
}
