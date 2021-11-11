package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public class TickData {
    
    private int time;

    public TickData(JSONObject jsonObject) {
        try {
            this.time = jsonObject.getInt("time");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTime() {
        return time;
    }
}
