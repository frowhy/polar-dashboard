package com.afollestad.polar.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.afollestad.polar.BuildConfig;
import com.afollestad.polar.R;
import com.afollestad.polar.fragments.base.BasePageFragment;

/**
 * @author Aidan Follestad (afollestad)
 */
public class HomeFragment extends BasePageFragment {

  private Unbinder unbinder;

//    @BindView(R.id.fab)
//    FloatingActionButton mFab;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_homepage, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.fab)
  public void onTapReview() {
    startActivity(new Intent(Intent.ACTION_VIEW)
        .setData(Uri.parse(String.format("http://coolapk.com/apk/%s",
            BuildConfig.APPLICATION_ID))));
  }

  @Override
  public int getTitle() {
    return R.string.home;
  }
}