package com.example.p1cashv5.models;

import android.content.Context;

public class UrlToSend {
    private String _url;
    private Context _context;

    public String get_url() {
        return _url;
    }
    public Context get_Context() {
        return _context;
    }

    public UrlToSend (String url,Context context){
        _url=url;
        _context=context;
    }
}
