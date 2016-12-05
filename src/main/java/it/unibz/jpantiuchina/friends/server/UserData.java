package it.unibz.jpantiuchina.friends.server;

import java.time.Instant;


public final class UserData
{
    private String name;
    private double lat;
    private double lng;
    private Instant lastUpdated;

    @SuppressWarnings("unused")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
