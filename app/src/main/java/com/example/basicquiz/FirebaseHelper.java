package com.example.basicquiz;

import android.content.Context;
import android.service.autofill.Dataset;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    private DatabaseReference database;
    List<Question> questionList;
    Context _context;
    QuestionDal questionDal;

    public FirebaseHelper(Context context){
       database = FirebaseDatabase.getInstance().getReference();
       questionList = new ArrayList<Question>();
       _context = context;
       questionDal = new QuestionDal(context);
       questionDal.openConnection();
    }


    public void addQuestion(Question question){
       database.child("questions").child(question.id + "").setValue(question);
    }

    public List<Question> getAllQuestions(){

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                Question question = keyNode.getValue(Question.class);
                questionList.add(question);
                boolean result = questionDal.addQuestion(question);
                if(result){
                    Toast.makeText(_context, "Value: " + question.name + " added", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(_context, "Value can't added", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.child("questions").addListenerForSingleValueEvent(valueEventListener);

        return questionList;
    }
}
