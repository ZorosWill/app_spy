package com.appspy.phone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appspy.Apps.AppsFragment.OnListFragmentInteractionListener;
import com.appspy.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PhoneInfoItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PhoneInfoRecyclerViewAdapter extends RecyclerView.Adapter<PhoneInfoRecyclerViewAdapter.ViewHolder> {

    private final List<PhoneInfoItem> mValues;
    private final PhoneInfoType CONTENT_TYPE;

    public PhoneInfoRecyclerViewAdapter(List<PhoneInfoItem> items, PhoneInfoType type) {
        mValues = items;
        CONTENT_TYPE = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone_info, parent, false);
        if (CONTENT_TYPE == PhoneInfoType.PROCESSES) {
            view.findViewById(R.id.process_view).setVisibility(View.VISIBLE);
        } else if (CONTENT_TYPE == PhoneInfoType.APPLICATIONS) {
            view.findViewById(R.id.application_view).setVisibility(View.VISIBLE);
        } else if (CONTENT_TYPE == PhoneInfoType.SERVICES) {
            view.findViewById(R.id.service_view).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.standard_view).setVisibility(View.VISIBLE);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mAttribueView.setText(mValues.get(position).attribute);
        holder.mValueView.setText(mValues.get(position).value);

        holder.mProcessNameView.setText(mValues.get(position).processName);
        holder.mProcessPidView.setText(mValues.get(position).pid);
        holder.mProcessUidView.setText(mValues.get(position).uid);

        holder.mApplicationNameView.setText(mValues.get(position).applicationName);
        holder.mMinSdkView.setText(mValues.get(position).minSdk);
        holder.mTargetSdkView.setText(mValues.get(position).targetSdk);

        holder.mServiceNameView.setText(mValues.get(position).serviceName);
        holder.mServicePidView.setText(mValues.get(position).pid);
        holder.mServiceUidView.setText(mValues.get(position).uid);
        holder.mServiceForegroudView.setText(mValues.get(position).foreground);
        holder.mProcessStartedView.setText(mValues.get(position).started);

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

        public final TextView mAttribueView;
        public final TextView mValueView;

        public final TextView mProcessNameView;
        public final TextView mProcessPidView;
        public final TextView mProcessUidView;

        public final TextView mApplicationNameView;
        public final TextView mMinSdkView;
        public final TextView mTargetSdkView;

        public final TextView mServiceNameView;
        public final TextView mServicePidView;
        public final TextView mServiceUidView;
        public final TextView mServiceForegroudView;
        public final TextView mProcessStartedView;

        public PhoneInfoItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAttribueView = (TextView) view.findViewById(R.id.attribute);
            mValueView = (TextView) view.findViewById(R.id.value);

            mProcessNameView = (TextView) view.findViewById(R.id.process_name);
            mProcessPidView = (TextView) view.findViewById(R.id.process_pid);
            mProcessUidView = (TextView) view.findViewById(R.id.process_uid);

            mApplicationNameView = (TextView) view.findViewById(R.id.application_name);
            mMinSdkView = (TextView) view.findViewById(R.id.minSdk);
            mTargetSdkView = (TextView) view.findViewById(R.id.targetSdk);

            mServiceNameView = (TextView) view.findViewById(R.id.service_name);
            mServicePidView = (TextView) view.findViewById(R.id.service_pid);
            mServiceUidView = (TextView) view.findViewById(R.id.service_uid);
            mServiceForegroudView = (TextView) view.findViewById(R.id.service_foreground);
            mProcessStartedView = (TextView) view.findViewById(R.id.service_started);
        }
    }
}
