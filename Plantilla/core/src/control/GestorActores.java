package control;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.MyActor;
import actores.MyActor2;
import interfaz.IReiniciable;

public class GestorActores {

	Actor myActor;
	Actor myActor2;
	ArrayList<IReiniciable> actoresReiniciable=new ArrayList<IReiniciable>();
	ArrayList<Actor> actors=new ArrayList<Actor>();
	/**
	 * Crea los actores
	 * @param world
	 */
	public GestorActores(World world,Stage stage) {
		myActor = new MyActor(world,20f,20f);
		myActor2 = new MyActor2(world,60f,60f);
		
		actoresReiniciable.add((IReiniciable)myActor);
		actoresReiniciable.add((IReiniciable)myActor2);
		this.anadirActoresStage(stage);
	}

	/**
	 *  A�ade los actores al stage
	 * @param stage
	 */
	private void anadirActoresStage(Stage stage) {
		stage.addActor(myActor);
		stage.addActor(myActor2);
    }
	
	public MyActor getActorUno(){
		return (MyActor) myActor;
	}
	
	public ArrayList<Actor> getActors() {
		return actors;
	}
	
	public ArrayList<IReiniciable> getActoresReiniciable() {
		return actoresReiniciable;
	}
}
