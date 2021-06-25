package kr.hs.emirim.ohm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {
    private List<ParticipantVo> data;

    public ParticipantAdapter(List<ParticipantVo> data, Context ctx) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nickname;
        private TextView state;
        private CircleImageView profile_img;
        private TextView me_badge;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.UserName);
            profile_img = itemView.findViewById(R.id.ProfileImg);
            state = itemView.findViewById(R.id.State);
            me_badge = itemView.findViewById(R.id.BadgeMe);
        }
    }
    @NonNull
    @NotNull
    @Override
    public ParticipantAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.paticipant_item_me, parent, false);
        ParticipantAdapter.ViewHolder vh = new ParticipantAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ParticipantAdapter.ViewHolder holder, int position) {
        ParticipantVo data_this = data.get(position);
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        holder.nickname.setText(data_this.getNickname());

        if(position==0){
            holder.state.setText("방장");
        }
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("rooms").child(ChatingActivity.code);
//        final String[] ownerId = {""};
//        mDatabase.child("ownerID").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
//                ownerId[0] = String.valueOf(task.getResult().getValue());
//                if (ownerId[0].equals(user.getUid())){
//                    holder.me_badge.setVisibility(View.VISIBLE); //여기서 보이게 하시면 됨
//                }
//            }
//        });
//
//        holder.profile_img.setImageURI(user.getPhotoUrl());

    }

    @Override
    public int getItemCount() {
        return data == null?0:data.size();
    }


}