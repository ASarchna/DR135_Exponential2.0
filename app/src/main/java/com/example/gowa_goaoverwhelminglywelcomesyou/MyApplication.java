package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.firebase.FirebaseApp;

import java.io.File;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true);

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPathSupplier(
                        new Supplier<File>() {
                            @Override
                            public File get() {
                                return getApplicationContext().getCacheDir();
                            }
                        })
                .setBaseDirectoryName("image_cache")
                .setMaxCacheSizeOnLowDiskSpace(25 * ByteConstants.MB)
                .setMaxCacheSize(120 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(2 * ByteConstants.MB)
                .build();
        builder.setMainDiskCacheConfig(diskCacheConfig);
        ImagePipelineConfig config = builder.build();
        Fresco.initialize(this, config);        super.onCreate();
    }

}

