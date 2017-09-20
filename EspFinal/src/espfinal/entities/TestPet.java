/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.entities;

import NeuralNetwork.*;
import NeuralNetwork.NeuralNetwork;
import espfinal.EspFinal;
import espfinal.Handler;
import espfinal.data.res.Assets;
import espfinal.tiles.Tile;
import espfinal.util.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Oden
 */
public class TestPet extends Entity implements Runnable {

    private String threadName;
    private Thread thread;
    private boolean running = false;

    private final float costEat = 0.01f;
    private final float costAttack = 0.1f;
    private final float costPerma = 0.1f;
    private final float costWalk = 0.005f;
    private final float costRotate = 0.005f;
    private final float foodDropPerTick = 0;
    private final float rotationFaktor = 0.1f;
    private final float moveSpeed = 4;
    private final float startEnergy = 100;
    private final float gainFood = 5f;

    private final float minEnergyToSurvive = 0;
    private float dna = (float) (Math.random() * 99);

    private float energy = 0;
    private float age;
    private float agesincelastExpand = 0;
    private float wasAttackt;
    private NeuralNetwork nw;

    private InputNeuron bias;
    private InputNeuron foodValueOnPosN;
    private InputNeuron foodValueOnFeelerN;
    private InputNeuron energyN;
    private InputNeuron ageN;
    private InputNeuron dnaDiffN;
    private InputNeuron wasAttacktN;
    private InputNeuron waterOnPosN;
    private InputNeuron waterOnFellerN;

    private WorkingNeuron forward;
    private WorkingNeuron rotate;
    private WorkingNeuron strafe;
    private WorkingNeuron feelerAngle;
    private WorkingNeuron attack;
    private WorkingNeuron eat;

    private String name;
    private boolean isNew = false;
    private float r = 0;
    private float fr = 0;
    private double fx = 0;
    private double fy = 0;
    private Color color;

    public TestPet(Handler handler, float x, float y, int widht, int height, String name, boolean isNew) {

        super(handler, x, y, widht, height);
        this.name = name;
        this.isNew = isNew;
        threadName = name;
        updaterotation(0);
        start();
        if (isNew) {
            energy = startEnergy;

            nw = new NeuralNetwork();
            //create bias
            bias = nw.CreateNewInput();
            bias.setValue(1);
            //create all varible onputs
            foodValueOnPosN = nw.CreateNewInput();
            foodValueOnPosN.setName("FoodValueOnPos");
            foodValueOnFeelerN = nw.CreateNewInput();
            foodValueOnFeelerN.setName("FoodValueOnFeeler");
            energyN = nw.CreateNewInput();
            energyN.setName("Energy");
            ageN = nw.CreateNewInput();
            ageN.setName("Age");
            dnaDiffN = nw.CreateNewInput();
            dnaDiffN.setName("DNADifferrence");
            wasAttacktN = nw.CreateNewInput();
            wasAttacktN.setName("WasAttackt");
            waterOnPosN = nw.CreateNewInput();
            waterOnPosN.setName("WaterOnPos");
            waterOnFellerN = nw.CreateNewInput();
            waterOnFellerN.setName("WaterOnFeeler");

            //create output neurons
            forward = nw.CreateNewOutput();
            forward.setName("Forward");
            rotate = nw.CreateNewOutput();

            rotate.setName("Rotate");
            strafe = nw.CreateNewOutput();
            strafe.setName("Strafe");
            feelerAngle = nw.CreateNewOutput();
            feelerAngle.setName("FeelerAngle");
            attack = nw.CreateNewOutput();
            attack.setName("Attack");
            eat = nw.CreateNewOutput();
            eat.setName("Eat");

            color = getColor();

            //create hidden
            nw.createHiddenNeurons(7);
            //create full mesh brain....
            nw.CreateFullMesh();
            nw.randomizeAllWieghts();
            updateNeuraNetValues();
            //sprintValues();
        }
        if (!isNew) {

        }
    }

    //acsasd
    private float getFoodValueOnPos() {

        int petX = (int) (x / Tile.TileWidht);
        int petY = (int) (y / Tile.TileHeight);

        if (handler.getWorld().getTileInt(petX, petY) == 2) {
            return handler.getWorld().getTileValue(petX, petY);

        }
        return 0;

    }

    private float getFoodValueOnFeeler() {

        int felX = (int) (fx / Tile.TileWidht);
        int felY = (int) (fy / Tile.TileHeight);

        if (handler.getWorld().getTileInt(felX, felY) == 2) {
            return handler.getWorld().getTileValue(felX, felY);

        }
        return 0;

    }

    private float getWaterOnPos() {

        int petX = (int) (x / Tile.TileWidht);
        int petY = (int) (y / Tile.TileHeight);

        if (handler.getWorld().getTileInt(petX, petY) == 0) {
            return 100;

        }
        return 0;

    }

    private float getWaterOnFeeler() {

        int felX = (int) (fx / Tile.TileWidht);
        int felY = (int) (fy / Tile.TileHeight);

        if (handler.getWorld().getTileInt(felX, felY) == 0) {
            return 100;

        }
        return 0;

    }

    private float getGenDiffOnFeeler() {
        float LdnaDiff = 0;
        boolean foundGenDiff = false;
        for (TestPet p : handler.getPets()) {

            if (p != this) {
                if (Math.abs(p.getX() - fx - 50) < 35 && Math.abs(p.getY() - fy - 25) < 35) {
                    LdnaDiff = Math.abs(p.getDNA() - dna);
                }
                foundGenDiff = true;
            }

        }
        return 100;

    }

    public float getDNA() {

        return dna;
    }

    public float getFX() {
        return (float) fx;
    }

    public float getFY() {
        return (float) fy;

    }

    private void newMove(float x, float y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void tick() {
        updateNeuraNetValues();
        ACT();

        //   printValues();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate((int) (getX() - handler.getSimCamera().getXoffset()), (int) (getY() - handler.getSimCamera().getYoffset()));
        g2d.rotate(fr);
        g2d.translate(-50, -25);

        g2d.drawImage(Assets.pet, 0, 0, 100, 50, null);
        g2d.setColor(color);
        g2d.fillOval(35, 10, 30, 30);
        g2d.setColor(Color.BLACK);
        g2d.translate(50, 25);
        g2d.rotate(-fr);

        g2d.drawRect((int) fx, (int) fy, 10, 10);

        g2d.translate((int) -(getX() - handler.getSimCamera().getXoffset()), (int) -(getY() - handler.getSimCamera().getYoffset()));
        //    g.fillOval((int)(getX()- handler.getSimCamera().getXoffset()),(int) (getY()- handler.getSimCamera().getYoffset()), 25, 25);

    }

    public float getRotation() {
        return r;
    }

    public void setRotation(float r) {
        this.r = r;
    }

    private void updaterotationFeeler(float r) {
        this.fr += r;
        fx = ((40 * Math.cos(fr)) - (0 * Math.sin(fr))) - 5;
        fy = (40 * Math.sin(fr)) + (0 * Math.cos(fr)) - 5;

    }

    private void updaterotation(float r) {
        this.r += r;

    }

    private float getForX() {
        return (float) ((1 * Math.cos(r)) - (0 * Math.sin(r)));

    }

    private float getForY() {
        return (float) ((1 * Math.sin(r)) + (0 * Math.cos(r)));

    }

    private void updateNeuraNetValues() {
        foodValueOnPosN.setValue(getFoodValueOnPos());
        foodValueOnFeelerN.setValue(getFoodValueOnFeeler());
        energyN.setValue(energy);
        ageN.setValue(age);
        dnaDiffN = nw.CreateNewInput();
        wasAttacktN.setValue(wasAttackt);
        waterOnPosN.setValue(getWaterOnPos());
        waterOnFellerN.setValue(getWaterOnFeeler());

    }

    public void ACT() {
        ActRotation();
        if (Math.abs(forward.getValue()) > Math.abs(strafe.getValue())) {

            ActMove();
        } else {
            //ActStrafe();
        }
        actBirth();
        ActFeelerRotation();
        ActEat();

        energy -= costPerma;
        if (energy < minEnergyToSurvive) {
            kill();
        }

    }

    private void ActRotation() {
        float rForce = rotate.getValue();
        updaterotation(rForce * rotationFaktor);
        energy -= Math.abs(rForce * costRotate);
    }

    private void ActMove() {
        float frx = getForX() * moveSpeed;
        float fry = getForY() * moveSpeed;
        float frForce = forward.getValue();
        frx *= frForce;
        fry *= frForce;
        newMove(frx, fry);
        energy -= Math.abs(frForce * costWalk);
    }

    private void ActStrafe() {
        float fry = getForX() * moveSpeed * -1;
        float frx = getForY() * moveSpeed;
        float stForce = strafe.getValue();
        newMove(frx, fry);
        energy -= Math.abs(stForce * costWalk);

    }

    private void actBirth() {
        age += 0.01;
        agesincelastExpand += 0.01f;
        if (age > 5 && energy > 200 && agesincelastExpand > 5) {
            expand();

            agesincelastExpand = 0;
        }

//System.out.println("espfinal.entities.TestPet.actBirth()");
//not finished
    }

    private void ActFeelerRotation() {
        updaterotationFeeler(feelerAngle.getValue());
    }

    private void ActEat() {
        if (energy < 300) {
            if (foodValueOnPosN.getValue() > 0) {
                float eatingValue = eat.getValue() * gainFood;

                handler.getWorld().eatTile((int) x / Tile.TileWidht, (int) y / Tile.TileWidht, eatingValue);
                energy += eatingValue;
                //not finished...clamp is lost on that time
            }
        }
    }

    private void kill() {
        //System.out.println(this + " died with an age of" + age);
        handler.getPets().remove(this);
        stop();

    }

    private void printValues() {
        System.out.println("------------------------------------------------------------");
        System.out.println("START");
        System.out.println("------------------------------------------------------------");
        System.out.println(x + " " + y);
        System.out.println("------------------------------------------------------------");
        System.out.println(foodValueOnPosN.getName() + " - " + foodValueOnPosN.getValue());
        System.out.println(foodValueOnFeelerN.getName() + " - " + foodValueOnFeelerN.getValue());
        System.out.println(waterOnPosN.getName() + " - " + waterOnPosN.getValue());
        System.out.println(waterOnFellerN.getName() + " - " + waterOnFellerN.getValue());
        System.out.println(ageN.getName() + " - " + ageN.getValue());
        System.out.println(wasAttacktN.getName() + " - " + wasAttacktN.getValue());
        System.out.println(dnaDiffN.getName() + " - " + dnaDiffN.getValue());
        System.out.println(energyN.getName() + " - " + energyN.getValue());
        System.out.println("------------------------------------------------------------");
        System.out.println(forward.getName() + " - " + forward.getValue());
        System.out.println(strafe.getName() + " - " + strafe.getValue());
        System.out.println(eat.getName() + " - " + eat.getValue());
        System.out.println(attack.getName() + " - " + attack.getValue());
        System.out.println(rotate.getName() + " - " + rotate.getValue());
        System.out.println(feelerAngle.getName() + " - " + feelerAngle.getValue());
        System.out.println("------------------------------------------------------------");
        String stC = "Connection Weights : ";
        stC = nw.getAllConnections().stream().map((c) -> "; " + c.getWeight() + "").reduce(stC, String::concat);
        System.out.println(stC);
        System.out.println("------------------------------------------------------------");
        System.out.println("END");
        System.out.println("------------------------------------------------------------");
    }
 
     public synchronized void start() {
        if (running) {
            return;

        }
        running = true;

        thread = new Thread(this, threadName);

        thread.start();
        System.out.println("espfinal.entities.TestPet.start()");
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            Logger.getLogger(TestPet.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("espfinal.entities.TestPet.stop()");
    }

   
    
    private Color getColor() {

        int r = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        System.out.println(r);
        System.out.println(g);
        System.out.println(b);
        return new Color(r, g, b);
    }

    public NeuralNetwork getNeuralNetwork() {
        return nw;
    }

    private void expand() {
        TestPet Lpet = new TestPet(handler, x, y, 0, 0, "Pet Thread", false);
        Lpet.setNeuroNetwork(nw.copyNeuralNetwork());
        Lpet.SetDNA(dna);
        Lpet.setColor(color);
        handler.getPets().add(Lpet);

        Lpet.bias = Lpet.getNeuralNetwork().getAllInputNeurons().get(0);
        Lpet.bias.setValue(1);
        //create all varible onputs
        Lpet.foodValueOnPosN = Lpet.getNeuralNetwork().getAllInputNeurons().get(1);
        Lpet.foodValueOnPosN.setName("FoodValueOnPos");
        Lpet.foodValueOnFeelerN = Lpet.getNeuralNetwork().getAllInputNeurons().get(2);
        Lpet.foodValueOnFeelerN.setName("FoodValueOnFeeler");
        Lpet.energyN = Lpet.getNeuralNetwork().getAllInputNeurons().get(3);
        Lpet.energyN.setName("Energy");
        Lpet.ageN = Lpet.getNeuralNetwork().getAllInputNeurons().get(4);
        Lpet.ageN.setName("Age");
        Lpet.dnaDiffN = Lpet.getNeuralNetwork().getAllInputNeurons().get(5);
        Lpet.dnaDiffN.setName("DNADifferrence");
        Lpet.wasAttacktN = Lpet.getNeuralNetwork().getAllInputNeurons().get(6);
        Lpet.wasAttacktN.setName("WasAttackt");
        Lpet.waterOnPosN = Lpet.getNeuralNetwork().getAllInputNeurons().get(7);
        Lpet.waterOnPosN.setName("WaterOnPos");
        Lpet.waterOnFellerN = Lpet.getNeuralNetwork().getAllInputNeurons().get(8);
        Lpet.waterOnFellerN.setName("WaterOnFeeler");

        //create output neurons
        Lpet.forward = Lpet.getNeuralNetwork().getAllOutputNeurons().get(0);
        Lpet.forward.setName("Forward");
        Lpet.rotate = Lpet.getNeuralNetwork().getAllOutputNeurons().get(1);
        Lpet.rotate.setName("Rotate");
        Lpet.strafe = Lpet.getNeuralNetwork().getAllOutputNeurons().get(2);
        Lpet.strafe.setName("Strafe");
        Lpet.feelerAngle = Lpet.getNeuralNetwork().getAllOutputNeurons().get(3);
        Lpet.feelerAngle.setName("FeelerAngle");
        Lpet.attack = Lpet.getNeuralNetwork().getAllOutputNeurons().get(4);
        Lpet.attack.setName("Attack");
        Lpet.eat = Lpet.getNeuralNetwork().getAllOutputNeurons().get(5);
        Lpet.eat.setName("Eat");

    }

    public void setNeuroNetwork(NeuralNetwork nw) {
        this.nw = nw;
    }

    public void SetDNA(float dna) {
        this.dna = dna;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void run() {

    }


}
