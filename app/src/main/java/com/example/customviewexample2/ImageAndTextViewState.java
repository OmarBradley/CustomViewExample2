package com.example.customviewexample2;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 재화 on 2016-04-15.
 */
public class ImageAndTextViewState extends View.BaseSavedState {

    Bitmap bitmap;
    String text;

    public ImageAndTextViewState(Parcelable superState) {
        super(superState);
    }

    public ImageAndTextViewState(Parcel in) {
        super(in);
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        text = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(bitmap, flags);
    }

    public static final Parcelable.Creator<ImageAndTextViewState> CREATOR = new Parcelable.Creator<ImageAndTextViewState>() {

        @Override
        public ImageAndTextViewState createFromParcel(Parcel in) {
            return new ImageAndTextViewState(in);
        }

        @Override
        public ImageAndTextViewState[] newArray(int size) {
            return new ImageAndTextViewState[size];
        }
    };

}
