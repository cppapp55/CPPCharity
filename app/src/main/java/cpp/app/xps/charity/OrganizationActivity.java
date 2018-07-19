package cpp.app.xps.charity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xps.charity.R;
import com.squareup.picasso.Picasso;

public class OrganizationActivity extends AppCompatActivity {

    TextView tvName, tvPhone, tvZip, tvAddress, tvDesc;
    ImageView ivLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        tvZip = findViewById(R.id.tvZip);
        ivLogo = findViewById(R.id.ivLogo);

        tvDesc.setMovementMethod(new ScrollingMovementMethod());
        String name = getIntent().getStringExtra("ORG_NAME");
        String desc = getIntent().getStringExtra("ORG_DESC");
        String image = getIntent().getStringExtra("ORG_IMAGE");
        final String address = getIntent().getStringExtra("ORG_ADDRESS");
        String zip = getIntent().getStringExtra("ORG_ZIP_CODE");
        final String phone = getIntent().getStringExtra("ORG_PHONE");


        tvName.setText(name);
        tvDesc.setText(desc);
        tvAddress.setText(address);
        tvZip.setText(zip);
        tvPhone.setText(phone);

        Picasso.get().load(image).into(ivLogo);


        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String map = "http://maps.google.co.in/maps?q=" + address;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                startActivity(i);



            }
        });


        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);

            }
        });
    }


}
