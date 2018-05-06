package com.example.majid.forurcomfy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majid.forurcomfy.Common.Current;
import com.example.majid.forurcomfy.Remote.ApiUtlis;
import com.example.majid.forurcomfy.Remote.RegisterService;
import com.example.majid.forurcomfy.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    //RegisterService registerService;
    private RegisterService registerService;
    private TextView mResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerService = ApiUtlis.getRegisterService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText fName = (EditText) findViewById(R.id.FirstName);
        final EditText lName = (EditText) findViewById(R.id.LastName);
        final EditText phoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        final EditText Email = (EditText) findViewById(R.id.Email);
        final EditText pass = (EditText) findViewById(R.id.Password);
        final EditText reTypePass = (EditText) findViewById(R.id.MatchPassword);
        final Button register = (Button) findViewById(R.id.RegisterBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = fName.getText().toString().trim();
                String lastName = lName.getText().toString().trim();
                String cellPhone = phoneNumber.getText().toString().trim();
                String emailAddress = Email.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String reTypePassword = reTypePass.getText().toString().trim();
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("Please waiting ...");
                mDialog.show();
                //validate Register
                mDialog.dismiss();
                if (validateRegister(firstName, lastName, cellPhone, emailAddress,
                        password, reTypePassword)) ;

//                doRegister(firstName,lastName,cellPhone,emailAddress
//                        ,password,reTypePassword);
                sendPost(firstName,lastName,cellPhone,emailAddress, password);


            }
        });

//        Pattern EmailAddress = Pattern.compile("@mail.fresnostate.edu");
//        Matcher EmailAddress2 = EmailAddress.matcher("@mail.fresnostate.edu");
//        boolean match = EmailAddress2.matches();
    }

    public boolean validateRegister(String fname, String lname, String cellphone,
                                    String eamil, String pass, String reTypePass) {
        if (fname == null || fname.trim().length() == 0) {
            Toast.makeText(this, "Your First Name  is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lname == null || lname.trim().length() == 0) {
            Toast.makeText(this, "Your Last Name  is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cellphone == null || cellphone.trim().length() == 0) {
            Toast.makeText(this, "Your Phone number  is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (pass == null || pass.trim().length() == 0) {
            Toast.makeText(this, "Your Password is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (reTypePass != pass) {
//            Toast.makeText(this, "Your Password does not match"
//                    , Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    //    private void doRegister(final String fname, final String lname, final String cellphone,
//                            final String email, final String pass, final String reTypePass) {
//        Call<ResObj> call = registerService.register(fname, lname, cellphone, email,
//                pass, reTypePass);
//        call.enqueue(new Callback<ResObj>() {
//            @Override
//            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
//                if (response.isSuccessful()) {
//                    ResObj resObj = response.body();
//                    if (resObj.getMessage().equals("true")) {
//                        Intent registerIntent = new Intent(RegisterActivity.this,
//                                UserAreaActivity.class);
//                        RegisterActivity.this.startActivity(registerIntent);
//
//                    } else {
//                        Toast.makeText(RegisterActivity.this,
//                                "Your information are not validated",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(RegisterActivity.this,
//                            "Error! Please try again!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Toast.makeText(RegisterActivity.this, t.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    public void sendPost(final String fname, final String lname, String cellphone, final String email,
                         String password) {
        registerService.savePostRegister(fname,lname,cellphone,email,
                password).enqueue(new Callback<Post>() {
            public static final String TAG = "tag";

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (response.isSuccessful()) {
//                            showResponse(response.body().toString());
//                            Log.i(TAG, "post submitted to API." + response.body().toString());
                    Intent homeIntent = new Intent(RegisterActivity.
                            this, Home.class);
                    Current.currentUser = new Post(email);
                   // registerIntent.putExtra("email", email);
                    RegisterActivity.this.startActivity(homeIntent);
                    finish();
                }
            }

            public void showResponse(String response) {
                if (mResponseTv.getVisibility() == View.GONE) {
                    mResponseTv.setVisibility(View.VISIBLE);
                }
                mResponseTv.setText(response);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }
}
