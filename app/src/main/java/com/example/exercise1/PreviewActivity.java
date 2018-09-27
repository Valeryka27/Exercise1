package com.example.exercise1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {

    public static final String Message_Preview = "Message_Preview";
    private static final String TAG = "PreviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        final String Message = getIntent().getStringExtra(Message_Preview);
        TextView previewText = findViewById(R.id.textView);
        previewText.setText(Message);

        Button emailButton = findViewById(R.id.button2);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView t = findViewById(R.id.sub);
                composeEmail(new String[] {"MahnachSV@taxcom.ru"}, t.getText().toString(), Message);
            }
        });
    }

    private void composeEmail(String[] address, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        final TextView t = findViewById(R.id.noApp);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            Log.i(TAG, "E-mail composed.");
        }
        else {
            Toast.makeText(this, t.getText().toString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "No E-mail app found.");
        }
    }
}
