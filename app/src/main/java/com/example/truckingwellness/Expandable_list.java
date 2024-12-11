package com.example.truckingwellness;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Expandable_list extends AppCompatActivity {
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_26);




        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.elvMobiles);
        expandableListAdapter = new Expandable_List_Adapter(this, groupList, mobileCollection) {
        };
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });

    }

    private void createCollection() {
        String[] set1 = {"Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set2 = {"Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set3 = {"Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set4= {"Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set5 = {"Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set6 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set7 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set8 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set9 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set10 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};
        String[] set11 = { "Item 1", "Item 2",
                "Item 3", "Item 4"};


        mobileCollection = new HashMap<String, List<String>>();
        for(String group : groupList){
            if (group.equals("1")){
                loadChild(set1);
            } else if (group.equals("2"))
                loadChild(set2);
            else if (group.equals("3"))
                loadChild(set3);
            else if (group.equals("4"))
                loadChild(set4);
            else if (group.equals("5"))
                loadChild(set5);
            else if (group.equals("6"))
                loadChild(set6);
            else if (group.equals("7"))
                loadChild(set7);
            else if (group.equals("8"))
                loadChild(set8);
            else if (group.equals("9"))
                loadChild(set9);
            else if (group.equals("10"))
                loadChild(set10);
            else
                loadChild(set11);
            mobileCollection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for(String model : mobileModels){
            childList.add(model);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("Set1");
        groupList.add("Set2");
        groupList.add("Set3");
        groupList.add("Set4");
        groupList.add("Set5");
        groupList.add("Set6");
        groupList.add("Set7");
        groupList.add("Set8");
        groupList.add("Set9");
        groupList.add("Set10");
        groupList.add("Set11");

    }
}