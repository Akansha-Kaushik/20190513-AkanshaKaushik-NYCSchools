package com.akanshakaushik.nycschools.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akanshakaushik.nycschools.R;
import com.akanshakaushik.nycschools.constants.Constants;
import com.akanshakaushik.nycschools.models.School;
import com.akanshakaushik.nycschools.views.fragments.ScoreDetailsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * this is a customer adapter to handle the recycler views
 */

public class SchoolsListAdapter extends RecyclerView.Adapter<SchoolsListAdapter.ViewHolder> {

    // Store all the data in list.
    private List<School> schoolsList;
    // This context we will use to inflate the layout.
    private Context context;

    /**
     * @param context     holds the application resources.
     * @param schoolsList is the list of the schools object
     */
    public SchoolsListAdapter(Context context, List<School> schoolsList) {
        this.context = context;
        this.schoolsList = schoolsList;
    }

    /**
     * This method returns a new instance of ViewHolder.
     *
     * @param parent   is the ViewGroup which the viewHolder will be inflate.
     * @param viewType is the layout.
     * @return the view.
     */
    @NonNull
    @Override
    public SchoolsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method binds the data to the view holder.
     *
     * @param holder   handle the views
     * @param position is the position in the list view.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(schoolsList.get(position));
    }

    /**
     * This returns the size of the List.
     *
     * @return the size of the array.
     */
    @Override
    public int getItemCount() {
        return schoolsList.size();
    }

    /**
     * Include the RecyclerView ViewHolder
     * Represents the data that is to be shown with the ViewHolder.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_card_view)
        CardView cardView;

        @BindView(R.id.item_school_name)
        TextView textViewName;

        @BindView(R.id.item_phone_number)
        TextView textViewPhoneNumber;

        @BindView(R.id.item_address)
        TextView textViewAddress;

        private String name;
        private String address;
        private String phoneNumber;
        private String id;


        /**
         * Represents the views of RecyclerView.
         *
         * @param itemView is the object wherein the views are.
         */
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this);
            textViewPhoneNumber.setOnClickListener(this);
            textViewAddress.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.item_card_view:
                    ScoreDetailsFragment scoreDetailsFragment = new ScoreDetailsFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                    Bundle arguments = new Bundle();
                    arguments.putString(Constants.Bundle.SCHOOL_ID_KEY, id);
                    scoreDetailsFragment.setArguments(arguments);
                    transaction.replace(R.id.fragment_container, scoreDetailsFragment);
                    transaction.addToBackStack(null); // Maintain fragment stack
                    transaction.commit(); // Commit the transaction*/
                    break;
                case R.id.item_phone_number:
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + phoneNumber));
                    context.startActivity(dialIntent);
                    break;
                case R.id.item_address:
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q=" + address));
                    context.startActivity(intent);
                    break;
                default:
            }
        }

        private void setData(School school) {
            this.name = school.getName();
            this.address = school.getAddress();
            this.phoneNumber = school.getPhoneNumber();
            this.id = school.getId();

            this.textViewName.setText(name);

            if (!TextUtils.isEmpty(school.getPhoneNumber())) {
                this.textViewPhoneNumber.setText(phoneNumber);
            } else {
                this.textViewPhoneNumber.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(school.getAddress())) {
                this.textViewAddress.setText(address);
            } else {
                this.textViewAddress.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Clear all data (a list of {@link School} object)
     */
    public void clearAll() {
        schoolsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Update RecyclerView data
     *
     * @param schools is the data source of the adapter.
     */
    public void addAll(List<School> schools) {
        schoolsList.clear();
        schoolsList.addAll(schools);
        notifyDataSetChanged();
    }
}
