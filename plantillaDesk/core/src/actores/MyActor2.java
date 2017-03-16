package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import comun.BodyEditorLoader;
import control.MaquinaEstados;
import global.Constantes;
import interfaz.IChocable;
import interfaz.IReiniciable;

public class MyActor2 extends Actor implements IChocable, IReiniciable {
	Body body;
	Sprite sprite;
	Texture texture;
	BodyDef bodyDef = new BodyDef();
	FixtureDef fixtureDef = new FixtureDef();

	public MyActor2(World world, float posX, float posY) {
		texture = new Texture(Gdx.files.internal("avion.png"));
		sprite = new Sprite(texture);
		// Para redimensionar imagen
		sprite.setSize(40, 40);

		sprite.setOrigin(0, 0);

		bodyDef.type = BodyType.DynamicBody;

		////////// SI TRAMEOS LA FIXTURA DEL PHYSICS BODY EDITOR
		////////// LOADER//////////////////////////////////////
		FileHandle file = Gdx.files.internal("avion.json");
		String cadena = file.readString();
		BodyEditorLoader loader = new BodyEditorLoader(cadena);
		bodyDef.position.set(posX / Constantes.PIXELS_TO_METERS, posY / Constantes.PIXELS_TO_METERS);
		fixtureDef.density = 1f;
		fixtureDef.friction = 3f;
		fixtureDef.restitution = 0.0f;
		// Para hacer filtros
		// fixtureDef.filter.maskBits=
		// fixtureDef.filter.categoryBits=

		body = world.createBody(bodyDef);

		loader.attachFixture(body, "avion", fixtureDef, sprite.getWidth() / Constantes.PIXELS_TO_METERS);
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		body.setUserData(this);

		/////// CONFIGURACIONES DEL BODY//////
		body.setFixedRotation(false);
		body.setBullet(false);
		/////////////////////////////////////

	}

	@Override
	public void act(float delta) {
		super.act(delta);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// Ajusta el sprite al body
		sprite.setPosition((body.getPosition().x * Constantes.PIXELS_TO_METERS),
				(body.getPosition().y * Constantes.PIXELS_TO_METERS));
		sprite.setRotation((float) Math.toDegrees(body.getAngle()));
		// Pinta el sprite
		sprite.draw(batch);

	}

	@Override
	public void chocar() {
		body.applyLinearImpulse(new Vector2(.2f, 0), body.getWorldCenter(), true);
		MaquinaEstados.juegoTerminado.setEstado(true);
	}

	@Override
	public void reiniciar() {
		sprite.setColor(Color.RED);
	}

}
