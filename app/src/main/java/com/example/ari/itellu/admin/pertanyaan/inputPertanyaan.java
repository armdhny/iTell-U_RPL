package com.example.ari.itellu.admin.pertanyaan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ari.itellu.FirebaseC;
import com.example.ari.itellu.R;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class inputPertanyaan extends Fragment {
    private TextInputEditText mJudulPertanyaan, mDeskripsiPertanyaan;
    private StorageReference refEvent;
    private FloatingActionButton postTanyaButton;
    private ProgressDialog pbDialog;


    public inputPertanyaan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_pertanyaan, container, false);

        mJudulPertanyaan = (TextInputEditText) view.findViewById(R.id.inputJudulTanya);
        mDeskripsiPertanyaan = (TextInputEditText) view.findViewById(R.id.inputDeskripsiTanya);
        pbDialog = new ProgressDialog(getContext());

        postTanyaButton = view.findViewById(R.id.btnPostTanya);
        postTanyaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mJudulPertanyaan.getText().toString().isEmpty()) {
                    mJudulPertanyaan.setError("Required");
                    return;
                }
                if (mDeskripsiPertanyaan.getText().toString().isEmpty()) {
                    mDeskripsiPertanyaan.setError("Required");
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
        Log.d("uploadData", "Proses Upload");
        String key_pertanyaan = FirebaseC.refPertanyaan.push().getKey();
        FirebaseC.refPertanyaan.child(key_pertanyaan).setValue(new mPertanyaan(
                key_pertanyaan,
                FirebaseC.currentUser.getEmail().split("@")[0],
                FirebaseC.currentUser.getEmail(),
                mJudulPertanyaan.getText().toString(),
                mDeskripsiPertanyaan.getText().toString()
        ));
        pbDialog.dismiss();
        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
        refreshq();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] data = baos.toByteArray();
//        UploadTask uploadTask = new UploadTask();
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                String key_pertanyaan = FirebaseC.refPertanyaan.push().getKey();
//                FirebaseC.refPertanyaan.child(key_pertanyaan).setValue(new mPertanyaan(

//                ));
//                pbDialog.dismiss();
//                Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
//                refreshq();
//            }
//        });

    }

    private void refreshq() {
        getFragmentManager().beginTransaction()
                .detach(inputPertanyaan.this).attach(inputPertanyaan.this).commit();
        mJudulPertanyaan.setText(null);
        mDeskripsiPertanyaan.setText(null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
