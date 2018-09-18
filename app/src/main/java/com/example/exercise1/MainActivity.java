package com.example.exercise1;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.exercise1.PreviewActivity.Message_Preview;

public class MainActivity extends AppCompatActivity {

    private String Message = "";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputLayout textInputLayout = findViewById(R.id.input_layout);
        final EditText editText = findViewById(R.id.editText);

        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                Message = editText.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) {
                    showError(textInputLayout);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });

        Button previewButton = findViewById(R.id.button);
        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Message.length() > 0) {
                    openPreviewActivity();
                } else {
                    showError(textInputLayout);
                }
            }
        });
    }

    private void showError(TextInputLayout t) {
        final TextView er = findViewById(R.id.error);
        t.setError(er.getText().toString());
        t.setErrorEnabled(true);
        Log.e(TAG, "No input from user.");
    }

    public void openPreviewActivity() {
        Intent previewActivityIntent = new Intent(this, PreviewActivity.class);
        previewActivityIntent.putExtra(Message_Preview, Message);
        startActivity(previewActivityIntent);
        Log.i(TAG, "Preview requested.");
    }
}
