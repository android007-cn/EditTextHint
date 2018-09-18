背景：
1.开发中遇到这样的场景，EditText中需要设置Hint，但Hint太长，不能采用和输入框一样的字体。
2.采用如下方案：
public static void setEditTextHintWithSize(EditText editText, String hintText, @Dimension int size) {
        if (!TextUtils.isEmpty(hintText)) {
            SpannableString ss = new SpannableString(hintText);
            //设置字体大小 true表示单位是sp
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setHint(new SpannedString(ss));
        }
    }
3.但是上面的方案在小米多款手机上会出现初始情况下光标靠上并且较短的问题。

优化方案：
1.在EditText上覆盖一层TextView来做Hint提示，支持：
  1）设置Hint颜色、字体大小、位置等
2.使用方法：
 1）最简用法：
    new EditTextHint(this, "RelativeLayout中为EditText添加hint", R.id.editText).showHint();
 2）一般用法：
    new EditTextHint(this, "LinearLayout中为EditText添加hint", R.id.editText).hintColor(Color.RED).hintSizeInDp(12).showHint();
 更多用法详见源代码。