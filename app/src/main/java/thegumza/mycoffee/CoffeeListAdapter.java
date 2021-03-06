package thegumza.mycoffee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.txusballesteros.widgets.FitChart;

import java.util.List;

import thegumza.coffeechart.CoffeeChart;

/**
 * Created by Wiwat on 7/30/2015.
 */
public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.ViewHolder>{
    List<Coffee> coffeeList;
    Context context;
    Typeface font;
    Coffee coffee;
    public CoffeeListAdapter(List<Coffee> coffeeList, Context context) {
        this.coffeeList = coffeeList;
        this.context = context;
    }

    @Override
    public CoffeeListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.coffee_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CoffeeListAdapter.ViewHolder viewHolder, int i) {
        coffee = new Coffee();
        coffee = coffeeList.get(i);
        viewHolder.coffeeName.setText("" + coffee.getName());
        viewHolder.fitChart.setValue((float) coffee.getCalories());
        viewHolder.coffeeCalories.setText(coffee.getCalories() + " แคลอรี่");
        Glide.with(context)
                .load("http://developer-thegumza.rhcloud.com/image/"+coffee.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart = new Intent(context, CoffeeChart.class);
                context.startActivity(chart);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FitChart fitChart;
        TextView coffeeName,coffeeCalories;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            fitChart = (FitChart)itemView.findViewById(R.id.fitChart);
            coffeeName = (TextView)itemView.findViewById(R.id.coffeeName);
            coffeeCalories = (TextView)itemView.findViewById(R.id.coffeeCalories);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            font = Typeface.createFromAsset(context.getAssets(), "AC_Espressa.ttf");
            coffeeName.setTypeface(font);
            coffeeCalories.setTypeface(font);
            fitChart.setMaxValue(1000);
        }
    }
}
