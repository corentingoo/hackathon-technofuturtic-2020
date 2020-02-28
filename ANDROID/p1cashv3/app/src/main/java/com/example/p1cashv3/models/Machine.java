package com.example.p1cashv3.models;

public class Machine {

    private int _id;
    private int _maxCapacity;
    private int _thisCapacity;

    public Machine(int _id, int _maxCapacity, int _thisCapacity) {
        this._id = _id;
        this._maxCapacity = _maxCapacity;
        this._thisCapacity = _thisCapacity;
    }

    public int get_id() {
        return _id;
    }
    public int get_maxCapacity() {
        return _maxCapacity;
    }
    public int get_thisCapacity() {
        return _thisCapacity;
    }

    public void set_thisCapacity(int _thisCapacity) {
        this._thisCapacity = _thisCapacity;
    }
}
