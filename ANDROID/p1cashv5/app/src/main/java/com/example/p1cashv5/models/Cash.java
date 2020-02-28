package com.example.p1cashv5.models;

public class Cash {
    private int _id;
    private int _idTypeCash;//1-coinin1 2-coinin2 3-coinin3 4-coinin4 5-coinin5 6-coinOut1 7-coinOut2 8-coinOut3 9-coinOut4 10-coinOut5 11-note1 12-note2 13-note3 14-note4
    private String _typeCash;
    private int _maxCapacity;
    private int _currentCapacity;
    private int _idMachine;

    public Cash(int _id,int _idTypeCash,String _type_cash,int _maxCapacity, int _currentCapacity ,int _idMachine) {
        this._id = _id;
        this._idTypeCash=_idTypeCash;
        this._typeCash=_type_cash;
        this._maxCapacity=_maxCapacity;
        this._currentCapacity=_currentCapacity;
        this._idMachine=_idMachine;
    }

    public Cash(){};

    public int Id(){return _id;}
    public int IdTypeCash(){return _idTypeCash;}
    public String Type_cash(){return _typeCash;}
    public int MaxCapacity(){return _maxCapacity;}
    public int CurrentCapacity(){return _currentCapacity;}
    public int IdMachine(){return _idMachine;}

    public void setCurrentCapacity(int _currentCapacity){this._currentCapacity = _currentCapacity;}
}
