package com.internship.mts.internproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.internship.mts.internproject.base.BaseActivity;
import com.internship.mts.internproject.network.model.CreateDiscountRequest;
import com.internship.mts.internproject.network.model.CreateDiscountResponseModel;
import com.internship.mts.internproject.utils.ImageUtil;
import com.internship.mts.internproject.utils.validation.TitleValidatorUtil;
import com.thomashaertel.widget.MultiSpinner;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateDiscountActivity extends BaseActivity implements IPickResult {
    //Categories
    private String[] discountCategory;
    private boolean[] selectedCategories;
    public ArrayList<String> selectedCategoriesArray;
    private String imagePath;
    //Location
    private static final int PLACE_PICKER_REQUEST = 1;
    private String mAttributions;
    LocationManager locationManager;
    LocationListener locationListener;

    private double locationLat;
    private double locationLong;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateDiscountActivity.class);
    }

    @BindView(R.id.activity_create_discount_edittext_title)
    EditText editTextTitle;

    @BindView(R.id.activity_create_discount_spinner_category)
    MultiSpinner multiSpinnerCategory;

    @BindView(R.id.activity_create_discount_edittext_description)
    EditText editTextDescription;

    @BindView(R.id.activity_create_discount_textview_photo)
    TextView textViewCamera;

    @BindView(R.id.activity_create_discount_textview_location)
    TextView textViewLocation;

    @BindView(R.id.activity_create_discount_google_attributions)
    ImageView attributionsGoogle;

    @BindView(R.id.activity_create_discount_button)
    Button buttonCreateDiscount;

    @BindView(R.id.activity_create_discount_checkBox)
    CheckBox checkBoxLocation;

    @BindView(R.id.create_discount_activity_toolbar)
    Toolbar createDiscountToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar(createDiscountToolbar, R.string.create_discount_title);

        selectedCategories = new boolean[]{false, false, false, false};
        discountCategory = getResources().getStringArray(R.array.categories_CAP);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_spinner_item);

        multiSpinnerCategory.setAdapter(adapter, false, new MultiSpinner.MultiSpinnerListener() {
            public void onItemsSelected(boolean[] selected) {
                selectedCategories = selected;
                allCategoriesSelected();
                fillSelectedCategories(selected);
            }
        });
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
        return R.layout.activity_create_discount;
    }

    @OnClick(R.id.activity_create_discount_button)
    public void createDiscount() {
        if (!validate()) {
            return;
        }
        CreateDiscountRequest createDiscountRequest = new CreateDiscountRequest();
        createDiscountRequest.setTitle(editTextTitle.getText().toString());
        createDiscountRequest.setDescription(editTextDescription.getText().toString());
        createDiscountRequest.setCategories(selectedCategoriesArray);
        createDiscountRequest.setLat(locationLat);
        createDiscountRequest.setLong(locationLong);

        addRequest(getService().createDiscount(createDiscountRequest), new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {

                CreateDiscountResponseModel createDiscountResponseModel =
                        (CreateDiscountResponseModel) response.body();

                if (imagePath != null) {
                    File compressedImageFile = ImageUtil.compressImage(CreateDiscountActivity.this, new File(imagePath), 1000);

                    if (compressedImageFile != null) {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), compressedImageFile);
                        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", compressedImageFile.getName(), reqFile);

                        addRequest(getService().uploadDealPhoto(
                                body, createDiscountResponseModel.getDiscountId()),
                                new Callback() {
                                    @Override
                                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                                        Toast.makeText(CreateDiscountActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }, false);
                    }
                }

                finish();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Toast.makeText(CreateDiscountActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.activity_create_discount_textview_location)
    public void getLocation(){

        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(CreateDiscountActivity.this);
            startActivityForResult(intentBuilder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException
                | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence address = place.getAddress();
            locationLat = place.getLatLng().latitude;
            locationLong = place.getLatLng().longitude;
            String attributions = (String) place.getAttributions();
            if (attributions == null) {

            }
            textViewLocation.setText(address);
            Log.i("place", place.toString());
            Log.i("lat", String.valueOf(locationLat));
            Log.i("long", String.valueOf(locationLong));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnCheckedChanged(R.id.activity_create_discount_checkBox)
    public void allCountry(){
        if (checkBoxLocation.isChecked()) {
            textViewLocation.setVisibility(View.INVISIBLE);
            attributionsGoogle.setVisibility(View.INVISIBLE);
        }
        else{
            textViewLocation.setVisibility(View.VISIBLE);
            attributionsGoogle.setVisibility(View.VISIBLE);
        }

    }

    private boolean validate() {
        boolean valid = true;
        TitleValidatorUtil titleValidatorUtil = new TitleValidatorUtil(editTextTitle.getText().toString());
        if (titleValidatorUtil.validate() != 0) {
            valid = false;
            //TODO: Ortak bir utill oluşturulacak
            editTextTitle.setError(titleValidatorUtil.getErrorMessage(this, titleValidatorUtil.validate()));
        }
        return valid;
    }

    private void allCategoriesSelected() {
        if (selectedCategories[0] && selectedCategories[1] && selectedCategories[2]
                && selectedCategories[3]) {
            multiSpinnerCategory.setText(getResources().getString(R.string.all));
        }
    }

    private void  fillSelectedCategories(boolean[] selected) {
        selectedCategoriesArray = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if(selected[i]) selectedCategoriesArray.add(discountCategory[i]);
        }
    }

    @OnClick(R.id.activity_create_discount_textview_photo)
    public void openImageIntent() {
        PickImageDialog.build(new PickSetup().setSystemDialog(true)).show(this);
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {
            imagePath = pickResult.getPath();

        } else {
            Toast.makeText(this, pickResult.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
