package kr.hs.emirim.ohm;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatingAdapter extends RecyclerView.Adapter<ChatingAdapter.ViewHolder> {
    private List<ChatingData> mDataset;
    private String mNickName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView chat_nickname; //닉네임 
        private TextView chat_msg; //채팅 내용
        private LinearLayout chat_pane;
        private CircleImageView other_profile;
        private View rootView;

        public ViewHolder(LinearLayout v) {
            super(v);
            chat_msg = v.findViewById(R.id.chat_msg);
            chat_nickname = v.findViewById(R.id.chat_nickname);
            chat_pane = v.findViewById(R.id.my_chat_pane);
            other_profile = v.findViewById(R.id.other_profileimg);
            rootView = v;
        }
    }

    public ChatingAdapter(List<ChatingData> MyDataSet, Context context, String mNickName) {
        mDataset = MyDataSet;
        this.mNickName = mNickName;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_chat, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatingData chat = mDataset.get(position);

        holder.chat_nickname.setText(chat.getNickname());
        holder.chat_msg.setText(chat.getMsg()); //DTD

        if(chat.getNickname() != null && chat.getNickname().equals(this.mNickName)){
            //holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            //holder.chat_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END); //내가 채팅을 할 경우 오른쪽
            holder.other_profile.setVisibility(View.GONE);
            holder.chat_nickname.setVisibility(View.GONE);
            holder.chat_pane.setGravity(Gravity.RIGHT);

        }else{
            //holder.chat_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            //holder.chat_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START); //상대방이 채팅을 할 경우 왼쪽
            holder.other_profile.setVisibility(View.VISIBLE);
            holder.chat_pane.setGravity(Gravity.LEFT);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
    public ChatingData getChat(int positon){
      return mDataset != null ? mDataset.get(positon) : null;
    }
    public void addChat(ChatingData chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }
}
