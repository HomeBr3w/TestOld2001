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

        MidiSequence sequence = new MidiSequence(90);
        MidiTrack tr = sequence.createTrack("miditrack 1");
        tr.changeInstrument(0, 53, 0);
        tr.changeInstrument(1, 53, 0);
        //MidiTrack tr2 = sequence.createTrack("miditrack 2");

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
            tr.addNote(1, new Note(67, 75, 1650 + (i * 1600), 25)); //G
            tr.addNote(1, new Note(66, 75, 1675 + (i * 1600), 25)); //F#
            tr.addNote(1, new Note(67, 75, 1700 + (i * 1600), 50)); //G
            tr.addNote(1, new Note(71, 75, 1750 + (i * 1600), 25)); //B
            tr.addNote(1, new Note(72, 75, 1775 + (i * 1600), 25)); //C
            tr.addNote(1, new Note(71, 75, 1800 + (i * 1600), 200)); //B

            tr.addNote(0, new Note(64, 50, 1600 + (i * 1600), 50)); // E
            tr.addNote(0, new Note(52, 50, 1600 + (i * 1600), 100)); // E
            tr.addNote(0, new Note(59, 50, 1650 + (i * 1600), 50)); // B

            tr.addNote(0, new Note(64, 50, 1700 + (i * 1600), 50)); //E
            tr.addNote(0, new Note(55, 50, 1700 + (i * 1600), 100)); //G
            tr.addNote(0, new Note(59, 50, 1750 + (i * 1600), 50));//B

            tr.addNote(0, new Note(64, 50, 1800 + (i * 1600), 50));//E
            tr.addNote(0, new Note(52, 50, 1800 + (i * 1600), 100));//E
            tr.addNote(0, new Note(59, 50, 1850 + (i * 1600), 50));//B

            tr.addNote(0, new Note(64, 50, 1900 + (i * 1600), 50));//E
            tr.addNote(0, new Note(55, 50, 1900 + (i * 1600), 100));//G        
            tr.addNote(0, new Note(59, 50, 1950 + (i * 1600), 50));//B

            //maat 6
            tr.addNote(1, new Note(66, 75, 2050 + (i * 1600), 25)); //F#
            tr.addNote(1, new Note(67, 75, 2075 + (i * 1600), 25)); //G
            tr.addNote(1, new Note(66, 75, 2100 + (i * 1600), 50)); //F#
            tr.addNote(1, new Note(67, 75, 2150 + (i * 1600), 25)); //G
            tr.addNote(1, new Note(69, 75, 2175 + (i * 1600), 25)); //A
            tr.addNote(1, new Note(67, 75, 2200 + (i * 1600), 200)); //G

            tr.addNote(0, new Note(62, 50, 2000 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 2000 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 50, 2050 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2100 + (i * 1600), 50));//D
            tr.addNote(0, new Note(55, 50, 2100 + (i * 1600), 100));//G
            tr.addNote(0, new Note(59, 50, 2150 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2200 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 2200 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 50, 2250 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2300 + (i * 1600), 50));//D
            tr.addNote(0, new Note(55, 50, 2300 + (i * 1600), 100));//G
            tr.addNote(0, new Note(59, 50, 2350 + (i * 1600), 50));//B

            //maat 7
            tr.addNote(1, new Note(66, 75, 2450 + (i * 1600), 25)); //F#
            tr.addNote(1, new Note(64, 75, 2475 + (i * 1600), 25)); //E
            tr.addNote(1, new Note(66, 75, 2500 + (i * 1600), 50)); //F#
            tr.addNote(1, new Note(71, 75, 2550 + (i * 1600), 25));//B
            tr.addNote(1, new Note(72, 75, 2575 + (i * 1600), 25));//C
            tr.addNote(1, new Note(71, 75, 2600 + (i * 1600), 200)); //B

            tr.addNote(0, new Note(62, 50, 2400 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 2400 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 50, 2450 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2500 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 50, 2500 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(59, 50, 2550 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2600 + (i * 1600), 50));//D
            tr.addNote(0, new Note(50, 50, 2600 + (i * 1600), 100));//D
            tr.addNote(0, new Note(59, 50, 2650 + (i * 1600), 50));//B

            tr.addNote(0, new Note(62, 50, 2700 + (i * 1600), 50));//D
            tr.addNote(0, new Note(54, 50, 2700 + (i * 1600), 100));//F#
            tr.addNote(0, new Note(59, 50, 2750 + (i * 1600), 50));//B           

            //maat 8
            tr.addNote(1, new Note(66, 75, 2850 + (i * 1600), 25)); //F#
            tr.addNote(1, new Note(64, 75, 2875 + (i * 1600), 25)); //E
            tr.addNote(1, new Note(66, 75, 2900 + (i * 1600), 300)); //F#

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
        tr.addNote(1, new Note(76, 75, 3200 + 1600, 150));//E
        tr.addNote(1, new Note(71, 75, 3350 + 1600, 250));//B

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
        tr.addNote(1, new Note(74, 75, 3600 + 1600, 150));//D
        tr.addNote(1, new Note(71, 75, 3750 + 1600, 250));//B

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
        tr.addNote(1, new Note(78, 75, 4000 + 1600, 150));//F
        tr.addNote(1, new Note(71, 75, 4150 + 1600, 250));//B

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
        tr.addNote(1, new Note(78, 75, 4400 + 1600, 150));//F
        tr.addNote(1, new Note(69, 75, 4550 + 1600, 250));//A

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

        //maat 13
        tr.addNote(1, new Note(79, 75, 4800 + 1600, 150)); //G
        tr.addNote(1, new Note(71, 75, 4800 + 1600, 150)); //B

        tr.addNote(1, new Note(76, 75, 4950 + 1600, 250)); //E
        tr.addNote(1, new Note(67, 75, 4950 + 1600, 250)); //G (1 lager)

        tr.addNote(0, new Note(62, 50, 4800 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 4800 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 4850 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 4900 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 4900 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 4950 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 5000 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 5000 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 5050 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 5100 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 5100 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 5150 + 1600, 50));//A

        //maat 14
        tr.addNote(1, new Note(79, 75, 5200 + 1600, 150)); //G
        tr.addNote(1, new Note(71, 75, 5200 + 1600, 150)); //B

        tr.addNote(1, new Note(67, 75, 5350 + 1600, 250)); //G
        tr.addNote(1, new Note(74, 75, 5350 + 1600, 250)); //D

        tr.addNote(0, new Note(62, 50, 5200 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 5200 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 5250 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5300 + 1600, 50));//D
        tr.addNote(0, new Note(55, 50, 5300 + 1600, 100));//G
        tr.addNote(0, new Note(59, 50, 5350 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5400 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 5400 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 5450 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5500 + 1600, 50));//D
        tr.addNote(0, new Note(55, 50, 5500 + 1600, 100));//G
        tr.addNote(0, new Note(59, 50, 5550 + 1600, 50));//B

        //maat 15
        tr.addNote(1, new Note(78, 75, 5600 + 1600, 150)); //F#
        tr.addNote(1, new Note(71, 75, 5600 + 1600, 150)); //B

        tr.addNote(1, new Note(66, 75, 5750 + 1600, 250)); //F#
        tr.addNote(1, new Note(74, 75, 5750 + 1600, 250)); //D

        tr.addNote(0, new Note(62, 50, 5600 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 5600 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 5650 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5700 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 5700 + 1600, 100));//F#
        tr.addNote(0, new Note(59, 50, 5750 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5800 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 5800 + 1600, 100));//D
        tr.addNote(0, new Note(59, 50, 5850 + 1600, 50));//B

        tr.addNote(0, new Note(62, 50, 5900 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 5900 + 1600, 100));//F#
        tr.addNote(0, new Note(59, 50, 5950 + 1600, 50));//B   

        //maat 16
        tr.addNote(1, new Note(78, 75, 6000 + 1600, 150)); //F#
        tr.addNote(1, new Note(71, 75, 6000 + 1600, 150)); //A

        tr.addNote(1, new Note(66, 75, 6150 + 1600, 250)); //F#
        tr.addNote(1, new Note(74, 75, 6150 + 1600, 250)); //D

        tr.addNote(0, new Note(62, 50, 6000 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 6000 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 6050 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 6100 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 6100 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 6150 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 6200 + 1600, 50));//D
        tr.addNote(0, new Note(50, 50, 6200 + 1600, 100));//D
        tr.addNote(0, new Note(57, 50, 6250 + 1600, 50));//A

        tr.addNote(0, new Note(62, 50, 6300 + 1600, 50));//D
        tr.addNote(0, new Note(54, 50, 6300 + 1600, 100));//F#
        tr.addNote(0, new Note(57, 50, 6350 + 1600, 50));//A
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
