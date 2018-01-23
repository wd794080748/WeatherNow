package com.wangdong.weathernow.model;

/**
 * Author:WangDong
 * Time:19:14
 * Description:this is County
 */
public class County {
    private int id;
    private String countryName;
    private String countryCode;
    private int cityId;

    public void setCityId(int cityId) {

        this.cityId = cityId;
    }

    public int getCityId() {

        return cityId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getId() {

        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

}
