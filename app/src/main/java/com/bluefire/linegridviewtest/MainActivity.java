package com.bluefire.linegridviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lineGridView)
    LineGridView lineGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        LineGirdAdapter adapter = new LineGirdAdapter(this, getDataList());
        lineGridView.setAdapter(adapter);
    }

    private List<Map<String,Object>> getDataList(){
        String[] names = new String[]{"技术手册","维修记录","地图","出行","饮食","住宿","费用报销"};
        int[] images = new int[]{R.drawable.head_book, R.drawable.record, R.drawable.map, R.drawable.trip,
                R.drawable.food, R.drawable.stay, R.drawable.apply_for_reimbursement};

        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++){
                Map<String, Object> map = new ArrayMap<>();
                map.put("text",names[i]);
                map.put("img",images[i]);
                list.add(map);
            }
        return list;
    }

}
