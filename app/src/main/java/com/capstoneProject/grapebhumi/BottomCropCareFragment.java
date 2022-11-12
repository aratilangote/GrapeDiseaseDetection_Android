package com.capstoneProject.grapebhumi;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstoneProject.grapebhumi.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomCropCareFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomCropCareFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView resulttxt, demotxt, classified, clickhere;
    ImageView imageView;
    Button picture;

    int imagesize = 256;

    ActivityResultLauncher<Intent> startforResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null && result.getResultCode() == RESULT_OK) {
                        Bitmap image = (Bitmap) result.getData().getExtras().get("data");
                        int dimension = Math.min(image.getWidth(), image.getHeight());
                        image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                        imageView.setImageBitmap(image);

                        demotxt.setVisibility(View.GONE);
                        clickhere.setVisibility(View.VISIBLE);
                        classified.setVisibility(View.VISIBLE);
                        resulttxt.setVisibility(View.VISIBLE);

                        image = Bitmap.createScaledBitmap(image, imagesize, imagesize, false);
                        classifyImage(image);
                    }

                }
            });

    public BottomCropCareFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomCropCareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomCropCareFragment newInstance(String param1, String param2) {
        BottomCropCareFragment fragment = new BottomCropCareFragment();
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
        return inflater.inflate(R.layout.fragment_bottom_crop_care, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        resulttxt = view.findViewById(R.id.result);
        imageView = view.findViewById(R.id.image);
        picture = view.findViewById(R.id.btn);

        demotxt = view.findViewById(R.id.demotxt);
        clickhere = view.findViewById(R.id.clickhere);
        classified = view.findViewById(R.id.classified);

        demotxt.setVisibility(View.VISIBLE);
        clickhere.setVisibility(View.GONE);
        classified.setVisibility(View.GONE);
        resulttxt.setVisibility(View.GONE);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startforResult.launch(cameraIntent);
                }else {
                    getActivity().requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void classifyImage(Bitmap image) {
        try {
            Model model = Model.newInstance(getContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imagesize * imagesize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imagesize * imagesize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for(int i=0; i<imagesize; i++){
                for (int j= 0; j<imagesize; j++){
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence = outputFeature0.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            for(int i=0; i<confidence.length; i++){
                if (confidence[i] > maxConfidence){
                    maxConfidence = confidence[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Grape_Black_rot", "Grape_Esca_(Black_Measles)", "Grape_healthy", "Grape_Leaf_blight_(Isariopsis_Leaf_Spot)"};
            resulttxt.setText(classes[maxPos]);
            resulttxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/search?q="+resulttxt.getText())));
                }
            });

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
            e.printStackTrace();
        }
    }
}