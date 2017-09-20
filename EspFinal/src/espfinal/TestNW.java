/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal;

import NeuralNetwork.*;
import NeuralNetwork.AceptionFunctions.*;

/**
 *
 * @author Christian Oden
 */
public class TestNW {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();

        InputNeuron i1 = nn.CreateNewInput();
        InputNeuron i2 = nn.CreateNewInput();
        InputNeuron i3 = nn.CreateNewInput();
        InputNeuron i4 = nn.CreateNewInput();
        nn.createHiddenNeurons(3);
        WorkingNeuron o1 = nn.CreateNewOutput();

        nn.CreateFullMesh(
                10, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                10, 0, 0
        );

        i1.setValue(1);
        i2.setValue(2);
        i3.setValue(3);
        i4.setValue(4);

        System.out.println(o1.getValue());
    }

}
