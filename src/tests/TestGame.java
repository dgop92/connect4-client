package tests;

import org.json.JSONException;
import org.json.JSONObject;

import core.dataclasses.GameState;
import game.GameSketch;
public class TestGame {
    
    public static void main(String[] args) {
        try {
            JSONObject jsonObject = new JSONObject("{\r\n  \"state\": {\r\n    \"table\": [\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null],\r\n      [null, null, null, null, null, null, null, null]\r\n    ],\r\n    \"m\": 8,\r\n    \"n\": 8\r\n  },\r\n  \"players\": [\r\n    {\r\n      \"color\": \"orange\",\r\n      \"connected\": true,\r\n      \"cumulativeTime\": 0,\r\n      \"username\": \"casa\"\r\n    },\r\n    {\r\n      \"color\": \"blue\",\r\n      \"connected\": true,\r\n      \"cumulativeTime\": 0,\r\n      \"username\": \"suci\"\r\n    }\r\n  ]\r\n}");
            GameSketch g = new GameSketch();
            g.run();
            GameState gst = new GameState(jsonObject);
            g.onUpdateState(gst);
            g.onPlayerTurn(gst.getPlayers().get(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        
    }
}
