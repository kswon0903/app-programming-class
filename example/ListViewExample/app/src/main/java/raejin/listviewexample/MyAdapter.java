package raejin.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    // 어답터를 만드는데 필요한 맴버번수들,
    ArrayList<ItemDate> temp_list;  // 자료를 담고 있는 ArrayList 객체를 저장할 변수
    Context context;                // 엑티비티의 정보를 저장할 변수
    LayoutInflater layoutInflater;  // 아이템 레이아웃을 생성해주는 객체를 저장할 변수
    int item_layout;                // 디자인한 레이아웃의 아이디를 저장할 변수

    MyAdapter(Context context, int item_layout,
              ArrayList<ItemDate> temp_list) {
        this.context = context;             // 변수에 엑티비티의 정보를 저장
        this.item_layout = item_layout;     // 변수에 레이아웃의 아이디를 저장
        this.temp_list = temp_list;         // 변수에 자료를 담고 있는 ArrayList 객체를 저장
        this.layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);   // 아이템 레이아웃을 생성해주는 객체를 만들고 저장
    }


    @Override
    public int getCount() { return 0; }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
