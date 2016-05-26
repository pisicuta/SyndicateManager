package adapters;

/**
 * Created by Paul on 18/11/2015.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.paulcurle.syndicatemanager.R;

import java.util.ArrayList;
import java.util.List;

import models.SyndicateMember;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */

public class SyndicateMembersAdapter extends RecyclerView.Adapter<SyndicateMembersAdapter.ViewHolder> {
    private ArrayList<SyndicateMember> mSyndicateMembers;
    private Context mContext;
    View rowView;
    private static final String TAG = "SyndicateMembersAdapter";

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView SmName, SmEmail, Ball1, Ball2, Ball3, Ball4, Ball5, LuckyStar1, LuckyStar2;
        //ToggleButton checkInCheckOutButton;
        Context contxt;

        public ViewHolder(View itemView,int ViewType,Context c) {
//            public ViewHolder(View itemView) {
            super(itemView);
            //
            contxt = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            //
            SmName = (TextView) itemView.findViewById(R.id.memberName);
            SmEmail = (TextView) itemView.findViewById(R.id.memberEmail);
            Ball1 = (TextView) itemView.findViewById(R.id.drawBall1);
            Ball2 = (TextView) itemView.findViewById(R.id.drawBall2);
            Ball3 = (TextView) itemView.findViewById(R.id.drawBall3);
            Ball4 = (TextView) itemView.findViewById(R.id.drawBall4);
            Ball5 = (TextView) itemView.findViewById(R.id.drawBall5);
            LuckyStar1 = (TextView) itemView.findViewById(R.id.drawLuckyStar1);
            LuckyStar2 = (TextView) itemView.findViewById(R.id.drawLuckyStar2);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(contxt,"The Item Clicked is: "+getPosition(),Toast.LENGTH_SHORT).show();

        }
    }

    public SyndicateMembersAdapter(ArrayList<SyndicateMember> membersList, Context context) {
        mSyndicateMembers = membersList;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SyndicateMembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//Does this first
        rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.members_list_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(rowView,viewType,mContext); //(rowView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SyndicateMembersAdapter.ViewHolder holder, int position) {
   // holder = new SyndicateMembersAdapter.ViewHolder();
        final SyndicateMember selectedSyndicateMember = mSyndicateMembers.get(position);
        Log.i(TAG, String.valueOf(holder.SmName));
        Log.i(TAG, String.valueOf(position));
        Log.i(TAG,selectedSyndicateMember.getMEMBER_CONTACT_INFO());
        //TODO Textview is null!!
        holder.SmName.setText(selectedSyndicateMember.getMEMBER_NAME());
        holder.SmEmail.setText(selectedSyndicateMember.getMEMBER_CONTACT_INFO());
        holder.Ball1.setText(selectedSyndicateMember.getBall1());
        holder.Ball2.setText(selectedSyndicateMember.getBall2());
        holder.Ball3.setText(selectedSyndicateMember.getBall3());
        holder.Ball4.setText(selectedSyndicateMember.getBall4());
        holder.Ball5.setText(selectedSyndicateMember.getBall5());
        holder.LuckyStar1.setText(selectedSyndicateMember.getLuckyStar1());
        holder.LuckyStar2.setText(selectedSyndicateMember.getLuckyStar2());


        if (position % 2 == 0) {
            rowView.setBackgroundColor(mContext.getResources().getColor(R.color.activated_color));
        }

        /*holder.checkInCheckOutButton.setOnClickListener(new View.OnClickListener() {
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
        });
        */
    }

    @Override
    public int getItemCount() {
        return mSyndicateMembers.size();
    }

    public void Add(SyndicateMember member) {
        mSyndicateMembers.add(member);
        notifyDataSetChanged();

    }
}