package core.dataclasses;

import org.json.JSONException;
import org.json.JSONObject;

public class InvalidData {

  private String errorMessage;

  public InvalidData(JSONObject jsonObject) {
      try {
        this.errorMessage = jsonObject.getString("errorMessage");
    } catch (JSONException e) {
        e.printStackTrace();
    }
  }

  public String getErrorMessage() {
      return errorMessage;
  }
}
