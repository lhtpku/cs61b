package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    private int red;

    private int green;

    private int blue;

    public Clorus() {
        this(1);
    }

    public Clorus(double e) {
        super("clorus");
        red = 0;
        green = 0;
        blue = 0;
        energy = e;
    }

    /**
     * Return the color of the clorus
     */
    @Override
    public Color color() {
        red = 34;
        blue = 231;
        return new Color(red, green, blue);
    }

    /**
     * Attack another creature
     */
    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    /**
     * Move the current clorus
     */
    @Override
    public void move() {
        if (this.energy < 0.03) {
            this.energy = 0;
        } else {
            this.energy -= 0.03;
        }
    }

    /**
     * Stay at the same place
     */
    @Override
    public void stay() {
        if (this.energy < 0.01) {
            this.energy = 0;
        } else {
            this.energy -= 0.01;
        }
    }

    /**
     * Return a baby clorus
     */
    @Override
    public Clorus replicate() {
        this.energy = this.energy / 2;
        return new Clorus(this.energy);
    }

    /**
     * Return the proper action
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbours = new ArrayDeque<>();
        Deque<Direction> plipNeighbours = new ArrayDeque<>();

        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbours.addFirst(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                plipNeighbours.addFirst(d);
            }
        }

        if (emptyNeighbours.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        if (plipNeighbours.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbours));
        }

        if (this.energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbours));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbours));
    }
}