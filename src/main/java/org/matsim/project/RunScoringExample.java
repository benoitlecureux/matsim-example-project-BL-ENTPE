package org.matsim.project;

import com.google.inject.Inject;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.events.Event;
import org.matsim.api.core.v01.events.LinkEnterEvent;
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

public class RunScoringExample {/*

    private static class MyScoringFunctionFactory implements ScoringFunctionFactory {
        @Inject
        private Network network;
        @Inject private ScoringParametersForPerson pparams;

        @Override public ScoringFunction createNewScoringFunction(final Person person) {
            final ScoringParameters params = pparams.getScoringParameters(person);
            SumScoringFunction ssf = new SumScoringFunction();
            ssf.addScoringFunction(new CharyparNagelLegScoring(params, network));
            ssf.addScoringFunction(new CharyparNagelActivityScoring(params));
            ssf.addScoringFunction(new CharyparNagelMoneyScoring(params));
            ssf.addScoringFunction(new CharyparNagelAgentStuckScoring(params));

            SumScoringFunction.BasicScoring aabb = new MyBasicFunction();
            ssf.addScoringFunction(aabb);

            return ssf;
        }

        private class MyBasicFunction implements SumScoringFunction.ArbitraryEventScoring {
            double score = 0;

            @Override
            public void handleEvent(Event event) {
                if ( event instanceof LinkEnterEvent) {
                    if (((LinkEnterEvent) event).getLinkId().toString().equals("6")) {
                        // give penalty if using link number 6:
                        score -= 10.;
                    }
                }
            }

            @Override
            public void finish() {
            }

            @Override
            public double getScore() {
                return score;
            }
        }
    }

    public static void main(String[] args) {
        Config config = ConfigUtils.loadConfig("scenarios/equil/config.xml");

        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        config.controler().setLastIteration(10);

        Scenario scenario = ScenarioUtils.loadScenario( config );

        Controler controler = new Controler( scenario );

        controler.addOverridingModule(new AbstractModule) {
            @Override public void install () {
                throw new RuntimeException("not implemented");

                ScoringFunctionFactory abc = new MyScoringFunctionFactory();
                this.bindScoringFunctionFactory().toInstance(abc);


            }
        }

        controler.run();
    }*/
}