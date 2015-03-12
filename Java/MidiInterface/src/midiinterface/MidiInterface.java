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

        MidiSequence sequence = new MidiSequence(72);
        MidiTrack tr = sequence.createTrack("miditrack 1");
        MidiTrack tr2 = sequence.createTrack("miditrack 2");

        //maat 1
        //tr2.addPause(0, 400);
        tr.addNote(0, new Note(64, 50, 0, 50)); // E
        tr.addNote(0, new Note(52, 50, 0, 100)); // E
        tr.addNote(0, new Note(59, 50, 50, 50)); // B

        tr.addNote(0, new Note(64, 50, 100, 50)); //E
        tr.addNote(0, new Note(55, 50, 100, 100)); //G
        tr.addNote(0, new Note(59, 50, 150, 50));//B

        tr.addNote(0, new Note(64, 50, 200, 50));//E
        tr.addNote(0, new Note(52, 50, 200, 100));//E
        tr.addNote(0, new Note(59, 50, 250, 50));//B

        tr.addNote(0, new Note(64, 50, 300, 50));//E
        tr.addNote(0, new Note(55, 50, 300, 100));//G
        tr.addNote(0, new Note(59, 50, 350, 50));//B

        //maat 2
        tr.addNote(0, new Note(62, 50, 400, 50));//D
        tr.addNote(0, new Note(50, 50, 400, 100));//D
        tr.addNote(0, new Note(59, 50, 450, 50));//B

        tr.addNote(0, new Note(62, 50, 500, 50));//D
        tr.addNote(0, new Note(55, 50, 500, 100));//G
        tr.addNote(0, new Note(59, 50, 550, 50));//B

        tr.addNote(0, new Note(62, 50, 600, 50));//D
        tr.addNote(0, new Note(50, 50, 600, 100));//D
        tr.addNote(0, new Note(59, 50, 650, 50));//B

        tr.addNote(0, new Note(62, 50, 700, 50));//D
        tr.addNote(0, new Note(55, 50, 700, 100));//G
        tr.addNote(0, new Note(59, 50, 750, 50));//B

        //maat 3
        tr.addNote(0, new Note(62, 50, 800, 50));//D
        tr.addNote(0, new Note(50, 50, 800, 100));//D
        tr.addNote(0, new Note(59, 50, 850, 50));//B

        tr.addNote(0, new Note(62, 50, 900, 50));//D
        tr.addNote(0, new Note(54, 50, 900, 100));//F#
        tr.addNote(0, new Note(59, 50, 950, 50));//B

        tr.addNote(0, new Note(62, 50, 1000, 50));//D
        tr.addNote(0, new Note(50, 50, 1000, 100));//D
        tr.addNote(0, new Note(59, 50, 1050, 50));//B

        tr.addNote(0, new Note(62, 50, 1100, 50));//D
        tr.addNote(0, new Note(54, 50, 1100, 100));//F#

        tr.addNote(0, new Note(59, 50, 1150, 50));//B        

        //maat 4
        tr.addNote(0, new Note(62, 50, 1200, 50));//D
        tr.addNote(0, new Note(50, 50, 1200, 100));//D
        tr.addNote(0, new Note(57, 50, 1250, 50));//A

        tr.addNote(0, new Note(62, 50, 1300, 50));//D
        tr.addNote(0, new Note(54, 50, 1300, 100));//F#
        tr.addNote(0, new Note(57, 50, 1350, 50));//A

        tr.addNote(0, new Note(62, 50, 1400, 50));//D
        tr.addNote(0, new Note(50, 50, 1400, 100));//D
        tr.addNote(0, new Note(57, 50, 1450, 50));//A

        tr.addNote(0, new Note(62, 50, 1500, 50));//D
        tr.addNote(0, new Note(54, 50, 1500, 100));//F#
        tr.addNote(0, new Note(57, 50, 1550, 50));//A

        for (int i = 0; i < 2; i++) {
            //maat 5
            tr2.addNote(0, new Note(67, 75, 1650 + (i * 1600), 25)); //G
            tr2.addNote(0, new Note(66, 75, 1675 + (i * 1600), 25)); //F#
            tr2.addNote(0, new Note(67, 75, 1700 + (i * 1600), 50)); //G
            tr2.addNote(0, new Note(71, 75, 1750 + (i * 1600), 25)); //B
            tr2.addNote(0, new Note(72, 75, 1775 + (i * 1600), 25)); //C
            tr2.addNote(0, new Note(71, 75, 1800 + (i * 1600), 200)); //B

            tr.addNote(0, new Note(64, 75, 1600 + (i * 1600), 50)); // E
            tr.addNote(0, new Note(52, 75, 1600 + (i * 1600), 100)); // E
            tr.addNote(0, new Note(59, 75, 1650 + (i * 1600), 50)); // B

            tr.addNote(0, new Note(64, 75, 1700 + (i * 1600), 50)); //E
            tr.addNote(0, new Note(55, 75, 1700 + (i * 1600), 100)); //G
            tr.addNote(0, new Note(59, 75, 1750 + (i * 1600), 50));//B

            tr.addNote(0, new Note(64, 75, 1800 + (i * 1600), 50));//E
            tr.addNote(0, new Note(52, 75, 1800 + (i * 1600), 100));//E
            tr.addNote(0, new Note(59, 75, 1850 + (i * 1600), 50));//B

            tr.addNote(0, new Note(64, 75, 1900 + (i * 1600), 50));//E
            tr.addNote(0, new Note(55, 75, 1900 + (i * 1600), 100));//G        
            tr.addNote(0, new Note(59, 75, 1950 + (i * 1600), 50));//B

            //maat 6
            tr2.addNote(0, new Note(66, 75, 2050 + (i * 1600), 25)); //F#
            tr2.addNote(0, new Note(67, 75, 2075 + (i * 1600), 25)); //G
            tr2.addNote(0, new Note(66, 75, 2100 + (i * 1600), 50)); //F#
            tr2.addNote(0, new Note(67, 75, 2150 + (i * 1600), 25)); //G
            tr2.addNote(0, new Note(69, 75, 2175 + (i * 1600), 25)); //A
            tr2.addNote(0, new Note(67, 75, 2200 + (i * 1600), 200)); //G

            tr.addNote(0, new Note(62, 75, 2000 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 75, 2000 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 75, 2050 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2100 + (i * 1600), 50));//D
            tr.addNote(0, new Note(55, 75, 2100 + (i * 1600), 100));//G
            tr.addNote(0, new Note(59, 75, 2150 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2200 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 75, 2200 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 75, 2250 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2300 + (i * 1600), 50));//D
            tr.addNote(0, new Note(55, 75, 2300 + (i * 1600), 100));//G
            tr.addNote(0, new Note(59, 75, 2350 + (i * 1600), 50));//B

            //maat 7
            tr2.addNote(0, new Note(66, 75, 2450 + (i * 1600), 25)); //F#
            tr2.addNote(0, new Note(64, 75, 2475 + (i * 1600), 25)); //E
            tr2.addNote(0, new Note(66, 75, 2500 + (i * 1600), 50)); //F#
            tr2.addNote(0, new Note(71, 75, 2550 + (i * 1600), 25));//B
            tr2.addNote(0, new Note(72, 75, 2575 + (i * 1600), 25));//C
            tr2.addNote(0, new Note(71, 75, 2600 + (i * 1600), 200)); //B

            tr.addNote(0, new Note(62, 75, 2400 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 75, 2400 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 75, 2450 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2500 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 75, 2500 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(59, 75, 2550 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2600 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 75, 2600 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 75, 2650 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 75, 2700 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 75, 2700 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(59, 75, 2750 + (i * 1600), 50));//B           

            //maat 8
            tr2.addNote(0, new Note(66, 75, 2850 + (i * 1600), 25)); //F#
            tr2.addNote(0, new Note(64, 75, 2875 + (i * 1600), 25)); //E
            tr2.addNote(0, new Note(66, 75, 2900 + (i * 1600), 300)); //F#

            tr.addNote(0, new Note(62, 50, 2800 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 2800 + (i * 1600), 100));//D
            tr.addNote(0, new Note(57, 50, 2850 + (i * 1600), 50));//A

            tr.addNote(0, new Note(62, 50, 2900 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 50, 2900 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(57, 50, 2950 + (i * 1600), 50));//A

            tr.addNote(0, new Note(62, 50, 3000 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 3000 + (i * 1600), 100));//D
            tr.addNote(0, new Note(57, 50, 3050 + (i * 1600), 50));//A

            tr.addNote(0, new Note(62, 50, 3100 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 50, 3100 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(57, 50, 3150 + (i * 1600), 50));//A 
        }

        //maat 9
        tr2.addNote(0, new Note(76, 50, 3200 + 1600, 150));//E
        tr2.addNote(0, new Note(71, 50, 3350 + 1600, 250));//B

        tr.addNote(0, new Note(64, 50, 3200 + 1600, 50)); // E
        tr.addNote(0, new Note(52, 50, 3200 + 1600, 100)); // E
        tr.addNote(0, new Note(59, 50, 3250 + 1600, 50)); // B

        tr.addNote(0, new Note(64, 50, 3300 + 1600, 50)); //E
        tr.addNote(0, new Note(55, 50, 3300 + 1600, 100)); //G
        tr.addNote(0, new Note(59, 50, 3350 + 1600, 50));//B

        tr.addNote(0, new Note(64, 50, 3400 + 1600, 50));//E
        tr.addNote(0, new Note(52, 50, 3400 + 1600, 100));//E
        tr.addNote(0, new Note(59, 50, 3450 + 1600, 50));//B

        tr.addNote(0, new Note(64, 50, 3500 + 1600, 50));//E
        tr.addNote(0, new Note(55, 50, 3500 + 1600, 100));//G        
        tr.addNote(0, new Note(59, 50, 3550 + 1600, 50));//B

        //maat 10
        tr2.addNote(0, new Note(74, 50, 3600 + 1600, 150));//D
        tr2.addNote(0, new Note(71, 50, 3750 + 1600, 250));//B

        tr.addNote(0, new Note(62, 50, 3600 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 3600 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 3650 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 3700 + 1600, 50));//D
        tr.addNote(0, new Note(55, 50, 3700 + 1600, 100));//G
        tr.addNote(0, new Note(59, 50, 3750 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 3800 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 3800 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 3850 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 3900 + 1600, 50));//D
        tr.addNote(0, new Note(55, 50, 3900 + 1600, 100));//G
        tr.addNote(0, new Note(59, 50, 3950 + 1600, 50));//B        

        //maat 11
        tr2.addNote(0, new Note(78, 50, 4000 + 1600, 150));//F
        tr2.addNote(0, new Note(71, 50, 4150 + 1600, 250));//B

        tr.addNote(0, new Note(62, 50, 4000 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 4000 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 4050 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 4100 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 4100 + 1600, 100));//F#
        tr.addNote(0, new Note(59, 50, 4150 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 4200 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 4200 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 4250 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 4300 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 4300 + 1600, 100));//F#
        tr.addNote(0, new Note(59, 50, 4350 + 1600, 50));//B             

        //maat 12
        tr2.addNote(0, new Note(78, 50, 4400 + 1600, 150));//F
        tr2.addNote(0, new Note(69, 50, 4550 + 1600, 250));//A

        tr.addNote(0, new Note(62, 50, 4400 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 4400 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 4450 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 4500 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 4500 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 4550 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 4600 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 4600 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 4650 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 4700 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 4700 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 4750 + 1600, 50));//A
/*
         for (int i = 0; i < 3; i++) {
         ArrayList<Note> notes = new ArrayList<>();
         tr.addNotenew Note
         (60, 100, 50));
         tr.addNote(new Note(64, 100, 50));
         tr.addNote(new Note(67, 100, 50));

         tr.addNotes(0, notes);
         tr.addNote(0, new Note(64, 100, 50));
         tr.addNote(0, new Note(64, 100, 50));
         tr.addPause(0, 200);
         tr.addNote(0, new Note(64, 100, 50));
         }
         *//*
         ArrayList<Note> notes = new ArrayList<>();
         tr.addNote(new Note(56, 100, 50));
         tr.addNote(new Note(60, 100, 50));
         tr.addNote(new Note(63, 100, 50));
         tr.addNotes(0, notes);

         tr.addNote(0, new Note(60, 100, 50));
         tr.addNote(0, new Note(60, 100, 50));
         tr.addNote(0, new Note(60, 100, 50));
         */

        sequence.writeToFile("midifile");
    }
}
