package com.dry.messagestest;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.gpg2.messages.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<Contacts> contactsList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View ContactsView;
        TextView contactsName;
        TextView contactsPhoneNum;

        private ViewHolder(View view) {
            super(view);
            ContactsView = view;
            contactsName = (TextView) view.findViewById(R.id.contacts_name);
            contactsPhoneNum = (TextView) view.findViewById(R.id.contacts_phone_num);
        }
    }

    public ContactsAdapter(Context context, List<Contacts> contactsList) {
        this.contactsList = contactsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ContactsView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent();
                Log.d("110", "Create a Intent.");
                int position = holder.getAdapterPosition();
                Contacts contacts = contactsList.get(position);
                intent_1.putExtra("phoneKey", contacts.getPhoneNumber());
                intent_1.putExtra("nameKey", contacts.getName());
                Toast.makeText(v.getContext(),
                        "Select: " + contacts.getName() + " " + contacts.getPhoneNumber(),
                        Toast.LENGTH_SHORT).show();
                Activity activity = getActivity(context);
                Log.d("110", "Get current Activity");
                activity.setResult(RESULT_OK, intent_1);
                Log.d("110", "Return a Intent.");
                activity.finish();

            }
        });

        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contacts contacts = contactsList.get(position);
        holder.contactsName.setText(contacts.getName());
        holder.contactsPhoneNum.setText(contacts.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    private Activity getActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }

        if (context instanceof Activity) {
            return (Activity) context;
        } else {
            return null;
        }
    }

}