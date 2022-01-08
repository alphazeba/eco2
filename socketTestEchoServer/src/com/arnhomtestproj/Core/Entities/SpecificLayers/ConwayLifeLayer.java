package com.arnhomtestproj.Core.Entities.SpecificLayers;

import com.arnhomtestproj.Core.Entities.Data.GridCell;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Entities.Data.WorldContext;
import com.arnhomtestproj.Core.Entities.GridBasedLayer;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.SpecificEvents.DiedEvent;
import com.arnhomtestproj.Core.Events.SpecificEvents.PlantedEvent;

public class ConwayLifeLayer extends GridBasedLayer {
    private static Position[] directions =
    {
            new Position(-1,0),
            new Position(1,0),
            new Position(0,-1),
            new Position(0,1),
            new Position(1,1),
            new Position(-1,1),
            new Position( 1,-1),
            new Position(-1, -1)
    };

    public ConwayLifeLayer() {
        super("conwayLife");
    }

    @Override
    public void setWorldContext(WorldContext world){
        super.setWorldContext(world);
        initOscillator();
    }

    private void initOscillator(){
        grid.set(new Position(1,3),1);
        grid.set(new Position(2,3),1);
        grid.set(new Position(3,3),1);
    }

    @Override
    public Events getDecisions() {
        Events decisions = new Events();
        for(GridCell cell: grid){
            int surroundingCells = numSurroundingCells(cell.getPos());
            if(cellAlive(cell.getPos())){
                if( surroundingCells < 2 || surroundingCells > 3){
                    decisions.add(cellDies(cell.getPos()));
                }
            }
            else{
                if( surroundingCells == 3){
                    decisions.add(cellIsBorn(cell.getPos()));
                }
            }
        }
        return decisions;
    }

    private Event cellDies(Position pos){
        return new DiedEvent(getFullAddressOfPosition(pos));
    }

    private Event cellIsBorn(Position pos){
        return new PlantedEvent(getFullAddressOfPosition(pos),pos, getLayerAddress());
    }


    private boolean cellAlive(Position pos){
        return grid.hasAValue(pos);
    }

    private int numSurroundingCells(Position pos){
        int surroundingCells = 0;
        for(Position offset: directions){
            Position testSpot = pos.add(offset);
            if (grid.hasAValue(testSpot))
                surroundingCells++;
        }
        return surroundingCells;
    }

    @Override
    protected Events handleLayerUniqueEvents(Events events) {
        return null;
    }
}
