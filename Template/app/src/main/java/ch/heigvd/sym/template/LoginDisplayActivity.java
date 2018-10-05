package ch.heigvd.sym.template;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginDisplayActivity extends Activity {

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_display);

		Intent intent = getIntent();
		TextView loginDisplay = (TextView) findViewById(R.id.email_display);
		TextView passwordDisplay = (TextView) findViewById(R.id.password_display);
		ImageView photo = findViewById(R.id.imageView);

		if (intent != null) {
			loginDisplay.setText(intent.getStringExtra("emailEntered"));
			passwordDisplay.setText(intent.getStringExtra("passwordGiven"));

            Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() +  "/Homer_Simpson.jpg");
            photo.setImageBitmap(bmp);

		}
	}
}
