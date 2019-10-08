package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends huglife.Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */


    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public void move(){
        energy -= 0.03;
        return;
    }

    public void stay(){
        energy -= 0.01;
        return;
    }

    public void attack(Creature c){
        energy += c.energy();
        return;
    }

    public Creature replicate(){
        double babyEnergy = energy * 0.5;
        energy = 0.5;
        return new Clorus(babyEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        for(Direction d : neighbors.keySet()){
            if(neighbors.get(d).name().equals("empty")){
                emptyNeighbors.addLast(d);
            }

            if(neighbors.get(d).name().equals("plip")){
                plipNeighbors.addLast(d);
                anyPlip = true;
            }
        }

        //Rule 1
        if(emptyNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2
        if(anyPlip){
            Direction d = randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        }

        //Rule 3
        if(energy >= 1){
            Direction d = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        Direction d = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, d);
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r,g,b);
    }
}
