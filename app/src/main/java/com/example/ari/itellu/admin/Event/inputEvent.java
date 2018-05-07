package com.example.ari.itellu.admin.Event;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class inputEvent extends Fragment {

    private TextInputEditText mNamaEvent, mLokasiEvent, mDeskripsiEvent;
    private TextView mTglEvent;
    private Button photoEventButton, dateEventButton;
    private ImageView imgPhotoEvent;
    private Uri photoUriEvent;
    private FloatingActionButton postEventButton;
    private ProgressDialog pbDialog;
    private StorageReference refPhotoEvent;
    private boolean isPicChange = false;

    private int mYear, mMonth, mDay;


    public inputEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_event, container, false);

        pbDialog = new ProgressDialog(getContext());
        mNamaEvent = view.findViewById(R.id.editTextNamaEvent);
        mLokasiEvent = view.findViewById(R.id.editTextLokasiEvent);
        mDeskripsiEvent = view.findViewById(R.id.editTextDeskripsiEvent);
        mTglEvent = view.findViewById(R.id.txtTglEvent);
        imgPhotoEvent = view.findViewById(R.id.imgphotoEvent);

        photoEventButton = view.findViewById(R.id.btnChooseEvent);
        photoEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihGambar();
            }
        });

        final Calendar eventCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener eventDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                eventCalendar.set(Calendar.YEAR, year);
                eventCalendar.set(Calendar.MONTH, monthOfYear);
                eventCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myformat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myformat, Locale.US);
                mTglEvent.setText(sdf.format(eventCalendar.getTime()));

            }
        };

        dateEventButton = view.findViewById(R.id.btnDateEvent);
        dateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                //Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear, mMonth, mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear, mMonth, mDay);

                                mTglEvent.setText(dayOfMonth + "-" +
                                        (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });

        postEventButton = view.findViewById(R.id.btnPostEvent);
        postEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNamaEvent.getText().toString().isEmpty()) {
                    mNamaEvent.setError("Required");
                    return;
                }
                if (mDeskripsiEvent.getText().toString().isEmpty()) {
                    mDeskripsiEvent.setError("Required");
                    return;
                }
                if (mLokasiEvent.getText().toString().isEmpty()) {
                    mLokasiEvent.setError("Required");
                    return;
                }
                if (mTglEvent.getText().toString().isEmpty()) {
                    mTglEvent.setError("Required");
                    return;
                }
                if (!isPicChange) {
                    Toast.makeText(getContext(), "Choose The Photo", Toast.LENGTH_SHORT).show();
                    return;
                }
                pbDialog.setMessage("Uploading...");
                pbDialog.setIndeterminate(true);
                pbDialog.show();
                uploadData();
            }
        });

        return view;
    }

    private void uploadData() {
        Log.d("Upload", "Proses Upload");
        refPhotoEvent = FirebaseC.storageRef.child("gambarEvent/" + System.currentTimeMillis() + ".jpg");
        final StorageReference photoImagesRef = FirebaseC.storageRef.child("gambarEvent/" + System.currentTimeMillis() + ".jpg");
        refPhotoEvent.getName().equals(photoImagesRef.getName());
        refPhotoEvent.getPath().equals(photoImagesRef.getPath());
        imgPhotoEvent.setDrawingCacheEnabled(true);
        imgPhotoEvent.buildDrawingCache();
        Bitmap bitmap = imgPhotoEvent.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = refPhotoEvent.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                photoUriEvent = taskSnapshot.getDownloadUrl();
                String key = FirebaseC.refPhotoEvent.push().getKey();
                FirebaseC.refPhotoEvent.child(key).setValue(new mEvent(
                        key,
                        photoUriEvent.toString(),
                        mNamaEvent.getText().toString(),
                        mLokasiEvent.getText().toString(),
                        mDeskripsiEvent.getText().toString(),
                        mTglEvent.getText().toString()
                ));
                pbDialog.dismiss();
                Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                refreshq();
            }
        });
    }

    private void refreshq() {
        getFragmentManager().beginTransaction()
                .detach(inputEvent.this).attach(inputEvent.this).commit();
        mNamaEvent.setText(null);
        mLokasiEvent.setText(null);
        mDeskripsiEvent.setText(null);
        mTglEvent.setText(null);
    }

    private void pilihGambar() {
        ImagePicker.create(inputEvent.this)
                .returnMode(ReturnMode.ALL)
                .folderMode(true)
                .toolbarFolderTitle("Folder")
                .toolbarImageTitle("Tap to select")
                .toolbarArrowColor(Color.WHITE)
                .single()
                .limit(1)
                .showCamera(true)
                .imageDirectory("Camera")
                .enableLog(false)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            File imgFile = new File(image.getPath());
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imgPhotoEvent.setImageBitmap(myBitmap);
                isPicChange = true;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
