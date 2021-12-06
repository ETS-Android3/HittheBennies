package com.example.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class GameDifficultyDialogFragment extends DialogFragment  {

    private OnGameDifficultySelectedListener mListener;

    public interface OnGameDifficultySelectedListener {
        void onGameDifficultyClick(int which);
    }




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.pick_game_difficulty);
        builder.setItems(R.array.time_array, (dialog, which) -> {
            // 'which' is the zero-based index position chosen
            mListener.onGameDifficultyClick(which);

        });
        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnGameDifficultySelectedListener) context;
    }
}