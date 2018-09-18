package com.cxyzy.tools.edittexthint;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EditTextHint {
    private Activity mActivity;
    /**
     * EditText控件资源ID
     */
    private int mEditTextResId;
    /**
     * hint提示文字
     */
    private String mHintText;
    /**
     * hint颜色
     */
    private int mHintColor;
    /**
     * hint字体大小，单位：dp
     */
    private float mHintSizeInDp;
    /**
     * hint在父控件中位置
     */
    private RelativeLayout.LayoutParams mLayoutParams;

    public EditTextHint(Activity activity, String hintText, int editTextResId) {
        this.mActivity = activity;
        this.mHintText = hintText;
        this.mEditTextResId = editTextResId;
        this.mHintColor = Color.LTGRAY;
        this.mHintSizeInDp = 15;
        this.mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = 20;
        mLayoutParams.bottomMargin = 25;
        mLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, editTextResId);
    }

    /**
     * hint颜色
     * @param hintColor
     * @return
     */
    public EditTextHint hintColor(@ColorInt int hintColor) {
        this.mHintColor = hintColor;
        return this;
    }

    /**
     * 设置hint字体大小
     * @param hintSizeInDp
     * @return
     */
    public EditTextHint hintSizeInDp(float hintSizeInDp) {
        this.mHintSizeInDp = hintSizeInDp;
        return this;
    }

    /**
     * 设置距离父控件左边距
     * @param leftMargin
     * @return
     */
    public EditTextHint leftMargin(int leftMargin) {
        this.mLayoutParams.leftMargin = leftMargin;
        return this;
    }

    /**
     * 设置距离父控件底部边距
     * @param bottomMargin
     * @return
     */
    public EditTextHint bottomMargin(int bottomMargin) {
        this.mLayoutParams.bottomMargin = bottomMargin;
        return this;
    }

    /**
     * 如果需要完全控制Hint的布局位置，可以不设置左边距和底边距，而设置layoutParams
     * @param layoutParams
     * @return
     */
    public EditTextHint layoutParams(RelativeLayout.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        return this;
    }

    public void showHint() {
        EditText editText = mActivity.findViewById(mEditTextResId);
        ViewGroup parentLayout = null;
        final TextView textView = new TextView(mActivity);
        textView.setText(mHintText);
        textView.setTextColor(mHintColor);
        textView.setTextSize(mHintSizeInDp);
        textView.setLayoutParams(mLayoutParams);
        if (editText.getParent() instanceof RelativeLayout) {
            parentLayout = (RelativeLayout) editText.getParent();
            parentLayout.addView(textView);
        } else {
            parentLayout = (ViewGroup) editText.getParent();
            RelativeLayout relativeLayout = new RelativeLayout(mActivity);
            int originalIndexInParent = parentLayout.indexOfChild(editText);
            parentLayout.removeView(editText);
            relativeLayout.addView(editText);
            parentLayout.addView(relativeLayout, originalIndexInParent);
            relativeLayout.addView(textView);
        }

        if (!TextUtils.isEmpty(editText.getText().toString())) {
            textView.setVisibility(View.GONE);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || TextUtils.isEmpty(s.toString())) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }
        });
    }
}
