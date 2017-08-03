package com.hungtran.footballscore.restBase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Hung Tran on 7/24/2017.
 * BitmapConverterFactory to convert byte stream to bitmap:
 */

public class BitmapConverterFactory  extends Converter.Factory{
    public static BitmapConverterFactory create() {
        return new BitmapConverterFactory();
    }
    private BitmapConverterFactory() {
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == Bitmap.class) {
            return new Converter<ResponseBody, Bitmap>(){

                @Override
                public Bitmap convert(ResponseBody value) throws IOException {
                    return BitmapFactory.decodeStream(value.byteStream());
                }
            };
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}
