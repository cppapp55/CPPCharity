package cpp.app.xps.charity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xps.charity.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.MyViewHolder> implements Filterable {

    private Context context;

    private List<Organization> organizationList;
    private List<Organization> organizationListFiltered;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
        this.organizationListFiltered = organizationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.organization_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Organization organization = organizationListFiltered.get(position);
        holder.address.setText(organization.getAddress());
        holder.name.setText(organization.getName());
        holder.phone.setText(organization.getPhone().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("APP_DEBUG", "Clicked item " + organization.getName());

                Intent intent = new Intent(context, OrganizationActivity.class);
                intent.putExtra("ORG_NAME", organization.getName());
                intent.putExtra("ORG_IMAGE", organization.getImage());
                intent.putExtra("ORG_ADDRESS", organization.getAddress());
                intent.putExtra("ORG_DESC", organization.getDescription());
                intent.putExtra("ORG_ZIP_CODE", organization.getZipcode().toString());
                intent.putExtra("ORG_PHONE", organization.getPhone().toString());

                context.startActivity(intent);


            }
        });

        Picasso.get().load(organization.getImage()).into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return organizationListFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    organizationListFiltered = organizationList;
                } else {
                    List<Organization> filteredList = new ArrayList<>();
                    for (Organization row : organizationList) {

                        Log.d("APP_DEBUG", row.getZipcode().toString());

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getZipcode().toString().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    organizationListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = organizationListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                organizationListFiltered = (ArrayList<Organization>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, phone, address;
        ImageView logo;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            address = (TextView) itemView.findViewById(R.id.address);
            logo = (ImageView) itemView.findViewById(R.id.logo);

        }


    }


}
