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
public interface ActivationFunction {

    public static Boolean ActivationBoolean = new Boolean();
    public static Identity ActivationIdentity = new Identity();
    public static Sigmoid ActivationSigmoid = new Sigmoid();
    public static HyperbolicTangent ActivationHyperbolicTangent = new HyperbolicTangent();

    public float activation(float input);

}
