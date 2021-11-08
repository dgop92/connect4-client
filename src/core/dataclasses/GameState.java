package core.dataclasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameState {
    
    private int m;
    private int n;
    private CellData[][] connect4Table;

    public GameState(JSONObject parentJsonObject) {
        try {
            JSONObject jsonObject = parentJsonObject.getJSONObject("state");
            this.m = jsonObject.getInt("m");
            this.n = jsonObject.getInt("n");
            this.connect4Table = new CellData[n][m];
            JSONArray table = jsonObject.getJSONArray("table");
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
}
