package com.example.astroweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDate.now;

public class Model extends ViewModel {
    public static final String api_key = "db8581c884fc364e9c695409c3258377";
    public static double szerokosc1 = 51.75;
    public static double dlugosc1 = 19.45;
    public static String sz;
    public static String dl;
    public static boolean cityExist = false;
    public static String city_static ;
    public static int time = 10;
    private AstroCalculator astroCalculator;
    private AstroCalculator.Location location;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    ScheduledFuture scheduledFuture;
    private Context mainContext = null;
    private RequestQueue requestQueue;
    private String units = "metric";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private final MutableLiveData<String> latitude;
    private final MutableLiveData<String> longitude;
    private final MutableLiveData<String> sunSetA;
    private final MutableLiveData<String> dawn;
    private final MutableLiveData<String> dusk;
    private final MutableLiveData<String> sunRiseT;
    private final MutableLiveData<String> sunSetT;
    private final MutableLiveData<String> sunRiseA;
    private final MutableLiveData<String> dlugosc;
    private final MutableLiveData<String> szerokosc;


    private final MutableLiveData<String> moonRise;
    private final MutableLiveData<String> moonSet;
    private final MutableLiveData<String> interlunar;
    private final MutableLiveData<String> moonFull;
    private final MutableLiveData<String> moonPhase;
    private final MutableLiveData<String> moonDaysToInterlunar;


    private final MutableLiveData<String> temperature;
    private final MutableLiveData<String> pressure;
    private final MutableLiveData<String> humidity;
    private final MutableLiveData<String> visibility;
    private final MutableLiveData<String> windPower;
    private final MutableLiveData<String> windDirection;
    private final MutableLiveData<String> icon;
    private final MutableLiveData<String> city;

    private final MutableLiveData<String> next_1_temp;
    private final MutableLiveData<String> next_2_temp;
    private final MutableLiveData<String> next_3_temp;
    private final MutableLiveData<String> next_4_temp;
    private final MutableLiveData<String> next_5_temp;

    private final MutableLiveData<String> next_1_date;
    private final MutableLiveData<String> next_2_date;
    private final MutableLiveData<String> next_3_date;
    private final MutableLiveData<String> next_4_date;
    private final MutableLiveData<String> next_5_date;

    private final MutableLiveData<String> next_1_icon;
    private final MutableLiveData<String> next_2_icon;
    private final MutableLiveData<String> next_3_icon;
    private final MutableLiveData<String> next_4_icon;
    private final MutableLiveData<String> next_5_icon;
    public LiveData<String> getLongitude(){return longitude;}
    public LiveData<String> getLatitude(){return latitude;}
    public LiveData<String> getTemp(){return temperature;}
    public LiveData<String> getPres(){return pressure;}
    public LiveData<String> getHum(){return humidity;}
    public LiveData<String> getWindP(){return windPower;}
    public LiveData<String> getWindD(){return windDirection;}
    public LiveData<String> getVis(){return visibility;}
    public LiveData<String> getIcon(){return icon;}
    public LiveData<String> getCity(){return city;}
    public LiveData<String> getNext_1_Date(){return next_1_date;}
    public LiveData<String> getNext_2_Date(){return next_2_date;}
    public LiveData<String> getNext_3_Date(){return next_3_date;}
    public LiveData<String> getNext_4_Date(){return next_4_date;}
    public LiveData<String> getNext_5_Date(){return next_5_date;}
    public LiveData<String> getNext_1_Temp(){return next_1_temp;}
    public LiveData<String> getNext_2_Temp(){return next_2_temp;}
    public LiveData<String> getNext_3_Temp(){return next_3_temp;}
    public LiveData<String> getNext_4_Temp(){return next_4_temp;}
    public LiveData<String> getNext_5_Temp(){return next_5_temp;}
    public LiveData<String> getNext_1_Icon(){return next_1_icon;}
    public LiveData<String> getNext_2_Icon(){return next_2_icon;}
    public LiveData<String> getNext_3_Icon(){return next_3_icon;}
    public LiveData<String> getNext_4_Icon(){return next_4_icon;}
    public LiveData<String> getNext_5_Icon(){return next_5_icon;}
    public AstroCalculator.Location getLocation(){
        return location;
    }
    public LiveData<String> getSunRiseT(){
        return sunRiseT;
    }
    public LiveData<String> getSunRiseA(){
        return sunRiseA;
    }
    public LiveData<String> getSunSetT(){
        return sunSetT;
    }
    public LiveData<String> getSunSetA(){
        return sunSetA;
    }
    public LiveData<String> getDawn(){
        return dawn;
    }
    public LiveData<String> getDusk(){
        return dusk;
    }
    //k
    public LiveData<String> getMoonRise(){
        return moonRise;
    }
    public LiveData<String> getMoonSet(){
        return moonSet;
    }
    public LiveData<String> getInterlunar(){
        return interlunar;
    }
    public LiveData<String> getMoonFull(){
        return moonFull;
    }
    public LiveData<String> getMoonPhase(){
        return moonPhase;
    }
    public LiveData<String> getMoonDaysToInterlunar(){
        return moonDaysToInterlunar;
    }
    private AstroCalculator.Location setLocation( double szerokosc1, double dlugosc1){
        dlugosc.setValue(String.format("%.3f", dlugosc1));
        szerokosc.setValue(String.format("%.3f", szerokosc1));

        return new AstroCalculator.Location(szerokosc1, dlugosc1);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Model(){
        temperature= new MutableLiveData<String>();
        pressure= new MutableLiveData<String>();
        latitude= new MutableLiveData<String>();
        longitude= new MutableLiveData<String>();
        icon = new MutableLiveData<String>();
        city = new MutableLiveData<String>();
        windDirection= new MutableLiveData<String>();
        windPower= new MutableLiveData<String>();
        visibility= new MutableLiveData<String>();
        humidity= new MutableLiveData<String>();
        next_1_temp = new MutableLiveData<String>();
        next_2_temp = new MutableLiveData<String>();
        next_3_temp = new MutableLiveData<String>();
        next_4_temp = new MutableLiveData<String>();
        next_5_temp = new MutableLiveData<String>();
        next_1_icon = new MutableLiveData<String>();
        next_2_icon = new MutableLiveData<String>();
        next_3_icon = new MutableLiveData<String>();
        next_4_icon = new MutableLiveData<String>();
        next_5_icon = new MutableLiveData<String>();
        next_1_date= new MutableLiveData<String>();
        next_2_date= new MutableLiveData<String>();
        next_3_date= new MutableLiveData<String>();
        next_4_date= new MutableLiveData<String>();
        next_5_date= new MutableLiveData<String>();
        szerokosc= new MutableLiveData<String>();
        dlugosc = new MutableLiveData<String>();
        dusk = new MutableLiveData<String>();
        dawn = new MutableLiveData<String>();
        sunRiseT = new MutableLiveData<>();
        sunRiseA = new MutableLiveData<String>();
        sunSetT = new MutableLiveData<String>();
        sunSetA = new MutableLiveData<String>();
        moonDaysToInterlunar = new MutableLiveData<String>();
        moonFull = new MutableLiveData<String>();
        moonPhase  = new MutableLiveData<String>();
        moonRise = new MutableLiveData<String>();
        moonSet = new MutableLiveData<String>();
        interlunar = new MutableLiveData<String>();
        Random rand = new Random();

        Double newLatitude;
        Double newLongitude;
        if (sharedPreferences != null){
            newLatitude = Double.parseDouble(sharedPreferences.getString("latitude", "51.7833"));
            newLongitude = Double.parseDouble(sharedPreferences.getString("longitude", "19.4667"));
        }
        else {
            newLatitude = 51.7833;
            newLongitude = 19.4667;
        }
        location = setLocation(newLatitude, newLongitude);
        setCityName(city_static);
        AstroDateTime date = new AstroDateTime();
        date.setTimezoneOffset(2);
        date.setYear(now().getYear());
        date.setMonth(now().getMonthValue());
        date.setDay(now().getDayOfMonth());
        date.setHour(LocalTime.now().getHour());
        date.setMinute(LocalTime.now().getMinute());
        date.setSecond(LocalTime.now().getSecond());
        setData();

        astroCalculator = new AstroCalculator(date,location);

    }
    private void setData(){
        scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(()->{
            AstroDateTime sunRiseT1 = astroCalculator.getSunInfo().getSunrise();
            sunRiseT.postValue(sunRiseT1.getHour() + " : " + sunRiseT1.getMinute());//1

            Double sunRiseA1 = astroCalculator.getSunInfo().getAzimuthRise();
           sunRiseA.postValue(String.format("%.1f", sunRiseA1));//?

            AstroDateTime sunSet1 = astroCalculator.getSunInfo().getSunset();
            sunSetT.postValue(sunSet1.getHour() + " : " + sunSet1.getMinute());//1

            Double sunSetT1 = astroCalculator.getSunInfo().getAzimuthSet();
            sunSetA.postValue(String.format("%.1f", sunSetT1));//?

            AstroDateTime dusk1 = astroCalculator.getSunInfo().getTwilightEvening();
            dusk.postValue(dusk1.getHour() + " : " + dusk1.getMinute());//1

            AstroDateTime dawn1 = astroCalculator.getSunInfo().getTwilightMorning();
            dawn.postValue(dawn1.getHour() + " : " + dawn1.getMinute());//1

            AstroDateTime moonRise1 = astroCalculator.getMoonInfo().getMoonrise();
            moonRise.postValue(moonRise1.getHour() + " : " + moonRise1.getMinute());//1

            AstroDateTime moonSet1 = astroCalculator.getMoonInfo().getMoonset();
            moonSet.postValue(moonSet1.getHour() + " : " + moonSet1.getMinute());//1

            AstroDateTime interlunar1 = astroCalculator.getMoonInfo().getNextNewMoon();
            interlunar.postValue(interlunar1.getDay() + "." + interlunar1.getMonth());//1

            AstroDateTime moonFul1 = astroCalculator.getMoonInfo().getNextFullMoon();
            moonFull.postValue(moonFul1.getDay() + "." + moonFul1.getMonth());//1

            Double moonPhase1 = astroCalculator.getMoonInfo().getIllumination();
            moonPhase.postValue(String.format("%.0f", moonPhase1*100) + "%");//1

            Double moonDays = astroCalculator.getMoonInfo().getAge();
            moonDaysToInterlunar.postValue(String.format("%.1f", moonDays));//1


        },0,time, TimeUnit.MINUTES);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void weather() throws JSONException {
//        if(cityExist){
//            requestQueue.add(City(sharedPreferences.getString("cityNamePreference", "Lodz"), editor));
//        }
//        if(!network()){
//            String response = sharedPreferences.getString("fullweather", "");
//            if(response.equals("")) return;
//            JSONObject json = new JSONObject(response);
//            build(json);
//        }
        if(sharedPreferences.getBoolean("isCity", false)){
            city.postValue(sharedPreferences.getString("city","Lodz"));
            requestQueue.add(City(editor));

        }
        else{
            requestQueue.add(Coor(editor));
            latitude.postValue(sharedPreferences.getString("latitude",""));
            longitude.postValue(sharedPreferences.getString("longitude",""));
        }
        String lon = sharedPreferences.getString("longitude","");
        if(lon.equals("")) lon = dl;
        String lat = sharedPreferences.getString("latitude","");
        if(lat.equals("")) lat = sz;
        String url = "https://api.openweathermap.org/data/2.5/onecall" + "?lat=" + lat+ "&lon=" + lon + "&units=" + sharedPreferences.getString("units","metric") +"&appid=" + api_key;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,response -> {
            try {
                build(response);
                editor.putString("fullweather",response.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // editor.putString
        },error -> System.out.println("error"));
        requestQueue.add(jsonObjectRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void build(JSONObject response) throws JSONException {

        JSONObject todayW = response.getJSONObject("current");
        temperature.postValue(todayW.getString("temp"));
        pressure.postValue(todayW.getString("pressure"));
        humidity.postValue(todayW.getString("humidity"));
        visibility.postValue(todayW.getString("visibility"));
        windPower.postValue(todayW.getString("wind_speed"));
        windDirection.postValue(todayW.getString("wind_deg"));
        icon.postValue("weather" + todayW.getJSONArray("weather").getJSONObject(0).getString("icon"));
        JSONArray daily = response.getJSONArray("daily");
        JSONObject next_1 = daily.getJSONObject(1);
        JSONObject next_1_T = next_1.getJSONObject("temp");
        JSONObject next_1_R = next_1.getJSONArray("weather").getJSONObject(0);
        JSONObject next_2 = daily.getJSONObject(2);
        JSONObject next_2_T = next_2.getJSONObject("temp");
        JSONObject next_2_R = next_2.getJSONArray("weather").getJSONObject(0);
        JSONObject next_3 = daily.getJSONObject(3);
        JSONObject next_3_T = next_3.getJSONObject("temp");
        JSONObject next_3_R = next_3.getJSONArray("weather").getJSONObject(0);
        JSONObject next_4 = daily.getJSONObject(4);
        JSONObject next_4_T = next_4.getJSONObject("temp");
        JSONObject next_4_R = next_4.getJSONArray("weather").getJSONObject(0);
        JSONObject next_5 = daily.getJSONObject(5);
        JSONObject next_5_T = next_5.getJSONObject("temp");
        JSONObject next_5_R = next_5.getJSONArray("weather").getJSONObject(0);


        next_1_temp.postValue(next_1_T.getString("day"));
        next_2_temp.postValue(next_2_T.getString("day"));
        next_3_temp.postValue(next_3_T.getString("day"));
        next_4_temp.postValue(next_4_T.getString("day"));
        next_5_temp.postValue(next_5_T.getString("day"));
        next_1_icon.postValue("weather" + next_1_R.getString("icon"));
        next_2_icon.postValue("weather" + next_2_R.getString("icon"));
        next_3_icon.postValue("weather" + next_3_R.getString("icon"));
        next_4_icon.postValue("weather" + next_4_R.getString("icon"));
        next_5_icon.postValue("weather" + next_5_R.getString("icon"));
        next_1_date.postValue(epoch(Integer.parseInt(next_1.getString("dt"))));
        next_2_date.postValue(epoch(Integer.parseInt(next_2.getString("dt"))));
        next_3_date.postValue(epoch(Integer.parseInt(next_3.getString("dt"))));
        next_4_date.postValue(epoch(Integer.parseInt(next_4.getString("dt"))));
        next_5_date.postValue(epoch(Integer.parseInt(next_5.getString("dt"))));
    }
    public JsonArrayRequest City( SharedPreferences.Editor editor){
        String url = "https://api.openweathermap.org/geo/1.0/direct" + "?q=" + sharedPreferences.getString("city","Lodz") + "&appid=" + api_key;
        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET,url,null, response -> {
            try {
                JSONObject ob = response.getJSONObject(0);
                sz = ob.getString("lat");
                dl = ob.getString("lon");
                szerokosc1 = Double.parseDouble(ob.getString("lon"));
                dlugosc1 = Double.parseDouble(ob.getString("lat"));
                longitude.postValue(ob.getString("lon"));
                latitude.postValue(ob.getString("lat"));
                editor.putString("latitude", latitude.getValue());
                editor.putString("longitude", longitude.getValue());
                editor.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> System.out.println("errorCity"));
        return json;
    }
    public JsonArrayRequest Coor(SharedPreferences.Editor editor){
        String url2 = "https://api.openweathermap.org/geo/1.0/reverse" + "?lat=" + sharedPreferences.getString("latitude","") + "&lon=" + sharedPreferences.getString("longitude","") + "&limit=2&appid=" + api_key;

        JsonArrayRequest json = new JsonArrayRequest(url2,response -> {
            try {
                System.out.println(response);
                JSONObject ob = response.getJSONObject(1);
                dl = sharedPreferences.getString("longitude","");
                sz =sharedPreferences.getString("latitude","");

                latitude.postValue(sharedPreferences.getString("latitude",""));
                longitude.postValue(sharedPreferences.getString("longitude",""));
                city.postValue(ob.getString("name"));
                editor.putString("city",city.getValue());
                editor.apply();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        },error -> System.out.println("errorCoor"));
        return json;
    }
    public int getDrawableByName(final Context context, final String value) {
        return context.getResources().getIdentifier(
                value, "drawable", context.getPackageName()
        );
    }
    public static String getStaticCity(){
        return city_static;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String epoch(int time){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC).toLocalDate().getDayOfWeek().toString() +" " + LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC).toLocalDate().getDayOfMonth()+"." + LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC).toLocalDate().getMonthValue();
    }
    public void setCityName(String name){
        city.postValue(name);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDayNameFromJSONObject(JSONObject jsonObject) throws JSONException {
        Instant instant = Instant.ofEpochSecond(Integer.parseInt(jsonObject.getString("dt")));
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toLocalDate().getDayOfWeek().toString();
    }

    public static void setCityExist(boolean cityExist) {
        Model.cityExist = cityExist;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public void setMainContext(Context context){
        if (mainContext == null) {
            mainContext = context;
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainContext);
        editor = sharedPreferences.edit();
    }
    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public boolean network(){
        ConnectivityManager cm = (ConnectivityManager) mainContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reload() throws JSONException {
        weather();
    }

}
