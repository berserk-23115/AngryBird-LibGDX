package game.dev.pigs;

import com.badlogic.gdx.graphics.Texture;

public class smallpig extends pigs{
    private Texture pigTexture;
    private Integer Health;
    public smallpig(Texture pigTexture) {
        super(pigTexture, 10);
    }
}
