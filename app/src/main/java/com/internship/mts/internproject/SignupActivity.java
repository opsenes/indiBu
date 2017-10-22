package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.internship.mts.internproject.utils.ImageUtil;
import com.internship.mts.internproject.utils.validation.EmailValidatorUtil;
import com.internship.mts.internproject.utils.validation.FirstNameValidatorUtil;
import com.internship.mts.internproject.utils.validation.InterestsValidatorUtil;
import com.internship.mts.internproject.utils.validation.LastNameValidatorUtil;
import com.internship.mts.internproject.utils.validation.NicknameValidatorUtil;
import com.internship.mts.internproject.utils.validation.PasswordValidatorUtil;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.SignupRequest;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.view.HomePageActivity;
import com.thomashaertel.widget.MultiSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseActivity implements IPickResult {

    private boolean[] selectedInterests;
    private String imagePath;

    @BindView(R.id.signup_activity_imageview_pp)
    SimpleDraweeView ppImageView;

    @BindView(R.id.signup_activity_edittext_username)
    EditText editTextUsername;

    @BindView(R.id.signup_activity_edittext_firstname)
    EditText editTextFirstName;

    @BindView(R.id.signup_activity_edittext_lastname)
    EditText editTextLastName;

    @BindView(R.id.signup_activity_edittext_email)
    EditText editTextEmail;

    @BindView(R.id.signup_activity_edittext_password)
    EditText editTextPassword;

    @BindView(R.id.signup_activity_edittext_password_reenter)
    EditText editTextPasswordRepeat;

    @BindView(R.id.signup_activity_button_signup)
    Button buttonSigup;

    @BindView(R.id.signup_activity_spinner_interest_spinner)
    MultiSpinner spinnerInterest;

    public static Intent newIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedInterests = new boolean[]{false, false, false, false};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.categories));

        spinnerInterest.setAdapter(spinnerAdapter, false, onSelectedListener);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_signup;
    }

    @OnClick(R.id.signup_activity_button_signup)
    public void goSignUp() {

        if (!validate()) {
            onSignupFailed();
            return;
        }
        buttonSigup.setEnabled(false);

        SignupRequest signupRequest = new SignupRequest(
                editTextUsername.getText().toString(),
                editTextFirstName.getText().toString(),
                editTextLastName.getText().toString(),
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString(),
                selectedInterests[0],
                selectedInterests[1],
                selectedInterests[2],
                selectedInterests[3]
        );
        sendNetworkRequest(signupRequest);
    }

    private void onSignUpSuccess() {
        buttonSigup.setEnabled(true);
        finish();
    }

    public boolean validate() {
        boolean valid = true;

        NicknameValidatorUtil nicknameValidatorUtil = new NicknameValidatorUtil(editTextUsername.getText().toString());
        if (nicknameValidatorUtil.validate() != 0) {
            valid = false;
            editTextUsername.setError(nicknameValidatorUtil.getErrorMessage(this, nicknameValidatorUtil.validate()));
        }

        FirstNameValidatorUtil firstNameValidatorUtil = new FirstNameValidatorUtil(editTextFirstName.getText().toString());
        if (firstNameValidatorUtil.validate() != 0) {
            valid = false;
            editTextFirstName.setError(firstNameValidatorUtil.getErrorMessage(this, firstNameValidatorUtil.validate()));
        }

        LastNameValidatorUtil lastNameValidatorUtil = new LastNameValidatorUtil(editTextLastName.getText().toString());
        if (lastNameValidatorUtil.validate() != 0) {
            valid = false;
            editTextLastName.setError(lastNameValidatorUtil.getErrorMessage(this, lastNameValidatorUtil.validate()));
        }

        EmailValidatorUtil emailValidatorUtil = new EmailValidatorUtil(editTextEmail.getText().toString());
        if (emailValidatorUtil.validate() != 0) {
            valid = false;
            editTextEmail.setError(emailValidatorUtil.getErrorMessage(this, emailValidatorUtil.validate()));
        }

        PasswordValidatorUtil passwordValidatorUtil = new PasswordValidatorUtil(editTextPassword.getText().toString());
        if (passwordValidatorUtil.validate() != 0) {
            valid = false;
            editTextPassword.setError(passwordValidatorUtil.getErrorMessage(this, passwordValidatorUtil.validate()));
        }
        if (passwordValidatorUtil.samePasswords(editTextPassword.getText().toString(), editTextPasswordRepeat.getText().toString()) != 0) {
            valid = false;
            editTextPassword.setError(passwordValidatorUtil.getErrorMessage(this,
                    passwordValidatorUtil.samePasswords(editTextPassword.getText().toString(), editTextPasswordRepeat.getText().toString())));
        }

        InterestsValidatorUtil interestValidatorUtil = new InterestsValidatorUtil(selectedInterests);
        if (interestValidatorUtil.validate() != 0) {
            valid = false;
            spinnerInterest.setError(interestValidatorUtil.getErrorMessage(this, interestValidatorUtil.validate()));
        }
        return valid;
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), R.string.err_login_failed, Toast.LENGTH_LONG).show();
        buttonSigup.setEnabled(true);
    }


    @OnClick(R.id.signup_activity_textview_signin)
    public void linkToSignin() {
        finish();
    }

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            selectedInterests = selected;
            if (selectedInterests[0] && selectedInterests[1] && selectedInterests[2]
                    && selectedInterests[3]) {
                spinnerInterest.setText(getResources().getString(R.string.all));
            }
        }
    };

    private void sendNetworkRequest(SignupRequest signupRequest) {

        addRequest(getService().createAccount(signupRequest),
                new Callback() {
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        User user = (User) response.body();
                        UserLocalStore.getInstance().storeUserData(user);
                        buttonSigup.setEnabled(true);

                        if(imagePath != null) {

                            File compressedImageFile = ImageUtil.compressImage(SignupActivity.this, new File(imagePath), 400);

                            if (compressedImageFile != null) {
                                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
                                final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", compressedImageFile.getName(), reqFile);

                                addRequest(getService().uploadUserPhoto(body), new Callback() {
                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                                        Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }, false);
                            }
                        }

                        Intent homePageIntent = HomePageActivity.newIntent(SignupActivity.this);
                        homePageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(homePageIntent);
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                        Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        buttonSigup.setEnabled(true);
                    }
                });

    }

    @OnClick(R.id.signup_activity_imageview_pp)
    public void openImageIntent() {
        PickImageDialog.build(new PickSetup().setSystemDialog(true)).show(this);
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {

            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.clearCaches();

            imagePath = pickResult.getPath();

            File compressedImageFile = ImageUtil.compressImage(SignupActivity.this, new File(imagePath), 400);

            Uri androidUri = Uri.parse(compressedImageFile.toURI().toString());

            ppImageView.setImageURI(androidUri);

        } else {
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}


