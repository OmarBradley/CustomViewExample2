package com.example.customviewexample2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 재화 on 2016-04-15.
 */
public class ImageAndTextView extends LinearLayout {

    @Bind(R.id.imageView)
    ImageView image;
    @Bind(R.id.textView)
    TextView text;

    public ImageAndTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.defaultImageAndTextView);
    }

    public ImageAndTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(); // ppt에서 잘못된 부분, view 초기화를 먼저 해줘야 한다......!!!
        if (attrs != null) {
            setTypedArray(attrs, defStyleAttr);
        }
    }

    private void initView() {
        String inflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(inflaterService);
        View view = layoutInflater.inflate(R.layout.image_text_view, this, false);
        addView(view); // viewGroup에다 해당 뷰를 add해주는 함수, 꼭 필요함!!
        ButterKnife.bind(this, view);
    }

    private void setTypedArray(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ImageAndTextView, defStyleAttr, 0);
        String name = typedArray.getString(R.styleable.ImageAndTextView_name);
        setText(name);
        int imageResourceId = typedArray.getResourceId(R.styleable.ImageAndTextView_image, R.drawable.icon);
        setImageResource(imageResourceId);
        typedArray.recycle();
    }

    public void setImageResource(int imageResourceId) {
        image.setImageResource(imageResourceId);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        ImageAndTextViewState savedState = new ImageAndTextViewState(superState);
        savedState.bitmap = extractBitmap(image);
        savedState.text = text.getText().toString();
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof ImageAndTextViewState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        ImageAndTextViewState imageAndTextViewState = (ImageAndTextViewState) state;
        super.onRestoreInstanceState(imageAndTextViewState.getSuperState());
        image.setImageBitmap(imageAndTextViewState.bitmap);
        text.setText(imageAndTextViewState.text);
    }

    private Bitmap extractBitmap(ImageView imageView) {
        Drawable imageDrawable = imageView.getDrawable();
        Bitmap imageBitmap = ((BitmapDrawable) imageDrawable).getBitmap();
        return imageBitmap;
    }


}
