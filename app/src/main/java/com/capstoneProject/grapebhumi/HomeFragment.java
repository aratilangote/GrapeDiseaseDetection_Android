package com.capstoneProject.grapebhumi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //AdapterViewFlipper
    AdapterViewFlipper adaptFlipper;
    private static final int[] images = {R.drawable.farm, R.drawable.grape_farm, R.drawable.grapegrowth, R.drawable.grape_types};

    Button btn_arrow_cropcare, btn_arrow_weather;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
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
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //AdapterViewFlipper
        //AdapterViewFlipper
        adaptFlipper = view.findViewById(R.id.adptflipper);
        ViewFlipperAdapter viewFlipper = new ViewFlipperAdapter(getContext(), images);
        adaptFlipper.setAdapter(viewFlipper);
        adaptFlipper.setFlipInterval(1000);
        adaptFlipper.setAutoStart(true);


        btn_arrow_cropcare = view.findViewById(R.id.btn_arrow_cropcare);
        btn_arrow_weather = view.findViewById(R.id.btn_arrow_weather);

        btn_arrow_cropcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BottomCropCareFragment.class);
                view.getContext().startActivity(intent);
            }
        });

        btn_arrow_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), BottomWeatherFragment.class);
                view.getContext().startActivity(intent1);
            }
        });
    }


    //AdapterViewFlipper viewFlipper custom class
    class ViewFlipperAdapter extends BaseAdapter{
        Context context;
        int[] images;
        LayoutInflater inflater;

        public ViewFlipperAdapter(Context mycontext, int[] myimages){
            this.context = mycontext;
            this.images = myimages;
            inflater = LayoutInflater.from(mycontext);
        }

        @Override
        public int getCount() {return 4;}

        @Override
        public Object getItem(int i) {return null;}

        @Override
        public long getItemId(int i) {return 0;}

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.activity_adapterimages, null);

            ImageView imageView = view.findViewById(R.id.flip_imgs);
            imageView.setImageResource(images[position]);
            return view;
        }
    }

}