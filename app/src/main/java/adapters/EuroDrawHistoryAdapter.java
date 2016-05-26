package adapters;

/**
 * Created by Paul on 18/11/2015.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.paulcurle.syndicatemanager.R;

import java.util.ArrayList;
import java.util.List;
import models.EuroDraw;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class EuroDrawHistoryAdapter extends RecyclerView.Adapter<EuroDrawHistoryAdapter.ViewHolder> {
    private List<EuroDraw> mEuroDraws;
    private Context mContext;
    View rowView;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView EdDrawDate, EdNumberOfWinningRows, Ball1, Ball2, Ball3, Ball4, Ball5, LuckyStar1, LuckyStar2;
        //ToggleButton checkInCheckOutButton;

        public ViewHolder(View itemView) {
            super(itemView);

            EdDrawDate = (TextView) itemView.findViewById(R.id.history_date);
            Ball1 = (TextView) itemView.findViewById(R.id.history_ball1);
            Ball2 = (TextView) itemView.findViewById(R.id.history_ball2);
            Ball3 = (TextView) itemView.findViewById(R.id.history_ball3);
            Ball4 = (TextView) itemView.findViewById(R.id.history_ball4);
            Ball5 = (TextView) itemView.findViewById(R.id.history_ball5);
            LuckyStar1 = (TextView) itemView.findViewById(R.id.history_lucky_star1);
            LuckyStar2 = (TextView) itemView.findViewById(R.id.history_lucky_star2);
            EdNumberOfWinningRows = (TextView) itemView.findViewById(R.id.history_winning_rowcount);
        }
    }

    public EuroDrawHistoryAdapter(List<EuroDraw> euroDrawsList, Context context) {
        mEuroDraws = euroDrawsList;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EuroDrawHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(EuroDrawHistoryAdapter.ViewHolder holder, int position) {
        final EuroDraw selectedEuroDraw = mEuroDraws.get(position);
//String strLong = Long.toString(longNumber);
        holder.EdDrawDate.setText(selectedEuroDraw.getDrawDate());
        holder.EdNumberOfWinningRows.setText(Long.toString(selectedEuroDraw.getNumberOfWinningRows()));
        holder.Ball1.setText(selectedEuroDraw.getBall1());
        holder.Ball2.setText(selectedEuroDraw.getBall2());
        holder.Ball3.setText(selectedEuroDraw.getBall3());
        holder.Ball4.setText(selectedEuroDraw.getBall4());
        holder.Ball5.setText(selectedEuroDraw.getBall5());
        holder.LuckyStar1.setText(selectedEuroDraw.getLuckyStar1());
        holder.LuckyStar2.setText(selectedEuroDraw.getLuckyStar2());


        if (position % 2 == 0) {
            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.activated_color));
        }

       /* holder.checkInCheckOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Is the toggle on?
                boolean on = ((ToggleButton) v).isChecked();

                if (on){
                    selectedSyndicateMember.setIsCheckedIn(true);
                    Toast.makeText(mContext, selectedSyndicateMember.getName() + " checked in ", Toast.LENGTH_SHORT).show();
                } else {
                    selectedSyndicateMember.setIsCheckedIn(false);
                    Toast.makeText(mContext, selectedSyndicateMember.getName() + " checked out ", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mEuroDraws.size();
    }

    public void Add(EuroDraw historyItem) {
        mEuroDraws.add(historyItem);
        notifyDataSetChanged();

    }
}