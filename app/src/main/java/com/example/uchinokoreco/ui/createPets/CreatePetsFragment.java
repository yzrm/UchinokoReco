package com.example.uchinokoreco.ui.createPets;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uchinokoreco.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CreatePetsFragment extends Fragment {
    public static Fragment getInstance(){ return new CreatePetsFragment();}

    private static final String TAG = CreatePetsActivity.class.getSimpleName();
    private ImageView selectedImageView = null;
    private EditText petNameEditText;
    private CreatePetsViewModel viewModel;
    private File file;

    // PhotoPicker起動用ランチャー
    ActivityResultLauncher<PickVisualMediaRequest> launcher = registerForActivityResult(
            new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri == null) {
                    //TODO:写真取得失敗時の処理
                    Log.d(TAG, "取得失敗");
                } else {
                    // TODO: 写真取得成功時の処理
                    Log.d(TAG, "取得成功！" + uri);

                    float orientation = 0f;
                    switch (getOrientation(uri)) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            orientation = 90f;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            orientation = 180f;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            orientation = 270f;
                            break;
                        default:
                            break;
                    }

                    if (selectedImageView != null) {
                        // TODO: アプリ内に保存した画像を表示
                        try {
                            InputStream stream = requireActivity().getContentResolver().openInputStream(uri);
                            Bitmap bmp = BitmapFactory.decodeStream(new BufferedInputStream(stream));
                            selectedImageView.setImageBitmap(bmp);
                            selectedImageView.setRotation(90f);
                            //選択画像のURIをViewModelに保持する
                            viewModel.setSelectedUri(uri);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    /***
     * 画像の無機を取得
     *
     * @param uri
     * @return
     */
    private int getOrientation(Uri uri) {
        try (InputStream src = getContext().getContentResolver().openInputStream(uri)) {
            ExifInterface exifInterface = new ExifInterface(src);
            return exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        } catch (IOException e) {
            e.printStackTrace();
            return ExifInterface.ORIENTATION_UNDEFINED;
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return  LayoutInflater.from(requireContext()).inflate(R.layout.fragment_create_pets, container, false);
    }

    @Override
    public void onViewCreated(@Nullable View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ViewModelの取得
        viewModel = new ViewModelProvider(requireActivity()).get(CreatePetsViewModel.class);
        //選択画像表示用ImageView
        selectedImageView = view.findViewById(R.id.image);
        //ペット名用EditText
        petNameEditText = view.findViewById(R.id.pet_name_edit_text);
        //各種ボタン設定
        buttonSetting(view);

    }

    private void buttonSetting(View view) {
        //okボタン設定
        Button okBtn = view.findViewById(R.id.ok_button);
//        if (petNameEditText.getText().toString().isEmpty() || selectedImageView.getDrawable() == null) {
//            okBtn.setEnabled(false);
//        } else {
//            okBtn.setEnabled(true);
//        }

        okBtn.setOnClickListener(v ->{
            String petName = petNameEditText.getText().toString().trim();
            if (petName.isEmpty()) return;
            viewModel.savePetsListData(
                    requireActivity(),
                    petName,
                    new CreatePetsViewModel.CreatePetsCallback() {
                        @Override
                        public void onFailed(String massage) {
                            //  エラー理由をToastで表示する。
                            Toast.makeText(requireActivity(), massage, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {
                            //ファイル保存完了後の処理
                            requireActivity().finish();
                        }
                    }
            );
        });

        // galleryボタン設定
        ImageButton imageBtn = view.findViewById(R.id.gallery_button);
        imageBtn.setOnClickListener(v -> {
            launcher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build()
            );
        });
        // cameraボタン設定
        ImageButton imageBtb = view.findViewById(R.id.camera_button);
        imageBtb.setOnClickListener(v -> {
            Log.d(TAG, "Cameraボタンが押された");
        });
    }

}
