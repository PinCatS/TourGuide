package com.example.android.tourguide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ActionProvider;

import java.util.ArrayList;
import java.util.List;

public class TourGuideActionProvider extends ActionProvider {

    private final Context mContext;
    private List<String> topList = new ArrayList<String>();
    private String listTitle;
    private Intent mIntent;

    public TourGuideActionProvider(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View onCreateActionView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View providerView =
                layoutInflater.inflate(R.layout.tour_guide_share_list_provider, null);

        ViewGroup providerViewGrp = (ViewGroup) providerView;
        View imgButton = providerViewGrp.getChildAt(0);

        imgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(listTitle);
                builder.setItems(getTopListStringArray(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        mContext.startActivity(mIntent);
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        return providerView;
    }

    public String[] getTopListStringArray() {
        return topList.toArray(new String[topList.size()]);
    }

    public void setShareOptionList(List<String> topList) {
        this.topList = topList;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public void setShareIntent(Intent mIntent) {
        this.mIntent = mIntent;
    }

}
