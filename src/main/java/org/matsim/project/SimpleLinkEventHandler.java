package org.matsim.project;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scoring.ScoringFunction;
import org.matsim.core.scoring.ScoringFunctionFactory;
import org.matsim.core.scoring.SumScoringFunction;
import org.matsim.core.scoring.functions.*;
import org.matsim.run.Controler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleLinkEventHandler implements LinkEnterEventHandler {

    private final BufferedWriter bufferedWriter;

    public SimpleLinkEventHandler (String outputFile) { //What a buffered writer is ?
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch(IOException ee){ // what's an IOException ?
            throw new RuntimeException(ee);
        }
    }

    private double[] volumeLink6 = new double[24]; //lists use less memory than hashmaps

    private int getSlot(double time) {
        return (int) time/3600;
    }

    @Override
    public void handleEvent(LinkEnterEvent event) {
        if (event.getLinkId().equals(Id.createLinkId("6"))) {
            int slot = getSlot(event.getTime());
            this.volumeLink6[slot]++; //incrementation by one
        }
    }

    public void printResult() {
        try {
            bufferedWriter.write("Hour \t Volume \n");
            for (int i=0; i<24; i++) {
                double volume = this.volumeLink6[i];
                bufferedWriter.write( i + "\t" + volume + "\n");
                //System.out.println("Volume on link 6 from " + i + " to " + (i+1) + "o'clock = " + volume);
            }
            bufferedWriter.close();
        } catch (IOException ee){ // what's an IOException ?
            throw new RuntimeException(ee);
        }
    }

/*

    private Map<Integer, Integer> vehiclesPerHour = new HashMap<>();

    public SimpleLinkEventHandler() {
        for(int i=0;i<24;i++){
            vehiclesPerHour.put(i,0);
        }
    }

    public void handleEvent(LinkEnterEvent event) {
        if (event.getLinkId().equals(Id.createLinkId(6))) {
            int hour = (int) Math.ceil(event.getTime() / 3600);
            vehiclesPerHour.put(hour, vehiclesPerHour.get(hour)+1);
        }
    }
    public void print(){
        for(Integer i : vehiclesPerHour.keySet()){
            System.out.println("During hout " + i + " : " + vehiclesPerHour.get(i) + " vehicles");
        }
    }
*/
}
