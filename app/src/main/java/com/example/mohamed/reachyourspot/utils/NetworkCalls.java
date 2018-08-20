package com.example.mohamed.reachyourspot.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;


import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NetworkCalls extends AsyncTask<String,Void,Integer> {
    Context context;
    private myInteface mListener;

    public NetworkCalls(Context context,myInteface mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    protected Integer doInBackground(String... params) {
        Integer result = 0;


        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);
            socket.connect(socketAddress, 15000);
            socket.close();
            result = 1;
        } catch (IOException e) {
            result = 0;
        }
        return result;

    }


    @Override
    protected void onPostExecute(Integer s) {
        if (isConnected()) {
            if (mListener!=null)
                mListener.myMethod(s);
            if (s==1) {
                Toast.makeText(context, "there is net", Toast.LENGTH_SHORT).show();
            }
             else {
                Toast.makeText(context, "there is no net", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "there is no net", Toast.LENGTH_SHORT).show();

        }
        super.onPostExecute(s);

    }

}
