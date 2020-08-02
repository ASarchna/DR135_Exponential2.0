package com.example.gowa_goaoverwhelminglywelcomesyou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.PlaceDetailsActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.ui.home.HomeViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlaceDescriptionActivity extends AppCompatActivity {

    ImageView scannedImage;
    CoordinatorLayout bottomView;
    private HomeViewModel homeViewModel;
    LinearLayout linearLayout;
    BottomSheetBehavior bottomSheetBehavior;

    //Camera Variables
    private static final int CAMERA_PHOTO = 100;
    private Uri imageToUploadUri;
    String currentPhotoPath;
    String imageCaptured;
    private Bitmap rgbFrameBitmap = null;
    DatabaseReference reference;
    String description = "";
    String monument = "";
    DatabaseReference response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_description_activity);
        response = FirebaseDatabase.getInstance().getReference();
        response.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                monument = String.valueOf(dataSnapshot.child("current-place").getValue());
                Log.e("---ANS---",monument);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        try {
//            classifier = Classifier.create(this, Classifier.Model.FLOAT, Classifier.Device.CPU, 10);
//        } catch (IOException e) {
//            Log.e("model create", e.toString());
//        }
        init();

        reference = FirebaseDatabase.getInstance().getReference().child("place-details");

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        linearLayout = findViewById(R.id.linera1);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        dispatchTakePictureIntent();

//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,0);

    }

    private void init() {
        scannedImage = findViewById(R.id.scanned_image);
        bottomView = findViewById(R.id.bottom_view);

    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            if (photoFile != null) {
                Uri photoURI = GenericFileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                getApplicationContext().grantUriPermission("com.android.App1.app", photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        imageCaptured = imageFileName;

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PHOTO && resultCode == Activity.RESULT_OK) {
            scannedImage.setImageURI(Uri.parse(currentPhotoPath));
            rgbFrameBitmap = getBitmap(currentPhotoPath);
//            results = classifier.recognizeImage(rgbFrameBitmap, 0);
//            Log.e("Classifier Response : ", results.toString());
            TextView title = bottomView.findViewById(R.id.title);
            TextView desc = bottomView.findViewById(R.id.desc);
//            monument = String.valueOf(results.get(0)).split("\\] ")[1].split("\\(")[0];
            Log.e("xxxx",monument);
            Intent intent = new Intent(PlaceDescriptionActivity.this, PlaceDetailsActivity.class);
            if(monument.equalsIgnoreCase("St. Cajetan Church")){
                intent.putExtra("placeId","st-cajetan-church");

            }
            else if(monument.equalsIgnoreCase("Church and Convent of St. Francis of Assisi")){
                intent.putExtra("placeId","church-and-convent-of-st-francis-of-assisi");

            }
            else{
                intent.putExtra("placeId", monument.trim().toLowerCase().replaceAll(" ", "-"));
            }
            Log.e("xxxx",monument.trim().toLowerCase().replaceAll(" ","-"));
            startActivity(intent);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    description = String.valueOf(dataSnapshot.child(monument).getValue());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            title.setText(String.valueOf(monument));
            desc.setText(description);
//            if(imageToUploadUri != null){
//                Uri selectedImage = imageToUploadUri;
//                getContentResolver().notifyChange(selectedImage, null);
//                Bitmap reducedSizeBitmap = getBitmap(imageToUploadUri.getPath());
//                if(reducedSizeBitmap != null){
//                    scannedImage.setImageBitmap(reducedSizeBitmap);
//                    bottomView.setVisibility(View.VISIBLE);}
        } else {
            Toast.makeText(this, "Error while capturing Image", Toast.LENGTH_LONG).show();
        }
    }


    private Bitmap getBitmap(String path) {

//        Uri uri = FileProvider.getUriForFile();
        Uri uri = FileProvider.getUriForFile(PlaceDescriptionActivity.this, PlaceDescriptionActivity.this.getPackageName() + ".provider", new File(path));

        InputStream in = null;
        try {
            final int IMAGE_MAX_SIZE = 1200000; // 1.2MP
            in = getContentResolver().openInputStream(uri);

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, o);
            in.close();


            int scale = 1;
            while ((o.outWidth * o.outHeight) * (1 / Math.pow(scale, 2)) >
                    IMAGE_MAX_SIZE) {
                scale++;
            }
            Log.d("", "scale = " + scale + ", orig-width: " + o.outWidth + ", orig-height: " + o.outHeight);

            Bitmap b = null;
            in = getContentResolver().openInputStream(uri);
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                o = new BitmapFactory.Options();
                o.inSampleSize = scale;
                b = BitmapFactory.decodeStream(in, null, o);

                // resize to desired dimensions
                int height = b.getHeight();
                int width = b.getWidth();
                Log.d("", "1th scale operation dimenions - width: " + width + ", height: " + height);

                double y = Math.sqrt(IMAGE_MAX_SIZE
                        / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(b, (int) x,
                        (int) y, true);
                b.recycle();
                b = scaledBitmap;

                System.gc();
            } else {
                b = BitmapFactory.decodeStream(in);
            }
            in.close();

            Log.d("", "bitmap size - width: " + b.getWidth() + ", height: " +
                    b.getHeight());
            return b;
        } catch (IOException e) {
            Log.e("", e.getMessage(), e);
            return null;
        }
    }


}
