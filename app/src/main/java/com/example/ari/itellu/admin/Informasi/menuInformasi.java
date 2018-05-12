package com.example.ari.itellu.admin.Informasi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ari.itellu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class menuInformasi extends Fragment {


    private String link1 = "<a href=\"https://see.telkomuniversity.ac.id/\">School of Electrical Engineering</a>";
    private String link2 = "<a href=\"http://bie.telkomuniversity.ac.id/\">School of Industrial Engineering</a>";
    private String link3 = "<a href=\"https://soc.telkomuniversity.ac.id\">School of Computing</a>";
    private String link4 = "<a href=\"http://seb.telkomuniversity.ac.id/\">School of Economic Business</a>";
    private String link5 = "<li><a href=\"http://scb.telkomuniversity.ac.id/\">School of Communication and Business</a></li>";
    private String link6 = "<a href=\"http://sci.telkomuniversity.ac.id/\">School of Creative Industry</a>";
    private String link7 = "<a href=\"https://soc.telkomuniversity.ac.id/\">School of Applied Sciences</a>";

    public menuInformasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_informasi, container, false);
        TextView mArticle = (TextView) view.findViewById(R.id.article);
//        mArticle.setClickable(true);
        mArticle.setMovementMethod(LinkMovementMethod.getInstance());
//        mArticle.setText(Html.fromHtml(link1));


        return view;

    }

}
