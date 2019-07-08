package du.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.camera.CameraSettings;

import java.lang.reflect.Parameter;
import java.util.List;

public class SearchFragment extends Fragment implements CompoundBarcodeView.TorchListener{

    private CompoundBarcodeView barcodeView;
    private TextView text;
    private Button btnRescan;
    private Button btnFlashOn;
    private Button btnFlashOff;
    private boolean isFlashOn = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        barcodeView = (CompoundBarcodeView) view.findViewById(R.id.barcode_scanner);
        barcodeView.setStatusText("");
        barcodeView.decodeSingle(callback);
       /* CameraSettings cameraSettings = new CameraSettings();
        cameraSettings.setRequestedCameraId(1);
        barcodeView.getBarcodeView().setCameraSettings(cameraSettings);*/
        text = (TextView) view.findViewById(R.id.txtResult);
        btnRescan = (Button) view.findViewById(R.id.btnRescan);
        btnFlashOn = (Button) view.findViewById(R.id.btnFlashOn);
        btnFlashOff = (Button) view.findViewById(R.id.btnFlashOff);
        btnRescan.setOnClickListener(onclick);
        btnFlashOn.setOnClickListener(onclick);
        btnFlashOff.setOnClickListener(onclick);

        Reader reader = new MultiFormatReader();
        return view;
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                Log.d("test", "barcodeResult: " + result.getText());
                text.setText(result.getText().trim());
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public void onResume() {
        barcodeView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        barcodeView.pause();
        super.onPause();
    }

    private View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.equals(btnRescan)) {
                barcodeView.setStatusText("");
                barcodeView.decodeSingle(callback);
                text.setText("");
            }
            if(v.equals(btnFlashOn)){
                barcodeView.setTorchOn();
            }
            if(v.equals(btnFlashOff)){
                barcodeView.setTorchOff();
            }
        }
    };

    private boolean hasFlash() {
        return getActivity().getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


    @Override
    public void onTorchOn() {
        isFlashOn = true;
    }

    @Override
    public void onTorchOff() {
        isFlashOn = false;
    }
}

