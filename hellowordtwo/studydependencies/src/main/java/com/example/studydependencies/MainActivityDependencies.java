package com.example.studydependencies;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivityDependencies extends AppCompatActivity {

    private static final String TAG = "GlideDemo";

    /**
     * 临时验证用地址（公开图床）。
     * TODO: 替换成你自己的完整图片地址。注意地址里不能有 "…" 这种被截断的省略号。
     */
    private static final String IMAGE_URL = "https://picsum.photos/id/237/400/400.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_dependencies);

        ImageView imageView = findViewById(R.id.image_glide);

        Glide.with(this)
                .load(IMAGE_URL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        Log.e(TAG, "加载失败, model=" + model, e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        Log.d(TAG, "加载成功, 数据来源=" + dataSource);
                        return false;
                    }
                })
                .into(imageView);
    }
}
