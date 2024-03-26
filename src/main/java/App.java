import resources.configs.util.WorldCreator;
import services.GameStarter;
import world.constants.Constants;


public class App
{
    public static void main( String[] args )
    {
        WorldCreator worldCreator = new WorldCreator(Constants.WORLD_SIZE_X, Constants.WORLD_SIZE_Y);
        GameStarter gameStarter = new GameStarter(worldCreator.worldMap);
        gameStarter.init();
    }
}
