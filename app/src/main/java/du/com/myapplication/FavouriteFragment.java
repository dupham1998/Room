package du.com.myapplication;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class FavouriteFragment extends Fragment {

    private Button btnGenarate;
    private Button btnSave;
    private EditText text;
    private ImageView imageView;
    private Bitmap bitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        btnGenarate = (Button) view.findViewById(R.id.btngenarate);
        btnSave = (Button) view.findViewById(R.id.btnsave);
        text = (EditText) view.findViewById(R.id.txtInfo);
        imageView = (ImageView) view.findViewById(R.id.imagView);
        btnGenarate.setOnClickListener(onClick);
        btnSave.setOnClickListener(onClick);
        return view;
    }

    public View.OnClickListener onClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(v.equals(btnGenarate)){
                String content = text.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(getActivity(), "you cancelled the scanning", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    }catch (WriterException e) {
                        e.printStackTrace();
                    }

                }
            }
            if(v.equals(btnSave)){
                if(bitmap != null){
                    saveImageToExternalStorage(bitmap);
                    Toast.makeText(getActivity(), "Saved successfully, Check gallery", Toast.LENGTH_SHORT).show();

                }
            }
        }
    };

    private void saveImageToExternalStorage(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images_1");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File("/storage/emulated/0/Pictures/", fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(getActivity(), new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }
}
