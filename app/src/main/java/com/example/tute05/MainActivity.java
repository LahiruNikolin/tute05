package com.example.tute05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText id,name,addr,cont;
    Button save,show,update,del;
    Student std=new Student();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ets
        id=findViewById(R.id.id_et);
        name=findViewById(R.id.name_et);
        addr=findViewById(R.id.addr_et);
        cont=findViewById(R.id.cont_et);

        //buttons
        save=findViewById(R.id.save_btn);
        show=findViewById(R.id.show_btn);
        update=findViewById(R.id.update_btn);
        del=findViewById(R.id.del_btn);

        save.setOnClickListener(this);
        show.setOnClickListener(this);
        update.setOnClickListener(this);
        del.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.save_btn:
                saveData();
                break;

            case R.id.show_btn:
                show();
                break;

            case R.id.update_btn:
                update();
                break;

            case R.id.del_btn:
                delete();
                break;

            default:

        }

    }

    public void clearControls(){
        id.setText("");
        name.setText("");
        addr.setText("");
        cont.setText("");

    }

    public void saveData(){
        myRef=database.getReference().child("Student");
        try {
            if(TextUtils.isEmpty(id.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter an ID",Toast.LENGTH_SHORT).show();

            else if(TextUtils.isEmpty(name.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter a Name",Toast.LENGTH_SHORT).show();

            else if(TextUtils.isEmpty(addr.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please Enter an Address",Toast.LENGTH_SHORT).show();

            else{
                std.setID(id.getText().toString().trim());
                std.setName(name.getText().toString().trim());
                std.setAddress(addr.getText().toString().trim());
                std.setContact_No(Integer.parseInt(id.getText().toString().trim()));

               // myRef.push().setValue(std);
                myRef.child("st1").setValue(std);
                Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT).show();
                clearControls();
            }

        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Invalid Data",Toast.LENGTH_SHORT).show();
        }
    }

    public  void show(){
        // Read from the database
        DatabaseReference myRef=FirebaseDatabase.getInstance().getReference().child("Student").child("st1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             if(dataSnapshot.hasChildren()){
                 id.setText(dataSnapshot.child("id").getValue().toString());
                 name.setText(dataSnapshot.child("name").getValue().toString());
                 addr.setText(dataSnapshot.child("contact_No").getValue().toString());
                 cont.setText(dataSnapshot.child("address").getValue().toString());
             }
             else {
                 Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();
             }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
              //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public  void update(){
        // Read from the database
        DatabaseReference myRef=FirebaseDatabase.getInstance().getReference().child("Student");

         myRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if(dataSnapshot.hasChild("st1")){
                     try{

                         std.setID(id.getText().toString().trim());
                         std.setName(name.getText().toString().trim());
                         std.setAddress(addr.getText().toString().trim());
                         std.setContact_No(Integer.parseInt(id.getText().toString().trim()));

                         database.getReference().child("Student").child("st1").setValue(std);
                         Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                         clearControls();
                     }
                     catch (NumberFormatException e){

                         Toast.makeText(getApplicationContext(),"Invalid data",Toast.LENGTH_SHORT).show();
                     }
                 }
                 else {
                     Toast.makeText(getApplicationContext(),"No source to display",Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onCancelled(DatabaseError error) {
                 // Failed to read value
                 //  Log.w(TAG, "Failed to read value.", error.toException());
             }
         } );
    }

    public void delete(){
        myRef=database.getReference().child("Student");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("st1")){
                    DatabaseReference delRef=database.getReference().child("Student").child("st1");
                    delRef.removeValue();
                    clearControls();
                    Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No source to Delete",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        } );

    }

}













