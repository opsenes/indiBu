package com.internship.mts.internproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.User;
import com.internship.mts.internproject.network.model.UserLocalStore;
import com.internship.mts.internproject.network.model.UserUpdateRequest;
import com.internship.mts.internproject.utils.ImageUtil;
import com.internship.mts.internproject.utils.UserUtil;
import com.internship.mts.internproject.utils.validation.InterestsValidatorUtil;
import com.internship.mts.internproject.utils.validation.LastNameValidatorUtil;
import com.internship.mts.internproject.utils.validation.PasswordValidatorUtil;
import com.internship.mts.internproject.utils.validation.FirstNameValidatorUtil;
import com.thomashaertel.widget.MultiSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingsActivity extends BaseActivity implements IPickResult {

    private boolean[] selectedCategories;
    private String[] discountCategory;
    private User user;
    private String imagePath;
    public ArrayList<String> selectedCategoriesArray;

    @BindView(R.id.activity_profile_settings_simpledraweeview_profile_image)
    SimpleDraweeView profileImageSimpleDraweeView;

    @BindView(R.id.activity_profile_settings_textview_nickname)
    TextView nicknameTextView;

    @BindView(R.id.activity_profile_settings_textview_email)
    TextView emailTextView;

    @BindView(R.id.activity_profile_settings_textinputedittext_first_name)
    TextInputEditText firstNameEditText;

    @BindView(R.id.activity_profile_settings_textinputedittext_last_name)
    TextInputEditText lastNameEditText;

    @BindView(R.id.activity_profile_settings_textinputedittext_oldpassword)
    TextInputEditText oldPasswordEditText;

    @BindView(R.id.activity_profile_settings_textinputedittext_password)
    TextInputEditText passwordEditText;

    @BindView(R.id.activity_profile_settings_textinputedittext_repassword)
    TextInputEditText rePasswordEditText;

    @BindView(R.id.activity_profile_settings_multispinner_interest_spinner)
    MultiSpinner interestMultiSpinner;

    @BindView(R.id.profile_settings_activity_toolbar)
    Toolbar profileSettingsToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, ProfileSettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(profileSettingsToolbar, R.string.profile_settings_title);

        selectedCategories = new boolean[]{false, false, false, false};
        discountCategory = getResources().getStringArray(R.array.categories_CAP);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.categories));

        interestMultiSpinner.setAdapter(spinnerAdapter, false, new MultiSpinner.MultiSpinnerListener() {
            public void onItemsSelected(boolean[] selected) {
                selectedCategories = selected;
                allCategoriesSelected();
                fillSelectedCategories(selected);
            }
        });
        getUserProfile();
    }

    private void allCategoriesSelected() {
        if (selectedCategories[0] && selectedCategories[1] && selectedCategories[2]
                && selectedCategories[3]) {
            interestMultiSpinner.setText(getResources().getString(R.string.all));
        }
    }

    private void  fillSelectedCategories(boolean[] selected) {
        selectedCategoriesArray = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if(selected[i]) selectedCategoriesArray.add(discountCategory[i]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_profile_settings;
    }

    private void setUserInfo() {
        profileImageSimpleDraweeView.setImageURI(UserUtil.getUserPhotoUrl(String.valueOf(user.getNickname())));
        nicknameTextView.setText(user.getNickname());
        emailTextView.setText(user.getEmail());
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());

        selectedCategories[0] = user.isElectronics();
        selectedCategories[1] = user.isCosmetics();
        selectedCategories[2] = user.isClothing();
        selectedCategories[3] = user.isFood();

        interestMultiSpinner.setSelected(selectedCategories);
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void getUserProfile() {

        addRequest(getService().getUser(), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                User user = (User) response.body();
                UserLocalStore.getInstance().storeUserData(user);
                setUser(user);
                setUserInfo();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                setUser(UserLocalStore.getInstance().getLoggedInUser());
                if (user == null){
                    Intent loginIntent = LoginActivity.newIntent(ProfileSettingsActivity.this);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                } else {
                    setUserInfo();
                }
                Toast.makeText(ProfileSettingsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.activity_profile_settings_button_user_update)
    void update() {

        if (!validate()) {
            return;
        }

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

        userUpdateRequest.setFirstName(firstNameEditText.getText().toString());
        userUpdateRequest.setLastName(lastNameEditText.getText().toString());
        userUpdateRequest.setOldPassword(oldPasswordEditText.getText().toString());
        userUpdateRequest.setNewPassword(passwordEditText.getText().toString());
        userUpdateRequest.setCategories(selectedCategoriesArray);

        addRequest(getService().updateUser(userUpdateRequest), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                if (imagePath != null) {

                    File compressedImageFile = ImageUtil.compressImage(ProfileSettingsActivity.this, new File(imagePath), 400);

                    if (compressedImageFile != null) {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
                        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", compressedImageFile.getName(), reqFile);

                        addRequest(getService().uploadUserPhoto(body), new Callback() {
                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) {
                            }

                            @Override
                            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                                Toast.makeText(ProfileSettingsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }, false);
                    }
                }

                finish();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Toast.makeText(ProfileSettingsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        FirstNameValidatorUtil firstNameValidatorUtil = new FirstNameValidatorUtil(firstNameEditText.getText().toString());
        if (firstNameValidatorUtil.validate() != 0) {
            valid = false;
            firstNameEditText.setError(firstNameValidatorUtil.getErrorMessage(this, firstNameValidatorUtil.validate()));
        }

        LastNameValidatorUtil lastNameValidatorUtil = new LastNameValidatorUtil(lastNameEditText.getText().toString());
        if (lastNameValidatorUtil.validate() != 0) {
            valid = false;
            lastNameEditText.setError(lastNameValidatorUtil.getErrorMessage(this, lastNameValidatorUtil.validate()));
        }

        PasswordValidatorUtil passwordValidatorUtil = new PasswordValidatorUtil(passwordEditText.getText().toString());
        if (passwordValidatorUtil.validate() > 1) {
            valid = false;
            passwordEditText.setError(passwordValidatorUtil.getErrorMessage(this, passwordValidatorUtil.validate()));
        }
        if (passwordValidatorUtil.samePasswords(passwordEditText.getText().toString(), rePasswordEditText.getText().toString()) != 0) {
            valid = false;
            passwordEditText.setError(passwordValidatorUtil.getErrorMessage(this,
                    passwordValidatorUtil.samePasswords(passwordEditText.getText().toString(), rePasswordEditText.getText().toString())));
        }

        InterestsValidatorUtil interestValidatorUtil = new InterestsValidatorUtil(selectedCategories);
        if (interestValidatorUtil.validate() != 0) {
            valid = false;
            interestMultiSpinner.setError(interestValidatorUtil.getErrorMessage(this, interestValidatorUtil.validate()));
        }

        return valid;
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {

            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.clearCaches();

            imagePath = pickResult.getPath();

            File compressedImageFile = ImageUtil.compressImage(ProfileSettingsActivity.this, new File(imagePath), 400);

            Uri androidUri = Uri.parse(compressedImageFile.toURI().toString());

            profileImageSimpleDraweeView.setImageURI(androidUri);

        } else {
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.activity_profile_settings_simpledraweeview_profile_image)
    public void openImageIntent() {
        PickImageDialog.build(new PickSetup().setSystemDialog(true)).show(this);
    }

}