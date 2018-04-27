package com.example.ari.itellu.admin.ukm;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class inputUkm extends Fragment {

    private TextInputEditText mNamaUkm,mDeskripsi;
    private StorageReference refPhoto;
    private boolean isPicChange = false;
    private ImageView imgPhoto;
    private Button photoButton;
    private ProgressDialog pbDialog;
    private FloatingActionButton postButton;
    private Uri photoUrl;



    public inputUkm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_ukm, container, false);
        pbDialog = new ProgressDialog(getContext());
        imgPhoto = view.findViewById(R.id.imgPhoto);
        mNamaUkm = view.findViewById(R.id.editTextNamaUkm);
        mDeskripsi = view.findViewById(R.id.editTextDeskripsi);
        photoButton = view.findViewById(R.id.btnChoose);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pilihGambar();
            }
        });
        postButton = view.findViewById(R.id.btnPost);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNamaUkm.getText().toString().isEmpty()){
                    mNamaUkm.setError("Requied");
                    return;
                }if(mDeskripsi.getText().toString().isEmpty()){
                    mDeskripsi.setError("Requied");
                    return;
                }if(!isPicChange) {
                    Toast.makeText(getContext(), "Choose The Photo!", Toast.LENGTH_SHORT).show();
                    return;
                }
                pbDialog.setMessage("Uploading..");
                pbDialog.setIndeterminate(true);
                pbDialog.show();
                uploadData();

            }
        });

        return view;
    }

    private void uploadData() {
        refPhoto = FirebaseC.storageRef.child("gambarUkm/" + System.currentTimeMillis() +".jpg");
        final StorageReference photoImagesRef = FirebaseC.storageRef.child("gambarUkm/" + System.currentTimeMillis() +".jpg");
        refPhoto.getName().equals(photoImagesRef.getName());
        refPhoto.getPath().equals(photoImagesRef.getPath());
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();
        Bitmap bitmap = imgPhoto.getDrawingCache(); //convert imageview ke bitmap
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); //convert bitmap ke bytearray
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = refPhoto.putBytes(data); //upload image yang sudah dalam bentuk bytearray ke firebase storage
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                photoUrl = taskSnapshot.getDownloadUrl();
                String key = FirebaseC.refPhoto.push().getKey();
                FirebaseC.refPhoto.child(key).setValue(new mUkm(
                        key,
                        photoUrl.toString(),
                        mNamaUkm.getText().toString(),
                        mDeskripsi.getText().toString()
                ));
                pbDialog.dismiss();
                Toast.makeText(getContext(), "Uploaded!", Toast.LENGTH_SHORT).show();
                refreshq();
//                Intent intent = getActivity().getIntent();
//                getActivity().finish();
//                startActivity(intent);
            }
        });
    }



    private void pilihGambar() {
        ImagePicker.create(inputUkm.this)
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

    private void refreshq() {
        getFragmentManager().beginTransaction().detach(inputUkm.this).attach(inputUkm.this).commit();
        mNamaUkm.setText(null);
        mDeskripsi.setText(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) { // jika ada data dipilih
            Image image = ImagePicker.getFirstImageOrNull(data); //ambil first image
            File imgFile = new File(image.getPath()); // dapatkan lokasi gambar yang dipilih
            if(imgFile.exists()){ //jika ditemukan
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()); //convert file ke bitmap
                imgPhoto.setImageBitmap(myBitmap); //set imageview dengan gambar yang dipilih
                isPicChange = true; // ubah state menjadi true untuk menandakan gambar telah dipilih
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
