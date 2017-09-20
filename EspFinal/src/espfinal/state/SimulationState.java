/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espfinal.state;

import espfinal.EspFinal;
import espfinal.Handler;
import espfinal.data.res.Assets;
import espfinal.entities.TestPet;
import espfinal.entities.Test_Camera;
import espfinal.tiles.Tile;
import espfinal.world.World;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Christian Oden
 */
public class SimulationState extends State {

    private ArrayList<TestPet> pets;
    private Test_Camera user;
    private World world;

    public SimulationState(Handler handler) {
        super(handler);
        world = new World(handler,new File("").getAbsolutePath() + "\\res\\test.text");
        handler.setWorld(world);
        user = new Test_Camera(handler, 0, 0, 10, 10);
        pets = new ArrayList<TestPet>();
     

        handler.setPets(pets);
    }

    @Override
    public void tick() {
        world.tick();

        for (int i = 0; i < pets.size(); i++) {
            pets.get(i).tick();

        }

        user.tick();

    }

    @Override
    public void render(Graphics g) {
        world.render(g);

        for (int i = 0; i < pets.size(); i++) {
            pets.get(i).render(g);

        }
        user.render(g);
    }

}
