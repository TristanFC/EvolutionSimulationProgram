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
public class Connection {

    private Neuron neuron;
    private float weight;

    public Connection(Neuron neuron, float weight) {
        this.neuron = neuron;
        this.weight = weight;
    }

    public float getValue() {
        return neuron.getValue() * weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
