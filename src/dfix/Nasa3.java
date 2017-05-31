package dfix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Nasa3 {

    private static final String IMG_QUERY_URL = "https://api.nasa.gov/planetary/earth/assets?";
    private static final String API_KEY = "DycmJivdJdCEQpjce2j8f1GAi27qMzKO8weG2YNN";//"DEMO_KEY";//
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf_nasa = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final long avgDelta = 1382400000;

    public static void main(String[] args){
        try {
            flyby(1.5, 100.75);
            flyby(36.098592, -112.097796);
            flyby(43.078154, -79.075891);
            flyby(36.998979, -109.045183);
            flyby(37.7937007, -122.4039064);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Unable to find next time " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Unable to find next time " + e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println("Unable to find next time " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void flyby(Double lat, Double lon) throws IOException, ParseException, org.json.simple.parser.ParseException {
        System.out.println("Next time: " +callNasaGetLatest(getAssets(lat, lon)));
    }


    private static Date callNasaGetLatest(final String output) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(output);
        Long  count = (Long) jsonObject.get("count");

        JSONArray imgDates = (JSONArray) jsonObject.get("results");
        if(imgDates == null || imgDates.isEmpty()){
            throw new RuntimeException("Unable to find next time");
        }

        Date lastDate = sdf_nasa.parse((String)(((JSONObject)imgDates.get(imgDates.size() - 1)).get("date")));
        Date firstDate = sdf_nasa.parse((String)(((JSONObject)imgDates.get(0)).get("date")));
        long expectedCount = ((lastDate.getTime() - firstDate.getTime())/avgDelta) + 1;

        if(lastDate.equals(new Date())) { // nextTime is now
            return lastDate;
        }
        long delta = System.currentTimeMillis() - lastDate.getTime();
        long nextCount = 1 + delta/avgDelta;  // next interval in future
        // if images not printed in equal intervals of 16 days then probability of being printed in future is delayed
        // by count/expectedCount
        if(nextCount * count / expectedCount < 1) {
            nextCount = expectedCount / count ;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(lastDate);
        cal1.setTimeInMillis(lastDate.getTime() +  (avgDelta * nextCount));
        return cal1.getTime();

    }

    private static String getAssets(final Double lat, final Double lon) throws IOException {
        if(lat == null || lon == null){
            throw new IllegalArgumentException("Please specify latitude and longitude.");
        }
        Calendar calNow = Calendar.getInstance();
        calNow.add(Calendar.YEAR, -2); // sample data for past two years
        String date = sdf.format(calNow.getTime());
        StringBuilder url = new StringBuilder(IMG_QUERY_URL);
        url.append("lon=").append(lon).append("&lat=").append(lat).append("&begin=").append(date).append("&api_key=").append(API_KEY);
        HttpURLConnection con = null;
        BufferedReader in = null;
        try {
            URL obj = new URL(url.toString());
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int rCode = con.getResponseCode();
            if (rCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } else {
                throw new RuntimeException("Server returned error " +  rCode);
            }
        } finally {
            try {
                if (con != null) {
                    con.disconnect();
                }
            } finally {
                if (in != null){
                    in.close();
                }
            }
        }
    }

}
