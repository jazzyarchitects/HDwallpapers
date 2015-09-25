package architects.jazzy.hdwallpapers.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import architects.jazzy.hdwallpapers.R;

public class Wallpaper extends Fragment {
    public static Wallpaper newInstance(String param1, String param2) {
        Wallpaper fragment = new Wallpaper();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Wallpaper() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_wallpaper, container, false);



        return v;
    }

}
