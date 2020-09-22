package com.adrianwozniak.mobileapp_ztm_busslocation.util;

import android.graphics.Bitmap;

import androidx.test.core.app.ApplicationProvider;

import com.adrianwozniak.mobileapp_ztm_busslocation.R;

import org.junit.Assert;
import org.junit.Test;

public class BitmapConventerTest {

    @Test
    public void bitmapConventer(){
        Bitmap bitmap = BitMapConventer.getBitmap(
                ApplicationProvider.getApplicationContext(),
                R.drawable.ic_arrow
        );

        Assert.assertTrue(bitmap.getHeight() > 0);
        Assert.assertTrue(bitmap.getWidth() > 0);
    }


}
