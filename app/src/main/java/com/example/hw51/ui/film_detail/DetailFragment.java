package com.example.hw51.ui.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.hw51.App;
import com.example.hw51.R;
import com.example.hw51.data.models.Film;
import com.example.hw51.databinding.FragmentDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDetailBinding.inflate(
                inflater,
                container,
                false
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      String id=getArguments().getString("id");
        App.api.getFilmById(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Film film = response.body();
                    assert film != null;
                    binding.tvDescription.setText(film.getProducer());
                    binding.tvOriginalTitle.setText(film.getOriginalTitle());
                    binding.tvRunningTime.setText(film.getRunning_time());
                    binding.tvDirector.setText(film.getDirector());
                    binding.tvTitle.setText(film.getTitle());
                    Glide.with(requireActivity()).load(film.getMovie_banner()).into(binding.ivMovieBanner);
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}