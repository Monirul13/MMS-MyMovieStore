package com.example.rana.mymoviestore;

import android.support.v4.app.Fragment;

/**
 * Created by Rana on 1/4/2019.
 */

public interface FragmentTransactionInterface {

    void loadFragment(Fragment fragment, boolean willReplace);
    void loadFragment(Fragment fragment);
}
