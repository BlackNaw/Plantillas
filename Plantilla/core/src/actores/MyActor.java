package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import global.Constantes;
import interfaz.IReiniciable;

public class MyActor extends Actor implements IReiniciable {

	Body body;
	Shape shape;
	Sprite sprite;
	Texture texture;
	BodyDef bodyDef = new BodyDef();
	FixtureDef fixtureDef = new FixtureDef();

	public MyActor(World world, float posX, float posY) {
		texture = new Texture(Gdx.files.internal("cabeza.png"));
		sprite = new Sprite(texture);
		sprite.setPosition(posX, posY);
		sprite.setSize(40, 40);
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(posX, posY);
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
		
		shape = new PolygonShape();
		
		((PolygonShape)shape).setAsBox((sprite.getWidth()/3)/Constantes.PIXELS_TO_METERS, (sprite.getHeight()/3)/Constantes.PIXELS_TO_METERS);
		fixtureDef.shape=shape;
		fixtureDef.density=1f;
		
		body=world.createBody(bodyDef);
		body.createFixture(fixtureDef);
//		body.setBullet(true);
		//True rota false no rota
		body.setFixedRotation(true);
		//Importante para el contact
		body.setUserData(this);
		// Para cambiar la textura usar
		// sprite.setTexture(texture);
		shape.dispose();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		sprite.setPosition(
			    (body.getPosition().x * Constantes.PIXELS_TO_METERS) - sprite.getWidth() / 2,
			    (body.getPosition().y * Constantes.PIXELS_TO_METERS) - sprite.getHeight() / 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
	}
	
	public void moverIzquierda(){
		body.setLinearVelocity(-Constantes.VELOCIDAD_JUGADOR,0);
	}
	public void moverDerecha(){
		body.setLinearVelocity(Constantes.VELOCIDAD_JUGADOR,0);
	}
	public void moverArriba(){
		body.setLinearVelocity(0,Constantes.VELOCIDAD_JUGADOR);
	}
	public void moverAbajo(){
		body.setLinearVelocity(0,-Constantes.VELOCIDAD_JUGADOR);
	}
	
	public void parar(){
		body.setLinearVelocity(0,0);
	}

	@Override
	public void reiniciar() {
		sprite.setColor(Color.BLUE);		
	}
}
