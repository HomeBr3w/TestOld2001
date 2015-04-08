/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiinterface;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.Track;

/**
 * Miditrack wraps around the Track class which is from the Java environment
 *
 * @author Siebren
 */
public class MidiTrack
{

    private final Track track;
    private final String trackName;

    /**
     *
     * @param track
     * @param trackName
     */
    public MidiTrack(Track track, String trackName)
    {
        this.trackName = trackName;
        this.track = track;
        this.init();
        this.setTrackName();
    }

    /**
     * Initializes the track by writing a SysexMessage to the track. This will
     * push an event to the track. The SysexMessage contains the header
     * information to comply to the MIDI-format description.
     */
    private void init()
    {
        try
        {
            byte[] b =
            {
                (byte) 0xF0, 0x7E, 0x7F, 0x09, 0x01, (byte) 0xF7
            };
            SysexMessage sysexMessage = new SysexMessage();
            sysexMessage.setMessage(b, 6);
            MidiEvent midiEvent = new MidiEvent(sysexMessage, (long) 0);
            track.add(midiEvent);
        }
        catch (InvalidMidiDataException ex)
        {
            System.out.println("Unable to initalize MidiTrack. " + ex.getMessage());
        }
    }

    /**
     * Adds a note to this channel. Requires a Note and a channel. Function
     * creates a shortMessage with a NoteON and NoteOFF command. User only needs
     * to specify the Note containing the required parameters.
     *
     * @param channel
     * @param note
     */
    public void addNote(int channel, Note note)
    {
        try
        {
            long startTick = note.getStartTick();
            long endTick = startTick + note.getDuration();
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, note.getNote(), note.getVelocity()), startTick));
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, channel, note.getNote(), note.getVelocity()), endTick));
        }
        catch (InvalidMidiDataException ex)
        {
            System.out.println("Error in addNote." + ex.getMessage());
        }
    }

    /**
     * Creates a MetaEvent to set the name of the track.
     *
     * @param trackName
     */
    private void setTrackName()
    {
        try
        {
            track.add(new MidiEvent(new MetaMessage(0x03, trackName.getBytes(), trackName.length()), (long) 0));
        }
        catch (InvalidMidiDataException ex)
        {
            System.out.println("Cannot set trackname. " + ex.getMessage());
        }
    }

    /**
     * Changes the program of a channel.
     * For example to change an instrument to PIANO.
     *
     * @param channel
     * @param instrument
     */
    public void changeInstrument(int channel, int instrument)
    {
        try
        {
            track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument), 0));
            System.out.println("Meh.");
        }
        catch (InvalidMidiDataException ex)
        {
            System.out.println("Invalid data was entered! " + ex.getMessage());
        }
    }

    /**
     * Calls a MetaMessage indicating that the track has ended.
     * Called when a writeToFile was called.
     * 0x2F => end of track
     * @throws javax.sound.midi.InvalidMidiDataException
     */
    public void close() throws InvalidMidiDataException
    {
        byte[] byteArray =
        {
        };
        track.add(new MidiEvent(new MetaMessage(0x2F, byteArray, 0), (long) 0));
    }
}
