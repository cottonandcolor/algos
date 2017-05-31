package dfix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import org.json.simple.parser.JSONParser;

/**
 * Account Email: preetidav@gmail.com
 Account ID: e38f4093-0f01-4d7c-9a96-2025c211ba44

 DycmJivdJdCEQpjce2j8f1GAi27qMzKO8weG2YNN
 * json-simple for parsing/encoding JSON.

 https://api.nasa.gov/api.html#imagery
 guava provides immutable collections and other handy utility classes.

 Apache Commons Lang for assorted utilities.
 */
public class Nasa1 {

    private static final String IMG_QUERY_URL = "https://api.nasa.gov/planetary/earth/assets?";
    private static final String API_KEY = "DycmJivdJdCEQpjce2j8f1GAi27qMzKO8weG2YNN";//"DEMO_KEY";//

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Double lat = scanner.nextDouble();
        Double lon = scanner.nextDouble();
        try {
            System.out.println("Next time: " +findNextTime(lat, lon));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Unable to find next time " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Unable to find next time " + e.getMessage());
        }
    }

    private static Date findNextTime(Double lat, Double lon) throws IOException, ParseException {
        return callNasaGetLatest(lat, lon);
    }

    private static Date callNasaGetLatest(final Double lat, final Double lon) throws MalformedURLException, ProtocolException, IOException, ParseException {
        //TODO check lat lon val ?? retry if timeout
        if(lat == null || lon == null){
            throw new IllegalArgumentException("Please specify latitude and longitude.");
        }
        Calendar calNow = Calendar.getInstance();
        calNow.add(Calendar.MONTH, -6);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calNow.getTime());
        StringBuilder url = new StringBuilder(IMG_QUERY_URL);
       // url.append("lon=").append(lon).append("&lat=").append(lat).append("&date=").append("2017-04-17").append("&api_key=").append(API_KEY);
        url.append("lon=").append(lon).append("&lat=").append(lat).append("&begin=").append(date).append("&api_key=").append(API_KEY);

        //String url = "https://api.nasa.gov/planetary/earth/imagery?lon=100.75&lat=1.5&api_key=DycmJivdJdCEQpjce2j8f1GAi27qMzKO8weG2YNN";
        HttpURLConnection con = null;
        BufferedReader in = null;
        try {
            //URL obj = new URL("https://api.nasa.gov/planetary/earth/imagery?lon=100.75&lat=1.5&date=2014-02-01&cloud_score=True&api_key=DEMO_KEY");
            URL obj = new URL(url.toString());
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode(); //if response code 429 try again

            if(responseCode == 200) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                String output = response.toString();
                //TODO use simplejson
                String lastDate = output.substring(output.lastIndexOf("\"date\""), output.lastIndexOf(","));
                String regex = "\"(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})\"";
                Matcher m = Pattern.compile(regex).matcher(lastDate);
                if (m.find()) {
                    System.out.println(response);
                    System.out.println(m.group(1));
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(m.group(1));

                    long delta = System.currentTimeMillis() - date2.getTime();
                    Date currentDate = new Date();

                    if(currentDate.equals(date2) || currentDate.before(date2)) {
                        return date2;
                    } else { // last is in the past
                        int diffDays = (int)(delta / (24 * 60 * 60 * 1000));
                        Calendar cal1 = Calendar.getInstance();
                        cal1.setTime(date2);
                        cal1.add(Calendar.DAY_OF_MONTH, 16*(1+(diffDays/16)));
                        return cal1.getTime();
                    }
                }
            }
        }
        finally {
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
        throw new RuntimeException("Unable to find next time");
    }
}
