package com.example.apitesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePost extends AppCompatActivity {

    private EditText id,title,text;
    private Button postButton;
    private APIclient apIclient;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        id=findViewById(R.id.userId);
        title=findViewById(R.id.userTitleId);
        text=findViewById(R.id.userTextId);
        textView=findViewById(R.id.finalResultId);
        postButton=findViewById(R.id.postButtonId);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apIclient=retrofit.create(APIclient.class);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = id.getText().toString();
                String userTitle= title.getText().toString();
                String userText=text.getText().toString();

                createPost(Integer.parseInt(userId), userTitle, userText);
            }
        });

    }

    private void createPost(int id, String userTitle, String userText) {

        Post post=new Post(id,userTitle, userText);
        Call<Post> call=apIclient.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(CreatePost.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Post post1=response.body();
                textView.setText("Code: "+response.code()+"\n"+"ID: "+post1.getPostId()+"\n"+
                        "User Id: "+post1.getUserId()+"\n"+"Title: "+post1.getTitle()+"\n"+
                        "Text: "+post1.getText());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(CreatePost.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}