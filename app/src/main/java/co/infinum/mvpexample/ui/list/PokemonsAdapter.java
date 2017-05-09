package co.infinum.mvpexample.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.infinum.mjolnirrecyclerview.MjolnirRecyclerAdapter;
import co.infinum.mjolnirrecyclerview.MjolnirViewHolder;
import co.infinum.mvpexample.R;
import co.infinum.mvpexample.data.models.Pokemon;

public class PokemonsAdapter extends MjolnirRecyclerAdapter<Pokemon> {

    public PokemonsAdapter(Context context) {
        super(context, Collections.<Pokemon>emptyList());
    }

    @Override
    protected MjolnirViewHolder<Pokemon> onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pokemon, parent, false);
        return new ViewHolder(view, listener);
    }

    static class ViewHolder extends MjolnirViewHolder<Pokemon> {

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.rootView)
        View rootView;

        private OnClickListener listener;

        public ViewHolder(View itemView, OnClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(final Pokemon item, final int position, List<Object> payloads) {
            titleTextView.setText(item.getName());
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position, item);
                }
            });
        }
    }
}

