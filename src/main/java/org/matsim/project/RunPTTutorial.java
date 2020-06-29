package org.matsim.project;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.pt.transitSchedule.api.*;
import org.matsim.pt.utils.TransitScheduleValidator;
import org.matsim.vehicles.Vehicle;
import org.matsim.vehicles.VehicleType;
import org.matsim.vehicles.VehiclesFactory;


public class RunPTTutorial {
    //Logger will write info in the console
    private final static Logger LOG = Logger.getLogger(RunPTTutorial.class);

    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("scenarios/pt-tutorial/O.config.xml");
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.overwriteExistingFiles);
        config.controler().setLastIteration(1);

        Scenario scenario = ScenarioUtils.loadScenario(config);

        //increaseServiceFrequency();
        //scenario.getTransitSchedule().getTransitLines().get("Blue Line").getRoutes().get("1to3").removeDeparture();
        TransitSchedule transitSchedule = scenario.getTransitSchedule();
        TransitLine blueLine= transitSchedule.getTransitLines().get(Id.create("Blue Line", TransitLine.class));
        // frequently used : Id.createLinkId ...

        TransitRoute route1to3 = blueLine.getRoutes().get(Id.create("1to3", TransitRoute.class));

        // create schedule legs
        TransitScheduleFactory tsf = transitSchedule.getFactory();
        Departure departure = tsf.createDeparture(Id.create("newDep", Departure.class), (8*60.+45)*60.);

        route1to3.addDeparture(departure);

        // validator
        TransitScheduleValidator.ValidationResult result = TransitScheduleValidator.validateAll(transitSchedule, scenario.getNetwork());
        for (String error : result.getErrors()) {
            LOG.warn(error);
        }
        for (String warning : result.getWarnings()) {
            LOG.warn(warning);
        }
        for (TransitScheduleValidator.ValidationResult.ValidationIssue issue : result.getIssues()) {
            LOG.warn(issue.getMessage());
        }

        // association of the departure with a vehicle
        VehiclesFactory vf = scenario.getVehicles().getFactory(); //creating veh factory
        Id<Vehicle> vehId = Id.createVehicleId("tr_3"); // creating a vehicle
        VehicleType smallTrain = scenario.getTransitVehicles().getVehicleTypes().get(Id.create("1", VehicleType.class));
        Vehicle vehicle = vf.createVehicle(vehId,smallTrain);
        scenario.getTransitVehicles().addVehicle(vehicle);

        departure.setVehicleId(vehId);

        TransitScheduleWriter tsw = new TransitScheduleWriter(transitSchedule);
        tsw.writeFile("scenarios/pt-tutorial/transitSchedule.xml");

        Controler controler = new Controler(scenario);

        controler.run();
    }
}
