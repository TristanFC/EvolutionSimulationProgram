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
public interface AceptionFunction {

    public static AccMin MinAcception = new AccMin();

    public boolean accept(float input);

    public void setAceptionValue(float value);
}
