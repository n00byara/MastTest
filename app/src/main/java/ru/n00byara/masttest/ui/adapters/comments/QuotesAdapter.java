package ru.n00byara.masttest.ui.adapters.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.n00byara.masttest.R;
import ru.n00byara.masttest.components.clientapi.pojo.Quotes;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Quotes.Quote> quotes;

    public QuotesAdapter(Context context, List<Quotes.Quote> quotes) {
        this.inflater = LayoutInflater.from(context);
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.quote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesAdapter.ViewHolder holder, int position) {
        Quotes.Quote quote = quotes.get(position);

        holder.author.setText(quote.quote);
        holder.quote.setText(quote.author);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView quote;
        final TextView author;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.quote = view.findViewById(R.id.quote);

            this.author = view.findViewById(R.id.username);
        }
    }
}
