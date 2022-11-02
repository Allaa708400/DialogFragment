package com.example.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class DialogFragment extends androidx.fragment.app.DialogFragment{


    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_ICON = "icon";

    // TODO: Rename and change types of parameters
    private String title;
    private String message;
    private int icon;

    public DialogFragment() {
        // Required empty public constructor
    }

    private OnPositiveClickListener positiveClickListener;
    private OnNegativeClickListener negativeClickListener;
    private OnNeutralClickListener neutralClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnPositiveClickListener){
            positiveClickListener = (OnPositiveClickListener) context;
        }
        else
              throw new RuntimeException("please implement listener: OnPositiveClickListener");

        if (context instanceof OnNegativeClickListener){
           negativeClickListener = (OnNegativeClickListener) context;
        }
        else
            throw new RuntimeException("please implement listener: OnNegativeClickListener");

        if (context instanceof OnNeutralClickListener){
            neutralClickListener = (OnNeutralClickListener) context;
        }
        else
            throw new RuntimeException("please implement listener: OnNeutralClickListener");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        positiveClickListener = null;
        negativeClickListener = null;
        neutralClickListener = null;
    }

    public static DialogFragment newInstance(String title, String message, int icon) {
        DialogFragment fragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putString(ARG_MESSAGE, message);
        bundle.putInt(ARG_ICON,icon);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(ARG_TITLE);
            message = args.getString(ARG_MESSAGE);
            icon = args.getInt(ARG_ICON);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(icon);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                positiveClickListener.onPositiveButtonClicked();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                negativeClickListener.onNegativeButtonClicked();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                neutralClickListener.onNeutralButtonClicked();
            }
        });
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    public interface OnPositiveClickListener{
        void onPositiveButtonClicked();
    }
    public interface OnNegativeClickListener{
        void onNegativeButtonClicked();
    }
    public interface OnNeutralClickListener{
        void onNeutralButtonClicked();
    }





}