/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

import NeuralNetwork.ActivationFunctions.*;
import java.util.ArrayList;
import java.util.List;
import NeuralNetwork.AceptionFunctions.AceptionFunction;

/**
 *
 * @author Christian Oden
 */
public class WorkingNeuron extends Neuron {

    private List<Connection> connections = new ArrayList<>();

    private ActivationFunction activationFunction = ActivationFunction.ActivationSigmoid;
    private AceptionFunction acf = null;

    @Override
    public float getValue() {

        float sum = 0;
        for (Connection c : connections) {
            sum += c.getValue();
        }
        if (acf == null) {
            return activationFunction.activation(sum);
        } else {
            if (acf.accept(sum)) {
                return activationFunction.activation(sum);

            } else {
                return 0;
            }
        }

    }

    public void addConnection(Connection c) {
        connections.add(c);

    }

    public void setActivationfunction(ActivationFunction acf) {
        activationFunction = acf;
    }

    public ActivationFunction getactivationFunction() {
        return activationFunction;
    }

    public void setAceptionFunction(AceptionFunction acf) {
        this.acf = acf;
    }

    public AceptionFunction getAceptionFunction() {
        return acf;
    }

    public void randomizeWeights(float faktor) {
        for (Connection c : connections) {
            c.setWeight((float) (Math.random() * 2 * faktor - faktor));

        }
    }

    public void randomMutation(float rate) {
        Connection c = connections.get((int) (connections.size() * Math.random()));
        c.setWeight((float) (Math.random() * 2 * rate - rate));
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
