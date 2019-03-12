package com.napier.world.connection;

public class ConnectionBuilder
{
    private String _location = "db";

    public ConnectionBuilder() {}

    public Connection buildConnection()
    {
        return new Connection(_location);
    }

    public ConnectionBuilder location(String location)
    {
        this._location = location;
        return this;
    }
}
