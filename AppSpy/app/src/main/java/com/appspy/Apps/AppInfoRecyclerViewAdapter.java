package com.appspy.Apps;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appspy.Apps.AppInfoFragment.OnListFragmentInteractionListener;
import com.appspy.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link AppItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AppInfoRecyclerViewAdapter extends RecyclerView.Adapter<AppInfoRecyclerViewAdapter.ViewHolder> {

    private final List<AppItem> mValues;

    public AppInfoRecyclerViewAdapter(List<AppItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_app_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageDrawable(mValues.get(position).icon);
        holder.mNameView.setText(mValues.get(position).name);
        holder.mVersionView.setText(mValues.get(position).version);
        holder.mPackageView.setText(mValues.get(position).packageName);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIconView;
        public final TextView mNameView;
        public final TextView mVersionView;
        public final TextView mPackageView;
        public AppItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIconView = (ImageView) view.findViewById(R.id.app_icon);
            mNameView = (TextView) view.findViewById(R.id.app_name);
            mVersionView = (TextView) view.findViewById(R.id.app_version);
            mPackageView = (TextView) view.findViewById(R.id.app_package);
        }
    }
}
