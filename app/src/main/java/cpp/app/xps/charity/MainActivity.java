package cpp.app.xps.charity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xps.charity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private TextView textViewResetPass;
    private ProgressBar progressB;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        Log.d("APP_DEBUG", firebaseAuth.getAccessToken(true).toString());
        if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().isEmailVerified()) {
            //Profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ListActivity.class));
        }

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignin);
        textViewSignup = findViewById(R.id.textViewSignup);
        textViewResetPass = findViewById(R.id.textViewResetPass);
        progressB = findViewById(R.id.progressLogin);
        progressB.setVisibility(View.GONE);

        buttonSignIn.setOnClickListener(this);
        textViewResetPass.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }



    private void userLogin() {
        progressB.setVisibility(View.VISIBLE);
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //Email is empty
            progressB.setVisibility(View.GONE);
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            //Stopping the function from executing
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //Password is empty
            progressB.setVisibility(View.GONE);
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            //Stopping the function from executing
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && firebaseAuth.getCurrentUser().isEmailVerified()) {
                            progressB.setVisibility(View.GONE);
                            finish();
                            startActivity(new Intent(MainActivity.this, ListActivity.class));
                            //start the profile activity
                        } else if (task.isSuccessful() && !firebaseAuth.getCurrentUser()
                                .isEmailVerified()) {
                            progressB.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "User has not verified their email!",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            progressB.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this,
                                    "Account does not exist or Email/Password is incorrect, " +
                                            "Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override


    public void onClick(View view) {
        if (view == buttonSignIn) {
            userLogin();
        }

        if (view == textViewSignup) {
            finish();
            startActivity(new Intent(this, Registration.class));
        }

        if (view == textViewResetPass) {
            startActivity(new Intent(this, ResetPassword.class));
        }
    }
}

