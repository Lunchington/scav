package com.breakfastcraft.scav.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.breakfastcraft.scav.GameObjects.Player;
import com.breakfastcraft.scav.GameObjects.Star;
import com.breakfastcraft.scav.Global;
import com.breakfastcraft.scav.Main;
import com.breakfastcraft.scav.Map.Chunk;
import com.breakfastcraft.scav.Map.Map;
import com.breakfastcraft.scav.controllers.HudController;
import com.breakfastcraft.scav.input.GameInput;
import com.breakfastcraft.scav.input.PlayerMovement;
import com.breakfastcraft.scav.managers.ArtManager;
import com.breakfastcraft.scav.managers.MusicManager;
import com.breakfastcraft.scav.managers.PreferencesManager;

public class GamePlayScreen extends AbstractScreen {

    private HudController hudController;

    private GameInput gameInput;
    private PlayerMovement pInput;

    private OrthographicCamera gameCamera;
    private Viewport viewport;

    private Map map;

    private SpriteBatch batch;

    private Table debug;
    private Label thrust;

    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;


    public GamePlayScreen() {
        batch = new SpriteBatch();
        gameInput = new GameInput(this);
        hudController = new HudController(stage);

        gameCamera = new OrthographicCamera( Global.WIDTH, Global.HEIGHT);


        debugRenderer = new Box2DDebugRenderer();

        map = new Map(3,3,gameCamera);

        Player p = new Player(map.getChunk(2,2).getChunkWorldCenter());
        map.setPlayer(p);

        pInput = new PlayerMovement(map.getPlayer());

        debug = new Table();
    }


    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gameInput);
        multiplexer.addProcessor(pInput);

        Gdx.input.setInputProcessor(multiplexer);

        if(PreferencesManager.getInstance().isMusicEnabled())
            MusicManager.getInstance().play(MusicManager.GameMusic.GAME);

        Skin skin = ArtManager.getInstance().getGUI();
        skin.getFont("default-font").getData().markupEnabled = true;
        skin.getFont("small-font").getData().markupEnabled = true;

        debug.setFillParent(true);
        debug.top().left();
        debug.defaults().pad(5f);
        thrust = new Label("",skin);
        debug.add(thrust);
        stage.addActor(debug);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        debugMatrix = batch.getProjectionMatrix().cpy();

        stage.setDebugAll(Main.DEV_MODE);
        update(delta);

        batch.setProjectionMatrix(gameCamera.combined);

        batch.begin();
            map.render(batch);
        batch.end();
        debugRenderer.render(map.getPhysicsWorld(), debugMatrix);
        stage.draw();

        System.out.println(Gdx.graphics.getFramesPerSecond());
    }

    private void update(float delta) {
        float lerp = 25f;

        Player p = map.getPlayer();

        float px = MathUtils.clamp(p.getX() , p.getWidth(),map.getMapWorldWidth()-p.getWidth());
        float py = MathUtils.clamp(p.getY(), p.getHeight(),map.getMapWorldHeight()-p.getHeight());


        map.getPlayer().setPosition(px,py);

        Vector3 position = gameCamera.position;

        position.x+= (map.getPlayer().getX() - position.x) * lerp * delta;
        position.y+= (map.getPlayer().getY() - position.y) * lerp * delta;

        float minX = gameCamera.zoom * (gameCamera.viewportWidth /2);
        float maxX = map.getMapWorldWidth() - minX;

        float minY = gameCamera.zoom *(gameCamera.viewportHeight /2);
        float maxY = map.getMapWorldHeight() - minY;




        gameCamera.position.set(
                MathUtils.clamp(position.x, minX,maxX),
                MathUtils.clamp(position.y, minY,maxY),
                0);
        gameCamera.update();

        map.update(delta);
        hudController.update(delta);

        Chunk c = map.getChunkFromWorldPos(pInput.getPlayerPosition());
        Vector2 chunkPos = c.getObjChunkPostionFromWorld(map.getPlayer().getPosition());

        thrust.setText(String.format("Chunk info: %s \n Player info: worldPos: %s ChunKPos: %s",c.toString(),map.getPlayer().getPosition(),chunkPos));
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }

}
