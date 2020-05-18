package org.matsim.project;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimpleEventHandler implements PersonDepartureEventHandler, PersonArrivalEventHandler {
    static Map<Id<Person>, Double> departureTimeByPersonMap = new HashMap<>();

    @Override
    public void handleEvent(PersonDepartureEvent event) {
        System.out.println("Departure event; time " + event.getTime() + " -- linkId: " + event.getLinkId()
                + " personId: " + event.getPersonId());
        departureTimeByPersonMap.put(event.getPersonId(), event.getTime());
    }

    @Override
    public void handleEvent(PersonArrivalEvent event) {
        double departureTime = departureTimeByPersonMap.get(event.getPersonId());
        double travelTime = event.getTime() - departureTime;
        System.out.println("Arrival event; time " + event.getTime() + " -- linkId: " + event.getLinkId() + " -- Travel time: " + travelTime + " -- personId: " + event.getPersonId());
    }
}