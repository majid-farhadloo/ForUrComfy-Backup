package com.example.majid.forurcomfy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.majid.forurcomfy.Common.Current;
import com.example.majid.forurcomfy.Remote.APIService;
import com.example.majid.forurcomfy.Remote.ApiUtlis;
import com.example.majid.forurcomfy.model.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private APIService mAPIService;
    private TextView mResponseTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAPIService = ApiUtlis.getAPIService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText edtemail = (EditText) findViewById(R.id.EmailLogIn);
        final EditText phoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        final EditText edtpassword = (EditText) findViewById(R.id.PasswordLogIn);
        final Button Login = (Button) findViewById(R.id.LogIn);
        final Button forgotPassword = (Button) findViewById(R.id.ForgotPass);

        //Init Firebase
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference table_user = database.getReference("User");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please waiting ...");
                mDialog.show();

                String email = edtemail.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();
                //phoneNumber.setText(phoneNumber.getText().toString());
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    sendPost(email, password);
                    mDialog.dismiss();
                }
                forgotPassword.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        Intent forgotPassIntent = new Intent(LoginActivity.
                                this, ForgotPassActivity.class);
                        LoginActivity.this.startActivity(forgotPassIntent);
                    }
                });
            }
            private void sendPost(final String email, String password) {
                mAPIService.savePost(email, password).enqueue(new Callback<Post>() {
                    public static final String TAG = "tag";

                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        if (response.isSuccessful()) {
//                            showResponse(response.body().toString());
//                            Log.i(TAG, "post submitted to API." + response.body().toString());
                            Intent LoginIntent = new Intent(LoginActivity.
                                    this, Home.class);
                            Current.currentUser = new Post(email);
                            LoginIntent.putExtra("email", email);
                            LoginActivity.this.startActivity(LoginIntent);
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

        });
    }
}
  /*private boolean validateLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Your Email address is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required"
                    , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }*/

    /*private void doLogin(final String username, final String password) {
        Call<ResObj> call = userService.login(username, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.isSuccessful()) {
                    ResObj resObj = response.body();
                    if (resObj.getMessage().equals("true")) {
                        Intent LoginIntent = new Intent(LoginActivity.
                                this, UserAreaActivity.class);
                        LoginIntent.putExtra("email", username);
                        LoginActivity.this.startActivity(LoginIntent);

                    } else {
                        Toast.makeText(LoginActivity.this,
                                "The username or password is incorrect",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }*/


// table_user.addValueEventListener(new ValueEventListener() {
//  @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // check if user doesn't exist in database
//                        if (dataSnapshot.child(phoneNumber.getText().toString()).exists()) {
//                            mDialog.dismiss();
//                            Post user = dataSnapshot.child(phoneNumber.getText().toString())
//                                    .getValue(Post.class);
//                            String pass = user.getPassword();
//                            if (pass.equals(password.getText().toString())) {
//                                Toast.makeText(LoginActivity.this, "Login successfully",
//                                        Toast.LENGTH_SHORT).show();
////                                Intent LoginIntent = new Intent(LoginActivity.
////                                        this, UserAreaActivity.class);
////                             //   LoginIntent.putExtra("Phone", (Parcelable) user);
////                                LoginActivity.this.startActivity(LoginIntent);
//                            } else {
//                                Toast.makeText(LoginActivity.this,
//                                        "Login Failed !!!", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            mDialog.dismiss();
//                            Toast.makeText(LoginActivity.this, "User does not exist",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
// });

