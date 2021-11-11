package core.dataclasses;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LobbyStatus {

    private int numberOfClients;
    private ArrayList<LobbyPlayer> players;

    public LobbyStatus(JSONObject jsonObject) {
        players = new ArrayList<>();
        try {
            this.numberOfClients = jsonObject.getInt("nClients");
            JSONArray jsonArray = jsonObject.getJSONArray("players");
            for (int i = 0; i < numberOfClients; i++) {
                players.add(new LobbyPlayer(jsonArray.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public ArrayList<LobbyPlayer> getPlayers() {
        return players;
    }

}
