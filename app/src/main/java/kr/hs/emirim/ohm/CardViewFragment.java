package kr.hs.emirim.ohm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_item, container, false);

        ListView card_image = view.findViewById(R.id.card_list);
        TextView card_title = view.findViewById(R.id.card_title);
        TextView card_desc = view.findViewById(R.id.card_desc);
        TextView card_day = view.findViewById(R.id.card_day);


        if (getArguments() != null) {
            Bundle args = getArguments();
            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            card_title.setText(args.getString("titleRes"));
            card_desc.setText(args.getString("descRes"));
            card_day.setText(args.getString("dayRes"));
        }

        return view;
    }
}
