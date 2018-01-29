package com.wangdong.weathernow.util;

import android.text.TextUtils;
import android.util.Log;

import com.wangdong.weathernow.db.WeatherNowDB;
import com.wangdong.weathernow.model.City;
import com.wangdong.weathernow.model.County;
import com.wangdong.weathernow.model.Province;

import java.util.Arrays;

public class Utility {
    public static final String TAG = "Utility";
    /*
    * 解析和处理服务器返回的省级数据
    * */
    public synchronized static boolean handleProvincesResponse(WeatherNowDB weatherNowDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if(allProvinces != null&& allProvinces.length>0){
                for(String provinceStr: allProvinces){
                    String[] array = provinceStr.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    DebugLog.debugLog(TAG,array[1]+"--"+array[0]);
                    //将解析出来的数据存储到Province表中
                    Log.i(TAG, "handleProvincesResponse: "+province.toString());
                    weatherNowDB.saveProvince(province);
                }
            }
            return true;
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     * */
    public synchronized static boolean handleCityResponse(WeatherNowDB weatherNowDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if(allCities != null&& allCities.length>0){
                for(String cityStr: allCities){
                    String[] array = cityStr.split("\\|");
                    City city = new City();
                    Log.i(TAG, "handleCityResponse: "+ Arrays.toString(array));
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表中
                    Log.i(TAG, "handleCityResponse: "+city.toString());
                    weatherNowDB.saveCity(city);
                }
            }
            return true;
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的县级数据
     * */
    public synchronized static boolean handleCountyResponse(WeatherNowDB weatherNowDB,String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if(allCounties != null&& allCounties.length>0){
                for(String countyStr: allCounties){
                    String[] array = countyStr.split("\\|");
                    County county = new County();
                    county.setCountryCode(array[0]);
                    county.setCountryName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到City表中
                    weatherNowDB.saveCounty(county);
                }
            }
            return true;
        }
        return false;
    }
}
