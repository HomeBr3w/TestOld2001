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
public class TestBV {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        MidiSequence sequence = new MidiSequence(90); //Maak MIDI-sequens met BPM = 90
        MidiTrack tr = sequence.createTrack("miditrack 1"); //Voeg track toe
        //merk op dat het object sequence een MidiTrack ervoor genereert
        tr.changeInstrument(0, 43, 0); //verander instrument naar viool
        
        tr.addNote(0, new Note(64, 50, 0, 50)); // voeg E-noot toe aan track
        tr.addNote(0, new Note(52, 50, 0, 100)); // voeg (lagere) E-noot toe aan track
        tr.addNote(0, new Note(59, 50, 50, 50)); // voeg B-noot toe aan track
        
        sequence.writeToFile("onderzoekmidi"); //schrijf naar bestand
    }
}
