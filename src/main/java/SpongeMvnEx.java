import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

// Imports for logger
import com.google.inject.Inject;
import org.slf4j.Logger;

@Plugin(id = "spongemvnex", name = "Sponge Maven Example", version = "1.0", description = "Maven Example")
public class SpongeMvnEx {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running the Sponge Maven Example Plugin!");
    }

}