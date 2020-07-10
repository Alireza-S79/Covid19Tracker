package com.codewithshubh.covid19tracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithshubh.covid19tracker.EachStateDataActivity;
import com.codewithshubh.covid19tracker.Models.StateWiseModel;
import com.codewithshubh.covid19tracker.R;

import java.util.ArrayList;

import static com.codewithshubh.covid19tracker.Constants.STATE_ACTIVE;
import static com.codewithshubh.covid19tracker.Constants.STATE_CONFIRMED;
import static com.codewithshubh.covid19tracker.Constants.STATE_CONFIRMED_NEW;
import static com.codewithshubh.covid19tracker.Constants.STATE_DEATH;
import static com.codewithshubh.covid19tracker.Constants.STATE_DEATH_NEW;
import static com.codewithshubh.covid19tracker.Constants.STATE_LAST_UPDATE;
import static com.codewithshubh.covid19tracker.Constants.STATE_NAME;
import static com.codewithshubh.covid19tracker.Constants.STATE_RECOVERED;
import static com.codewithshubh.covid19tracker.Constants.STATE_RECOVERED_NEW;

public class StateWiseAdapter extends RecyclerView.Adapter<StateWiseAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<StateWiseModel> arrayList;

    public StateWiseAdapter(Context mContext, ArrayList<StateWiseModel> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_state_wise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StateWiseModel currentItem = arrayList.get(position);
        String stateName = currentItem.getState();

        String stateTotal = currentItem.getConfirmed();
        holder.stateTotalCases.setText(stateTotal);
        holder.stateName.setText(stateName);

         holder.lin_state_wise.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 StateWiseModel clickedItem = arrayList.get(position);
                 Intent perStateIntent = new Intent(mContext, EachStateDataActivity.class);
                 perStateIntent.putExtra(STATE_NAME, clickedItem.getState());
                 perStateIntent.putExtra(STATE_CONFIRMED, clickedItem.getConfirmed());
                 perStateIntent.putExtra(STATE_CONFIRMED_NEW, clickedItem.getConfirmed_new());
                 perStateIntent.putExtra(STATE_ACTIVE, clickedItem.getActive());
                 perStateIntent.putExtra(STATE_DEATH, clickedItem.getDeath());
                 perStateIntent.putExtra(STATE_DEATH_NEW, clickedItem.getDeath_new());
                 perStateIntent.putExtra(STATE_RECOVERED, clickedItem.getRecovered());
                 perStateIntent.putExtra(STATE_RECOVERED_NEW, clickedItem.getRecovered_new());
                 perStateIntent.putExtra(STATE_LAST_UPDATE, clickedItem.getLastupdate());

                 mContext.startActivity(perStateIntent);
             }
         });
    }

    public void filterList(ArrayList<StateWiseModel> filteredList) {
        arrayList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList==null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName, stateTotalCases;
        LinearLayout lin_state_wise;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stateName = itemView.findViewById(R.id.statewise_layout_name_textview);
            stateTotalCases = itemView.findViewById(R.id.statewise_layout_confirmed_textview);
            lin_state_wise = itemView.findViewById(R.id.layout_state_wise_lin);
        }
    }
}
