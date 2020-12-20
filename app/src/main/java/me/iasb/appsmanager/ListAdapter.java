package me.iasb.appsmanager;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyAdapter> {
    Context c;
    List<Model> appList;
    int appListSize;

    public ListAdapter(Context c, List<Model> appList, int appListSize) {
        this.c = c;
        this.appList = appList;
        this.appListSize = appListSize;
    }

    @NonNull
    @Override
    public ListAdapter.MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object, parent, false);
        return new MyAdapter(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        Model model = appList.get(position);
        holder.appIcon.setImageDrawable(model.getAppIcon());
        holder.appName.setText(model.getAppName());
        holder.appDesc.setText(model.getappPackageName());
        holder.termiateBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Snackbar.make(v, "Opening " + model.getAppName() + " settings", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", model.getappPackageName(), null);
                    intent.setData(uri);
                    c.startActivity(intent);
                    /*String SETTINGS_CLASS_DATA_USAGE_SETTINGS = model.getappPackageName() + ".Settings$DataUsageSummaryActivity";
                    final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                    final ComponentName cn = new ComponentName(model.getappPackageName(), SETTINGS_CLASS_DATA_USAGE_SETTINGS);
                    intent.setComponent(cn);
                    c.startActivity(intent);*/
                } catch (ActivityNotFoundException e) {
                    Log.v(TAG, "Data settings usage Activity is not present");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return appListSize;
    }

    public static class MyAdapter extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView appIcon;
        TextView appName;
        TextView appDesc;
        Button termiateBttn;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.imageView);
            appName = itemView.findViewById(R.id.appName);
            appDesc = itemView.findViewById(R.id.appDesc);
            termiateBttn = itemView.findViewById(R.id.bttn);
        }
    }
}
