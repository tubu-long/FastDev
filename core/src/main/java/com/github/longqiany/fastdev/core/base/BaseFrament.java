package com.github.longqiany.fastdev.core.base;

import android.app.Fragment;
import android.os.Bundle;
import com.github.longqiany.fastdev.core.FastApplication;
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
        RefWatcher refWatcher = FastApplication.getRefWatcher();
        refWatcher.watch(this);
    }
}
