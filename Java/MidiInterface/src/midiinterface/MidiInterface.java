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
public class MidiInterface {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        MidiSequence sequence = new MidiSequence(100);
        MidiTrack tr = sequence.createTrack("miditrack 1");
        MidiTrack tr2 = sequence.createTrack("miditrack 2");

        for (int i = 0; i < 100; i++) {
            tr.addNote(0, new Note(64, 50, 0 + (i * 400), 50)); // E
            tr.addNote(0, new Note(52, 50, 0 + (i * 400), 100)); // E
            tr.addNote(0, new Note(59, 50, 50 + (i * 400), 50)); // B

            tr.addNote(0, new Note(64, 50, 100 + (i * 400), 50)); //E
            tr.addNote(0, new Note(55, 50, 100 + (i * 400), 100)); //G
            tr.addNote(0, new Note(59, 50, 150 + (i * 400), 50));//B

            tr.addNote(0, new Note(64, 50, 200 + (i * 400), 50));//E
            tr.addNote(0, new Note(52, 50, 200 + (i * 400), 100));//E
            tr.addNote(0, new Note(59, 50, 250 + (i * 400), 50));//B

            tr.addNote(0, new Note(64, 50, 300 + (i * 400), 50));//E
            tr.addNote(0, new Note(55, 50, 300 + (i * 400), 100));//G
            tr.addNote(0, new Note(59, 50, 350 + (i * 400), 50));//B
        }

        sequence.writeToFile("midifile");
    }
}
