package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Player {

    private String username;
    private String colorAsString;

    public Player(JSONObject jsonObject) throws JSONException {
        this.username = jsonObject.getString("username");
        this.colorAsString = jsonObject.getString("color");
    }

    public String getUsername() {
        return username;
    }
    
    public String getColorAsString() {
        return colorAsString;
    }

    @Override
    public String toString() {
        return username;
    }
}
