package core.dataclasses;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameState {
    
    private int m;
    private int n;
    private ArrayList<InGamePlayer> players; 
    private CellData[][] connect4Table;

    public GameState(JSONObject parentJsonObject) {
        try {
            players = new ArrayList<>();

            JSONObject state = parentJsonObject.getJSONObject("state");
            JSONArray playersObj = parentJsonObject.getJSONArray("players");
            
            for (int i = 0; i < playersObj.length(); i++) {
                JSONObject playerObj = playersObj.optJSONObject(i);
                players.add(new InGamePlayer(playerObj));
            }

            this.m = state.getInt("m");
            this.n = state.getInt("n");
            this.connect4Table = new CellData[n][m];
            JSONArray table = state.getJSONArray("table");
            for (int i = 0; i < n; i++) {
                JSONArray row = table.optJSONArray(i);
                for (int j = 0; j < m; j++) {
                    JSONObject rawObject = row.optJSONObject(j);
                    if (rawObject != null) {
                        CellData cellData = new CellData(rawObject.getString("color"));
                        this.connect4Table[i][j] = cellData;
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CellData[][] getConnect4Table() {
        return connect4Table;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public ArrayList<InGamePlayer> getPlayers() {
        return players;
    }
}
