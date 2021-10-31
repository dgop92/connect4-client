package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public class TurnData {
    
    private int timeConsumed;

    public TurnData(JSONObject jsonObject) {
        try {
            this.timeConsumed = jsonObject.getInt("timeConsumed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTimeConsumed() {
        return timeConsumed;
    }
}
