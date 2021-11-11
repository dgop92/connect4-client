package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public class InGamePlayer extends Player {

    private int cumulativeTime;
    private boolean connected;

    public InGamePlayer(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
        this.cumulativeTime = jsonObject.getInt("cumulativeTime");
        this.connected = jsonObject.getBoolean("connected");
    }

    public boolean isConnected() {
        return connected;
    }

    public int getCumulativeTime() {
        return cumulativeTime;
    }
}
