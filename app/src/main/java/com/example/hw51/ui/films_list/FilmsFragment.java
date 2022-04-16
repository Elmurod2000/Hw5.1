package com.example.hw51.ui.films_list;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw51.App;
import com.example.hw51.R;
import com.example.hw51.data.models.Film;
import com.example.hw51.databinding.FragmentFilmsBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsFragment extends Fragment implements OnClick {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;



    public FilmsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new FilmsAdapter(this::onClick);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentFilmsBinding.inflate(
                inflater,
                container,
                false
        );
        binding.recycler.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    adapter.setFilms(response.body());
                }
                else {
                    Snackbar.make(
                            binding.getRoot(),
                            response.message(),
                            BaseTransientBottomBar.LENGTH_LONG
                    )
                            .setBackgroundTint(Color.YELLOW)
                            .show();

                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Snackbar.make(
                        binding.getRoot(),
                        t.getLocalizedMessage(),
                        BaseTransientBottomBar.LENGTH_LONG
                )
                        .setBackgroundTint(Color.YELLOW)
                        .show();
            }
        });
    }

    @Override
    public void onClick(Film film) {
        Bundle bundle=new Bundle();
        bundle.putString("id", film.getId());
        NavController navController= Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.detailFragment,bundle);
    }
}