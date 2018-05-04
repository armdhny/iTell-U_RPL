package com.example.ari.itellu;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Tavs on 26/04/2018.
 */

public class FirebaseC {
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;
    //Firebase Storage
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageRef = storage.getReference();
    public final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final static DatabaseReference refPhoto = database.getReference("ukm");
    public final static DatabaseReference refPhotoKomuniti = database.getReference("komuniti");
}
