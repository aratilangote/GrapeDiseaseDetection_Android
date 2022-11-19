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
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {

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

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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

        btn_arrow_cropcare.setOnClickListener(view1 -> {
            Intent intent = new Intent(view1.getContext(), BottomCropCareFragment.class);
            view1.getContext().startActivity(intent);
        });

        btn_arrow_weather.setOnClickListener(view12 -> {
            Intent intent1 = new Intent(view12.getContext(), BottomWeatherFragment.class);
            view12.getContext().startActivity(intent1);
        });
    }

    //AdapterViewFlipper viewFlipper custom class
    static class ViewFlipperAdapter extends BaseAdapter {
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

