package com.example.apitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PutActivity extends AppCompatActivity {

    private Button putButton, patchButton, delButton;
    private TextView textView;
    private APIclient apIclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        textView=findViewById(R.id.putFinalResultId);
        putButton=findViewById(R.id.putButton);
        patchButton=findViewById(R.id.patchButtonId);
        delButton=findViewById(R.id.delButtonId);


        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apIclient=retrofit.create(APIclient.class);
        
        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putPost();
            }
        });
        
        patchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patchPost();
            }
        });
        
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost();
            }
        });
    }

    private void deletePost() {
        Call<Void> call =apIclient.deletePost(4);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText("Code: "+response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void patchPost() {
        Post post=new Post(23,null,"My new text here");
        Call<Post> call =apIclient.patchPost(4,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(PutActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1=response.body();
                textView.setText("Code: "+response.code()+"\n"+"ID: "+post1.getPostId()+"\n"+
                        "User Id: "+post1.getUserId()+"\n"+"Title: "+post1.getTitle()+"\n"+
                        "Text: "+post1.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void putPost() {

        Post post=new Post(23,null,"My new text here");
        Call<Post> call =apIclient.putPost(4,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(PutActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Post post1=response.body();
                textView.setText("Code: "+response.code()+"\n"+"ID: "+post1.getPostId()+"\n"+
                        "User Id: "+post1.getUserId()+"\n"+"Title: "+post1.getTitle()+"\n"+
                        "Text: "+post1.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PutActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}