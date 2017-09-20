/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal;

import espfinal.entities.SimCamera;
import espfinal.entities.TestPet;
import espfinal.input.KeyManager;
import espfinal.world.World;
import java.util.ArrayList;

/**
 *
 * @author Christian Oden
 */
public class Handler {

    private EspFinal esp;
    private World world;
    private ArrayList<TestPet> pets;

    public Handler(EspFinal esp) {
        this.esp = esp;
        //this.world = world;

    }

    public int getWidth() {
        return esp.display.getWidth();
    }

    public int getHeight() {
        return esp.display.getHeight();
    }

    public KeyManager getKeyManager() {
        return esp.getKeyManager();
    }

    public SimCamera getSimCamera() {
        return esp.getSimCamera();
    }

    /**
     * @return the esp
     */
    public EspFinal getEsp() {
        return esp;
    }

    /**
     * @param esp the esp to set
     */
    public void setEsp(EspFinal esp) {
        this.esp = esp;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(World world) {
        this.world = world;
    }

    public ArrayList<TestPet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<TestPet> pets) {
        this.pets = pets;
    }
    public void removePet(TestPet p){
    pets.remove(p);
    }
}
