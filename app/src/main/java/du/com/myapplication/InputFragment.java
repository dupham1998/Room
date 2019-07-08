package du.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputFragment extends Fragment {

    private TextView txt1, txt2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        txt1 = (TextView) view.findViewById(R.id.txt_title2);
        txt2 = (TextView) view.findViewById(R.id.txt_description2);

        Bundle bundle = getArguments();

        String title = bundle.getString("title");
        String description = bundle.getString("description");
        txt1.setText(title);
        txt2.setText(description);
        return view;
    }
}
