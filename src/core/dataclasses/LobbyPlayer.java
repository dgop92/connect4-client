package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public class LobbyPlayer extends Player{

    public LobbyPlayer(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
    
}
