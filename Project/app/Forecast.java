import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Forecast {
    private List<Data> data;

    public static Forecast fromJson(JSONArray jsonArray) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setData(Data.fromJson(jsonArray));
        return forecast;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private Double minTemp;
        private Double maxTemp;
        private Double windSpeed;
        private Double snow;

        public static List<Data> fromJson(JSONArray jsonArray) throws JSONException {
            List<Data> dataList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Data data = new Data();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                data.setMinTemp(jsonObject.getDouble("min_temp"));
                data.setMaxTemp(jsonObject.getDouble("max_temp"));
                data.setWindSpeed(jsonObject.getDouble("wind_spd"));
                data.setSnow(jsonObject.getDouble("snow"));
                dataList.add(data);
            }
            return dataList;
        }

        public Double getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(Double minTemp) {
            this.minTemp = minTemp;
        }

        public Double getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(Double maxTemp) {
            this.maxTemp = maxTemp;
        }

        public Double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public Double getSnow() {
            return snow;
        }

        public void setSnow(Double snow) {
            this.snow = snow;
        }
    }
}
