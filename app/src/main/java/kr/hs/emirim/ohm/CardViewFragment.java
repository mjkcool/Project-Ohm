package kr.hs.emirim.ohm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_room, container, false);

        //ImageView card_image = view.findViewById(R.id.card_image);
        TextView card_title = view.findViewById(R.id.title_meeting_item);
        TextView card_desc = view.findViewById(R.id.desc_meeting_item);
        TextView card_day = view.findViewById(R.id.date_meeting_item);


        if (getArguments() != null) {
            Bundle args = getArguments();
            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            //card_image.setImageResource(args.getInt("imgRes"));
            card_title.setText(args.getString("titleRes"));
            card_desc.setText(args.getString("descRes"));
            card_day.setText(args.getString("dayRes"));
        }

        return view;
    }
}
