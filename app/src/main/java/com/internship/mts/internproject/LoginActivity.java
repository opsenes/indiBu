package com.internship.mts.internproject;

import android.content.Context;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.LoginRequest;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.utils.validation.EmailNicknameValidatorUtil;
import com.internship.mts.internproject.view.HomePageActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.activity_login_edittext_input_email)
    EditText editTextEmail;

    @BindView(R.id.activity_login_edittext_password)
    EditText editTextPassword;

    @BindView(R.id.activity_login_button_login)
    Button buttonLogin;

    @BindView(R.id.activity_login_textview_signup)
    TextView textViewSignup;

    public static Intent newIntent(Context activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @OnClick(R.id.activity_login_textview_signup)
    public void linkSignup() {
        startActivity(SignupActivity.newIntent(this));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.activity_login_button_login)
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            return;
        }

        buttonLogin.setEnabled(false);

        LoginRequest loginRequest;
        if (editTextEmail.getText().toString().contains("@")) {
            loginRequest = LoginRequest.createFromEmail(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
        } else {
            loginRequest = LoginRequest.createFromNickname(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
        }

        addRequest(getService().login(loginRequest), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                getUser();
                buttonLogin.setEnabled(true);
                Intent homePageIntent = HomePageActivity.newIntent(LoginActivity.this);
                homePageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homePageIntent);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                buttonLogin.setEnabled(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                finish();
            }
        }
    }

    public void onLoginSuccess() {
        finish();
    }

    public boolean validate() {
        boolean valid = true;

        EmailNicknameValidatorUtil emailNicknameValidatorUtil = new EmailNicknameValidatorUtil(editTextEmail.getText().toString());
        int validationResult = emailNicknameValidatorUtil.validate();
        if(validationResult != 0){
            valid = false;
            editTextEmail.setError(emailNicknameValidatorUtil.getErrorMessage(this, validationResult));
        }
        return valid;
    }

    private void getUser() {

        addRequest(getService().getUser(), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                User user = (User) response.body();
                UserLocalStore.getInstance().storeUserData(user);
                Log.i("login", user.toString());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.i("getuser", t.getMessage());
            }

        }, false);
    }
}
