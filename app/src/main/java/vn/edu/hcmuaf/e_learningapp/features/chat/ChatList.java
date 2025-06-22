package vn.edu.hcmuaf.e_learningapp.features.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import vn.edu.hcmuaf.e_learningapp.R;

public class ChatList extends Fragment {
    public ChatList() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_chats, container, false);
    }
}

