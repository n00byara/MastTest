package ru.n00byara.masttest.ui.adapters.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.n00byara.masttest.R;
import ru.n00byara.masttest.components.clientapi.pojo.Products;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Products.Product> products;

    public ProductAdapter(Context context, List<Products.Product> products) {
        this.inflater = LayoutInflater.from(context);
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Products.Product product = products.get(position);

        holder.title.setText(product.title);
        holder.price.setText("price: " + product.price);
        holder.thumbnail.setText(product.thumbnail);
        holder.description.setText(product.description);

        if (product.images.size() != 0) {
            String image = getImageUrl(product.images);
            Picasso.get().load(image).into(holder.image);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.description.getVisibility() == View.GONE) {
                    holder.image.setVisibility(View.VISIBLE);
                    holder.description.setVisibility(View.VISIBLE);
                } else {
                    holder.image.setVisibility(View.GONE);
                    holder.description.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView title;
        final TextView price;
        final TextView description;
        final ImageView image;
        final TextView thumbnail;
        final CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.description = view.findViewById(R.id.description);
            this.price = view.findViewById(R.id.price);
            this.title = view.findViewById(R.id.title);
            this.image = view.findViewById(R.id.phone_image);
            this.thumbnail = view.findViewById(R.id.thumbnail);
            this.cardView = view.findViewById(R.id.product_card);
        }
    }

    private String getImageUrl(List<String> imageStrings) {
        int index = getNumber(imageStrings.size());

        return imageStrings.get(index);
    }

    private int getNumber(int num) {
        return (int) (Math.random() * num);
    }
}
