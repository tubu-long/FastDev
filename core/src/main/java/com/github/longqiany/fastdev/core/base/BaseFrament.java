package com.github.longqiany.fastdev.core.base;

import android.app.Fragment;
import android.os.Bundle;
import com.github.longqiany.fastdev.core.FastDevApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by zzz on 11/16/15.
 */
public class BaseFrament extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = FastDevApplication.getRefWatcher();
        refWatcher.watch(this);
    }
}
