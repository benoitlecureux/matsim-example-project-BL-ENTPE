package org.matsim.project;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler {

    /*public static void main(String[] args) {

        String inputFile = "output_BASE/output_events.xml.gz";

        EventsManager eventsManager = EventsUtils.createEventsManager();

        SimpleEventHandler eventHandler = new SimpleEventHandler();
        eventsManager.addHandler(eventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
        eventsReader.readFile(inputFile);


    }*/
    public static void main(String[] args) {

        String inputFile = "output-reduced/output_events.xml.gz";
        String outputFile = "output-reduced/Link6volumes.txt";

        EventsManager eventsManager = EventsUtils.createEventsManager();

        SimpleLinkEventHandler linkEventHandler = new SimpleLinkEventHandler(outputFile);
        eventsManager.addHandler(linkEventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
        eventsReader.readFile(inputFile);

        linkEventHandler.printResult(); // print the results ;
    }

}