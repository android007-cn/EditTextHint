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
    private int mEditTextResId;
    private Activity mActivity;
    private String mHintText;
    private RelativeLayout.LayoutParams mLayoutParams;
    private int mHintColor;
    private float mHintSizeInDp;

    public EditTextHint(int editTextResId) {
        this.mEditTextResId = editTextResId;
        this.mHintText = "默认Hint";
        this.mHintColor = Color.LTGRAY;
        this.mHintSizeInDp = 15;
        this.mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = 20;
        mLayoutParams.bottomMargin = 25;
        mLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, editTextResId);
    }

    public EditTextHint activity(Activity activity) {
        this.mActivity = activity;
        return this;
    }

    public EditTextHint hintText(String hintText) {
        this.mHintText = hintText;
        return this;
    }

    public EditTextHint layoutParams(RelativeLayout.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        return this;
    }

    public EditTextHint leftMargin(int leftMargin) {
        this.mLayoutParams.leftMargin = leftMargin;
        return this;
    }

    public EditTextHint bottomMargin(int bottomMargin) {
        this.mLayoutParams.bottomMargin = bottomMargin;
        return this;
    }

    public EditTextHint hintColor(@ColorInt int hintColor) {
        this.mHintColor = hintColor;
        return this;
    }

    public EditTextHint hintSizeInDp(float hintSizeInDp) {
        this.mHintSizeInDp = hintSizeInDp;
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
