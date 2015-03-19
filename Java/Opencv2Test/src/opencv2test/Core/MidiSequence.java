/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv2test.Core;

import java.io.File;
import java.util.ArrayList;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import opencv2test.Support.MidiTrack;

/**
 *
 * @author Siebren
 */
public class MidiSequence {

    private Sequence sequence;
    private int bpm;
    private ArrayList<MidiTrack> tracks = new ArrayList<>();

    /**
     * Default constructor, initializes this class with a BPM of 100.
     */
    public MidiSequence() {
        this(100);
    }

    /**
     * Constructor of the MidiSequence
     * Requires BPM, this is relative to the ticks.
     * @param bpm 
     */
    public MidiSequence(int bpm) {
        this.bpm = bpm;
        init();
    }

    /**
     * Initializes the MidiSequence.
     * Creates a Sequence object with the BPM provided.
     */
    private void init() {
        try {
            sequence = new Sequence(javax.sound.midi.Sequence.PPQ, bpm);
        } catch (InvalidMidiDataException ex) {
            System.out.println("Exception in creation of Sequence. " + ex.getMessage());
        }
    }

    /**
     * Function to retrieve all tracks in this sequence
     * @return tracks within this sequence (ArrayList<MidiTrack>)
     */
    public ArrayList<MidiTrack> getTracks() {
        return this.tracks;
    }

    /**
     * Writes the contents of the Sequence to a MIDI-file.
     * Calls close of all track so that they have an end.
     * @param fileName
     * @return saved successfully (boolean)
     */
    public boolean writeToFile(String fileName) {
        try {
            for (MidiTrack t : tracks) {
                t.close();
            }
            File f = new File(fileName + ".mid");
            MidiSystem.write(sequence, 1, f);
            return true;
        } catch (Exception ex) {
            System.out.println("Error while saving file! " + ex.getMessage());
            return false;
        }
    }

    /**
     * Function to create a MidiTrack within this sequence.
     * Returns the created MidiTrack to be used to add notes to.
     * @param trackName
     * @return recent created track (MidiTrack)
     */
    public MidiTrack createTrack(String trackName) {
        MidiTrack tr = new MidiTrack(sequence.createTrack(), trackName);
        tracks.add(tr);
        return tr;
    }

}
