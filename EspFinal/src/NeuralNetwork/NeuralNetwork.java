/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetwork;

import NeuralNetwork.ActivationFunctions.ActivationFunction;
import java.util.ArrayList;
import java.util.List;
import NeuralNetwork.AceptionFunctions.AceptionFunction;

/**
 *
 * @author Christian Oden
 */
public class NeuralNetwork {

    private List<InputNeuron> inputNeurons = new ArrayList<>();

    private List<WorkingNeuron> hiddenNeurons = new ArrayList<>();

    private List<WorkingNeuron> outputNeurons = new ArrayList<>();

    public InputNeuron CreateNewInput() {
        InputNeuron in = new InputNeuron();
        inputNeurons.add(in);
        return in;
    }

    public void createHiddenNeurons(int size) {
        for (int i = 0; i < size; i++) {
            hiddenNeurons.add(new WorkingNeuron());

        }

    }

    public WorkingNeuron CreateNewOutput() {
        WorkingNeuron on = new WorkingNeuron();
        outputNeurons.add(on);
        return on;
    }

    public void CreateFullMesh() {
        if (hiddenNeurons.isEmpty()) {

            for (WorkingNeuron wn : outputNeurons) {
                for (InputNeuron in : inputNeurons) {
                    wn.addConnection(new Connection(in, 0));

                }
            }
        } else {

            for (WorkingNeuron wn : outputNeurons) {
                for (WorkingNeuron hn : hiddenNeurons) {
                    wn.addConnection(new Connection(hn, 0));

                }

            }
            for (WorkingNeuron hn : hiddenNeurons) {
                for (InputNeuron in : inputNeurons) {
                    hn.addConnection(new Connection(in, 0));

                }
            }
        }
    }

    public void CreateFullMesh(float... weights) {
        if (hiddenNeurons.size() == 0) {
            if (weights.length != inputNeurons.size() * outputNeurons.size()) {
                throw new RuntimeException();
            }
            int index = 0;
            for (WorkingNeuron wn : outputNeurons) {
                for (InputNeuron in : inputNeurons) {
                    wn.addConnection(new Connection(in, weights[index++]));

                }
            }

        } else {
            if (weights.length != inputNeurons.size() * hiddenNeurons.size() + hiddenNeurons.size() * outputNeurons.size()) {
                throw new RuntimeException();
            }
            int index = 0;
            for (WorkingNeuron hn : hiddenNeurons) {
                for (InputNeuron in : inputNeurons) {
                    hn.addConnection(new Connection(in, weights[index++]));

                }
            }
            for (WorkingNeuron on : outputNeurons) {
                for (WorkingNeuron hn : hiddenNeurons) {
                    on.addConnection(new Connection(hn, weights[index++]));

                }
            }

        }
    }

    public void changeActivationfunctionInHidden(int place, ActivationFunction acf) {
        hiddenNeurons.get(place).setActivationfunction(acf);
    }

    public void changeAceptionFunctionInHidden(int place, AceptionFunction acf) {
        hiddenNeurons.get(place).setAceptionFunction(acf);
    }

    public WorkingNeuron getHiddenNeuron(int place) {
        return hiddenNeurons.get(place);
    }

    public void randomizeAllWieghts() {
        for (WorkingNeuron nw : outputNeurons) {
            nw.randomizeWeights(5f);
        }
        for (WorkingNeuron nw : hiddenNeurons) {
            nw.randomizeWeights(5f);
        }
    }

    public ArrayList<Connection> getAllConnections() {
        ArrayList<Connection> c = new ArrayList<Connection>();

        for (WorkingNeuron nw : hiddenNeurons) {
            for (Connection lc : nw.getConnections()) {
                c.add(lc);
            }
        }
        for (WorkingNeuron nw : outputNeurons) {
            for (Connection lc : nw.getConnections()) {
                c.add(lc);
            }
        }
        return c;
    }

    public NeuralNetwork copyNeuralNetwork() {
        NeuralNetwork nw = new NeuralNetwork();
        for (InputNeuron in : inputNeurons) {
            InputNeuron lin = nw.CreateNewInput();
            lin.setName(in.getName());
        }

        nw.createHiddenNeurons(hiddenNeurons.size());

        for (WorkingNeuron in : outputNeurons) {
            WorkingNeuron lin = nw.CreateNewOutput();
            lin.setName(in.getName());
        }

        nw.CreateFullMesh();
        for (int i = 0; i < getAllConnections().size(); i++) {

            nw.getAllConnections().get(i).setWeight(getAllConnections().get(i).getWeight());
        }
        MutateWeights((int)(Math.random()*5), 0.01f);
        
        return nw;
        
    }
public List<WorkingNeuron> getAllOutputNeurons(){
return outputNeurons;
}
public List<InputNeuron> getAllInputNeurons(){
return inputNeurons;
}
public void MutateWeights(int howmuch,float rate){
for(int i = 0;i<howmuch;i++){
outputNeurons.get((int)(outputNeurons.size()*Math.random())).randomMutation(rate);
hiddenNeurons.get((int)(hiddenNeurons.size()*Math.random())).randomMutation(rate);
}

}

}
