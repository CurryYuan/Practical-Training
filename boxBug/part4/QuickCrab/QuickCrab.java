package part4.QuickCrab;

import java.util.ArrayList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import sourceCode.CrabCritter;

public class QuickCrab extends CrabCritter {
    public ArrayList<Location> getMoveLocations()
    {
    	int[] directions =
            { Location.LEFT, Location.RIGHT };
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)&&getGrid().get(neighborLoc)==null) {
            	Location neighborLoc1=neighborLoc.getAdjacentLocation(getDirection()+d);
            	if (gr.isValid(neighborLoc1)&&getGrid().get(neighborLoc1)==null) {
            		locs.add(neighborLoc1);
            	}
            	else {
                    locs.add(neighborLoc);
            	}
            }

        }
        return locs;
    }   
	
}
