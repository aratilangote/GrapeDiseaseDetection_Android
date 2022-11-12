package com.capstoneProject.grapebhumi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomVideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomVideosFragment extends Fragment {

    //Videos
    LinearLayout root;

    String[] list = {
            "https://youtu.be/Iy0E5McxE3A",
            "https://youtu.be/ejhkk0_BNns",
            "https://youtu.be/fPVGuvENhlA",
            "https://youtu.be/FSYb2q8FZE0",
            "https://youtu.be/Iwn-3xKZOhg",
            "https://youtu.be/1uVdjCcgt_0",
            "https://youtu.be/dQIzI5aTTIk",
            "https://youtu.be/hhz7boG4JNg",
            "https://youtu.be/xtbVblyT-hc",
            "https://youtu.be/1rXl_Cb6y4s",
            "https://youtu.be/A5-YiIQCFjo",
            "https://youtu.be/Np8jtzVT_WY",
            "https://youtu.be/J9eJ9vNJNIE",
            "https://youtu.be/Eu_oHN4S6tc",
            "https://youtu.be/_7SQY-q_o_I"
    };



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BottomVideosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomVideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomVideosFragment newInstance(String param1, String param2) {
        BottomVideosFragment fragment = new BottomVideosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottom_videos, null);

        root = view.findViewById(R.id.root);
        for (String item : list) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.videoview, null);
            YouTubePlayerView player = v.findViewById(R.id.videoPlayer);
            getLifecycle().addObserver(player);

            player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(item, 0);
                }
            });
            root.addView(v);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}