package me.iasb.appsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Model> appList = new ArrayList<>();
    ListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addData();
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new ListAdapter(this, appList, appList.size());
        recyclerView.setAdapter(myAdapter);
    }

    private void addData(){

        List installedPackages = getPackageManager().getInstalledPackages(0);
        for (int i=0; i< installedPackages.size(); i++) {
            PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
            if(!isSystemPackage(packageInfo)) {
                Drawable icon = packageInfo.applicationInfo.loadIcon(getPackageManager());
                String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                String appPackageName = packageInfo.applicationInfo.packageName;
                appList.add(new Model(icon, appName, appPackageName));
            }
        }
    }

    private boolean isSystemPackage(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}