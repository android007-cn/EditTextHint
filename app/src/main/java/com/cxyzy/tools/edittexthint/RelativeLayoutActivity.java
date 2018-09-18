package com.cxyzy.tools.edittexthint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class RelativeLayoutActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);
        new EditTextHint(R.id.editText).hintText("RelativeLayout中为EditText添加hint").activity(this).showHint();
    }


}
