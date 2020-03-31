package com.geektech.quizapp.presentation.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.core.CoreFragment;
import com.geektech.quizapp.model.History;
import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.presentation.history.recycler.HistoryAdapter;

import java.util.List;

public class HistoryFragment extends CoreFragment {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel

        mViewModel.history.observe((LifecycleOwner) getContext(), new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> quizResults) {
                adapter.update(quizResults);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.recycler_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new HistoryAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    protected int getViewLayout() {
        return R.layout.fragment_history;
    }




}

