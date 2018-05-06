package part4.BlusterCritter;

import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;

public class BlusterCritter extends Critter {
	private int c;
	public BlusterCritter(int c) {
		// TODO Auto-generated constructor stub
		this.c=c;
		setColor(Color.RED);
	}
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> actors=new ArrayList<Actor>();
		int row=getLocation().getRow();
		int col=getLocation().getCol();
		Location temp;
		
		for(int i=row-2;i<=row+2;++i) {
			for(int j=col-2;j<=col+2;++j) {
				temp=new Location(i, j);
				if(!this.getGrid().isValid(temp)) {
					break;
				}
				if(temp.equals(this.getLocation())){
					break;
				}

				Actor actor=getGrid().get(temp);
				if(actor instanceof Critter) {
					actors.add(actor);
				}
			}
		}
		
		return actors;	
	}
	
	public void processActors(ArrayList<Actor> actors) {
		//System.out.println(actors.size());
		if(actors.size()>=c) {
			Color d = this.getColor();
    		int red = (int) ((double) d.getRed() * 0.9D);
    		int green = (int) ((double) d.getGreen() * 0.9D);
    		int blue = (int) ((double) d.getBlue() * 0.9D);
    		this.setColor(new Color(red, green, blue));
		}
		else {
			Color d = this.getColor();
    		int red = (int) ((double) (d.getRed()+10) * 1.1D);
    		int green = (int) ((double) (d.getGreen()+10) * 1.1D);
    		int blue = (int) ((double) (d.getBlue()+10) * 1.1D);
    		if(red>240) {
    			red=240;
    		}
    		if(green>240) {
    			green=240;
    		}
    		if(blue>240) {
    			blue=240;
    		}
    		this.setColor(new Color(red, green, blue));
		}

	}
}
