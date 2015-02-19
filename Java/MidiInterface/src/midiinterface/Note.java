/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiinterface;

/**
 *
 * @author Siebren
 */
public class Note {

    private final int startTick;
    private final int duration;
    private final int note;
    private final int velocity;

    /**
     * Constructor of Note. Used to store data specific for a Note to be played
     * in MIDI.
     *
     * @param note (0 - 127)
     * @param velocity (0 - 127)
     * @param startTick moment to play the note (tick)
     * @param duration length of the note (in ticks)
     */
    public Note(int note, int velocity, int startTick, int duration) {
        this.note = note;
        this.velocity = velocity;
        this.startTick = startTick + 1;
        this.duration = duration + 1;
    }

    /**
     * Returns the startTick of this Note
     * @return startTick (int)
     */
    public int getStartTick() {
        return startTick;
    }

    /**
     * Returns the Note
     * @return note (int)
     */
    public int getNote() {
        return note;
    }

    /**
     * Returns the duration of this note
     * @return duration (int)
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the velocity of this note
     * @return velocity (int)
     */
    public int getVelocity() {
        return velocity;
    }

}
