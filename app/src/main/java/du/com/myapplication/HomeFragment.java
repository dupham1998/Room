package du.com.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import du.com.myapplication.JsonData.Helper;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnNext;
    private EditText title, description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        title = (EditText) view.findViewById(R.id.txt_title);
        description = (EditText) view.findViewById(R.id.txt_description);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnNext)){
            replaceFragments();
        }
    }

    public void replaceFragments(){
        Fragment selectedFragment = null;
        String Title = title.getText().toString().trim();
        String Description = description.getText().toString().trim();

        Helper helper = new Helper();
        String result = helper.JsonToZxing(Description);

        Log.d("test", "replaceFragments: " + result);
        selectedFragment = new InputFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title", Title);
        bundle.putString("description", result);

        selectedFragment.setArguments(bundle);

        // Insert the fragment by replacing any existing fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, selectedFragment);
        transaction.addToBackStack("aa");
        transaction.commit();

    }
}
