package com.afollestad.polar.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import moe.feng.alipay.zerosdk.AlipayZeroSdk;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.polar.R;
import com.afollestad.polar.adapters.AboutAdapter;
import com.afollestad.polar.config.Config;
import com.afollestad.polar.fragments.base.BasePageFragment;
import com.afollestad.polar.ui.MainActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class AboutFragment extends BasePageFragment implements AboutAdapter.OptionsClickListener {

  public AboutFragment() {
  }

  @Override
  public int getTitle() {
    return R.string.about;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    final View v = inflater.inflate(R.layout.fragment_recyclerview_bare, container, false);
    final RecyclerView mRecyclerView = ButterKnife.findById(v, android.R.id.list);

    final LinearLayoutManager lm = new LinearLayoutManager(getActivity());
    final AboutAdapter mAdapter = new AboutAdapter(getActivity(), this);
    mRecyclerView.setLayoutManager(lm);
    mRecyclerView.setAdapter(mAdapter);
    return v;
  }

  @Override
  public void onOptionFeedback() {
    if(AlipayZeroSdk.hasInstalledAlipayClient(getActivity())){
      AlipayZeroSdk.startAlipayClient(getActivity(), "aex06053lqq7gh8aftzhc4e");
    }
  }

  @Override
  public void onOptionDonate() {
    final String[] optionNames = Config.get().donateOptionsNames();
    final String[] optionIds = Config.get().donateOptionsIds();
    if (optionNames == null || optionIds == null || optionNames.length != optionIds.length) {
      Toast.makeText(getActivity(), "Donation not configured correctly.", Toast.LENGTH_SHORT)
          .show();
      return;
    }
    new MaterialDialog.Builder(getActivity())
        .title(R.string.donate)
        .items(optionNames)
        .itemsCallback(new MaterialDialog.ListCallback() {
          @Override
          public void onSelection(MaterialDialog dialog, View itemView, int which,
              CharSequence text) {
            MainActivity act = (MainActivity) getActivity();
            if (act != null) {
              act.purchase(optionIds[which]);
            }
          }
        }).show();
  }
}