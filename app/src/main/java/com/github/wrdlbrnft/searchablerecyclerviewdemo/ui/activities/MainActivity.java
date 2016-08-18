package com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.wrdlbrnft.searchablerecyclerviewdemo.R;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.databinding.ActivityMainBinding;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.adapter.ExampleAdapter;
import com.github.wrdlbrnft.searchablerecyclerviewdemo.ui.models.ExampleModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String[] MOVIES = new String[]{
            "The Woman in Black: Angel of Death",
            "20 Once Again",
            "Taken 3",
            "Tevar",
            "I",
            "Blackhat",
            "Spare Parts",
            "The Wedding Ringer",
            "Ex Machina",
            "Mortdecai",
            "Strange Magic",
            "The Boy Next Door",
            "The SpongeBob Movie: Sponge Out of Water",
            "Kingsman: The Secret Service",
            "Boonie Bears: Mystical Winter",
            "Project Almanac",
            "Running Man",
            "Wild Card",
            "It Follows",
            "C'est si bon",
            "Yennai Arindhaal",
            "Shaun the Sheep Movie",
            "Jupiter Ascending",
            "Old Fashioned",
            "Somewhere Only We Know",
            "Fifty Shades of Grey",
            "Dragon Blade",
            "Zhong Kui: Snow Girl and the Dark Crystal",
            "Badlapur",
            "Hot Tub Time Machine 2",
            "McFarland, USA",
            "The Duff",
            "The Second Best Exotic Marigold Hotel",
            "A la mala",
            "Focus",
            "The Lazarus Effect",
            "Chappie",
            "Faults",
            "Road Hard",
            "Unfinished Business",
            "Cinderella",
            "NH10",
            "Run All Night",
            "X+Y",
            "Furious 7",
            "Danny Collins",
            "Do You Believe?",
            "Jalaibee",
            "The Divergent Series: Insurgent",
            "The Gunman",
            "Get Hard",
            "Home"
    };

    private static final Comparator<ExampleModel> ALPHABETICAL_COMPARATOR = new Comparator<ExampleModel>() {
        @Override
        public int compare(ExampleModel a, ExampleModel b) {
            return a.getText().compareTo(b.getText());
        }
    };

    private ExampleAdapter mAdapter;
    private List<ExampleModel> mModels;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAdapter = new ExampleAdapter(this, ALPHABETICAL_COMPARATOR);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);

        mModels = new ArrayList<>();
        for (int i = 0, count = MOVIES.length; i < count; i++) {
            mModels.add(new ExampleModel(i, MOVIES[i]));
        }
        mAdapter.edit()
                .replaceAll(mModels)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<ExampleModel> filteredModelList = filter(mModels, query);
        mAdapter.edit()
                .replaceAll(filteredModelList)
                .commit();
        mBinding.recyclerView.scrollToPosition(0);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<ExampleModel> filter(List<ExampleModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<ExampleModel> filteredModelList = new ArrayList<>();
        for (ExampleModel model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
