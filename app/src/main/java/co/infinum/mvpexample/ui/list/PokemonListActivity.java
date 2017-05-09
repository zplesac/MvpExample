package co.infinum.mvpexample.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerAdapter;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerView;
import co.infinum.mvpexample.R;
import co.infinum.mvpexample.data.models.Pokemon;
import co.infinum.mvpexample.di.components.AppComponent;
import co.infinum.mvpexample.ui.shared.BaseActivity;

public class PokemonListActivity extends BaseActivity implements PokemonListMvp.View {

    @BindView(R.id.pokemonsRecyclerView)
    MjolnirRecyclerView pokemonsRecyclerView;

    @BindView(R.id.emptyView)
    View emptyView;

    private PokemonsAdapter adapter;

    @Inject
    PokemonListMvp.Presenter presenter;

    @Override
    protected void injectDependencies(AppComponent appComponent) {
        appComponent.plus(new PokemonListDi.Module(this)).inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);
        ButterKnife.bind(this);
        presenter.init();
    }

    @Override
    public void initUi() {
        adapter = new PokemonsAdapter(this);
        adapter.setOnClickListener(new MjolnirRecyclerAdapter.OnClickListener<Pokemon>() {
            @Override
            public void onClick(int index, Pokemon item) {
                presenter.onItemClicked(index, item);
            }
        });

        pokemonsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setPokemonList(List<Pokemon> pokemons) {
        adapter.addAll(pokemons);
    }

    @Override
    public void navigateToDetails(Pokemon pokemon) {

    }

    @Override
    public void setEmptyViewVisibility(boolean isVisible) {
        if (isVisible) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.cancel();
    }
}
