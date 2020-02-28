package be.corentingoo.p1cashv7.models;

import java.util.ArrayList;

public class Machine {
    /*
    private int _id;
    private int _idParking;

    public Machine(int _id,int _idParking) {
        this._id = _id;
        this._idParking = _idParking;
    }

    public int Id() {
        return _id;
    }
    public int IdParking() {
        return _idParking;
    }

     */

    private int _id;
    private int _idParking;
    private ArrayList<Cash> _listCash;

    public Machine(int _id,int _idParking) {
        this._id = _id;
        this._idParking = _idParking;
        this._listCash = new ArrayList<>();
    }

    public int Id() {
        return _id;
    }
    public int IdParking() {
        return _idParking;
    }
    public ArrayList<Cash> ListCash(){
        return _listCash;
    }

//    public void set_ListCash(ArrayList<Cash> _listCash){
//        this._listCash=_listCash;
//    }

    public void addCash(Cash cash) {
        this._listCash.add(cash);
    }
}
