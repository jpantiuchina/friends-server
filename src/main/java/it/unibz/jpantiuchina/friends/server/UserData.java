package it.unibz.jpantiuchina.friends.server;

import java.time.Instant;


public final class UserData
{
    private String phoneNumber;
    private double lat;
    private double lng;
    private Instant lastUpdated;

    @SuppressWarnings("unused")
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @SuppressWarnings("unused")
    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    @SuppressWarnings("unused")
    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    @SuppressWarnings("unused")
    public Instant getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }
}
