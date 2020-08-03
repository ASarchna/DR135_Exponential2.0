package com.example.gowa_goaoverwhelminglywelcomesyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gowa_goaoverwhelminglywelcomesyou.Login.LoginOptionsActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.PlaceDetails.PlaceDetailsActivity;
import com.example.gowa_goaoverwhelminglywelcomesyou.Splash.SplashActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class TestActivity extends AppCompatActivity {

    private Camera mCamera;
    private SurfaceViewActivity mPreview;
    String key;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FirebaseDatabase.getInstance().getReference().child("key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                key = String.valueOf(dataSnapshot.getValue());
                Log.d("xxxx",key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new SurfaceViewActivity(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, mPicture);
                    }
                }
        );
    }
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();// attempt to get a Camera instance
            Camera.Parameters params = c.getParameters();
//*EDIT*//params.setFocusMode("continuous-picture");
//It is better to use defined constraints as opposed to String, thanks to AbdelHady
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            c.setParameters(params);

        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
//            showBottomSheet();
            Bitmap picture = BitmapFactory.decodeByteArray(data, 0, data.length);
            int width = picture.getWidth();
            int height = picture.getHeight();
            Bitmap img = Bitmap.createScaledBitmap(picture,width/10, height/10,false);
            Log.d("xxxx", picture.getWidth()+" "+picture.getHeight());
            Log.d("xxxx", img.getWidth()+" "+img.getHeight());

            sendRequest(img);

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null){
                return;
            }

            try {

                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    };

    private void sendRequest(final Bitmap img) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getString(R.string.model_url), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("xxxx",response.toString());
                        if(response.has("payload")){
                            try {
                            String result = response.getJSONArray("payload").getJSONObject(0).getString("displayName");
                            showBottomSheet(result);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("xxxx",error.toString());

                    }
                }) {
                @Override
                public byte[] getBody() {
                JSONObject body = new JSONObject();
                try {
                    JSONObject j1 = new JSONObject();
                    JSONObject j2 = new JSONObject();
                    j1.put("imageBytes", MyHelper.getbase64Image(img).replaceAll("(\\r|\\n|\\r\\n)+", ""));
                    j2.put("image", j1);
                    body.put("payload",j2);
                    Log.d("xxxx",body.toString());
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("message", body.toString());
                    clipboard.setPrimaryClip(clip);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return body.toString().getBytes();
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", key);
                return params;
            }
                @Override
                public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(jsonObjectRequest);
        }


    private void showBottomSheet(String result) {
        final String placeId = MyHelper.getMappedMonument(Integer.parseInt(result));
        final String placeDesc = MyHelper.getMappedMonumentDesc(placeId);
//        final String placeId = "aguada-fort";

        View bottomView = getLayoutInflater().inflate(R.layout.bottom_sheet_scanned, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(TestActivity.this);
        dialog.setContentView(bottomView);
        ((TextView)dialog.findViewById(R.id.report_title)).setText(MyHelper.getMappedMonumentName(placeId));
        ((TextView)dialog.findViewById(R.id.why_report)).setText(MyHelper.getMappedMonumentDesc(placeId));
        dialog.show();

        dialog.findViewById(R.id.report_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, PlaceDetailsActivity.class);
                intent.putExtra("placeId",placeId);
                startActivity(intent);
            }
        });
        dialog.findViewById(R.id.report_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                recreate();
            }
        });
    }
}
